package com.ais.alert.service;

import java.util.List;

import com.ais.alert.dto.AlertDTO;
import com.ais.slot.model.SlotEntity;

public interface AlertService {

	AlertDTO createAlert(SlotEntity slotEntity);

	List<AlertDTO> getAllAlerts();

}
