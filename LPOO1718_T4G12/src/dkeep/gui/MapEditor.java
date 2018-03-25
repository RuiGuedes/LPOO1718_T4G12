package dkeep.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MapEditor extends JPanel implements MouseListener {
	
	public Map<Character,BufferedImage> gameElements = new HashMap<Character,BufferedImage>();
	public char[][] map; 
	public int x;
	public int y;
	public int size = 10;
	public BufferedImage floor; 
	
	
	public MapEditor() {
		// TODO Auto-generated constructor stub
		this.addMouseListener(this);
		try {
			gameElements.put('X', ImageIO.read(getClass().getResourceAsStream("/wall.jpg")));
			gameElements.put('A', ImageIO.read(getClass().getResourceAsStream("/hero.png")));
			gameElements.put('k', ImageIO.read(getClass().getResourceAsStream("/key.png")));
			gameElements.put('O', ImageIO.read(getClass().getResourceAsStream("/ogre.png")));
			gameElements.put('*', ImageIO.read(getClass().getResourceAsStream("/club.png")));
			gameElements.put('I', ImageIO.read(getClass().getResourceAsStream("/door.png")));
			gameElements.put(' ', ImageIO.read(getClass().getResourceAsStream("/floor.jpg")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		map = new char[size][size];
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[i].length; j++) {
				if((i == 0) || (j == 0) || (i == (size -1)) || (j == (size -1)))
					map[i][j] = 'X';
				else
					map[i][j] = ' ';
			}
		}
	}
	
	public void setMap(char[][] map) {
		this.map = map;
		repaint();
	}

	public MapEditor(LayoutManager arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public MapEditor(boolean arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public MapEditor(LayoutManager arg0, boolean arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if(map == null)
			return;

		int deltaX = (int) Math.ceil((float)getWidth()/map.length);
		int deltaY = (int) Math.ceil((float)getHeight()/map[0].length);

		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[0].length; j++) {
					g.drawImage(gameElements.get(' '), i*deltaX, j*deltaY, deltaX, deltaY,null);
					g.drawImage(gameElements.get(map[i][j]), i*deltaX, j*deltaY, deltaX, deltaY,null);
			}

		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
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
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
