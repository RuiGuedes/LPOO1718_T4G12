package dkeep.gui;

import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JScrollBar;
import javax.swing.JSlider;

public class Settings {

	private JFrame frame;
	private JLabel title;
	private JLabel numberOfOgres;
	private JLabel guardType;
	private JComboBox guardTypeSelected;
	private JComboBox comboBox;

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
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void componentResized(ComponentEvent e) {
				// TODO Auto-generated method stub
				int width = frame.getWidth();
				int height = frame.getHeight();
				title.setFont(new Font("Scream Again", Font.PLAIN, (width + height) / 50));
				guardType.setFont(new Font("HACKED", Font.PLAIN, (width + height) / 50));
				numberOfOgres.setFont(new Font("HACKED", Font.PLAIN, (width + height) / 50));
				guardTypeSelected.setFont(new Font("HACKED", Font.PLAIN, (width + height) / 75));
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
		frame.requestFocus();
		
		DrawImage background = new DrawImage();
		BufferedImage image = null;
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/MainMenuBackground.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
		gbl_background.rowHeights = new int[]{-14, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_background.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_background.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		background.setLayout(gbl_background);
		
		title = new JLabel("Settings");
		title.setForeground(Color.ORANGE);
		title.setFont(new Font("Scream Again", Font.PLAIN, 30));
		GridBagConstraints gbc_title = new GridBagConstraints();
		gbc_title.weightx = 1.0;
		gbc_title.weighty = 1.0;
		gbc_title.gridwidth = 10;
		gbc_title.insets = new Insets(0, 0, 5, 5);
		gbc_title.gridx = 6;
		gbc_title.gridy = 3;
		background.add(title, gbc_title);
		
		guardType = new JLabel("Guard Type:");
		guardType.setForeground(Color.WHITE);
		guardType.setFont(new Font("HACKED", Font.PLAIN, 35));
		GridBagConstraints gbc_guardType = new GridBagConstraints();
		gbc_guardType.weighty = 1.0;
		gbc_guardType.anchor = GridBagConstraints.WEST;
		gbc_guardType.gridwidth = 6;
		gbc_guardType.insets = new Insets(0, 0, 5, 5);
		gbc_guardType.gridx = 6;
		gbc_guardType.gridy = 6;
		background.add(guardType, gbc_guardType);
		
		guardTypeSelected = new JComboBox();
		
		guardTypeSelected.setBackground(Color.WHITE);
		guardTypeSelected.setFont(new Font("Tahoma", Font.BOLD, 13));
		guardTypeSelected.setModel(new DefaultComboBoxModel(new String[] {"Rookie", "Drunken", "Suspicious"}));
		GridBagConstraints gbc_guardTypeSelected = new GridBagConstraints();
		gbc_guardTypeSelected.insets = new Insets(0, 0, 5, 5);
		gbc_guardTypeSelected.gridx = 12;
		gbc_guardTypeSelected.gridy = 6;
		background.add(guardTypeSelected, gbc_guardTypeSelected);
		
		numberOfOgres = new JLabel("Number of Ogres:");
		numberOfOgres.setForeground(Color.WHITE);
		numberOfOgres.setFont(new Font("HACKED", Font.PLAIN, 35));
		GridBagConstraints gbc_numberOfOgres = new GridBagConstraints();
		gbc_numberOfOgres.weighty = 1.0;
		gbc_numberOfOgres.anchor = GridBagConstraints.WEST;
		gbc_numberOfOgres.gridwidth = 7;
		gbc_numberOfOgres.insets = new Insets(0, 0, 5, 5);
		gbc_numberOfOgres.gridx = 6;
		gbc_numberOfOgres.gridy = 8;
		background.add(numberOfOgres, gbc_numberOfOgres);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 13;
		gbc_comboBox.gridy = 8;
		background.add(comboBox, gbc_comboBox);
		
	}

}
