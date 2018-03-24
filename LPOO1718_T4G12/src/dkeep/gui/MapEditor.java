package dkeep.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MapEditor extends JPanel implements MouseListener {
	
	public BufferedImage[][] map; 
	public int x;
	public int y;
	public int size = 10;
	public BufferedImage wall;
	public BufferedImage floor; 
	
	
	public MapEditor() {
		// TODO Auto-generated constructor stub
		this.addMouseListener(this);
		try {
			wall = ImageIO.read(getClass().getResourceAsStream("/wall.jpg"));
			floor = ImageIO.read(getClass().getResourceAsStream("/floor.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		map = new BufferedImage[size][size];
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[i].length; j++) {
				if((i == 0) || (j == 0) || (i == (size -1)) || (j == (size -1)))
					map[i][j] = wall;
				else
					map[i][j] = floor;
			}
		}
	}
	
	public void setMap(BufferedImage[][] map) {
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
				if(map[i][j] != null)
					g.drawImage(map[i][j], i*deltaX, j*deltaY, deltaX, deltaY,null);
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
