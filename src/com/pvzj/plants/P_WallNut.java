package com.pvzj.plants;

import java.awt.Graphics;

import com.pvzj.animation.Animation;
import com.pvzj.animation.AnimationAsset;
import com.pvzj.game.MyTool;
import com.pvzj.scene.adventure.Floor;

public class P_WallNut extends Plant {

	private static AnimationAsset aAsset;
	private static AnimationAsset aAsset1;
	private static AnimationAsset aAsset2;
	Animation plant=new Animation(aAsset);
	Animation plant1=new Animation(aAsset1);
	Animation plant2=new Animation(aAsset2);
	long fireTime=System.currentTimeMillis();	
	static {
		aAsset=new AnimationAsset(MyTool.toAbsolutePath("assets/biology/botany/WallNut"), "WallNut", 16);
		aAsset1=new AnimationAsset(MyTool.toAbsolutePath("assets/biology/botany/Wallnut_cracked1"), "Wallnut_cracked1", 10);
		aAsset2=new AnimationAsset(MyTool.toAbsolutePath("assets/biology/botany/Wallnut_cracked2"), "Wallnut_cracked2", 15);
	}
	public P_WallNut(Floor floor,int gridX,int gridY) {
		super(floor,gridX,gridY);
		this.nowBoold=30;
		this.fireRate=2000;		
	}
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		if (nowBoold>20) {
			plant.X=x;
			plant.Y=y;
			plant.draw(g);	
		}else if (nowBoold>10) {
			plant1.X=x;
			plant1.Y=y;
			plant1.draw(g);
		}else {
			plant2.X=x;
			plant2.Y=y;
			plant2.draw(g);
		}
			
	}
}
