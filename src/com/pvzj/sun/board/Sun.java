package com.pvzj.sun.board;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import com.pvzj.game.Game;
import com.pvzj.game.GamePanel;
import com.pvzj.game.MyTool;
import com.pvzj.game.Vector2D;

public class Sun{
	boolean isCatch=false;
	public Vector2D position;
	public Vector2D target;
	public Seed parent;
	Vector2D spendVec=new Vector2D();
	private long createTime=System.currentTimeMillis();
	private static ImageIcon[] sunImg=new ImageIcon[3];
	static {
		sunImg[0]=new ImageIcon(MyTool.toAbsolutePath("assets/img/sun1.png"));
		sunImg[1]=new ImageIcon(MyTool.toAbsolutePath("assets/img/sun2.png"));
		sunImg[2]=new ImageIcon(MyTool.toAbsolutePath("assets/img/sun3.png"));		
	}
	
	public Sun(Vector2D position, Vector2D target,Seed seed) {
		super();
		this.position = position;
		this.target = target;
		this.parent=seed;
	}
	public void catchSun() {
		isCatch=true;
		this.target.x =170;         
		this.target.y =0;		
	}
	public void draw(Graphics g ) {
		if (isCatch) {
			spendVec=target.minusVec(position);
			if (spendVec.getNorm()<=20) {
				parent.sunSum+=25;
				parent.suns.remove(this);
				return;
			}
			spendVec.normaliz();			
			position.x+=(GamePanel.frameTime/1000)*spendVec.x*800;
			position.y+=(GamePanel.frameTime/1000)*spendVec.y*800;
		}else {
			if (position.y<target.y) {
				position.y+=(GamePanel.frameTime/1000)*100;
			}
			if (position.x<target.x) {
				position.x+=(GamePanel.frameTime/1000)*100;
			}
		}
		if(System.currentTimeMillis()-createTime>3000)
			parent.suns.remove(this);
		sunDraw(position.x,position.y,g);
	}
	private void sunDraw(double x,double y,Graphics g) {
		g.drawImage(sunImg[2].getImage(), (int)x+0, (int)y+0, sunImg[2].getImageObserver());
		g.drawImage(sunImg[1].getImage(), (int)x+20, (int)y+20, sunImg[1].getImageObserver());
		g.drawImage(sunImg[0].getImage(), (int)x+40, (int)y+40, sunImg[0].getImageObserver());
	}
}
