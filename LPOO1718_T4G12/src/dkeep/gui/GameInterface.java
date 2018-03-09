package dkeep.gui;

import dkeep.logic.*;

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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSplitPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import net.miginfocom.swing.MigLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.CardLayout;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameInterface {

	private JFrame frame;
	private JTextField ogresNumber;

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
		gridBagLayout.rowHeights = new int[]{134, 395, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 2;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		frame.getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{34, 136, 45, 23, 0};
		gbl_panel.rowHeights = new int[]{51, 52, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel Ogres = new JLabel("Number of Ogres");
		Ogres.setFont(new Font("Courier New", Font.BOLD, 11));
		GridBagConstraints gbc_Ogres = new GridBagConstraints();
		gbc_Ogres.anchor = GridBagConstraints.WEST;
		gbc_Ogres.fill = GridBagConstraints.VERTICAL;
		gbc_Ogres.insets = new Insets(0, 0, 5, 5);
		gbc_Ogres.gridx = 1;
		gbc_Ogres.gridy = 0;
		panel.add(Ogres, gbc_Ogres);
		
		ogresNumber = new JTextField();
		GridBagConstraints gbc_ogresNumber = new GridBagConstraints();
		gbc_ogresNumber.insets = new Insets(0, 0, 5, 5);
		gbc_ogresNumber.fill = GridBagConstraints.HORIZONTAL;
		gbc_ogresNumber.gridx = 2;
		gbc_ogresNumber.gridy = 0;
		panel.add(ogresNumber, gbc_ogresNumber);
		ogresNumber.setColumns(1);
		
		JLabel Guard = new JLabel("Guard personality");
		Guard.setFont(new Font("Courier New", Font.BOLD, 11));
		GridBagConstraints gbc_Guard = new GridBagConstraints();
		gbc_Guard.anchor = GridBagConstraints.WEST;
		gbc_Guard.insets = new Insets(0, 0, 0, 5);
		gbc_Guard.gridx = 1;
		gbc_Guard.gridy = 1;
		panel.add(Guard, gbc_Guard);
		
		JComboBox guardPersonality = new JComboBox();
		guardPersonality.setFont(new Font("Courier New", Font.PLAIN, 11));
		guardPersonality.setModel(new DefaultComboBoxModel(new String[] {"Rookie", "Drunken", "Suspicious"}));
		guardPersonality.setMaximumRowCount(3);
		GridBagConstraints gbc_guardPersonality = new GridBagConstraints();
		gbc_guardPersonality.fill = GridBagConstraints.HORIZONTAL;
		gbc_guardPersonality.gridwidth = 2;
		gbc_guardPersonality.gridx = 2;
		gbc_guardPersonality.gridy = 1;
		panel.add(guardPersonality, gbc_guardPersonality);
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 0, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 1;
		frame.getContentPane().add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{17, 391, 0};
		gbl_panel_2.rowHeights = new int[]{377, 20, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 255, 255));
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 1;
		gbc_panel_3.gridy = 0;
		panel_2.add(panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{161, 68, 0};
		gbl_panel_3.rowHeights = new int[]{22, 0};
		gbl_panel_3.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		JTextArea mapa = new JTextArea();
		mapa.setFont(new Font("Courier New", Font.BOLD, 13));
		GridBagConstraints gbc_mapa = new GridBagConstraints();
		gbc_mapa.insets = new Insets(0, 0, 0, 5);
		gbc_mapa.anchor = GridBagConstraints.NORTHWEST;
		gbc_mapa.gridx = 0;
		gbc_mapa.gridy = 0;
		panel_3.add(mapa, gbc_mapa);
		
		JLabel messages = new JLabel("You can start a new game");
		messages.setFont(new Font("Courier New", Font.BOLD, 11));
		GridBagConstraints gbc_messages = new GridBagConstraints();
		gbc_messages.anchor = GridBagConstraints.SOUTHWEST;
		gbc_messages.gridx = 1;
		gbc_messages.gridy = 1;
		panel_2.add(messages, gbc_messages);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 1;
		frame.getContentPane().add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{78, 89, 81, 0};
		gbl_panel_1.rowHeights = new int[]{23, 120, 0, 0, 0, 108, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JButton Up = new JButton("Up");
		Up.setEnabled(false);
		GridBagConstraints gbc_Up = new GridBagConstraints();
		gbc_Up.insets = new Insets(0, 0, 5, 5);
		gbc_Up.gridx = 1;
		gbc_Up.gridy = 2;
		panel_1.add(Up, gbc_Up);
		
		JButton Left = new JButton("Left");
		Left.setEnabled(false);
		GridBagConstraints gbc_Left = new GridBagConstraints();
		gbc_Left.anchor = GridBagConstraints.EAST;
		gbc_Left.insets = new Insets(0, 0, 5, 5);
		gbc_Left.gridx = 0;
		gbc_Left.gridy = 3;
		panel_1.add(Left, gbc_Left);
		
		JButton Right = new JButton("Right");
		Right.setEnabled(false);
		GridBagConstraints gbc_Right = new GridBagConstraints();
		gbc_Right.anchor = GridBagConstraints.WEST;
		gbc_Right.insets = new Insets(0, 0, 5, 0);
		gbc_Right.gridx = 2;
		gbc_Right.gridy = 3;
		panel_1.add(Right, gbc_Right);
		
		JButton Down = new JButton("Down");
		Down.setEnabled(false);
		GridBagConstraints gbc_Down = new GridBagConstraints();
		gbc_Down.insets = new Insets(0, 0, 5, 5);
		gbc_Down.gridx = 1;
		gbc_Down.gridy = 4;
		panel_1.add(Down, gbc_Down);
		
		JButton Exit = new JButton("Exit");
		GridBagConstraints gbc_Exit = new GridBagConstraints();
		gbc_Exit.insets = new Insets(0, 0, 0, 5);
		gbc_Exit.gridx = 1;
		gbc_Exit.gridy = 6;
		panel_1.add(Exit, gbc_Exit);
		
		JButton NewGame = new JButton("New Game");
		NewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//Enable variables
				Up.setEnabled(true);
				Down.setEnabled(true);
				Left.setEnabled(true);
				Right.setEnabled(true);
				
				//Variables
				Game.LEVEL = 1;
				GameMap gameMap = new GameMap();
				Game firstGame = new Game(gameMap, guardPersonality.getToolTipText(), Integer.parseInt(ogresNumber.getText()));
				
				//Set text
				mapa.setText(firstGame.mapToString(firstGame.updateMap(firstGame.map.getMap())));
				
				//Disable variables
				ogresNumber.setEnabled(false);
				guardPersonality.setEnabled(false);
				
				//Set message text
				messages.setText("You can play now");
			}
		});
		GridBagConstraints gbc_NewGame = new GridBagConstraints();
		gbc_NewGame.insets = new Insets(0, 0, 5, 5);
		gbc_NewGame.anchor = GridBagConstraints.NORTHWEST;
		gbc_NewGame.gridx = 1;
		gbc_NewGame.gridy = 0;
		panel_1.add(NewGame, gbc_NewGame);
	}
}
