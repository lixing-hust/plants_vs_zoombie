package com.pvzj.plants;

import java.awt.Graphics;

public abstract class Bullet {
	public int atk;
	public int buff=0;
	// 0飞行 1碰撞 
 	public int status=0;
 	public long hitTime=0;
	public double speed=600;
	public double speedY=0;
	public double X;
	public double basicY;
	public double Y;
	public abstract void draw(Graphics g);
}
