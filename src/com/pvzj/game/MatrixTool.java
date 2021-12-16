package com.pvzj.game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.Stack;

class MatrixTool {
	private Graphics2D g2;
	private Stack<AffineTransform> matrxS = new Stack<AffineTransform>();

	public MatrixTool(Graphics g) {
		super();
		g2 = (Graphics2D) g;
	}

	public void matrixPush() {
		matrxS.push((AffineTransform) g2.getTransform().clone());
	}

	public void matrixPop() {
		if (matrxS.empty()) {
			System.out.println("MatrixTool error:Matrx is empty!!");
			System.exit(0);
		}
		g2.setTransform(matrxS.pop());
	}
}