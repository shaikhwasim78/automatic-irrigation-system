package com.ais.plot.dto;

import java.io.IOException;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;

import com.ais.plot.model.MeasurementUnit;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.istack.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlotRequestDTO {

	@NotEmpty
	@JsonProperty("title")
	private String title;

	@NotEmpty
	@JsonProperty("address")
	private String address;

	@NotNull
	@JsonProperty("area")
	private Float area;

	@NotNull
	@Enumerated(EnumType.STRING)
	@JsonProperty("unit")
	private MeasurementUnit unit;

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
