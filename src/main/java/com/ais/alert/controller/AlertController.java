package com.ais.alert.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ais.alert.dto.AlertDTO;
import com.ais.alert.service.AlertService;

@RestController
@RequestMapping("/alert")
public class AlertController {

	private AlertService alertService;

	@Autowired
	public AlertController(AlertService alertService) {
		this.alertService = alertService;
	}

	@GetMapping
	public ResponseEntity<List<AlertDTO>> getAllAlerts() {
		List<AlertDTO> alertDtoList = alertService.getAllAlerts();
		return ResponseEntity.ok(alertDtoList);
	}

}
