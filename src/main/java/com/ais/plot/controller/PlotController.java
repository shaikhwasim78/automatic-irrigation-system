package com.ais.plot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ais.plot.dto.PlotDTO;
import com.ais.plot.dto.PlotRequestDTO;
import com.ais.plot.service.PlotService;

@RestController
@RequestMapping("/plot")
public class PlotController {

	private PlotService plotService;

	@Autowired
	public PlotController(PlotService plotService) {
		this.plotService = plotService;
	}

	@PostMapping
	public ResponseEntity<PlotDTO> createPlot(@RequestBody @Validated PlotRequestDTO plotRequestDTO) {
		PlotDTO plotDTO = plotService.createPlot(plotRequestDTO);
		return ResponseEntity.ok(plotDTO);
	}

	@PutMapping("/{plotId}")
	public ResponseEntity<PlotDTO> updatePlot(@PathVariable("plotId") Long plotId, @RequestBody @Validated PlotRequestDTO plotRequestDTO) {
		PlotDTO plotDTO = plotService.updatePlot(plotId, plotRequestDTO);
		return ResponseEntity.ok(plotDTO);
	}

	@GetMapping
	public ResponseEntity<List<PlotDTO>> getAllPlots() {
		List<PlotDTO> plotDtoList = plotService.getAllPlots();
		return ResponseEntity.ok(plotDtoList);
	}

}
