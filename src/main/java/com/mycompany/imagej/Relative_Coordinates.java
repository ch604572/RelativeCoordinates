package com.mycompany.imagej;

import ij.IJ;
import ij.ImagePlus;
import ij.gui.GenericDialog;
import ij.measure.Calibration;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;

public class Relative_Coordinates implements PlugInFilter {
	
	protected ImagePlus imp;	
	
	public double xOrigin;
	
	public double yOrigin;
	
	public double angleDeg;
	
	@Override
	public int setup(String arg, ImagePlus imp) {
		IJ.log("Setting up relative coordinate plugin...");
		this.imp = imp;
		return DOES_8G | DOES_16 | DOES_32 | DOES_RGB;
	}

	@Override
	public void run(ImageProcessor ip ) {
		IJ.log(" - Running ...");
		if( showDialog( imp ) ) {
			IJ.log( " - Origin: x = " + this.xOrigin + ", y = " + this.yOrigin );
			new RelativeCoordinates( this.xOrigin, this.yOrigin, angleDeg );
		}
		IJ.log( "Done.");
	}
		
	private boolean showDialog( ImagePlus imp ) {
		Calibration cal = imp.getCalibration();
		
		GenericDialog gd = new GenericDialog("Relative Coordinates");	
		
		gd.addNumericField( "x-translated", cal.xOrigin, 3 );
		gd.addNumericField( "y-translated", cal.yOrigin, 3 );
		gd.addNumericField( "Angle (deg) ", 0.00, 2);

		gd.showDialog();
		if ( gd.wasCanceled() ) {
			return false;
		}
		// Get entered values
		cal.xOrigin = gd.getNextNumber();
		cal.yOrigin = gd.getNextNumber();
		angleDeg = gd.getNextNumber();
		angleDeg = angleDeg == 0.0 ? 0.0 : angleDeg;

		this.xOrigin = cal.xOrigin;
		this.yOrigin = cal.yOrigin;
		
		return true;		
	}

}

