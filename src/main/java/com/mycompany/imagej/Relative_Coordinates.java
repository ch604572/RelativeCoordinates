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
		RelativeCoordinatesFrame frame = new  RelativeCoordinatesFrame();
	}
	
	protected double xOrigin;
	
	protected double yOrigin;
	
	protected double x_translated;
	
	protected double y_translated;
	
	private double angleDeg;
	
	protected double x_rotated;
	
	protected double y_rotated;
	
	public Relative_Coordinates( ) {
		ImagePlus imp = IJ.getImage();
		this.xOrigin = imp.getCalibration().xOrigin;
		this.yOrigin = imp.getCalibration().yOrigin;
		IJ.log("new StrainActionRelativeCoordinates created. " + this.xOrigin + "  " + this.yOrigin);
	}
	
	public Relative_Coordinates( final double x, final double y ) {
		ImagePlus imp = IJ.getImage();
		this.xOrigin = imp.getCalibration().xOrigin;
		this.yOrigin = imp.getCalibration().yOrigin;
		this.x_translated = x - this.xOrigin;
		this.y_translated = y - this.yOrigin;	
		IJ.log("new StrainActionRelativeCoordinates created. x_translated = " + this.x_translated + " y_translated = " + this.y_translated) ;
	}
	
	public Relative_Coordinates( final double x, final double y, final double angleDeg ) {
		ImagePlus imp = IJ.getImage();
		this.xOrigin = imp.getCalibration().xOrigin;
		this.yOrigin = imp.getCalibration().yOrigin;
		this.x_translated = x - xOrigin;
		this.y_translated = y - yOrigin;
		this.angleDeg = angleDeg;
		setXRotated(x_translated, y_translated);
		setYRotated(x_translated, y_translated);		
		IJ.log("new StrainActionRelativeCoordinates created. x_translated = " + this.x_translated + " y_translated = " + this.y_translated + " angleDeg = " + this.angleDeg
				+ " x_rotated = " + this.x_rotated + " y_rotated = " + this.y_rotated);
	}
	
	public void setXTranslated( final double x )
	{
		this.x_translated = x - this.xOrigin;
	}
	
	public void setYTranslated( final double y )
	{
		this.y_translated = y - this.yOrigin;
	}
	
	public void setAngleDeg( final double angleDeg )
	{
		this.angleDeg = angleDeg;
	}
		
	public void setXRotated( final double x_translated, final double y_translated )
	{
		this.x_rotated = x_translated * Math.cos(Math.toRadians(angleDeg)) + y_translated * Math.sin(Math.toRadians(angleDeg));
	}
	
	public void setYRotated( final double x_translated, final double y_translated )
	{
		this.y_rotated = - x_translated * Math.sin(Math.toRadians(angleDeg)) + y_translated * Math.cos(Math.toRadians(angleDeg));
	}
	
	public double getAngleDeg()
	{
		return angleDeg;
	}
	
	public double getXTranslated()
	{
		return x_translated;
	}
	
	public double getYTranslated()
	{
		return y_translated;
	}
	
	public double getXRotated()
	{
		return y_rotated;
	}
	
	public double getYRotated()
	{
		return y_rotated;
	}
	
}

