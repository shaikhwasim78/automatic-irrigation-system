package com.ais.alert.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ais.alert.dto.AlertDTO;
import com.ais.alert.model.AlertEntity;
import com.ais.alert.model.Category;
import com.ais.alert.model.Level;
import com.ais.alert.repo.AlertRepo;
import com.ais.slot.model.SlotEntity;
import com.ais.util.ObjectUtilMapper;

@Service
@Transactional
public class AlertServiceImpl implements AlertService {

	private AlertRepo alertRepo;

	@Autowired
	public AlertServiceImpl(AlertRepo alertRepo) {
		this.alertRepo = alertRepo;
	}

	@Async
	@Override
	public AlertDTO createAlert(SlotEntity slotEntity) {
		AlertEntity alert = new AlertEntity();
		alert.setSensorAddress(slotEntity.getSensorAddress());
		alert.setCategory(Category.SENSOR_ERROR);
		alert.setLevel(Level.HIGH);
		alert.setCreatedTime(System.currentTimeMillis());
		alert.setPlot(slotEntity.getPlot());
		alert = alertRepo.save(alert);
		return getAlertDtoFromEntity(alert);
	}

	@Override
	public List<AlertDTO> getAllAlerts() {
		List<AlertEntity> alertList = alertRepo.findAll();
		return alertList.stream().map(alert -> getAlertDtoFromEntity(alert)).collect(Collectors.toList());
	}

	private AlertDTO getAlertDtoFromEntity(AlertEntity alertEntity) {
		AlertDTO alertDTO = ObjectUtilMapper.map(alertEntity, AlertDTO.class);
		alertDTO.setPlotId(alertEntity.getPlot().getId());
		return alertDTO;
	}

}
