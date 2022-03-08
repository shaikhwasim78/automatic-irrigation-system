package com.ais.irrigation.service;

import com.ais.slot.model.SlotEntity;

public interface SensorService {

	boolean irrigate(SlotEntity slotEntity);

}
