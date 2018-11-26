package com.mycompany.imagej;

import ij.IJ;
import ij.ImagePlus;

public class RelativeCoordinates {
	
	protected double xOrigin;
	
	protected double yOrigin;
	
	protected double x_translated;
	
	protected double y_translated;
	
	private double angleDeg;
	
	/*
	 *  Constructors 
	 */
	public RelativeCoordinates( ) {
		ImagePlus imp = IJ.getImage();
		this.xOrigin = imp.getCalibration().xOrigin;
		this.yOrigin = imp.getCalibration().yOrigin;
	}
	
	public RelativeCoordinates( final double angleDeg ) {
		this.angleDeg = angleDeg;		
	}
	
	public RelativeCoordinates( final double xOrigin, final double yOrigin ) {
		this.xOrigin = xOrigin;
		this.yOrigin = yOrigin;		
	}	

	public RelativeCoordinates( final double xOrigin, final double yOrigin, final double angleDeg ) {
		this.xOrigin = xOrigin;
		this.yOrigin = yOrigin;	
		this.angleDeg = angleDeg;
	}
	
	/*
	 *  Methods
	 */
	public void setAngleDeg( final double angleDeg )
	{
		this.angleDeg = angleDeg;
	}	
	
	public double getAngleDeg()
	{
		return angleDeg;
	}

	public double xTranslated( final double x )
	{
		return ( x - this.xOrigin );
	}
	
	public double yTranslated( final double y )
	{
		return ( y - this.yOrigin );
	}
	
	public double XRotated( final double x, final double y )
	{
		final double x_translated = xTranslated( x );
		final double y_translated = yTranslated( y );
		return x_translated * Math.cos( Math.toRadians( angleDeg ) ) + y_translated * Math.sin( Math.toRadians( angleDeg ) );
	}
	
	public double YRotated( final double x, final double y )
	{
		final double x_translated = xTranslated( x );
		final double y_translated = yTranslated( y );
		return -x_translated * Math.sin( Math.toRadians( angleDeg ) ) + y_translated * Math.cos( Math.toRadians( angleDeg ) );
	}
}
