package com.ais.irrigation.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ais.crop.model.Crop;
import com.ais.crop.model.CropFactory;
import com.ais.exception.BadRequestException;
import com.ais.exception.ResourceNotFoundException;
import com.ais.irrigation.dto.PlotCropRequestDTO;
import com.ais.irrigation.dto.PlotIrrigationDTO;
import com.ais.irrigation.dto.PlotIrrigationRequestDTO;
import com.ais.irrigation.model.PlotIrrigationEntity;
import com.ais.irrigation.model.Status;
import com.ais.irrigation.repo.PlotIrrigationRepo;
import com.ais.plot.model.PlotEntity;
import com.ais.plot.repo.PlotRepo;
import com.ais.slot.model.SlotEntity;
import com.ais.util.ObjectUtilMapper;

@Service
@Transactional
public class PlotIrrigationServiceImpl implements PlotIrrigationService {

	private PlotRepo plotRepo;

	private PlotIrrigationRepo plotIrrigationRepo;

	@Autowired
	public PlotIrrigationServiceImpl(PlotRepo plotRepo, PlotIrrigationRepo plotIrrigationRepo) {
		this.plotRepo = plotRepo;
		this.plotIrrigationRepo = plotIrrigationRepo;
	}

	@Override
	public PlotIrrigationDTO configurePlot(Long plotId, PlotCropRequestDTO plotCropRequestDTO) {
		PlotEntity plotEntity = plotRepo.findByIdAndIsDeletedFalse(plotId);
		if (plotEntity == null)
			throw new ResourceNotFoundException("No such plot exists: " + plotId);
		PlotIrrigationEntity plotIrrigationEntity = plotIrrigationRepo.findByPlot_Id(plotId);
		if (plotIrrigationEntity == null) {

			plotIrrigationEntity = new PlotIrrigationEntity();
			plotIrrigationEntity.setCreatedTime(System.currentTimeMillis());
			plotIrrigationEntity.setNextIrrigationTime(0L);
			plotIrrigationEntity.setStatus(Status.NO_SENSOR);
		}
		ObjectUtilMapper.map(plotCropRequestDTO, plotIrrigationEntity);
		plotIrrigationEntity.setPlot(plotEntity);
		plotIrrigationEntity.setUpdatedTime(System.currentTimeMillis());
		Crop crop = CropFactory.getCrop(plotEntity, plotCropRequestDTO.getCropType());
		plotIrrigationEntity.setDurationInMin(crop.getIrrigationDurationInMin());
		plotIrrigationEntity.setIntervalInMin(crop.getIrrigationIntervalInMin());
		plotIrrigationEntity.setWaterInMm(crop.getWaterRequirementInMm());
		plotIrrigationEntity = plotIrrigationRepo.save(plotIrrigationEntity);
		return getPlotIrrigationDtoByEntity(plotIrrigationEntity);
	}

	@Override
	public PlotIrrigationDTO irrigatePlot(Long plotId, PlotIrrigationRequestDTO plotIrrigationRequestDTO) {
		PlotEntity plotEntity = plotRepo.findByIdAndIsDeletedFalse(plotId);
		if (plotEntity == null)
			throw new ResourceNotFoundException("No such plot exists: " + plotId);
		PlotIrrigationEntity plotIrrigationEntity = plotIrrigationRepo.findByPlot_Id(plotId);
		if (plotIrrigationEntity == null)
			throw new BadRequestException("Plot not configured: " + plotId);
		ObjectUtilMapper.map(plotIrrigationRequestDTO, plotIrrigationEntity);
		plotIrrigationEntity.setStatus(Status.IDLE);
		plotIrrigationEntity.setUpdatedTime(System.currentTimeMillis());
		return getPlotIrrigationDtoByEntity(plotIrrigationEntity);
	}

	private PlotIrrigationDTO getPlotIrrigationDtoByEntity(PlotIrrigationEntity plotIrrigationEntity) {
		PlotIrrigationDTO plotItrrigationDTO = ObjectUtilMapper.map(plotIrrigationEntity, PlotIrrigationDTO.class);
		plotItrrigationDTO.setPlotId(plotIrrigationEntity.getPlot().getId());
		return plotItrrigationDTO;
	}

}
