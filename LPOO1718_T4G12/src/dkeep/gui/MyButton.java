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
		// TODO Auto-generated constructor stub
	}

	public MyButton(Icon icon) {
		super(icon);
		// TODO Auto-generated constructor stub
	}

	public MyButton(String text) {
		super(text);
		// TODO Auto-generated constructor stub
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
		// TODO Auto-generated constructor stub
	}

	public MyButton(String text, Icon icon) {
		super(text, icon);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		this.setForeground(Color.RED);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		this.setForeground(Color.ORANGE);
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
