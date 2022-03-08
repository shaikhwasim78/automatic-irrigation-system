package com.ais.irrigation.dto;

import java.io.IOException;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import com.ais.crop.model.CropType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlotCropRequestDTO {

	@NotNull
	@Enumerated(EnumType.STRING)
	@JsonProperty("cropType")
	private CropType cropType;

	public CropType getCropType() {
		return cropType;
	}

	public void setCropType(CropType cropType) {
		this.cropType = cropType;
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
