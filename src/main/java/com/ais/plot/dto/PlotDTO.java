package com.ais.plot.dto;

import java.io.IOException;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.ais.irrigation.dto.PlotIrrigationDTO;
import com.ais.irrigation.model.PlotIrrigationEntity;
import com.ais.plot.model.MeasurementUnit;
import com.ais.plot.model.Status;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlotDTO {

	@JsonProperty("id")
	private Long id;

	@JsonProperty("title")
	private String title;

	@JsonProperty("address")
	private String address;

	@JsonProperty("area")
	private Float area;

	@Enumerated(EnumType.STRING)
	@JsonProperty("unit")
	private MeasurementUnit unit;

	@Enumerated(EnumType.STRING)
	@JsonProperty("status")
	private Status status;

	@JsonProperty("created_time")
	private Long createdTime;

	@JsonProperty("updated_time")
	private Long updatedTime;

	@JsonProperty("is_deleted")
	private Boolean isDeleted;

	@JsonProperty("plot_irrigation_details")
	private PlotIrrigationDTO plotIrrigationDetails;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Float getArea() {
		return area;
	}

	public void setArea(Float area) {
		this.area = area;
	}

	public MeasurementUnit getUnit() {
		return unit;
	}

	public void setUnit(MeasurementUnit unit) {
		this.unit = unit;
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

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public PlotIrrigationDTO getPlotIrrigationDetails() {
		return plotIrrigationDetails;
	}

	public void setPlotIrrigationDetails(PlotIrrigationDTO plotIrrigationDetails) {
		this.plotIrrigationDetails = plotIrrigationDetails;
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
