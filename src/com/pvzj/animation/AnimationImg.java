package com.pvzj.animation;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import com.pvzj.game.Game;

public class AnimationImg {
	public int X = 0;
	public int Y = 0;

	private ImageIcon img;
	private double nowScale = 0;
	private double originScale = 0.5;
	private double targetScale = 1;
	private int palyTime = 3000; // 动画持续时间 单位:ms
	private int state = 3;// 0:循环播放;1:正在播放中;2:播放完毕

	private long time;

	public AnimationImg(ImageIcon img, double originScale, double targetScale) {
		super();
		this.img = img;
		this.originScale = originScale;
		this.targetScale = targetScale;
		this.time = System.currentTimeMillis();
	}

	public void draw(Graphics g) {
		if (state==1) {
			if (nowScale < targetScale) {
				nowScale = originScale + (targetScale - originScale) * (System.currentTimeMillis() - time) / palyTime;
				g.drawImage(img.getImage(), X - (int) (img.getIconWidth() * nowScale) / 2,
						Y - (int) (img.getIconHeight() * nowScale) / 2, (int) (img.getIconWidth() * nowScale),
						(int) (img.getIconHeight() * nowScale), img.getImageObserver());
			} else {
				state = 2;
			}
		}
		

		// debug
		if (Game.debugMode) {
			g.drawRect(X, Y, (int) (img.getIconWidth() * nowScale), (int) (img.getIconHeight() * nowScale));
		}
	}

	/**
	 * 开始播放并且指定为单次播放
	 */
	public void play() {
		state = 1;
		nowScale=originScale;
		this.time = System.currentTimeMillis();
	}

	/**
	 * 查询是否播放完毕
	 * 
	 * @return
	 */
	public boolean isPlayEnd() {
		if (state == 2) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 重新播放动画
	 */
	public void rePlay() {
	}

	/**
	 * 指定每帧的停留时间
	 * 
	 * @param time 每帧的停留时间
	 */
	public void setPlaySpeed(int time) {

	}

	/**
	 * 将绘图的位置移动到(x,y)处
	 * 
	 * @param x
	 * @param y
	 */
	public void moveTo(int x, int y) {
		// TODO Auto-generated method stub
		X = x;
		Y = y;
	}

	/**
	 * 将绘图的位置移动(x,y)个单位
	 * 
	 * @param x
	 * @param y
	 */
	public void move(int x, int y) {
		// TODO Auto-generated method stub
		X += x;
		Y += y;
	}
}
