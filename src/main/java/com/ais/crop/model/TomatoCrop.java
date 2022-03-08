package com.ais.crop.model;

import com.ais.plot.model.PlotEntity;

public class TomatoCrop extends Crop {

	public TomatoCrop(PlotEntity plot) {
		super(plot);
	}

	@Override
	public Integer getIrrigationDurationInMin() {
		return Math.round(plot.getArea() / 125);
	}

	@Override
	public Integer getIrrigationIntervalInMin() {
		return Math.round(plot.getArea() / 130);
	}

	@Override
	public Float getWaterRequirementInMm() {
		return plot.getArea() / 8000;
	}

}
