package com.mycompany.imagej;

import ij.IJ;
import ij.ImagePlus;
import ij.plugin.PlugIn;

public class Relative_Coordinates implements PlugIn {
	
	@Override
	public void run(String arg) {
		ImagePlus imp = IJ.getImage();
		IJ.run(imp, "Invert", "");
		IJ.wait(2000);
		IJ.run(imp, "Invert", "");
	}
	
}
