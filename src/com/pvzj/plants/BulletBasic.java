package com.pvzj.plants;

import java.awt.Graphics;

import com.pvzj.game.Game;
import com.pvzj.scene.adventure.BulletManage;

public class BulletBasic extends Bullet { 
		
	public BulletBasic(int x,int y) {
		this.atk=1;
		this.X=x;
		this.Y=y;
		this.basicY=y;
		this.speed=400;
	}
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		if (this.status==0) {
			g.drawImage(BulletManage.bulletImgs[0].getImage(), (int)X-BulletManage.bulletImgs[0].getIconWidth()/2, (int)Y-BulletManage.bulletImgs[0].getIconHeight()/2, BulletManage.bulletImgs[0].getImageObserver());
		}else {
			g.drawImage(BulletManage.bulletImgs[2].getImage(), (int)X-BulletManage.bulletImgs[2].getIconWidth()/2, (int)Y-BulletManage.bulletImgs[2].getIconHeight()/2, BulletManage.bulletImgs[2].getImageObserver());
		}		
		//g.fillOval((int)X-5, (int)Y-5, 10, 10);
		if (Game.debugMode) {
			g.drawRect((int)X-BulletManage.bulletImgs[0].getIconWidth()/2, (int)Y-BulletManage.bulletImgs[0].getIconHeight()/2, BulletManage.bulletImgs[0].getIconHeight(), BulletManage.bulletImgs[0].getIconWidth());
		}
	}

}
