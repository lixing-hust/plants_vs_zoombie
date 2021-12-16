package com.pvzj.zombies;

import java.awt.Graphics;

import com.pvzj.scene.adventure.Floor;
import com.pvzj.scene.adventure.ZombieManage;

public abstract class Zombie {
	
	protected int state=0;
	public int blood=5;
	public double atk=1.2;
	public double speed=20;
	public double x;
	public double y;	
	public ZombieManage manage;
	
	protected boolean buffIce=false;
	protected  long buffIceBegin;
	protected long buffIceTime=1500;
	protected int spendIce=10;
		
	public abstract void draw(Graphics g);
	public abstract void setDie(int state);
	
	public Zombie(ZombieManage manage) {
		this.manage=manage;
	}
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	public void setIceBuff() {
		buffIce=true;
		buffIceBegin=System.currentTimeMillis();
	}
	public boolean isHit(double x1,double y1 ) {
		if (x1>x&&x1<x+Floor.boxwidth) {
			if (y1>y&&y1<y+Floor.boxHeight) {
				return true;
			}
		}
		return false;
	}
}
