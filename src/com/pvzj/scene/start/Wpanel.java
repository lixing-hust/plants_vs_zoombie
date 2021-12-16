package com.pvzj.scene.start;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Wpanel {
	public ImageIcon panelIcon;
	public int X;
	public int Y;
	public int h;
	public int w;
	
	public Wpanel(ImageIcon panelIcon) {
		super();
		this.panelIcon=panelIcon;
		h=panelIcon.getIconHeight();
		w=panelIcon.getIconWidth();		
	}
	public void draw(Graphics g) {
		g.drawImage(panelIcon.getImage(),X, Y, panelIcon.getImageObserver());
	}

}
