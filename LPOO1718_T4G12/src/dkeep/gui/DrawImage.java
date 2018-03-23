package dkeep.gui;

import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class DrawImage extends JPanel {
	
	private BufferedImage image;
	
	public DrawImage() {
		// TODO Auto-generated constructor stub
	} 
	
	public void setImage(BufferedImage image) {
		this.image = image;
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
}
