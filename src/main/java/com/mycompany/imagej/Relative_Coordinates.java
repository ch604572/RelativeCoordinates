package com.mycompany.imagej;

import ij.IJ;
import ij.ImagePlus;
import ij.gui.GenericDialog;
import ij.plugin.PlugIn;


public class Relative_Coordinates implements PlugIn {
	ImagePlus imp = IJ.getImage();	
	public double x_translated = imp.getCalibration().xOrigin;
	public double y_translated = imp.getCalibration().yOrigin;
	public double angleDeg;
	
	@Override
	public void run(String arg) {
		if( showDialog() ) return;
		RelativeCoordinates coordinates = new RelativeCoordinates(x_translated, y_translated, angleDeg);
	}
		
	private boolean showDialog() {
		GenericDialog gd = new GenericDialog("Relative Coordinates");
		
			
		// default value is 0.00, 2 digits right of the decimal point
		gd.addNumericField( "x-translated", x_translated, 3 );
		gd.addNumericField( "y-translated", y_translated, 3 );
		gd.addNumericField( "Angle (deg) ", 0.00, 2);

		gd.showDialog();
		if (gd.wasCanceled()) {
			return true;
		}
		// get entered values
		x_translated = gd.getNextNumber();
		y_translated = gd.getNextNumber();
		angleDeg = gd.getNextNumber();
		
		return false;
	}
	
}

