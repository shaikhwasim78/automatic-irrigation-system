package com.ais.crop.model;

import com.ais.plot.model.PlotEntity;

public class RiceCrop extends Crop {

	public RiceCrop(PlotEntity plot) {
		super(plot);
	}

	@Override
	public Integer getIrrigationDurationInMin() {
		return Math.round(plot.getArea() / 100);
	}

	@Override
	public Integer getIrrigationIntervalInMin() {
		return Math.round(plot.getArea() / 110);
	}

	@Override
	public Float getWaterRequirementInMm() {
		return plot.getArea() / 10000;
	}

}
