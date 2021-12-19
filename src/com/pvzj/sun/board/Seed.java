package com.pvzj.sun.board;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import com.pvzj.animation.AnimationImg;
import com.pvzj.game.GameComponent;
import com.pvzj.game.GamePanel;
import com.pvzj.game.MyTool;
import com.pvzj.game.Vector2D;
import com.pvzj.scene.adventure.Floor;
import com.pvzj.scene.start.Start;
import com.pvzj.scene.start.Wbutton;
import com.pvzj.scene.start.Wpanel;

public class Seed extends GameComponent {
	public static final int sunLength = 117;

	public int sunSum = 5000;
	public int sunTime = 9000;
	public boolean isOver = false;
	private Floor floor;
	private long createTime = System.currentTimeMillis();
	private int selectCardID = -1;

	public ArrayList<Sun> suns = new ArrayList<Sun>();
	private Card[] cards = new Card[6];
	public int x = 170;
	public int y = 0;
	public int cardX = x + 75;
	public int cardY = y + 5;
	
	public long time;
	// 图片资源
	private static ImageIcon bgImageIcon = new ImageIcon(MyTool.toAbsolutePath("assets/ui/SeedBank.png"));
	private static ImageIcon shovelBoxImg = new ImageIcon(MyTool.toAbsolutePath("assets/ui/ShovelBank.png"));
	private static ImageIcon shovelImg = new ImageIcon(MyTool.toAbsolutePath("assets/ui/Shovel.png"));
	private static ImageIcon zombiesWonImg = new ImageIcon(MyTool.toAbsolutePath("assets/img/ZombiesWon.png"));
	private static ImageIcon gameOverPanelImg = new ImageIcon(MyTool.toAbsolutePath("assets/ui/GameOverPanel.png"));
	private static ImageIcon gameOverImg = new ImageIcon(MyTool.toAbsolutePath("assets/ui/GameOver.png"));
	private static ImageIcon gameOverHighlightImg = new ImageIcon(MyTool.toAbsolutePath("assets/ui/GameOver_Highlight.png"));
	
	//游戏结束后重开按钮
	private Wpanel gameOverPanel=new Wpanel(gameOverPanelImg);
	private Wbutton gameOverButton=new Wbutton(gameOverImg, gameOverHighlightImg);
	
	private AnimationImg gameOverAnim =new AnimationImg(zombiesWonImg, 0.5, 1.5);

	public Seed(Floor floor) {
		super();
		this.floor = floor;
		setCardsID(new Card(2000, 2000, 50, 0, this), 0);
		setCardsID(new Card(2500, 2500, 100, 1, this), 1);
		setCardsID(new Card(5000, 5000, 50, 2, this), 2);
		setCardsID(new Card(15000, 15000, 150, 3, this), 3);
		setCardsID(new Card(8000, 8000, 175, 4, this), 4);
		setCardsID(new Card(10000, 10000, 200, 5, this), 5);
		// Vector2D position = new Vector2D(300,250);
		// Vector2D target = new Vector2D(300, 250);
		// suns.add(new Sun(position, target, this));
	}

	public void setCards(Card[] cards) {
		this.cards = cards;
	}

	public void setCardsID(Card card, int id) {
		this.cards[id] = card;
	}

	public void createSun(int x, int y) {
		suns.add(new Sun(new Vector2D(x, y), new Vector2D(x, y), this));
	}

	public void createSun(Vector2D position, Vector2D targe) {
		suns.add(new Sun(position, targe, this));
	}
	
	public void zombiesWon() {
		gameOverAnim.play();
		gameOverAnim.moveTo(400, 300);
		
		gameOverPanel.X=(800-gameOverPanel.w)/2;
		gameOverPanel.Y=(600-gameOverPanel.h)/2;
		
		gameOverButton.X=gameOverPanel.X+48;
		gameOverButton.Y=gameOverPanel.Y+175;
		isOver=true;
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (gameOverButton.mouseClick(e.getX(), e.getY())) {
			GamePanel.nowMoled= new Start();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int CardID;
		if (e.getY() > 0 && e.getY() <= Card.CARD_HEIGHT) {
			if (e.getX() > cardX && e.getX() < cardX + Card.CARD_WIATH * 6) {
				CardID = (e.getX() - cardX) / Card.CARD_WIATH;
				if (cards[CardID] != null) {
					if (cards[CardID].nowCd <= 0) {
						if (sunSum >= cards[CardID].price) {
							floor.setPointPlant(cards[CardID].plantID);
							selectCardID = CardID;
							return;
						}
					}
				}
			}
		}
		// 进行放置植物
		if (selectCardID != -1) {
			Vector2D temp = floor.getCheckerBoard(e.getX(), e.getY());
			if (temp != null) {
				floor.setPlant(temp, cards[selectCardID].plantID);
				sunSum -= cards[selectCardID].price;
				cards[selectCardID].reCD();
				selectCardID = -1;
			}
		}
		floor.setPointPlant(-1);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX();
		int y = e.getY();
		for (Sun sun : suns) {
			if (!sun.isCatch) {
				if (x > sun.position.x && x < sun.position.x + sunLength) {
					if (y > sun.position.y && y < sun.position.y + sunLength) {
						sun.catchSun();
					}
				}
			}
		}
		gameOverButton.mouseMove(x, y);
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		// 渲染背景

		g.drawImage(bgImageIcon.getImage(), x, y, bgImageIcon.getImageObserver());
		g.drawImage(shovelBoxImg.getImage(), x + bgImageIcon.getIconWidth(), y, shovelBoxImg.getImageObserver());
		g.drawImage(shovelImg.getImage(), x + bgImageIcon.getIconWidth(), y, shovelImg.getImageObserver());

		// 渲染卡片
		for (int i = 0; i < cards.length; i++) {
			if (cards[i] != null) {
				cards[i].draw(cardX + (i * Card.CARD_WIATH), cardY, g);
			}
		}
		// 渲染阳光数量
		g.drawString(sunSum + "", x + 20, 75);
		// 渲染僵尸波数
		g.setFont(new Font("微软雅黑", Font.BOLD, 29));
		g.setColor(Color.black);
		g.drawString("第" + floor.group + "波僵尸", 640, 555);
		g.setFont(new Font("微软雅黑", Font.BOLD, 25));
		g.setColor(Color.white);
		g.drawString("第" + floor.group + "波僵尸", 650, 550);
		// 渲染阳光
		for (int i = 0; i < suns.size(); i++) {
			suns.get(i).draw(g);
		}
		// 渲染僵尸胜利动画
		if (isOver) {
			gameOverAnim.draw(g);
			if (gameOverAnim.isPlayEnd()) {
				gameOverPanel.draw(g);
				gameOverButton.draw(g);
			}
		}
		// 生成阳光
		if (System.currentTimeMillis() - createTime >= sunTime) {
			Vector2D position = new Vector2D((int) (Math.random() * 600 + Floor.checkerBoardX), 0);
			Vector2D target = new Vector2D(0, (int) (Math.random() * 400 + 100));
			suns.add(new Sun(position, target, this));
			createTime = System.currentTimeMillis();
		}

	}

}