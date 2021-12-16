package com.pvzj.game;

import java.awt.geom.Point2D;

public class Vector2D extends Point2D.Double{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Vector2D() {		
		super();
		// TODO Auto-generated constructor stub
	}
	public Vector2D(double x, double y) {
		super();

		this.x = x;
		this.y = y;
	}	
	public Vector2D minusVec(Vector2D vec2d) {
		return new Vector2D(x-vec2d.x, y-vec2d.y);
	}
	public Vector2D subtractVec(Vector2D vec2d) {
		return new Vector2D(x+vec2d.x, y+vec2d.y);
	}
	public double getNorm() {		
		return Math.sqrt(x*x+y*y);
	}
	public double multDot(Vector2D vec2d) {
		return x*vec2d.x+y*vec2d.y;
	}
	public double multCross(Vector2D vec2d) {		
		return this.x*vec2d.y-this.y*vec2d.x;
	}
	public void normaliz() {
		double  nor=getNorm();
		x=x/nor;
		y=y/nor;
	}
	@Override
	public String toString() {
		return "Vector2D [x=" + x + ", y=" + y + "]";
	}

}
