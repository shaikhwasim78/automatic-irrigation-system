package com.ais.irrigation.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ais.slot.model.SlotEntity;

@Service
@Transactional
public class SensorServiceImpl implements SensorService {

	/**
	 * This method mocks actual irrigation by the sensor. We're intentionally failing the irrigation for plotID:1 to test the alarm system
	 */
	@Override
	public boolean irrigate(SlotEntity slotEntity) {
		if (slotEntity.getPlot().getId() == 1)
			return false;
		return true;
	}

}
