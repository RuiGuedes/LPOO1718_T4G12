package dkeep.gui;

import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Settings {

	private JFrame frame;
	private JLabel title;
	private JLabel numberOfOgres;
	private JLabel guardType;
	private JComboBox<String> guardTypeSelected;
	private JComboBox<String> numberOfOgresSelected;
	private MyButton save;
	private JLabel keepLevel;
	private JComboBox<String> keepLevelSelected;
	
	public static int numberOfKeepLevels;
 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Settings window = new Settings();
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
	public Settings() {
		initialize();
		Charset charset = Charset.forName("US-ASCII");
		File settingsFile = new File("files/settings.txt");
		File numbersFile = new File("files/numberOfKeepLevels.txt");
		
		try (BufferedReader reader = Files.newBufferedReader(numbersFile.toPath(), charset)) {
			numberOfKeepLevels = Integer.parseInt(reader.readLine());
			keepLevelSelected.setModel(new DefaultComboBoxModel<String>(new String[] {"default"}));
			for(int i = 1; i < numberOfKeepLevels; i++) {
				keepLevelSelected.addItem("keep" + i);
			}
		}
		catch (IOException x) {
			System.err.format("IOException: %s%n", x);
		}
		
		try (BufferedReader reader = Files.newBufferedReader(settingsFile.toPath(), charset)) {
			keepLevelSelected.setSelectedItem(reader.readLine());
			guardTypeSelected.setSelectedItem(reader.readLine());
			numberOfOgresSelected.setSelectedItem(reader.readLine());
		}
		catch (IOException x) {
			System.err.format("IOException: %s%n", x);
		}
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 770, 549);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{752, 0};
		gridBagLayout.rowHeights = new int[]{72, 0};
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
				title.setFont(new Font("Scream Again", Font.PLAIN, (width + height) / 50));
				guardType.setFont(new Font("HACKED", Font.PLAIN, (width + height) / 50));
				numberOfOgres.setFont(new Font("HACKED", Font.PLAIN, (width + height) / 50));
				keepLevel.setFont(new Font("HACKED", Font.PLAIN, (width + height) / 50));
				guardTypeSelected.setFont(new Font("HACKED", Font.PLAIN, (width + height) / 75));
				numberOfOgresSelected.setFont(new Font("HACKED", Font.PLAIN, (width + height) / 75));
				keepLevelSelected.setFont(new Font("HACKED", Font.PLAIN, (width + height) / 75));
				save.setFont(new Font("Scream Again", Font.PLAIN, (width + height) / 75));
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
		gbl_background.columnWidths = new int[]{9, 7, 0, 0, 0, 0, 0, 41, 0, 0, 0, 0, 52, 73, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_background.rowHeights = new int[]{-14, 9, 0, 0, 0, 0, 0, 0, 0, 24, 0, 0, 0, 0, 0, 0, 0};
		gbl_background.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_background.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		background.setLayout(gbl_background);
		
		title = new JLabel("Settings");
		title.setForeground(Color.RED);
		title.setFont(new Font("Scream Again", Font.PLAIN, 30));
		GridBagConstraints gbc_title = new GridBagConstraints();
		gbc_title.weightx = 1.0;
		gbc_title.weighty = 1.0;
		gbc_title.gridwidth = 7;
		gbc_title.insets = new Insets(0, 0, 5, 5); 
		gbc_title.gridx = 8;
		gbc_title.gridy = 3;
		background.add(title, gbc_title);
		
		keepLevel = new JLabel("Keep Level:");
		keepLevel.setForeground(Color.WHITE);
		keepLevel.setFont(new Font("HACKED", Font.PLAIN, 35));
		GridBagConstraints gbc_keepLevel = new GridBagConstraints();
		gbc_keepLevel.weighty = 1.0;
		gbc_keepLevel.anchor = GridBagConstraints.WEST;
		gbc_keepLevel.gridwidth = 6;
		gbc_keepLevel.insets = new Insets(0, 0, 5, 5);
		gbc_keepLevel.gridx = 6;
		gbc_keepLevel.gridy = 6;
		background.add(keepLevel, gbc_keepLevel);
		
		keepLevelSelected = new JComboBox<String>();
		keepLevelSelected.setFont(new Font("HACKED", Font.PLAIN, 17));
		GridBagConstraints gbc_keepLevelSelected = new GridBagConstraints();
		gbc_keepLevelSelected.fill = GridBagConstraints.HORIZONTAL;
		gbc_keepLevelSelected.insets = new Insets(0, 0, 5, 5);
		gbc_keepLevelSelected.gridx = 12;
		gbc_keepLevelSelected.gridy = 6;
		background.add(keepLevelSelected, gbc_keepLevelSelected);
		
		guardType = new JLabel("Guard Type:");
		guardType.setForeground(Color.WHITE);
		guardType.setFont(new Font("HACKED", Font.PLAIN, 35));
		GridBagConstraints gbc_guardType = new GridBagConstraints();
		gbc_guardType.weighty = 1.0;
		gbc_guardType.anchor = GridBagConstraints.WEST;
		gbc_guardType.gridwidth = 6;
		gbc_guardType.insets = new Insets(0, 0, 5, 5);
		gbc_guardType.gridx = 6;
		gbc_guardType.gridy = 8;
		background.add(guardType, gbc_guardType);
		
		guardTypeSelected = new JComboBox<String>();
		guardTypeSelected.setBackground(Color.WHITE);
		guardTypeSelected.setFont(new Font("HACKED", Font.PLAIN, 17));
		guardTypeSelected.setModel(new DefaultComboBoxModel<String>(new String[] {"Rookie", "Drunken", "Suspicious"}));
		GridBagConstraints gbc_guardTypeSelected = new GridBagConstraints();
		gbc_guardTypeSelected.anchor = GridBagConstraints.WEST;
		gbc_guardTypeSelected.insets = new Insets(0, 0, 5, 5);
		gbc_guardTypeSelected.gridx = 12;
		gbc_guardTypeSelected.gridy = 8;
		background.add(guardTypeSelected, gbc_guardTypeSelected);
		
		save = new MyButton("Save");
		save.addActionListener(new ActionListener() {
			 
			@Override
			public void actionPerformed(ActionEvent e) {
				Charset charset = Charset.forName("US-ASCII");
				File settingsFile = new File("files/settings.txt");
				
				try (BufferedWriter writer = Files.newBufferedWriter(settingsFile.toPath(), charset)) {
					writer.write((String)keepLevelSelected.getSelectedItem() + "\n");
					writer.write((String)guardTypeSelected.getSelectedItem() + "\n");
					writer.write((String)numberOfOgresSelected.getSelectedItem() + "\n");
				} catch (IOException x) {
				    System.err.format("IOException: %s%n", x);
				}
				
				MainMenu newWindow = new MainMenu();
				if(frame.getExtendedState() == JFrame.MAXIMIZED_BOTH)
					newWindow.getFrame().setExtendedState(JFrame.MAXIMIZED_BOTH);
				else
					newWindow.getFrame().setSize(frame.getWidth(), frame.getHeight());
				newWindow.getFrame().setVisible(true);
				frame.dispose(); 
			}
		});
		
		numberOfOgres = new JLabel("Number of Enemies:");
		numberOfOgres.setForeground(Color.WHITE);
		numberOfOgres.setFont(new Font("HACKED", Font.PLAIN, 35));
		GridBagConstraints gbc_numberOfOgres = new GridBagConstraints();
		gbc_numberOfOgres.weighty = 1.0;
		gbc_numberOfOgres.anchor = GridBagConstraints.WEST;
		gbc_numberOfOgres.gridwidth = 7;
		gbc_numberOfOgres.insets = new Insets(0, 0, 5, 5);
		gbc_numberOfOgres.gridx = 6;
		gbc_numberOfOgres.gridy = 9; 
		background.add(numberOfOgres, gbc_numberOfOgres);
		
		numberOfOgresSelected = new JComboBox<String>();
		numberOfOgresSelected.setFont(new Font("HACKED", Font.PLAIN, 17));
		numberOfOgresSelected.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5"}));
		GridBagConstraints gbc_numberOfOgresSelected = new GridBagConstraints();
		gbc_numberOfOgresSelected.insets = new Insets(0, 0, 5, 5);
		gbc_numberOfOgresSelected.fill = GridBagConstraints.HORIZONTAL;
		gbc_numberOfOgresSelected.gridx = 13;
		gbc_numberOfOgresSelected.gridy = 9;
		background.add(numberOfOgresSelected, gbc_numberOfOgresSelected);
		GridBagConstraints gbc_save = new GridBagConstraints();
		gbc_save.weighty = 1.0;
		gbc_save.gridwidth = 3;
		gbc_save.insets = new Insets(0, 0, 0, 5);
		gbc_save.gridx = 18;
		gbc_save.gridy = 15;
		background.add(save, gbc_save);
		
	}
	
	public JFrame getFrame() {
		return frame;
	}

}
