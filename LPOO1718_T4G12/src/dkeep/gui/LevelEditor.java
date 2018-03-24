package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;

public class LevelEditor {

	private JFrame frame;
	private JLabel title;
	private MapEditor mapEditor;
	private BufferedImage selected;
	private int size = 6;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LevelEditor window = new LevelEditor();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LevelEditor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setBounds(100, 100, 771, 549);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{461, 176, 0};
		gridBagLayout.rowHeights = new int[]{27, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		frame.addComponentListener(new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void componentResized(ComponentEvent e) {
				// TODO Auto-generated method stub
				// TODO Auto-generated method stub
				int width = frame.getWidth();
				int height = frame.getHeight();
				title.setFont(new Font("Scream Again", Font.PLAIN, (width + height) / 50));
//				guardType.setFont(new Font("HACKED", Font.PLAIN, (width + height) / 50));
//				numberOfOgres.setFont(new Font("HACKED", Font.PLAIN, (width + height) / 50));
//				keepLevel.setFont(new Font("HACKED", Font.PLAIN, (width + height) / 50));
//				guardTypeSelected.setFont(new Font("HACKED", Font.PLAIN, (width + height) / 75));
//				numberOfOgresSelected.setFont(new Font("HACKED", Font.PLAIN, (width + height) / 75));
//				keepLevelSelected.setFont(new Font("HACKED", Font.PLAIN, (width + height) / 75));
//				save.setFont(new Font("Scream Again", Font.PLAIN, (width + height) / 75));
				frame.getContentPane().revalidate();
			}
			
			@Override
			public void componentMoved(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		mapEditor = new MapEditor();
		mapEditor.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				int deltaX = (int) Math.ceil((float)mapEditor.getWidth()/mapEditor.map.length);
				int deltaY = (int) Math.ceil((float)mapEditor.getHeight()/mapEditor.map[0].length);
				if(selected != null) {
					mapEditor.map[e.getX()/deltaX][e.getY()/deltaY] = selected;
					mapEditor.repaint();
				}
				mapEditor.x = e.getX();
				mapEditor.y = e.getY();
//				panel.repaint();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		GridBagConstraints gbc_mapEditor = new GridBagConstraints();
		gbc_mapEditor.insets = new Insets(0, 0, 0, 5);
		gbc_mapEditor.fill = GridBagConstraints.BOTH;
		gbc_mapEditor.gridx = 0;
		gbc_mapEditor.gridy = 0;
		frame.getContentPane().add(mapEditor, gbc_mapEditor);
		GridBagLayout gbl_mapEditor = new GridBagLayout();
		gbl_mapEditor.columnWidths = new int[]{0};
		gbl_mapEditor.rowHeights = new int[]{0};
		gbl_mapEditor.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_mapEditor.rowWeights = new double[]{Double.MIN_VALUE};
		mapEditor.setLayout(gbl_mapEditor);
		
		DrawImage elementsPanel = new DrawImage();
		BufferedImage image = null; 
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/MainMenuBackground.png"));
			selected = ImageIO.read(getClass().getResourceAsStream("/floor.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		elementsPanel.setImage(image);
		GridBagConstraints gbc_elementsPanel = new GridBagConstraints();
		gbc_elementsPanel.weightx = 0.5;
		gbc_elementsPanel.fill = GridBagConstraints.BOTH;
		gbc_elementsPanel.gridx = 1;
		gbc_elementsPanel.gridy = 0;
		frame.getContentPane().add(elementsPanel, gbc_elementsPanel);
		GridBagLayout gbl_elementsPanel = new GridBagLayout();
		gbl_elementsPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_elementsPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_elementsPanel.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_elementsPanel.rowWeights = new double[]{0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		elementsPanel.setLayout(gbl_elementsPanel);
		
		title = new JLabel("Elements");
		title.setForeground(Color.RED);
		title.setFont(new Font("Scream Again", Font.PLAIN, 20));
		GridBagConstraints gbc_title = new GridBagConstraints();
		gbc_title.weightx = 1.0;
		gbc_title.weighty = 0.2;
		gbc_title.gridwidth = 9;
		gbc_title.insets = new Insets(0, 0, 5, 5);
		gbc_title.gridx = 0;
		gbc_title.gridy = 0;
		elementsPanel.add(title, gbc_title);
	}

}
