package com.ais.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ais.slot.service.SlotService;

@Component
public class SlotScheduler {

	private SlotService slotService;

	@Autowired
	public SlotScheduler(SlotService slotService) {
		this.slotService = slotService;
	}

	@Scheduled(fixedRateString = "${slot.scheduler.intervalInMillis}")
	public void run() {
		try {

			slotService.createSlotsForValidIrrigation();
			System.out.println(this.getClass().getName() + " - createSlotsForValidIrrigation() executed!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
