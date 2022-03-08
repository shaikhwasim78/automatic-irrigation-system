package com.ais.crop.model;

import com.ais.plot.model.PlotEntity;

public abstract class Crop {

	PlotEntity plot;

	public Crop(PlotEntity plot) {
		this.plot = plot;
	}

	public abstract Integer getIrrigationDurationInMin();

	public abstract Integer getIrrigationIntervalInMin();

	public abstract Float getWaterRequirementInMm();

}
