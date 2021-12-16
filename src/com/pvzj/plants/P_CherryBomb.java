package com.pvzj.plants;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import com.pvzj.animation.Animation;
import com.pvzj.animation.AnimationAsset;
import com.pvzj.game.MyTool;
import com.pvzj.game.Vector2D;
import com.pvzj.scene.adventure.Floor;

public class P_CherryBomb extends Plant {

	private static AnimationAsset aAsset;
	private static ImageIcon boomImg=new ImageIcon(MyTool.toAbsolutePath("assets/img/Boom.png"));
	
	Animation plant=new Animation(aAsset);
	
	private int state=0;
	private long Dietime; 
	
	long fireTime=System.currentTimeMillis();	
	static {
		aAsset=new AnimationAsset(MyTool.toAbsolutePath("assets/biology/botany/CherryBomb"), "CherryBomb", 7);
	}
	public P_CherryBomb(Floor floor,int gridX,int gridY) {
		super(floor,gridX,gridY);
		this.nowBoold=5;
		this.fireRate=2000;
		plant.play();
	}
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		plant.X=x;
		plant.Y=y;
		plant.draw(g);
		if (plant.isPlayEnd()) {
			g.drawImage(boomImg.getImage(), x-(boomImg.getIconWidth()/3), y-(boomImg.getIconHeight()/3), boomImg.getImageObserver());
			if (state==0) {
				manage.zombieManage.hasZombie(this.getGridY(), new Vector2D(x,y), 300);
				state=1;
				Dietime=System.currentTimeMillis();
			}else if (state==1) {
				if (System.currentTimeMillis()-Dietime>=200) {
					manage.reMovePlant(getGridX(), getGridY());
				}
			}			
		}		
	}
}
