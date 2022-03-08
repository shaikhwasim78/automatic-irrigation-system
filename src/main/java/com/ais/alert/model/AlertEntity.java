package com.ais.alert.model;

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

import com.ais.plot.model.PlotEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
@Table(name = "alert")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AlertEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("id")
	private Long id;

	@OneToOne
	@JoinColumn(name = "plot_id")
	@NotFound(action = NotFoundAction.IGNORE)
	@JsonProperty("plot")
	private PlotEntity plot;

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

	public PlotEntity getPlot() {
		return plot;
	}

	public void setPlot(PlotEntity plot) {
		this.plot = plot;
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
		final AlertEntity alertEntity = (AlertEntity) obj;
		return alertEntity.id == this.id;
	}

}
