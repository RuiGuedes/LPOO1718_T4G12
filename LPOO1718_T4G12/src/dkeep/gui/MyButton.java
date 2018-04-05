package dkeep.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

public class MyButton extends JButton implements MouseListener {

	public MyButton() {
	}

	public MyButton(Icon icon) {
		super(icon);
	}
 
	public MyButton(String text) {
		super(text);
		this.setBorderPainted(false); 
		this.setContentAreaFilled(false); 
		this.setFocusPainted(false); 
		this.setOpaque(false);
		this.setFont(new Font("Scream Again", Font.PLAIN, 20));
		this.setForeground(Color.ORANGE);
		this.addMouseListener(this);
	}

	public MyButton(Action a) {
		super(a);
	}

	public MyButton(String text, Icon icon) {
		super(text, icon);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		this.setForeground(Color.RED);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.setForeground(Color.ORANGE);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

}
