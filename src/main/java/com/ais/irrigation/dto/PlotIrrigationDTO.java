package com.ais.irrigation.dto;

import java.io.IOException;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.ais.crop.model.CropType;
import com.ais.irrigation.model.Status;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlotIrrigationDTO {

	@JsonProperty("id")
	private Long id;

	@JsonProperty("plot_id")
	private Long plotId;

	@Enumerated(EnumType.STRING)
	@JsonProperty("cropType")
	private CropType cropType;

	@JsonProperty("duration_in_min")
	private Integer durationInMin;

	@JsonProperty("interval_in_min")
	private Integer intervalInMin;

	@JsonProperty("water_in_mm")
	private Float waterInMm;

	@JsonProperty("sensor_address")
	private String sensorAddress;

	@JsonProperty("next_irrigation_time")
	private Long next_irrigation_time;

	@Enumerated(EnumType.STRING)
	@JsonProperty("status")
	private Status status;

	@JsonProperty("created_time")
	private Long createdTime;

	@JsonProperty("updated_time")
	private Long updatedTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CropType getCropType() {
		return cropType;
	}

	public void setCropType(CropType cropType) {
		this.cropType = cropType;
	}

	public Integer getDurationInMin() {
		return durationInMin;
	}

	public void setDurationInMin(Integer durationInMin) {
		this.durationInMin = durationInMin;
	}

	public Integer getIntervalInMin() {
		return intervalInMin;
	}

	public void setIntervalInMin(Integer intervalInMin) {
		this.intervalInMin = intervalInMin;
	}

	public Float getWaterInMm() {
		return waterInMm;
	}

	public void setWaterInMm(Float waterInMm) {
		this.waterInMm = waterInMm;
	}

	public String getSensorAddress() {
		return sensorAddress;
	}

	public void setSensorAddress(String sensorAddress) {
		this.sensorAddress = sensorAddress;
	}

	public Long getNext_irrigation_time() {
		return next_irrigation_time;
	}

	public void setNext_irrigation_time(Long next_irrigation_time) {
		this.next_irrigation_time = next_irrigation_time;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Long createdTime) {
		this.createdTime = createdTime;
	}

	public Long getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Long updatedTime) {
		this.updatedTime = updatedTime;
	}

	public Long getPlotId() {
		return plotId;
	}

	public void setPlotId(Long plotId) {
		this.plotId = plotId;
	}

	@Override
	public String toString() {
		ObjectMapper Obj = new ObjectMapper();
		try {
			// return JSON String
			return Obj.writeValueAsString(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this.getClass().getName();
	}

}
