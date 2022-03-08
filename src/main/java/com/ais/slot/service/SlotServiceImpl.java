package com.ais.slot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ais.alert.service.AlertService;
import com.ais.irrigation.model.PlotIrrigationEntity;
import com.ais.irrigation.repo.PlotIrrigationRepo;
import com.ais.irrigation.service.SensorService;
import com.ais.slot.model.SlotEntity;
import com.ais.slot.model.Status;
import com.ais.slot.repo.SlotRepo;

@Service
@Transactional
public class SlotServiceImpl implements SlotService {

	private PlotIrrigationRepo plotIrrigationRepo;

	private SlotRepo slotRepo;

	private SensorService sensorService;

	private AlertService alerService;

	@Value("${irrigation.retry.limit}")
	private Integer RETRY_LIMIT;

	@Value("${irrigation.retry.intervalInMillis}")
	private Integer RETRY_INTERVAL;

	@Autowired
	public SlotServiceImpl(PlotIrrigationRepo plotIrrigationRepo, SlotRepo slotRepo, SensorService sensorService, AlertService alertService) {
		this.plotIrrigationRepo = plotIrrigationRepo;
		this.slotRepo = slotRepo;
		this.sensorService = sensorService;
		this.alerService = alertService;
	}

	@Override
	public void createSlotsForValidIrrigation() {
		// find all valid plot irrigation for slots creation
		List<PlotIrrigationEntity> plotIrrigationList = plotIrrigationRepo.findAllPlotIrrigationToCreateSlots(System.currentTimeMillis());
		// create slots
		List<SlotEntity> slotEntityList = plotIrrigationList.stream().map(plotIrrigation -> {
			SlotEntity slotEntity = new SlotEntity();
			slotEntity.setPlot(plotIrrigation.getPlot());
			slotEntity.setCreatedTime(System.currentTimeMillis());
			slotEntity.setUpdatedTime(System.currentTimeMillis());
			slotEntity.setDurationInMin(plotIrrigation.getDurationInMin());
			slotEntity.setRetryCount(0);
			slotEntity.setLastRetryTime(System.currentTimeMillis());
			slotEntity.setSensorAddress(plotIrrigation.getSensorAddress());
			slotEntity.setStatus(Status.PENDING);
			return slotEntity;
		}).collect(Collectors.toList());
		// save slots
		slotEntityList = slotRepo.saveAll(slotEntityList);
		// update plot irrigation status
		plotIrrigationList.forEach(plotIrrigation -> {
			plotIrrigation.setStatus(com.ais.irrigation.model.Status.IN_PROGRESS);
			plotIrrigation.setUpdatedTime(System.currentTimeMillis());
		});
		plotIrrigationList = plotIrrigationRepo.saveAll(plotIrrigationList);
	}

	@Override
	public void irrigateAndUpdateSlots() {
		List<SlotEntity> slotEntityList = slotRepo.findAllSlotForUpdate();
		List<PlotIrrigationEntity> plotIrrigationList = new ArrayList<>();
		slotEntityList.forEach(slot -> {
			// for slots with PENDING status
			if (slot.getStatus() == Status.PENDING) {
				// proceed only if trying after the mentioned retry interval
				long interval = System.currentTimeMillis() - slot.getLastRetryTime();
				if (interval >= RETRY_INTERVAL) {
					// try to irrigate
					boolean isSuccess = sensorService.irrigate(slot);
					// irrigation successfully accepted by sensor
					if (isSuccess) {
						slot.setUpdatedTime(System.currentTimeMillis());
						slot.setStatus(Status.IN_PROGRESS);
					}
					// irrigation failed to be accepted by sensor
					else {
						int retryCount = slot.getRetryCount();
						retryCount++;
						slot.setRetryCount(retryCount);
						slot.setLastRetryTime(System.currentTimeMillis());
						slot.setUpdatedTime(System.currentTimeMillis());
						// If retry limit exceeded
						if (slot.getRetryCount() >= RETRY_LIMIT) {
							slot.setStatus(Status.FAILED);
							PlotIrrigationEntity plotIrrigationDetails = slot.getPlot().getPlotIrrigationDetails();
							calculateAndSetNextIrrigationtime(plotIrrigationDetails);
							plotIrrigationList.add(plotIrrigationDetails);
							// send alert
							alerService.createAlert(slot);
						}
					}
				}
			}
			// for slots with IN_PROGRESS status
			else if (slot.getStatus() == Status.IN_PROGRESS) {
				// checks if irrigation running for the slot for more than given duration
				if (System.currentTimeMillis() >= (slot.getDurationInMin() * 60 * 1000)) {
					slot.setStatus(Status.COMPLETED);
					slot.setUpdatedTime(System.currentTimeMillis());
					PlotIrrigationEntity plotIrrigationDetails = slot.getPlot().getPlotIrrigationDetails();
					calculateAndSetNextIrrigationtime(plotIrrigationDetails);
					plotIrrigationList.add(plotIrrigationDetails);
				}
			}
		});
		plotIrrigationRepo.saveAll(plotIrrigationList);
		slotRepo.saveAll(slotEntityList);
	}

	private void calculateAndSetNextIrrigationtime(PlotIrrigationEntity plotIrrigationEntity) {
		Long nextIrrigationTime = System.currentTimeMillis() + (plotIrrigationEntity.getIntervalInMin() * 60 * 1000);
		plotIrrigationEntity.setNextIrrigationTime(nextIrrigationTime);
		plotIrrigationEntity.setStatus(com.ais.irrigation.model.Status.IDLE);
		plotIrrigationEntity.setUpdatedTime(System.currentTimeMillis());
	}

}
