package com.ais.crop.model;

import com.ais.plot.model.PlotEntity;

public class OnionCrop extends Crop {

	public OnionCrop(PlotEntity plot) {
		super(plot);
	}

	@Override
	public Integer getIrrigationDurationInMin() {
		return Math.round(plot.getArea() / 120);
	}

	@Override
	public Integer getIrrigationIntervalInMin() {
		return Math.round(plot.getArea() / 125);
	}

	@Override
	public Float getWaterRequirementInMm() {
		return plot.getArea() / 9000;
	}

}
