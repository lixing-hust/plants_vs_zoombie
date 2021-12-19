package com.pvzj.scene.adventure;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.ImageIcon;

import com.pvzj.game.GamePanel;
import com.pvzj.game.MyTool;
import com.pvzj.plants.Bullet;
import com.pvzj.plants.BulletBasic;
import com.pvzj.plants.BulletIce;
import com.pvzj.zombies.Zombie;

public class BulletManage {
	public Floor manage;
	private static final int MAX_DISTANCE=820;
	
	public LinkedList<ArrayList<Bullet>> bulletList=new LinkedList<ArrayList<Bullet>>();
	public static ImageIcon[]  bulletImgs=new ImageIcon[3];
	static {
		bulletImgs[0]=new ImageIcon(MyTool.toAbsolutePath("assets/biology\\botany\\bullet/ProjectilePea.png"));
		bulletImgs[1]=new ImageIcon(MyTool.toAbsolutePath("assets/biology\\botany\\bullet//ProjectileSnowPea.png"));
		bulletImgs[2]=new ImageIcon(MyTool.toAbsolutePath("assets/biology\\botany\\bullet/BulletHit.png"));
		
	}
	
	public ArrayList<Bullet> Bullets=new ArrayList<Bullet>();
	
	public BulletManage(Floor floor) {
		super();
		this.manage=floor;
		bulletList.add(new ArrayList<Bullet>());
		bulletList.add(new ArrayList<Bullet>());
		bulletList.add(new ArrayList<Bullet>());
		bulletList.add(new ArrayList<Bullet>());
		bulletList.add(new ArrayList<Bullet>());
	}
	
	public void addBullet(int rowIndex, int x,int y,int index) {
		if (index==0) {
			bulletList.get(rowIndex).add(new BulletBasic(x, y));
		}else if (index==1) {
			bulletList.get(rowIndex).add(new BulletIce(x, y));
		}
		
	}
	
	public void draw(Graphics g) {
		ArrayList<Bullet> bullets;
		Zombie hitZombie;
		
		for (int i = 0; i < bulletList.size(); i++) {
			bullets=bulletList.get(i);
			for (int j = 0; j < bullets.size(); j++) {
				
				bullets.get(j).X+=bullets.get(j).speed*(GamePanel.frameTime/1000);
				
				if (bullets.get(j).status==1) {
					bullets.get(j).draw(g);
					if (System.currentTimeMillis()-bullets.get(j).hitTime>=100) {
						bullets.remove(j);
					}
					continue;
				}
				
				hitZombie=manage.zombieManage.hasZombie(i, bullets.get(j).X, bullets.get(j).Y);
				if (hitZombie!=null) {
					if (bullets.get(j).buff==1) {
						hitZombie.setIceBuff();
					}
					bullets.get(j).status=1;
					bullets.get(j).hitTime=System.currentTimeMillis();
					manage.zombieManage.injuredZombie(i, hitZombie, bullets.get(j).atk);					
				}else {
					bullets.get(j).draw(g);
					if (bullets.get(j).X>MAX_DISTANCE) {
						bullets.remove(j);
					}	
				}								
			}			
		}
	}
}
