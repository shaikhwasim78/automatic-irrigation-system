package com.ais.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ais.slot.service.SlotService;

@Component
public class IrrigationScheduler {

	private SlotService slotService;

	@Autowired
	public IrrigationScheduler(SlotService slotService) {
		this.slotService = slotService;
	}

	@Scheduled(fixedRateString = "${irrigation.scheduler.intervalInMillis}")
	public void run() {
		try {

			slotService.irrigateAndUpdateSlots();
			System.out.println(this.getClass().getName() + " - irrigateAndUpdateSlots() executed!");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
