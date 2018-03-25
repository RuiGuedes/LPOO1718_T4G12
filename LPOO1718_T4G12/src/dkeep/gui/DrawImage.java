package dkeep.gui;

import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class DrawImage extends JPanel implements MouseListener {
	
	private BufferedImage image;
	private char element;
	
	public DrawImage() { 
		// TODO Auto-generated constructor stub
		this.addMouseListener(this);
	} 
	
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	public void setElement(char element) {
		this.element = element;
	}

	public DrawImage(LayoutManager arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public DrawImage(boolean arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public DrawImage(LayoutManager arg0, boolean arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
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
		LevelEditor.imageSelected = element;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
