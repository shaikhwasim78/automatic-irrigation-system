package com.ais.irrigation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ais.irrigation.dto.PlotCropRequestDTO;
import com.ais.irrigation.dto.PlotIrrigationDTO;
import com.ais.irrigation.dto.PlotIrrigationRequestDTO;
import com.ais.irrigation.service.PlotIrrigationService;

@RestController
@RequestMapping("/plot")
public class PlotIrrigationController {

	private PlotIrrigationService plotIrrigationService;

	@Autowired
	public PlotIrrigationController(PlotIrrigationService plotIrrigationService) {
		this.plotIrrigationService = plotIrrigationService;
	}

	@PostMapping("/{plotId}/configure")
	public ResponseEntity<PlotIrrigationDTO> configurePlot(@PathVariable("plotId") Long plotId, @RequestBody @Validated PlotCropRequestDTO plotCropRequestDTO) {
		PlotIrrigationDTO plotIrrigationDTO = plotIrrigationService.configurePlot(plotId, plotCropRequestDTO);
		return ResponseEntity.ok(plotIrrigationDTO);
	}

	@PutMapping("/{plotId}/irrigate")
	public ResponseEntity<PlotIrrigationDTO> irrigatePlot(@PathVariable("plotId") Long plotId, @RequestBody @Validated PlotIrrigationRequestDTO plotIrrigationRequestDTO) {
		PlotIrrigationDTO plotIrrigationDTO = plotIrrigationService.irrigatePlot(plotId, plotIrrigationRequestDTO);
		return ResponseEntity.ok(plotIrrigationDTO);
	}

}
