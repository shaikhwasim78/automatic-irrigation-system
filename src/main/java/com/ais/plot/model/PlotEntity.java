package com.ais.plot.model;

import java.io.IOException;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Type;

import com.ais.irrigation.model.PlotIrrigationEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
@Table(name = "plot")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlotEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@Column(nullable = false, columnDefinition = "TINYINT(1) default 0")
	@JsonProperty("is_deleted")
	private Boolean isDeleted;

	@OneToOne(mappedBy = "plot")
	@NotFound(action = NotFoundAction.IGNORE)
	@JsonProperty("plot_irrigation_details")
	private PlotIrrigationEntity plotIrrigationDetails;

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

	public PlotIrrigationEntity getPlotIrrigationDetails() {
		return plotIrrigationDetails;
	}

	public void setPlotIrrigationDetails(PlotIrrigationEntity plotIrrigationDetails) {
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
		final PlotEntity plotEntity = (PlotEntity) obj;
		return plotEntity.id == this.id;
	}

}
