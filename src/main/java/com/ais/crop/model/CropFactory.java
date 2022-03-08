package com.ais.crop.model;

import com.ais.plot.model.PlotEntity;

public class CropFactory {

	public static Crop getCrop(PlotEntity plot, CropType cropType) {
		switch (cropType) {
			case CORN :
				return new CornCrop(plot);
			case ONION :
				return new OnionCrop(plot);
			case POTATO :
				return new PotatoCrop(plot);
			case RICE :
				return new RiceCrop(plot);
			case TOMATO :
				return new TomatoCrop(plot);
			case WHEAT :
				return new WheatCrop(plot);
			default :
				throw new IllegalStateException("No such crop available");
		}
	}

}
