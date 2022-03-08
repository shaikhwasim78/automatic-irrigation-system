package com.ais.irrigation.model;

import java.io.IOException;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.ais.crop.model.CropType;
import com.ais.plot.model.PlotEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
@Table(name = "plot_irrigation")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlotIrrigationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("id")
	private Long id;

	@OneToOne
	@JoinColumn(name = "plot_id")
	@NotFound(action = NotFoundAction.IGNORE)
	@JsonProperty("plot")
	private PlotEntity plot;

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
	private Long nextIrrigationTime;

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

	public PlotEntity getPlot() {
		return plot;
	}

	public void setPlot(PlotEntity plot) {
		this.plot = plot;
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

	public Long getNextIrrigationTime() {
		return nextIrrigationTime;
	}

	public void setNextIrrigationTime(Long nextIrrigationTime) {
		this.nextIrrigationTime = nextIrrigationTime;
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

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 13 * hash + Objects.hashCode(this.id);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass()) {
			return false;
		}
		final PlotIrrigationEntity plotIrrigationEntity = (PlotIrrigationEntity) obj;
		return plotIrrigationEntity.id == this.id;
	}

}
