package dkeep.gui;

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
	private LevelEditor levelEditor;
	private char[][] map; 
	private int x;
	private int y;
	private int size = 10;

	public MapEditor(LevelEditor levelEditor) {
		this.addMouseListener(this);
		this.levelEditor = levelEditor;
		try {
			gameElements.put('X', ImageIO.read(getClass().getResourceAsStream("/wall.jpg")));
			gameElements.put('A', ImageIO.read(getClass().getResourceAsStream("/hero.png")));
			gameElements.put('k', ImageIO.read(getClass().getResourceAsStream("/key.png")));
			gameElements.put('O', ImageIO.read(getClass().getResourceAsStream("/ogre.png")));
			gameElements.put('*', ImageIO.read(getClass().getResourceAsStream("/club.png")));
			gameElements.put('I', ImageIO.read(getClass().getResourceAsStream("/door.png")));
			gameElements.put(' ', ImageIO.read(getClass().getResourceAsStream("/floor.jpg")));
		} catch (IOException e) {
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
	}

	public MapEditor(boolean arg0) {
		super(arg0);
	}

	public MapEditor(LayoutManager arg0, boolean arg1) {
		super(arg0, arg1);
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
				if(map[i][j] == 'I')
					g.drawImage(gameElements.get('X'), i*deltaX, j*deltaY, deltaX, deltaY,null);
				else
					g.drawImage(gameElements.get(' '), i*deltaX, j*deltaY, deltaX, deltaY,null);

				g.drawImage(gameElements.get(map[i][j]), i*deltaX, j*deltaY, deltaX, deltaY,null);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) { 
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int deltaX = (int) Math.ceil((float)getWidth()/map.length);
		int deltaY = (int) Math.ceil((float)getHeight()/map[0].length);

		setElement(e.getX()/deltaX, e.getY()/deltaY);
		repaint();

		x = e.getX();
		y = e.getY();
	}

	@Override 
	public void mouseReleased(MouseEvent e) {
	}

	public void setElement(int coordX, int coordY) {
		char element;
		if((element = LevelEditor.elementSelected) == ' ')
			return;

		char oldElement = map[coordX][coordY];

		if((map[coordX][coordY] == ' ') && ((element == 'X') || (element == 'A') ||  (element == 'O') || (element == 'k')))
			map[coordX][coordY] = element;
		else if((map[coordX][coordY] == 'X') && (element == 'I'))
			map[coordX][coordY] = element;
		else if((element == '*') && (coordX > 0) && (coordX < (map.length - 1)) && (coordY > 0) && (coordY < (map[0].length - 1))) {
			if(((map[coordX-1][coordY] == 'O') || (map[coordX+1][coordY] == 'O') || (map[coordX][coordY-1] == 'O') || (map[coordX][coordY+1] == 'O')) && (element == '*'))
				map[coordX][coordY] = element; 
		}

		if(oldElement != element) {
			if(element != 'X') {
				levelEditor.getDrawImage().setAvailable();
				LevelEditor.elementSelected = ' '; 
			}

		}

	}

}
