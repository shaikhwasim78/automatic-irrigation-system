package com.ais.irrigation.service;

import com.ais.irrigation.dto.PlotCropRequestDTO;
import com.ais.irrigation.dto.PlotIrrigationDTO;
import com.ais.irrigation.dto.PlotIrrigationRequestDTO;

public interface PlotIrrigationService {

	PlotIrrigationDTO configurePlot(Long plotId, PlotCropRequestDTO plotCropRequestDTO);

	PlotIrrigationDTO irrigatePlot(Long plotId, PlotIrrigationRequestDTO plotIrrigationRequestDTO);

}
