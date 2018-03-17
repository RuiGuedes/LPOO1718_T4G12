package dkeep.gui;

import dkeep.logic.*;
import dkeep.logic.Game.GameState;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.DropMode;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSplitPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.InputVerifier;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import net.miginfocom.swing.MigLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.CardLayout;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;


public class GameInterface {

	/////////////
	// PRIVATE //
	/////////////
	private JFrame frame;
	private JTextField ogresNumber;
	private JComboBox guardPersonality;
	private JLabel info;
	private JButton Down;
	private JButton Up;
	private JButton Left;
	private JButton Right;
	private JButton NewGame;
	private Playground playground;

	////////////
	// PUBLIC //
	////////////
	public Game game;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameInterface window = new GameInterface();
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
	public GameInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 688, 604);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{427, 245, 0};
		gridBagLayout.rowHeights = new int[]{134, 429, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);

		JPanel SettingsPanel = new JPanel();
		GridBagConstraints gbc_SettingsPanel = new GridBagConstraints();
		gbc_SettingsPanel.gridwidth = 2;
		gbc_SettingsPanel.insets = new Insets(0, 0, 5, 0);
		gbc_SettingsPanel.fill = GridBagConstraints.BOTH;
		gbc_SettingsPanel.gridx = 0;
		gbc_SettingsPanel.gridy = 0;
		frame.getContentPane().add(SettingsPanel, gbc_SettingsPanel);
		GridBagLayout gbl_SettingsPanel = new GridBagLayout();
		gbl_SettingsPanel.columnWidths = new int[]{34, 136, 45, 23, 0};
		gbl_SettingsPanel.rowHeights = new int[]{51, 52, 0};
		gbl_SettingsPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_SettingsPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		SettingsPanel.setLayout(gbl_SettingsPanel);

		JLabel Ogres = new JLabel("Number of Ogres");
		Ogres.setFont(new Font("Courier New", Font.BOLD, 11));
		GridBagConstraints gbc_Ogres = new GridBagConstraints();
		gbc_Ogres.anchor = GridBagConstraints.WEST;
		gbc_Ogres.fill = GridBagConstraints.VERTICAL;
		gbc_Ogres.insets = new Insets(0, 0, 5, 5);
		gbc_Ogres.gridx = 1;
		gbc_Ogres.gridy = 0;
		SettingsPanel.add(Ogres, gbc_Ogres);

		ogresNumber = new JTextField();
		GridBagConstraints gbc_ogresNumber = new GridBagConstraints();
		gbc_ogresNumber.insets = new Insets(0, 0, 5, 5);
		gbc_ogresNumber.fill = GridBagConstraints.HORIZONTAL;
		gbc_ogresNumber.gridx = 2;
		gbc_ogresNumber.gridy = 0;
		SettingsPanel.add(ogresNumber, gbc_ogresNumber);
		ogresNumber.setColumns(1);

		JLabel Guard = new JLabel("Guard personality");
		Guard.setFont(new Font("Courier New", Font.BOLD, 11));
		GridBagConstraints gbc_Guard = new GridBagConstraints();
		gbc_Guard.anchor = GridBagConstraints.WEST;
		gbc_Guard.insets = new Insets(0, 0, 0, 5);
		gbc_Guard.gridx = 1;
		gbc_Guard.gridy = 1;
		SettingsPanel.add(Guard, gbc_Guard);

		guardPersonality = new JComboBox();
		guardPersonality.setFont(new Font("Courier New", Font.PLAIN, 11));
		guardPersonality.setModel(new DefaultComboBoxModel(new String[] {"Rookie", "Drunken", "Suspicious"}));
		guardPersonality.setMaximumRowCount(3);
		GridBagConstraints gbc_guardPersonality = new GridBagConstraints();
		gbc_guardPersonality.fill = GridBagConstraints.HORIZONTAL;
		gbc_guardPersonality.gridwidth = 2;
		gbc_guardPersonality.gridx = 2;
		gbc_guardPersonality.gridy = 1;
		SettingsPanel.add(guardPersonality, gbc_guardPersonality);

		JPanel GamePanel = new JPanel();
		GridBagConstraints gbc_GamePanel = new GridBagConstraints();
		gbc_GamePanel.weightx = 1.0;
		gbc_GamePanel.weighty = 1.0;
		gbc_GamePanel.insets = new Insets(0, 0, 0, 5);
		gbc_GamePanel.fill = GridBagConstraints.BOTH;
		gbc_GamePanel.gridx = 0;
		gbc_GamePanel.gridy = 1;
		frame.getContentPane().add(GamePanel, gbc_GamePanel);
		GridBagLayout gbl_GamePanel = new GridBagLayout();
		gbl_GamePanel.columnWidths = new int[]{17, 391, 0};
		gbl_GamePanel.rowHeights = new int[]{349, 20, 0};
		gbl_GamePanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_GamePanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		GamePanel.setLayout(gbl_GamePanel);

		JPanel PlaygroundPanel = new JPanel();
		PlaygroundPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc_PlaygroundPanel = new GridBagConstraints();
		gbc_PlaygroundPanel.weighty = 1.0;
		gbc_PlaygroundPanel.insets = new Insets(0, 0, 5, 0);
		gbc_PlaygroundPanel.fill = GridBagConstraints.BOTH;
		gbc_PlaygroundPanel.gridx = 1;
		gbc_PlaygroundPanel.gridy = 0;
		GamePanel.add(PlaygroundPanel, gbc_PlaygroundPanel);
		GridBagLayout gbl_PlaygroundPanel = new GridBagLayout();
		gbl_PlaygroundPanel.columnWidths = new int[]{161, 0};
		gbl_PlaygroundPanel.rowHeights = new int[]{22, 0};
		gbl_PlaygroundPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_PlaygroundPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		PlaygroundPanel.setLayout(gbl_PlaygroundPanel);
		
		playground = new Playground();
		GridBagConstraints gbc_playground = new GridBagConstraints();
		gbc_playground.fill = GridBagConstraints.BOTH;
		gbc_playground.gridx = 0;
		gbc_playground.gridy = 0;
		PlaygroundPanel.add(playground, gbc_playground);

		info = new JLabel("You can start a new game");
		info.setFont(new Font("Courier New", Font.BOLD, 11));
		GridBagConstraints gbc_info = new GridBagConstraints();
		gbc_info.weightx = 1.0;
		gbc_info.weighty = 0.1;
		gbc_info.anchor = GridBagConstraints.WEST;
		gbc_info.gridx = 1;
		gbc_info.gridy = 1;
		GamePanel.add(info, gbc_info);

		JPanel InteractionPanel = new JPanel();
		GridBagConstraints gbc_InteractionPanel = new GridBagConstraints();
		gbc_InteractionPanel.weighty = 1.0;
		gbc_InteractionPanel.fill = GridBagConstraints.BOTH;
		gbc_InteractionPanel.gridx = 1;
		gbc_InteractionPanel.gridy = 1;
		frame.getContentPane().add(InteractionPanel, gbc_InteractionPanel);
		GridBagLayout gbl_InteractionPanel = new GridBagLayout();
		gbl_InteractionPanel.columnWidths = new int[]{78, 89, 81, 0};
		gbl_InteractionPanel.rowHeights = new int[]{23, 34, 55, 56, 0, 78, 0, 0};
		gbl_InteractionPanel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_InteractionPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		InteractionPanel.setLayout(gbl_InteractionPanel);

		Up = new JButton("Up");
		Up.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Moves the hero in the respective direction
				game.hero.heroMovement('w', game.updateMap(game.map.getMap()));	

				//Update all needed variables
				automaticSteps();
			}
		});
		Up.setEnabled(false);
		GridBagConstraints gbc_Up = new GridBagConstraints();
		gbc_Up.insets = new Insets(0, 0, 5, 5);
		gbc_Up.gridx = 1;
		gbc_Up.gridy = 2;
		InteractionPanel.add(Up, gbc_Up);

		Left = new JButton("Left");
		Left.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Moves the hero in the respective direction
				game.hero.heroMovement('a', game.updateMap(game.map.getMap()));	

				//Update all needed variables
				automaticSteps();
			}
		});
		Left.setEnabled(false);
		GridBagConstraints gbc_Left = new GridBagConstraints();
		gbc_Left.weightx = 1.0;
		gbc_Left.anchor = GridBagConstraints.EAST;
		gbc_Left.insets = new Insets(0, 0, 5, 5);
		gbc_Left.gridx = 0;
		gbc_Left.gridy = 3;
		InteractionPanel.add(Left, gbc_Left);

		Right = new JButton("Right");
		Right.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Moves the hero in the respective direction
				game.hero.heroMovement('d', game.updateMap(game.map.getMap()));	

				//Update all needed variables
				automaticSteps();
			}
		});
		Right.setEnabled(false);
		GridBagConstraints gbc_Right = new GridBagConstraints();
		gbc_Right.weightx = 1.0;
		gbc_Right.anchor = GridBagConstraints.WEST;
		gbc_Right.insets = new Insets(0, 0, 5, 0);
		gbc_Right.gridx = 2;
		gbc_Right.gridy = 3;
		InteractionPanel.add(Right, gbc_Right);

		Down = new JButton("Down");
		Down.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Moves the hero in the respective direction
				game.hero.heroMovement('s', game.updateMap(game.map.getMap()));	

				//Update all needed variables
				automaticSteps();
			}
		});
		Down.setEnabled(false);
		GridBagConstraints gbc_Down = new GridBagConstraints();
		gbc_Down.insets = new Insets(0, 0, 5, 5);
		gbc_Down.gridx = 1;
		gbc_Down.gridy = 4;
		InteractionPanel.add(Down, gbc_Down);

		JButton Exit = new JButton("Exit");
		Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		GridBagConstraints gbc_Exit = new GridBagConstraints();
		gbc_Exit.weighty = 3.0;
		gbc_Exit.insets = new Insets(0, 0, 0, 5);
		gbc_Exit.gridx = 1;
		gbc_Exit.gridy = 6;
		InteractionPanel.add(Exit, gbc_Exit);

		NewGame = new JButton("New Game");
		NewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					int numberOfOgres = Integer.parseInt(ogresNumber.getText());
					
					if((numberOfOgres > 5) || (numberOfOgres < 1))
						throw new NumberFormatException();
				}
				catch (NumberFormatException exception){
					info.setText("Number of ogres: Invalid input !");
					return;
				}

				startGame();
			}
		});
		GridBagConstraints gbc_NewGame = new GridBagConstraints();
		gbc_NewGame.weighty = 2.0;
		gbc_NewGame.insets = new Insets(0, 0, 5, 5);
		gbc_NewGame.anchor = GridBagConstraints.NORTHWEST;
		gbc_NewGame.gridx = 1;
		gbc_NewGame.gridy = 0;
		InteractionPanel.add(NewGame, gbc_NewGame);
	}

	public void startGame() { 

		//Enable variables
		Up.setEnabled(true);
		Down.setEnabled(true);
		Left.setEnabled(true);
		Right.setEnabled(true);
		playground.setEnabled(true);

		//Game Variables
		Game.LEVEL = 1;
		GameMap gameMap = new GameMap();
		game = new Game(gameMap, (String)guardPersonality.getSelectedItem(), Integer.parseInt(ogresNumber.getText()));

		//Set text
		//playground.setText(game.mapToString(game.updateMap(game.map.getMap())));
		playground.setPlayground(game.updateMap(game.map.getMap()));
		
		//Disable variables
		ogresNumber.setEnabled(false);
		guardPersonality.setEnabled(false);
		NewGame.setEnabled(false);

		//Set message text
		info.setText("You can play now");
	}

	public void automaticSteps() {

		//Check if level is complete
		if((Game.gameState == Game.GameState.VICTORY) && (Game.LEVEL == 1)) {
			Game.LEVEL = 2;
			Game.gameState = GameState.PLAYING;
			GameMap gameMap = new GameMap();
			game = new Game(gameMap,(String)guardPersonality.getSelectedItem(), Integer.parseInt(ogresNumber.getText()));
			Lock.lockState = 'k';
		}
		else if((Game.gameState == Game.GameState.VICTORY) && (Game.LEVEL == 2)) {
			info.setText("Victory !");
			playground.setEnabled(false);
			Up.setEnabled(false);
			Down.setEnabled(false);
			Left.setEnabled(false);
			Right.setEnabled(false);
			NewGame.setEnabled(true);
			guardPersonality.setEnabled(true);
			ogresNumber.setEnabled(true);
			return;
		}

		//Executes enemy movement
		if(Game.LEVEL == 1) 
			game.guard[game.guardRouting].guardMovement();	
		else if(Game.LEVEL == 2) {
			for(int i = 0; i < game.horde; i++)
				game.ogre.get(i).ogreMovement(game.hero.x, game.hero.y, game.updateMap(game.map.getMap()));
		}

		//Update playground text
		//playground.setText(game.mapToString(game.updateMap(game.map.getMap())));
		playground.setPlayground(game.updateMap(game.map.getMap()));
		
		//Check the status game in order to continue playing or not
		if(Game.LEVEL == 1)
			game.checkGameStatus("Guard");
		else
			game.checkGameStatus("Ogre");

		//Check game status after enemy movement
		if(Game.gameState == GameState.GAMEOVER) {
			info.setText("Game over !");
			playground.setEnabled(false);
			Up.setEnabled(false);
			Down.setEnabled(false);
			Left.setEnabled(false);
			Right.setEnabled(false);
			NewGame.setEnabled(true);
			guardPersonality.setEnabled(true);
			ogresNumber.setEnabled(true);
		}

	}

	
}
