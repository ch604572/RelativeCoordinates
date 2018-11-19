package com.mycompany.imagej;

import ij.IJ;
import ij.ImagePlus;
import ij.gui.GenericDialog;
import ij.measure.Calibration;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;

public class Relative_Coordinates implements PlugInFilter {
	protected ImagePlus imp;	
	public double x_translated;
	public double y_translated;
	public double angleDeg;
	
	public double value;
	public String name;
	
	@Override
	public int setup(String arg, ImagePlus imp) {
		IJ.log("in setup");
		this.imp = imp;
		return DOES_8G | DOES_16 | DOES_32 | DOES_RGB;
	}

	@Override
	public void run(ImageProcessor ip ) {
		IJ.log("in run");
		if( showDialog( imp ) ) {
			IJ.log( "  x = " + x_translated + " y = " + y_translated );
			RelativeCoordinates coordinates = new RelativeCoordinates( x_translated, y_translated, angleDeg );
			IJ.log("dialog OK");
		}
	}
		
	private boolean showDialog( ImagePlus imp ) {
		IJ.log("in show dialog");
		Calibration cal = imp.getCalibration();
		
		GenericDialog gd = new GenericDialog("Relative Coordinates");	
		
		x_translated = cal.xOrigin;
		y_translated = cal.yOrigin;
		// default value is 0.00, 2 digits right of the decimal point
		gd.addNumericField( "x-translated", x_translated, 3 );
		gd.addNumericField( "y-translated", y_translated, 3 );
		gd.addNumericField( "Angle (deg) ", 0.00, 2);

		gd.showDialog();
		if ( gd.wasCanceled() ) {
			return false;
		}
		// get entered values
		x_translated = gd.getNextNumber();
		y_translated = gd.getNextNumber();
		angleDeg = gd.getNextNumber();
		angleDeg = angleDeg == 0.0 ? 0.0 : angleDeg;
		
		cal.xOrigin = x_translated;
		cal.yOrigin = y_translated;	
		
		IJ.log( "x = " + x_translated + " y = " + y_translated );
		
		return true;		
	}
}

