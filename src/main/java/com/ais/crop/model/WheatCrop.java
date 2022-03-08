package com.ais.crop.model;

import com.ais.plot.model.PlotEntity;

public class WheatCrop extends Crop {

	public WheatCrop(PlotEntity plot) {
		super(plot);
	}

	@Override
	public Integer getIrrigationDurationInMin() {
		return Math.round(plot.getArea() / 111);
	}

	@Override
	public Integer getIrrigationIntervalInMin() {
		return Math.round(plot.getArea() / 112);
	}

	@Override
	public Float getWaterRequirementInMm() {
		return plot.getArea() / 8890;
	}

}
