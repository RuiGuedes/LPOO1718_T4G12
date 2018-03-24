package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.SwingConstants;

public class LevelEditor {

	private JFrame frame;
	public static BufferedImage imageSelected;
	private BufferedImage backgroundInteraction; 
	private JLabel title;
	private JPanel background;
	private DrawImage elementsPanel;
	private MapEditor mapEditor; 
	private DrawImage wall;
	private DrawImage hero;
	private DrawImage key;
	private DrawImage ogre;
	private DrawImage door;
	private DrawImage club;
	private MyButton save;
	private MyButton exit;

	
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
		gridBagLayout.columnWidths = new int[]{461, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
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
				title.setFont(new Font("Scream Again", Font.PLAIN, (width + height) / 55));
//				guardType.setFont(new Font("HACKED", Font.PLAIN, (width + height) / 50));
//				numberOfOgres.setFont(new Font("HACKED", Font.PLAIN, (width + height) / 50));
//				keepLevel.setFont(new Font("HACKED", Font.PLAIN, (width + height) / 50));
//				guardTypeSelected.setFont(new Font("HACKED", Font.PLAIN, (width + height) / 75));
//				numberOfOgresSelected.setFont(new Font("HACKED", Font.PLAIN, (width + height) / 75));
//				keepLevelSelected.setFont(new Font("HACKED", Font.PLAIN, (width + height) / 75));
				save.setFont(new Font("Scream Again", Font.PLAIN, (width + height) / 60));
				exit.setFont(new Font("Scream Again", Font.PLAIN, (width + height) / 60));
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
		
		background = new JPanel();
		GridBagConstraints gbc_background = new GridBagConstraints();
		gbc_background.fill = GridBagConstraints.BOTH;
		gbc_background.gridx = 0;
		gbc_background.gridy = 0;
		frame.getContentPane().add(background, gbc_background);
		GridBagLayout gbl_background = new GridBagLayout();
		gbl_background.columnWidths = new int[]{494, 250, 0};
		gbl_background.rowHeights = new int[]{395, 0};
		gbl_background.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_background.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		background.setLayout(gbl_background);
		
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
				if(imageSelected != null) {
					mapEditor.map[e.getX()/deltaX][e.getY()/deltaY] = imageSelected;
					mapEditor.repaint();
				}
				mapEditor.x = e.getX();
				mapEditor.y = e.getY();
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
		gbc_mapEditor.fill = GridBagConstraints.BOTH;
		gbc_mapEditor.gridx = 0;
		gbc_mapEditor.gridy = 0;
		background.add(mapEditor, gbc_mapEditor);
		
		elementsPanel = new DrawImage();
		elementsPanel.setOpaque(false);
		try {
			backgroundInteraction = ImageIO.read(getClass().getResourceAsStream("/backgroundInteraction.jpg"));
			elementsPanel.setImage(backgroundInteraction);
		} catch (IOException exception) {
			// TODO Auto-generated catch block
			exception.printStackTrace();
		} 
		GridBagConstraints gbc_elementsPanel = new GridBagConstraints();
		gbc_elementsPanel.fill = GridBagConstraints.BOTH;
		gbc_elementsPanel.gridx = 1;
		gbc_elementsPanel.gridy = 0;
		background.add(elementsPanel, gbc_elementsPanel);
		GridBagLayout gbl_elementsPanel = new GridBagLayout();
		gbl_elementsPanel.columnWidths = new int[]{110, 110, 0};
		gbl_elementsPanel.rowHeights = new int[]{72, 22, 110, 110, 106, 101, 0};
		gbl_elementsPanel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_elementsPanel.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		elementsPanel.setLayout(gbl_elementsPanel);
		
		title = new JLabel("Elements");
		title.setForeground(Color.RED);
		title.setFont(new Font("Scream Again", Font.PLAIN, 20));
		GridBagConstraints gbc_title = new GridBagConstraints();
		gbc_title.gridwidth = 2;
		gbc_title.insets = new Insets(15, 0, 5, 0);
		gbc_title.gridx = 0;
		gbc_title.gridy = 0;
		elementsPanel.add(title, gbc_title);
		
		hero = new DrawImage();
		hero.setOpaque(false);
		try {
			hero.setImage(ImageIO.read(getClass().getResourceAsStream("/hero.png")));
		} catch (IOException exc) {
			// TODO Auto-generated catch block
			exc.printStackTrace();
		} 
		GridBagConstraints gbc_hero = new GridBagConstraints();
		gbc_hero.insets = new Insets(5, 10, 5, 5);
		gbc_hero.fill = GridBagConstraints.BOTH;
		gbc_hero.gridx = 0;
		gbc_hero.gridy = 2;
		elementsPanel.add(hero, gbc_hero);
		
		key = new DrawImage();
		key.setOpaque(false);
		try {
			key.setImage(ImageIO.read(getClass().getResourceAsStream("/key.png")));
		} catch (IOException exc) {
			// TODO Auto-generated catch block
			exc.printStackTrace();
		} 
		GridBagConstraints gbc_key = new GridBagConstraints();
		gbc_key.weighty = 1.0;
		gbc_key.weightx = 1.0;
		gbc_key.insets = new Insets(5, 5, 5, 10);
		gbc_key.fill = GridBagConstraints.BOTH;
		gbc_key.gridx = 1;
		gbc_key.gridy = 2;
		elementsPanel.add(key, gbc_key);
		
		ogre = new DrawImage();
		ogre.setOpaque(false);
		try {
			ogre.setImage(ImageIO.read(getClass().getResourceAsStream("/ogre.png")));
		} catch (IOException exc) {
			// TODO Auto-generated catch block
			exc.printStackTrace();
		} 
		GridBagConstraints gbc_ogre = new GridBagConstraints();
		gbc_ogre.weighty = 1.0;
		gbc_ogre.weightx = 1.0;
		gbc_ogre.insets = new Insets(5, 10, 5, 5);
		gbc_ogre.fill = GridBagConstraints.BOTH;
		gbc_ogre.gridx = 0;
		gbc_ogre.gridy = 3;
		elementsPanel.add(ogre, gbc_ogre);
		
		door = new DrawImage();
		door.setOpaque(false);
		try {
			door.setImage(ImageIO.read(getClass().getResourceAsStream("/door.png")));
		} catch (IOException exc) {
			// TODO Auto-generated catch block
			exc.printStackTrace();
		} 
		GridBagConstraints gbc_door = new GridBagConstraints();
		gbc_door.weighty = 1.0;
		gbc_door.weightx = 1.0;
		gbc_door.insets = new Insets(5, 5, 5, 10);
		gbc_door.fill = GridBagConstraints.BOTH;
		gbc_door.gridx = 1;
		gbc_door.gridy = 3;
		elementsPanel.add(door, gbc_door);
		
		club = new DrawImage();
		club.setOpaque(false);
		try {
			club.setImage(ImageIO.read(getClass().getResourceAsStream("/club.png")));
		} catch (IOException exc) {
			// TODO Auto-generated catch block
			exc.printStackTrace();
		} 
		GridBagConstraints gbc_club = new GridBagConstraints();
		gbc_club.insets = new Insets(5, 10, 5, 5);
		gbc_club.fill = GridBagConstraints.BOTH;
		gbc_club.gridx = 0;
		gbc_club.gridy = 4;
		elementsPanel.add(club, gbc_club);
		
		wall = new DrawImage();
		wall.setOpaque(false);
		try {
			wall.setImage(ImageIO.read(getClass().getResourceAsStream("/wall.jpg")));
		} catch (IOException exc) {
			// TODO Auto-generated catch block
			exc.printStackTrace();
		} 
		GridBagConstraints gbc_wall = new GridBagConstraints();
		gbc_wall.weighty = 1.0;
		gbc_wall.weightx = 1.0;
		gbc_wall.insets = new Insets(5, 5, 5, 10);
		gbc_wall.fill = GridBagConstraints.BOTH;
		gbc_wall.gridx = 1;
		gbc_wall.gridy = 4;
		elementsPanel.add(wall, gbc_wall);
		GridBagLayout gbl_wall = new GridBagLayout();
		gbl_wall.columnWidths = new int[]{97, 0};
		gbl_wall.rowHeights = new int[]{25, 0};
		gbl_wall.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_wall.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		wall.setLayout(gbl_wall);
		
		save = new MyButton("Save");
		save.setForeground(Color.ORANGE);
		save.setFont(new Font("Scream Again", Font.PLAIN, 20));
		GridBagConstraints gbc_save = new GridBagConstraints();
		gbc_save.insets = new Insets(0, 0, 0, 5);
		gbc_save.gridx = 0;
		gbc_save.gridy = 5;
		elementsPanel.add(save, gbc_save);
		
		exit = new MyButton("Exit");
		exit.setForeground(Color.ORANGE);
		exit.setFont(new Font("Scream Again", Font.PLAIN, 20));
		GridBagConstraints gbc_exit = new GridBagConstraints();
		gbc_exit.gridx = 1;
		gbc_exit.gridy = 5;
		elementsPanel.add(exit, gbc_exit);
	}

}
