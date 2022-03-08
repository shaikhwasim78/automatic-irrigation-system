package com.ais.slot.model;

public enum Status {

	/**
	 * when a slot is just added and waiting for execution
	 */
	PENDING,
	/**
	 * Irrigation in progress
	 */
	IN_PROGRESS,
	/**
	 * Irrigation failed due to sensor
	 */
	FAILED,
	/**
	 * Successful Irrigation
	 */
	COMPLETED

}
