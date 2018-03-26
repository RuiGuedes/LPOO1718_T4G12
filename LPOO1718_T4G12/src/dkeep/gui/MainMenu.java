package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;


public class MainMenu {

	private JFrame frame;
	private MyButton newGame;
	private MyButton settings;
	private MyButton levelEditor;
	private MyButton exit;
	
	private Settings settingsWindow;
	private LevelEditor levelEditorWindow;

	/**
	 * Launch the application.
	 */ 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu window = new MainMenu();
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
	public MainMenu() {
		initialize();
		settingsWindow = new Settings();
		levelEditorWindow = new LevelEditor();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 770, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
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
				newGame.setFont(new Font("Scream Again", Font.PLAIN, (width + height) / 50));
				settings.setFont(new Font("Scream Again", Font.PLAIN, (width + height) / 50));
				levelEditor.setFont(new Font("Scream Again", Font.PLAIN, (width + height) / 50));
				exit.setFont(new Font("Scream Again", Font.PLAIN, (width + height) / 50));
				frame.getContentPane().revalidate();
			}

			@Override
			public void componentMoved(ComponentEvent e) {
			}

			@Override
			public void componentHidden(ComponentEvent e) {
			}
		});

		DrawImage background = new DrawImage();
		BufferedImage image = null;
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/MainMenuBackground.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		background.setImage(image);
		GridBagConstraints gbc_background = new GridBagConstraints();
		gbc_background.fill = GridBagConstraints.BOTH;
		gbc_background.gridx = 0;
		gbc_background.gridy = 0;
		frame.getContentPane().add(background, gbc_background);
		GridBagLayout gbl_background = new GridBagLayout();
		gbl_background.columnWidths = new int[]{142, 371, 146, 0};
		gbl_background.rowHeights = new int[]{88, 64, 55, 50, 53, 67, 0};
		gbl_background.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_background.rowWeights = new double[]{1.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		background.setLayout(gbl_background);

		newGame = new MyButton(" New Game");
		newGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				NewGame newWindow = new NewGame();
				if(frame.getExtendedState() == JFrame.MAXIMIZED_BOTH)
					newWindow.getFrame().setExtendedState(JFrame.MAXIMIZED_BOTH);
				else
					newWindow.getFrame().setSize(frame.getWidth(), frame.getHeight());
				newWindow.getFrame().setVisible(true);
				frame.dispose();
			}
		});
		GridBagConstraints gbc_newGame = new GridBagConstraints();
		gbc_newGame.weightx = 1.0;
		gbc_newGame.weighty = 1.0;
		gbc_newGame.insets = new Insets(15, 0, 15, 5);
		gbc_newGame.gridx = 1;
		gbc_newGame.gridy = 1;
		background.add(newGame, gbc_newGame);


		settings = new MyButton("Settings");
		settings.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				settingsWindow = new Settings();
				if(frame.getExtendedState() == JFrame.MAXIMIZED_BOTH) 
					settingsWindow.getFrame().setExtendedState(JFrame.MAXIMIZED_BOTH);
				else 
					settingsWindow.getFrame().setSize(frame.getWidth(), frame.getHeight());
				settingsWindow.getFrame().setVisible(true);
				frame.dispose(); 
			}
		}); 
		GridBagConstraints gbc_settings = new GridBagConstraints();
		gbc_settings.weightx = 1.0;
		gbc_settings.weighty = 1.0;
		gbc_settings.fill = GridBagConstraints.BOTH;
		gbc_settings.insets = new Insets(0, 0, 5, 5);
		gbc_settings.gridx = 1;
		gbc_settings.gridy = 2;
		background.add(settings, gbc_settings);
 
		levelEditor = new MyButton("Level Editor");
		levelEditor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(frame.getExtendedState() == JFrame.MAXIMIZED_BOTH) 
					levelEditorWindow.getFrame().setExtendedState(JFrame.MAXIMIZED_BOTH);
				else 
					levelEditorWindow.getFrame().setSize(frame.getWidth(), frame.getHeight());
				levelEditorWindow.getFrame().setVisible(true);
				frame.dispose(); 
			}
		});
		GridBagConstraints gbc_levelEditor = new GridBagConstraints();
		gbc_levelEditor.weightx = 1.0;
		gbc_levelEditor.weighty = 1.0;
		gbc_levelEditor.fill = GridBagConstraints.BOTH;
		gbc_levelEditor.insets = new Insets(0, 0, 5, 5);
		gbc_levelEditor.gridx = 1;
		gbc_levelEditor.gridy = 3;
		background.add(levelEditor, gbc_levelEditor);

		exit = new MyButton("Exit");
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		GridBagConstraints gbc_exit = new GridBagConstraints();
		gbc_exit.weightx = 1.0;
		gbc_exit.weighty = 1.0;
		gbc_exit.insets = new Insets(0, 0, 5, 5);
		gbc_exit.fill = GridBagConstraints.BOTH;
		gbc_exit.gridx = 1;
		gbc_exit.gridy = 4;
		background.add(exit, gbc_exit);

	}

	public JFrame getFrame() {
		return frame;
	}
}
