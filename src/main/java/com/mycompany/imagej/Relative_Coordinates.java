package com.mycompany.imagej;

import ij.IJ;
import ij.ImagePlus;
import ij.plugin.PlugIn;


public class Relative_Coordinates implements PlugIn {
		// Use to massage the data retrieved from trackmate
	// since trackmate does not use the origin set in ImageJ 
	// nor the updated scales and origins after its model is set
	
	@Override
	public void run(String arg) {
		RelativeCoordinates coordinates = new RelativeCoordinates();
		RelativeCoordinatesFrame frame = new  RelativeCoordinatesFrame();
	}
	
}

