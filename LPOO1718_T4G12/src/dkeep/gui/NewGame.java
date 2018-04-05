package dkeep.gui;

import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;


import javax.swing.SwingConstants;

import dkeep.logic.Game;
import dkeep.logic.GameMap;
import dkeep.logic.Lock;
import dkeep.logic.Game.GameState;

public class NewGame {

	private JFrame frame;
	private Playground playground;
	private DrawImage controllers;
	private JLabel title;
	private MyButton exit;
	private JLabel gameStatus;
	private JLabel info;
	
	public Game game;
	public MyButton upButton;
	public MyButton leftButton;
	public MyButton rightButton;
	public MyButton downButton;
	public String keepLevel;
	public String guardPersonality;
	public int ogresNumber;
	public char[][] dungeon;
	public char[][] keep;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewGame window = new NewGame();
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
	public NewGame() {
		initialize();
		initializeDrawImageMembers();
		initializeGameMembers();
		initializeLevelsMap();
		startGame();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 770, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{507, 250, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		frame.addComponentListener(new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent e) {
			}
			
			@Override
			public void componentResized(ComponentEvent e) {
				int width = frame.getWidth();
				int height = frame.getHeight();
				title.setFont(new Font("Scream Again", Font.PLAIN, (width + height) / 65));
				upButton.setFont(new Font("Scream Again", Font.PLAIN, (width + height) / 70));
				downButton.setFont(new Font("Scream Again", Font.PLAIN, (width + height) / 70));
				leftButton.setFont(new Font("Scream Again", Font.PLAIN, (width + height) / 70));
				rightButton.setFont(new Font("Scream Again", Font.PLAIN, (width + height) / 70));
				gameStatus.setFont(new Font("Scream Again", Font.PLAIN, (width + height) / 65));
				info.setFont(new Font("Scream Again", Font.PLAIN, (width + height) / 145));
				exit.setFont(new Font("Scream Again", Font.PLAIN, (width + height) / 65));
				frame.getContentPane().revalidate();
			}
			
			@Override
			public void componentMoved(ComponentEvent e) {
			}
			
			@Override
			public void componentHidden(ComponentEvent e) {
			}
		});
		
		playground = new Playground(this);
		playground.setBackground(Color.MAGENTA);
		GridBagConstraints gbc_playground = new GridBagConstraints();
		gbc_playground.weighty = 1.0;
		gbc_playground.weightx = 1.0;
		gbc_playground.fill = GridBagConstraints.BOTH;
		gbc_playground.gridx = 0;
		gbc_playground.gridy = 0;
		frame.getContentPane().add(playground, gbc_playground);
		GridBagLayout gbl_playground = new GridBagLayout();
		gbl_playground.columnWidths = new int[]{0};
		gbl_playground.rowHeights = new int[]{0};
		gbl_playground.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_playground.rowWeights = new double[]{Double.MIN_VALUE};
		playground.setLayout(gbl_playground);
		
		controllers = new DrawImage();
		controllers.setOpaque(false);
		GridBagConstraints gbc_controllers = new GridBagConstraints();
		gbc_controllers.weightx = 0.3;
		gbc_controllers.fill = GridBagConstraints.BOTH;
		gbc_controllers.gridx = 1;
		gbc_controllers.gridy = 0;
		frame.getContentPane().add(controllers, gbc_controllers);
		GridBagLayout gbl_controllers = new GridBagLayout();
		gbl_controllers.columnWidths = new int[]{62, 62, 62, 64, 0};
		gbl_controllers.rowHeights = new int[]{72, 0, 0, 0, 51, 44, 41, 98, 0, 0};
		gbl_controllers.columnWeights = new double[]{1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_controllers.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		controllers.setLayout(gbl_controllers);
		
		title = new JLabel("Controllers");
		title.setForeground(Color.RED);
		title.setFont(new Font("Scream Again", Font.PLAIN, 20));
		GridBagConstraints gbc_title = new GridBagConstraints();
		gbc_title.weighty = 1.0;
		gbc_title.insets = new Insets(0, 0, 5, 0);
		gbc_title.gridwidth = 4;
		gbc_title.gridx = 0;
		gbc_title.gridy = 0;
		controllers.add(title, gbc_title);
		
		upButton = new MyButton("New button");
		upButton.setFont(new Font("Scream Again", Font.PLAIN, 15));
		upButton.setText("Up");
		upButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				game.hero.heroMovement('w', game.updateMap(game.map.getMap()));	
				automaticSteps();
			}
		});
		GridBagConstraints gbc_upButton = new GridBagConstraints();
		gbc_upButton.gridwidth = 2;
		gbc_upButton.insets = new Insets(0, 0, 5, 5);
		gbc_upButton.gridx = 1;
		gbc_upButton.gridy = 1;
		controllers.add(upButton, gbc_upButton);
		
		leftButton = new  MyButton("New button");
		leftButton.setFont(new Font("Scream Again", Font.PLAIN, 15));
		leftButton.setText("Left");
		leftButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				game.hero.heroMovement('a', game.updateMap(game.map.getMap()));	
				automaticSteps();
			}
		});
		GridBagConstraints gbc_leftButton = new GridBagConstraints();
		gbc_leftButton.gridwidth = 2;
		gbc_leftButton.insets = new Insets(0, 0, 5, 5);
		gbc_leftButton.gridx = 0;
		gbc_leftButton.gridy = 2;
		controllers.add(leftButton, gbc_leftButton);
		
		rightButton = new MyButton("New button");
		rightButton.setFont(new Font("Scream Again", Font.PLAIN, 15));
		rightButton.setText("Right");
		rightButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				game.hero.heroMovement('d', game.updateMap(game.map.getMap()));	
				automaticSteps();
			}
		});
		GridBagConstraints gbc_rightButton = new GridBagConstraints();
		gbc_rightButton.gridwidth = 2;
		gbc_rightButton.insets = new Insets(0, 0, 5, 0);
		gbc_rightButton.gridx = 2;
		gbc_rightButton.gridy = 2;
		controllers.add(rightButton, gbc_rightButton);
		
		downButton = new MyButton("New button");
		downButton.setFont(new Font("Scream Again", Font.PLAIN, 15));
		downButton.setText("Down");
		downButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				game.hero.heroMovement('s', game.updateMap(game.map.getMap()));	
				automaticSteps();
			}
		});
		GridBagConstraints gbc_downButton = new GridBagConstraints();
		gbc_downButton.gridwidth = 2;
		gbc_downButton.insets = new Insets(0, 0, 5, 5);
		gbc_downButton.gridx = 1;
		gbc_downButton.gridy = 3;
		controllers.add(downButton, gbc_downButton);
		
		gameStatus = new JLabel("Game Status");
		gameStatus.setForeground(Color.LIGHT_GRAY);
		gameStatus.setFont(new Font("Scream Again", Font.ITALIC, 20));
		GridBagConstraints gbc_gameStatus = new GridBagConstraints();
		gbc_gameStatus.gridwidth = 4;
		gbc_gameStatus.insets = new Insets(0, 0, 5, 0);
		gbc_gameStatus.gridx = 0;
		gbc_gameStatus.gridy = 5;
		controllers.add(gameStatus, gbc_gameStatus);
		
		info = new JLabel("You can start a new game");
		info.setForeground(Color.WHITE);
		info.setFont(new Font("HACKED", Font.ITALIC, 17));
		info.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_info = new GridBagConstraints();
		gbc_info.gridwidth = 4;
		gbc_info.insets = new Insets(0, 0, 5, 5);
		gbc_info.gridx = 0;
		gbc_info.gridy = 6;
		controllers.add(info, gbc_info);
		
		exit = new MyButton("Exit");
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainMenu newWindow = new MainMenu();
				if(frame.getExtendedState() == JFrame.MAXIMIZED_BOTH)
					newWindow.getFrame().setExtendedState(JFrame.MAXIMIZED_BOTH);
				else
					newWindow.getFrame().setSize(frame.getWidth(), frame.getHeight());
				newWindow.getFrame().setVisible(true);
				frame.dispose();
			}
		});
		GridBagConstraints gbc_exit = new GridBagConstraints();
		gbc_exit.weighty = 1.0;
		gbc_exit.gridwidth = 2;
		gbc_exit.insets = new Insets(0, 0, 0, 5);
		gbc_exit.gridx = 1;
		gbc_exit.gridy = 8;
		controllers.add(exit, gbc_exit);
	}
	
	public void initializeDrawImageMembers() {
		try {
			controllers.setImage(ImageIO.read(getClass().getResourceAsStream("/backgroundInteraction.jpg")));
		} catch (IOException exc) {
			exc.printStackTrace();
		} 
	}
	
	public void initializeGameMembers() {
		Charset charset = Charset.forName("US-ASCII");
		File settingsFile = new File("files/settings.txt");
		
		try (BufferedReader reader = Files.newBufferedReader(settingsFile.toPath(), charset)) {
			keepLevel = reader.readLine();
			guardPersonality = reader.readLine();
			ogresNumber = Integer.parseInt(reader.readLine());
		}
		catch (IOException x) {
			System.err.format("IOException: %s%n", x);
		}
	}
	
	public void initializeLevelsMap() {
		Charset charset = Charset.forName("US-ASCII");
		File settingsFile = new File("files/" + keepLevel + ".txt");
		
		try (BufferedReader reader = Files.newBufferedReader(settingsFile.toPath(), charset)) {
			int size = Integer.parseInt(reader.readLine());
			keep = new char[size][size];
			for(int i = 0; i < keep.length; i++) {
				keep[i] = reader.readLine().toCharArray();
			}
		}
		catch (IOException x) {
			System.err.format("IOException: %s%n", x);
		}
		
		dungeon = new char[][] { 
			{'X','X','X','X','X','X','X','X','X','X'},
			{'X','H',' ',' ','I',' ','X',' ','G','X'},
			{'X','X','X',' ','X','X','X',' ',' ','X'}, 
			{'X',' ','I',' ','I',' ','X',' ',' ','X'}, 
			{'X','X','X',' ','X','X','X',' ',' ','X'}, 
			{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'}, 
			{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X','X','X',' ','X','X','X','X',' ','X'}, 
			{'X',' ','I',' ','I',' ','X','k',' ','X'}, 
			{'X','X','X','X','X','X','X','X','X','X'} };
	}
	
	public void startGame() { 
		Game.LEVEL = 1;
		GameMap gameMap = new GameMap(dungeon);
		game = new Game(gameMap,guardPersonality, ogresNumber);
		playground.setPlayground(game.updateMap(game.map.getMap()));
		info.setText("You can play now");
	}
	
	public void automaticSteps() {
		if((Game.gameState == Game.GameState.VICTORY) && (Game.LEVEL == 1)) {
			Game.LEVEL = 2;
			Game.gameState = GameState.PLAYING;
			GameMap gameMap = new GameMap(keep);
			game = new Game(gameMap,guardPersonality, ogresNumber);
			playground.setPlayground(game.updateMap(game.map.getMap()));
			Lock.lockState = 'k';
			playground.repaint();
			return;
		}
		else if((Game.gameState == Game.GameState.VICTORY) && (Game.LEVEL == 2)) {
			info.setText("Victory");
			disableElements();
			return;
		}

		if(Game.LEVEL == 1) {
			if(game.guard[game.guardRouting].guardMovement()) {
				if(game.guardRouting == 2)
					game.guardRouting = 0;
				else
					game.guardRouting++;
			}
		}
		else if(Game.LEVEL == 2) {
			for(int i = 0; i < game.horde; i++)
				game.ogre.get(i).ogreMovement(game.hero, game.updateMap(game.map.getMap()));
		}

		playground.setPlayground(game.updateMap(game.map.getMap()));
		game.checkGameStatus();
	
		if(Game.gameState == GameState.GAMEOVER) {
			info.setText("Game over");
			disableElements();
		}
	}
	
	public void disableElements() {
		playground.setEnabled(false);
		upButton.setEnabled(false);
		downButton.setEnabled(false);
		leftButton.setEnabled(false);
		rightButton.setEnabled(false);
	}
	
	public JFrame getFrame() {
		return frame;
	}
}
