package com.ais.alert.dto;

import java.io.IOException;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.ais.alert.model.Category;
import com.ais.alert.model.Level;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AlertDTO {

	@JsonProperty("id")
	private Long id;

	@JsonProperty("plot_id")
	private Long plotId;

	@JsonProperty("sensor_address")
	private String sensorAddress;

	@Enumerated(EnumType.STRING)
	@JsonProperty("level")
	private Level level;

	@Enumerated(EnumType.STRING)
	@JsonProperty("category")
	private Category category;

	@JsonProperty("created_time")
	private Long createdTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPlotId() {
		return plotId;
	}

	public void setPlotId(Long plotId) {
		this.plotId = plotId;
	}

	public String getSensorAddress() {
		return sensorAddress;
	}

	public void setSensorAddress(String sensorAddress) {
		this.sensorAddress = sensorAddress;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Long createdTime) {
		this.createdTime = createdTime;
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
