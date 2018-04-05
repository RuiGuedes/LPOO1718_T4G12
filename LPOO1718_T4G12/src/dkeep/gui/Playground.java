package dkeep.gui;

import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import dkeep.logic.*;

public class Playground extends JPanel implements MouseListener, KeyListener {

	private char[][] playground; 
	private Map<Character,BufferedImage> elements;
	private NewGame game;
	
	public Playground(NewGame game) {	
		this.game = game;
		this.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
			}

			@Override
			public void focusGained(FocusEvent e) {
			}
		});

		addMouseListener(this);
		addKeyListener(this);
		elements = new HashMap<Character,BufferedImage>();

		try {
			elements.put('H', ImageIO.read(getClass().getResourceAsStream("/hero.png")));
			elements.put('A', ImageIO.read(getClass().getResourceAsStream("/hero.png")));
			elements.put('G', ImageIO.read(getClass().getResourceAsStream("/guard.png")));
			elements.put('X', ImageIO.read(getClass().getResourceAsStream("/wall.jpg")));
			elements.put('O', ImageIO.read(getClass().getResourceAsStream("/ogre.png")));
			elements.put('I', ImageIO.read(getClass().getResourceAsStream("/door.png")));
			elements.put('k', ImageIO.read(getClass().getResourceAsStream("/leverOff.jpg")));
			elements.put('K', ImageIO.read(getClass().getResourceAsStream("/leverOn.jpg")));
			elements.put('C', ImageIO.read(getClass().getResourceAsStream("/key.png")));
			elements.put('*', ImageIO.read(getClass().getResourceAsStream("/club.png")));
			elements.put('S', ImageIO.read(getClass().getResourceAsStream("/stair.png")));
			elements.put(' ', ImageIO.read(getClass().getResourceAsStream("/floor.jpg")));
			elements.put('Z', ImageIO.read(getClass().getResourceAsStream("/doorOpen.png")));
			elements.put('8', ImageIO.read(getClass().getResourceAsStream("/stuntOgre.png")));
			elements.put('$', ImageIO.read(getClass().getResourceAsStream("/cifrao.png")));
			elements.put('g', ImageIO.read(getClass().getResourceAsStream("/snoozeGuard.png")));
		} catch (IOException e) { 
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
	}

	public Playground(boolean arg0) {
		super(arg0);
	}

	public Playground(LayoutManager arg0, boolean arg1) { 
		super(arg0, arg1);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if(playground == null)
			return;
 
		int deltaX = (int) Math.ceil((float)getWidth()/playground.length);
		int deltaY = (int) Math.ceil((float)getHeight()/playground[0].length);
		
		for(int i = 0; i < playground.length; i++) {
			for(int j = 0; j < playground[0].length; j++) {
				if((playground[i][j] == 'I') || (playground[i][j] == 'S') || (playground[i][j] == 'Z'))
					g.drawImage(elements.get('X'), j*deltaX, i*deltaY, deltaX, deltaY,null);
				else
					g.drawImage(elements.get(' '), j*deltaX, i*deltaY, deltaX, deltaY,null);
				
				if((playground[i][j] == 'k') || (playground[i][j] == 'K')) {
					if(Game.LEVEL == 1)
						g.drawImage(elements.get(playground[i][j]), j*deltaX + 5, i*deltaY + 5, deltaX - 10, deltaY - 10,null);
					else if(Game.LEVEL == 2)
						g.drawImage(elements.get('C'), j*deltaX, i*deltaY, deltaX, deltaY,null);
				}
				else
					g.drawImage(elements.get(playground[i][j]), j*deltaX, i*deltaY, deltaX, deltaY,null);
			}
		}
		
	}

	public void keyPressed(KeyEvent arg0) {
		switch(arg0.getKeyCode()){
		case KeyEvent.VK_LEFT: 
			game.leftButton.doClick();	 
			break;
		case KeyEvent.VK_RIGHT: 
			game.rightButton.doClick();	
			break;
		case KeyEvent.VK_UP: 
			game.upButton.doClick();	 
			break;
		case KeyEvent.VK_DOWN: 
			game.downButton.doClick();	 
			break;
		}
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		this.requestFocus();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}


}
