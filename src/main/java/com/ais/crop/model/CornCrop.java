package com.ais.crop.model;

import com.ais.plot.model.PlotEntity;

public class CornCrop extends Crop {

	public CornCrop(PlotEntity plot) {
		super(plot);
	}

	@Override
	public Integer getIrrigationDurationInMin() {
		return Math.round(plot.getArea() / 110);
	}

	@Override
	public Integer getIrrigationIntervalInMin() {
		return Math.round(plot.getArea() / 120);
	}

	@Override
	public Float getWaterRequirementInMm() {
		return plot.getArea() / 9500;
	}

}
