package com.ais.plot.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ais.exception.ResourceNotFoundException;
import com.ais.irrigation.dto.PlotIrrigationDTO;
import com.ais.plot.dto.PlotDTO;
import com.ais.plot.dto.PlotRequestDTO;
import com.ais.plot.model.PlotEntity;
import com.ais.plot.model.Status;
import com.ais.plot.repo.PlotRepo;
import com.ais.util.ObjectUtilMapper;

@Service
@Transactional
public class PlotServiceImpl implements PlotService {

	private PlotRepo plotRepo;

	@Autowired
	public PlotServiceImpl(PlotRepo plotRepo) {
		this.plotRepo = plotRepo;
	}

	@Override
	public PlotDTO createPlot(PlotRequestDTO plotRequestDTO) {
		PlotEntity plotEntity = ObjectUtilMapper.map(plotRequestDTO, PlotEntity.class);
		plotEntity.setStatus(Status.NEW);
		plotEntity.setIsDeleted(false);
		plotEntity.setCreatedTime(System.currentTimeMillis());
		plotEntity.setUpdatedTime(System.currentTimeMillis());
		plotEntity = plotRepo.save(plotEntity);
		return ObjectUtilMapper.map(plotEntity, PlotDTO.class);
	}

	@Override
	public PlotDTO updatePlot(Long plotId, PlotRequestDTO plotRequestDTO) {
		PlotEntity plotEntity = plotRepo.findByIdAndIsDeletedFalse(plotId);
		if (plotEntity == null)
			throw new ResourceNotFoundException("Plot not found: " + plotId);
		ObjectUtilMapper.map(plotRequestDTO, plotEntity);
		plotEntity.setUpdatedTime(System.currentTimeMillis());
		plotEntity = plotRepo.save(plotEntity);
		return ObjectUtilMapper.map(plotEntity, PlotDTO.class);
	}

	@Override
	public List<PlotDTO> getAllPlots() {
		List<PlotEntity> plotEntityList = plotRepo.findAllByIsDeletedFalse();
		return plotEntityList.stream().map(plot -> getPlotDtoByEntity(plot)).collect(Collectors.toList());
	}

	private PlotDTO getPlotDtoByEntity(PlotEntity plotEntity) {
		PlotDTO plotDTO = ObjectUtilMapper.map(plotEntity, PlotDTO.class);
		if (plotEntity.getPlotIrrigationDetails() != null)
			plotDTO.setPlotIrrigationDetails(ObjectUtilMapper.map(plotEntity.getPlotIrrigationDetails(), PlotIrrigationDTO.class));
		return plotDTO;
	}

}
