package com.pvzj.game;

import javax.swing.JFrame;

public class Game extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static boolean debugMode=false;
	
	GamePanel gp = new GamePanel();
	public Game() {
		add(gp);
		setSize(820, 620);
		setLocation(550, 230);
		setVisible(true);
		setTitle("Plants vs. Zombies");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

	}
	public static void main(String[] args) throws InterruptedException {
		Game game =new Game();
		while (true) {
			game.repaint();			
		}
	}
}
