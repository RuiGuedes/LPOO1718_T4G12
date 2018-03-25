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
	private boolean available;
	
	public DrawImage() { 
		this.addMouseListener(this);
		available = true;
	}  
	
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	public void setElement(char element) {
		this.element = element;
	}

	public DrawImage(LayoutManager arg0) {
		super(arg0);
	}

	public DrawImage(boolean arg0) {
		super(arg0);
	}

	public DrawImage(LayoutManager arg0, boolean arg1) {
		super(arg0, arg1);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
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
		if(available)
			LevelEditor.elementSelected = element;
	} 

	@Override
	public void mouseReleased(MouseEvent e) {
	}
	
	public void setAvailable() {
		available = false;
	}
}
