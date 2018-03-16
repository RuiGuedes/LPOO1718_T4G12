package dkeep.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import dkeep.logic.*;

public class Playground extends JPanel {
	
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
	
	public Playground() {
		try {
			hero = ImageIO.read(new File("C:\\Users\\Rui\\Downloads\\hero.png"));
			guard = ImageIO.read(new File("C:\\Users\\Rui\\Downloads\\guard.png")); 
			wall = ImageIO.read(new File("C:\\Users\\Rui\\Downloads\\wall.jpg")); 
			ogre = ImageIO.read(new File("C:\\Users\\Rui\\Downloads\\ogre.png"));
			door = ImageIO.read(new File("C:\\Users\\Rui\\Downloads\\door.png"));
			leverOff = ImageIO.read(new File("C:\\Users\\Rui\\Downloads\\leverOff.jpg"));
			leverOn = ImageIO.read(new File("C:\\Users\\Rui\\Downloads\\leverOn.jpg"));
			key = ImageIO.read(new File("C:\\Users\\Rui\\Downloads\\key.png"));
			club = ImageIO.read(new File("C:\\Users\\Rui\\Downloads\\club.png"));
			stairs = ImageIO.read(new File("C:\\Users\\Rui\\Downloads\\stair.png"));
			floor = ImageIO.read(new File("C:\\Users\\Rui\\Downloads\\floor.jpg"));
			doorOpen = ImageIO.read(new File("C:\\Users\\Rui\\Downloads\\doorOpen.png"));
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
			
				if((playground[i][j] == 'H') || (playground[i][j] == 'A'))
					g.drawImage(hero, (int)x, (int)y, deltaX, deltaY, null);
				else if(playground[i][j] == 'X')
					g.drawImage(wall, (int)x, (int)y, deltaX, deltaY, null);
				else if(playground[i][j] == 'I') {
					g.drawImage(wall, (int)x, (int)y, deltaX, deltaY, null);
					g.drawImage(door, (int)x, (int)y, deltaX, deltaY, null);
				}
				else if(playground[i][j] == 'G')
					g.drawImage(guard, (int)x, (int)y, deltaX, deltaY, null);
				else if(playground[i][j] == 'O')
					g.drawImage(ogre, (int)x, (int)y, deltaX, deltaY, null);
				else if(playground[i][j] == '*')
					g.drawImage(club, (int)x, (int)y, deltaX, deltaY, null);
				else if(playground[i][j] == 'S') {
					g.drawImage(wall, (int)x, (int)y, deltaX, deltaY, null);
					g.drawImage(stairs, (int)x, (int)y, deltaX, deltaY, null);
				}
				else if(playground[i][j] == 'k') {
					if(Lock.lockType)
						g.drawImage(key, (int)x + 5, (int)y + 5, deltaX - 10, deltaY - 10, null);
					else
						g.drawImage(leverOff, (int)x + 7, (int)y + 5, deltaX - 15, deltaY - 10, null);
				}
				else if(playground[i][j] == 'K')
					g.drawImage(leverOn, (int)x + 7, (int)y + 5, deltaX - 15, deltaY - 10, null);
				else if(playground[i][j] == 'Z') {
					g.drawImage(wall, (int)x, (int)y, deltaX, deltaY, null);
					g.drawImage(doorOpen, (int)x, (int)y, deltaX, deltaY, null);
				}
				
				x += deltaX;
			}
			x = 0;
			y += deltaY;
		}

	}

}
