package com.ais.irrigation.model;

public enum Status {

	/**
	 * when a plot is just configured
	 */
	NO_SENSOR,
	/**
	 * when a sensor is attached but irrigation not started
	 */
	IDLE,
	/**
	 * A sensor is attached and irrigation is in progress
	 */
	IN_PROGRESS

}
