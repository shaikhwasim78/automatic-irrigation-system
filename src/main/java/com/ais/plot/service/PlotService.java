package com.ais.plot.service;

import java.util.List;

import com.ais.plot.dto.PlotDTO;
import com.ais.plot.dto.PlotRequestDTO;

public interface PlotService {

	PlotDTO createPlot(PlotRequestDTO plotRequestDTO);

	PlotDTO updatePlot(Long plotId, PlotRequestDTO plotRequestDTO);

	List<PlotDTO> getAllPlots();
}
