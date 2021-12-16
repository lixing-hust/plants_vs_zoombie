package com.pvzj.plants;

import java.awt.Graphics;

import com.pvzj.animation.Animation;
import com.pvzj.animation.AnimationAsset;
import com.pvzj.game.MyTool;
import com.pvzj.scene.adventure.Floor;

public class P_SnowPea extends Plant {

	private static AnimationAsset aAsset;
	Animation plant=new Animation(aAsset);
	long fireTime=System.currentTimeMillis();
	
	static {
		aAsset=new AnimationAsset(MyTool.toAbsolutePath("assets/biology/botany/SnowPea"), "SnowPea", 15);
	}
	public P_SnowPea(Floor floor,int gridX,int gridY) {
		super(floor,gridX,gridY);
		this.nowBoold=5;
		this.fireRate=2000;		
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		plant.X=x;
		plant.Y=y;
		plant.draw(g);
		if (isFire) {
			if (System.currentTimeMillis()-fireTime>=2000) {
				manage.bulletManage.addBullet( super.getGridY(),x+25,y+25,1);;
				fireTime=System.currentTimeMillis();
			}			
		}		
	}

}
