package dkeep.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import dkeep.logic.*;

public class Playground extends JPanel implements MouseListener, KeyListener {

	private char[][] playground; 

	//Elements Images
	private BufferedImage hero;
	private BufferedImage wall;
	private BufferedImage guard;
	private BufferedImage ogre;
	private BufferedImage door;
	private BufferedImage leverOff;
	private BufferedImage leverOn;
	private BufferedImage key;
	private BufferedImage stairs;
	private BufferedImage club;
	private BufferedImage floor;
	private BufferedImage doorOpen;
	private BufferedImage stuntOgre;
	private BufferedImage cifrao;

	public Playground() {	

		this.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
			}
		});

		addMouseListener(this);
		addKeyListener(this);

		try {
			hero = ImageIO.read(getClass().getResourceAsStream("/hero.png"));
			guard = ImageIO.read(getClass().getResourceAsStream("/guard.png"));
			wall = ImageIO.read(getClass().getResourceAsStream("/wall.jpg"));
			ogre = ImageIO.read(getClass().getResourceAsStream("/ogre.png"));
			door = ImageIO.read(getClass().getResourceAsStream("/door.png"));
			leverOff = ImageIO.read(getClass().getResourceAsStream("/leverOff.jpg"));
			leverOn = ImageIO.read(getClass().getResourceAsStream("/leverOn.jpg"));
			key = ImageIO.read(getClass().getResourceAsStream("/key.png"));
			club = ImageIO.read(getClass().getResourceAsStream("/club.png"));
			stairs = ImageIO.read(getClass().getResourceAsStream("/stair.png"));
			floor = ImageIO.read(getClass().getResourceAsStream("/floor.jpg"));
			doorOpen = ImageIO.read(getClass().getResourceAsStream("/doorOpen.png"));
			stuntOgre = ImageIO.read(getClass().getResourceAsStream("/stuntOgre.png"));
			cifrao = ImageIO.read(getClass().getResourceAsStream("/cifrao.png"));
		} catch (IOException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
	}

	public void setPlayground(char[][] playground) {
		this.playground = playground;
		repaint();
	}

	public Playground(LayoutManager arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public Playground(boolean arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public Playground(LayoutManager arg0, boolean arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if(playground == null)
			return;

		int deltaX = (int) Math.ceil((float)getWidth()/playground.length);
		int deltaY = (int) Math.ceil((float)getHeight()/playground[0].length);
		float x = 0;
		float y = 0; 

		for(int i = 0; i < playground.length; i++) {
			for(int j = 0; j < playground[0].length; j++) {
				g.drawImage(floor, (int)x, (int)y, deltaX, deltaY, null);
				x += deltaX;
			}
			x = 0;
			y += deltaY;
		}
		x = 0;
		y = 0;

		for(int i = 0; i < playground.length; i++) {
			for(int j = 0; j < playground[0].length; j++) {

				switch(playground[i][j]) 
				{
				case 'H':
					g.drawImage(hero, (int)x + 5, (int)y + 5, deltaX - 15, deltaY - 10, null);
					break;
				case 'A':
					g.drawImage(hero, (int)x + 5, (int)y + 5, deltaX - 15, deltaY - 10, null);
					break;
				case 'X':
					g.drawImage(wall, (int)x, (int)y, deltaX, deltaY, null);
					break;
				case 'G':
					g.drawImage(guard, (int)x, (int)y, deltaX, deltaY, null);
					break;
				case 'O':
					g.drawImage(ogre, (int)x + 5, (int)y + 5, deltaX - 15, deltaY - 10, null);
					break;
				case '8':
					g.drawImage(stuntOgre, (int)x + 5, (int)y + 5, deltaX - 15, deltaY - 10, null);
					break;
				case '*':
					g.drawImage(club, (int)x + 10, (int)y + 10, deltaX - 25, deltaY - 20, null);
					break;
				case '$':
					g.drawImage(cifrao, (int)x, (int)y, deltaX, deltaY, null);
					break;
				case 'I':
					g.drawImage(wall, (int)x, (int)y, deltaX, deltaY, null);
					g.drawImage(door, (int)x, (int)y, deltaX, deltaY, null);
					break;
				case 'Z':
					g.drawImage(wall, (int)x, (int)y, deltaX, deltaY, null);
					g.drawImage(doorOpen, (int)x, (int)y, deltaX, deltaY, null);
					break;
				case 'S':
					g.drawImage(wall, (int)x, (int)y, deltaX, deltaY, null);
					if(Game.LEVEL == 1)
						g.drawImage(stairs, (int)x, (int)y, deltaX, deltaY, null);
					else
						g.drawImage(doorOpen, (int)x, (int)y, deltaX, deltaY, null);
					break;
				case 'k':
					if(Lock.lockType)
						g.drawImage(key, (int)x + 5, (int)y + 5, deltaX - 10, deltaY - 10, null);
					else
						g.drawImage(leverOff, (int)x + 7, (int)y + 5, deltaX - 15, deltaY - 10, null);
					break;
				case 'K':
					g.drawImage(leverOn, (int)x + 7, (int)y + 5, deltaX - 15, deltaY - 10, null);
					break;
				}
				x += deltaX;
			}
			x = 0;
			y += deltaY;
		}

	}


	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
		repaint();
		
	}


	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}


	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		this.requestFocus();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}


}
