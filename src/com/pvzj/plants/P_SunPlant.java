package com.pvzj.plants;

import java.awt.Graphics;

import com.pvzj.animation.Animation;
import com.pvzj.animation.AnimationAsset;
import com.pvzj.game.MyTool;
import com.pvzj.game.Vector2D;
import com.pvzj.scene.adventure.Floor;

public class P_SunPlant extends Plant {

	private static AnimationAsset aAsset;
	Animation plant=new Animation(aAsset);
	long fireTime=System.currentTimeMillis();
	
	static {
		aAsset=new AnimationAsset(MyTool.toAbsolutePath("assets/biology/botany/SunFlower"), "SunFlower", 18);
	}
	public P_SunPlant(Floor floor,int gridX,int gridY) {
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
		
		if (System.currentTimeMillis()-fireTime>=10000) {
			manage.seedSun.createSun(new Vector2D(x-10, y-10), new Vector2D(x, y));
			//Vector2D target = new Vector2D(1000, (int) (Math.random() * 600-100));
			//manage.seedSun.createSun(new Vector2D(x, y),target);
			fireTime=System.currentTimeMillis();
		}			
				
	}

}
