package start;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class ElevatorMainWindow2 extends JFrame implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean undoReady = false;
	private Vector<JTextField[]> jtf2Dvector = new Vector<JTextField[]>();
	private Vector<Double[]> double2DVector = new Vector<Double[]>();
	private JLabel[] jlabelHourArray = new JLabel[24];
	private Vector<JLabel> jlabelFloorVector = new Vector<JLabel>();
	private JButton fillAllButton = new JButton("Fill All");
	private Vector<JButton> fillButtonVector = new Vector<JButton>();
	private Vector<JButton> clearButtonVector = new Vector<JButton>();
	private JButton undoButton = new JButton("Undo Last Change");
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_16;
	private JTextField textField_17;
	private JTextField textField_18;
	private JTextField textField_19;
	private JTextField textField_20;
	private JTextField textField_21;
	private JTextField textField_22;
	private JTextField textField_23;
	private JTextField textField_24;
	private JTextField textField_25;
	private JTextField textField_26;
	private JTextField textField_27;
	private JTextField textField_28;
	private JTextField textField_29;
	private JTextField textField_30;
	private JTextField textField_31;
	private JTextField textField_32;
	private JTextField textField_33;
	private JTextField textField_34;
	private JTextField textField_35;
	private JPanel panel_16;
	private JPanel panel_11;
	
	public ElevatorMainWindow2() {
	
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(800,600));
		this.setSize(800, 600);
		
		fillAllButton.addActionListener(this);
		undoButton.addActionListener(this);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.WEST);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.NORTH);
		
		JLabel lblElevatorSimulation = new JLabel("Elevator Simulation!!!");
		panel_1.add(lblElevatorSimulation);
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.SOUTH);
		
		JPanel panel_3 = new JPanel();
		getContentPane().add(panel_3, BorderLayout.EAST);
		
		JPanel panel_4 = new JPanel();
		getContentPane().add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_4.add(panel_5,BorderLayout.WEST);
		
		JLabel lblWest_1 = new JLabel("WEST");
		panel_5.add(lblWest_1);
		
		JPanel panel_6 = new JPanel();
		panel_4.add(panel_6, BorderLayout.NORTH);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JLabel lblVersion = new JLabel("Version 1.0");
		panel_6.add(lblVersion);
		
		JPanel panel_17 = new JPanel();
		panel_6.add(panel_17, BorderLayout.SOUTH);
		
		JPanel panel_7 = new JPanel();
		panel_4.add(panel_7, BorderLayout.EAST);
		
		JLabel lblNewLabel = new JLabel("New label");
		panel_7.add(lblNewLabel);
		
		JPanel panel_8 = new JPanel();
		panel_4.add(panel_8, BorderLayout.SOUTH);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setForeground(Color.MAGENTA);
		panel_4.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel_9 = new JPanel();
		panel_9.setLayout(new BorderLayout());
		JPanel panel_9_center = new JPanel();
		JPanel panel_9_north = new JPanel();
		JPanel panel_9_south = new JPanel();
		JPanel panel_9_east = new JPanel();
		JPanel panel_9_west = new JPanel();
		tabbedPane.addTab("Decision Variables", null, panel_9, null);
		
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setViewportView(panel_9_center);
		
		panel_9.add(scrollPane2, BorderLayout.CENTER);
		panel_9.add(panel_9_north, BorderLayout.NORTH);
		panel_9.add(panel_9_west, BorderLayout.WEST);
		panel_9.add(panel_9_east, BorderLayout.EAST);
		panel_9.add(panel_9_south, BorderLayout.SOUTH);
		
		panel_9_north.add(new JLabel("NORTH"));
		panel_9_south.add(new JLabel("SOUTH"));
		panel_9_east.add(new JLabel("EAST"));
		panel_9_west.add(new JLabel("WEST"));
		
		
		GridBagLayout gbl_panel_9 = new GridBagLayout();
		gbl_panel_9.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_9.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_9.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_9.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_9_center.setLayout(gbl_panel_9);
		
		JLabel lblVariable = new JLabel("Variable");
		lblVariable.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblVariable = new GridBagConstraints();
		gbc_lblVariable.insets = new Insets(0, 0, 5, 5);
		gbc_lblVariable.gridx = 0;
		gbc_lblVariable.gridy = 0;
		panel_9_center.add(lblVariable, gbc_lblVariable);
		
		JLabel lblFixed = new JLabel("Fixed?");
		lblFixed.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblFixed = new GridBagConstraints();
		gbc_lblFixed.insets = new Insets(0, 0, 5, 5);
		gbc_lblFixed.gridx = 1;
		gbc_lblFixed.gridy = 0;
		panel_9_center.add(lblFixed, gbc_lblFixed);
		
		JLabel lblMin = new JLabel("MIN");
		lblMin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblMin = new GridBagConstraints();
		gbc_lblMin.insets = new Insets(0, 0, 5, 5);
		gbc_lblMin.gridx = 2;
		gbc_lblMin.gridy = 0;
		panel_9_center.add(lblMin, gbc_lblMin);
		
		JLabel lblMax = new JLabel("MAX");
		lblMax.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblMax = new GridBagConstraints();
		gbc_lblMax.insets = new Insets(0, 0, 5, 5);
		gbc_lblMax.gridx = 3;
		gbc_lblMax.gridy = 0;
		panel_9_center.add(lblMax, gbc_lblMax);
		
		JLabel lblStepsize = new JLabel("Stepsize");
		lblStepsize.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblStepsize = new GridBagConstraints();
		gbc_lblStepsize.insets = new Insets(0, 0, 5, 0);
		gbc_lblStepsize.gridx = 4;
		gbc_lblStepsize.gridy = 0;
		panel_9_center.add(lblStepsize, gbc_lblStepsize);
		
		JLabel lblNumberOfFloors = new JLabel("Number of Floors");
		GridBagConstraints gbc_lblNumberOfFloors = new GridBagConstraints();
		gbc_lblNumberOfFloors.anchor = GridBagConstraints.EAST;
		gbc_lblNumberOfFloors.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumberOfFloors.gridx = 0;
		gbc_lblNumberOfFloors.gridy = 1;
		panel_9_center.add(lblNumberOfFloors, gbc_lblNumberOfFloors);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("");
		GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
		gbc_chckbxNewCheckBox.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxNewCheckBox.gridx = 1;
		gbc_chckbxNewCheckBox.gridy = 1;
		panel_9_center.add(chckbxNewCheckBox, gbc_chckbxNewCheckBox);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.gridx = 2;
		gbc_textField_1.gridy = 1;
		panel_9_center.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.gridx = 3;
		gbc_textField_2.gridy = 1;
		panel_9_center.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 5, 0);
		gbc_textField_3.gridx = 4;
		gbc_textField_3.gridy = 1;
		panel_9_center.add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNumberOfElevators = new JLabel("Number Of Elevators");
		GridBagConstraints gbc_lblNumberOfElevators = new GridBagConstraints();
		gbc_lblNumberOfElevators.anchor = GridBagConstraints.EAST;
		gbc_lblNumberOfElevators.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumberOfElevators.gridx = 0;
		gbc_lblNumberOfElevators.gridy = 3;
		panel_9_center.add(lblNumberOfElevators, gbc_lblNumberOfElevators);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("");
		GridBagConstraints gbc_chckbxNewCheckBox_1 = new GridBagConstraints();
		gbc_chckbxNewCheckBox_1.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxNewCheckBox_1.gridx = 1;
		gbc_chckbxNewCheckBox_1.gridy = 3;
		panel_9_center.add(chckbxNewCheckBox_1, gbc_chckbxNewCheckBox_1);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 3;
		panel_9_center.add(textField, gbc_textField);
		textField.setColumns(10);
		
		textField_14 = new JTextField();
		GridBagConstraints gbc_textField_14 = new GridBagConstraints();
		gbc_textField_14.insets = new Insets(0, 0, 5, 5);
		gbc_textField_14.gridx = 3;
		gbc_textField_14.gridy = 3;
		panel_9_center.add(textField_14, gbc_textField_14);
		textField_14.setColumns(10);
		
		textField_25 = new JTextField();
		GridBagConstraints gbc_textField_25 = new GridBagConstraints();
		gbc_textField_25.insets = new Insets(0, 0, 5, 0);
		gbc_textField_25.gridx = 4;
		gbc_textField_25.gridy = 3;
		panel_9_center.add(textField_25, gbc_textField_25);
		textField_25.setColumns(10);
		
		JLabel lblElevatorCapacity = new JLabel("Elevator Capacity");
		GridBagConstraints gbc_lblElevatorCapacity = new GridBagConstraints();
		gbc_lblElevatorCapacity.anchor = GridBagConstraints.EAST;
		gbc_lblElevatorCapacity.insets = new Insets(0, 0, 5, 5);
		gbc_lblElevatorCapacity.gridx = 0;
		gbc_lblElevatorCapacity.gridy = 4;
		panel_9_center.add(lblElevatorCapacity, gbc_lblElevatorCapacity);
		
		JCheckBox chckbxNewCheckBox_2 = new JCheckBox("");
		GridBagConstraints gbc_chckbxNewCheckBox_2 = new GridBagConstraints();
		gbc_chckbxNewCheckBox_2.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxNewCheckBox_2.gridx = 1;
		gbc_chckbxNewCheckBox_2.gridy = 4;
		panel_9_center.add(chckbxNewCheckBox_2, gbc_chckbxNewCheckBox_2);
		
		textField_4 = new JTextField();
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.insets = new Insets(0, 0, 5, 5);
		gbc_textField_4.gridx = 2;
		gbc_textField_4.gridy = 4;
		panel_9_center.add(textField_4, gbc_textField_4);
		textField_4.setColumns(10);
		
		textField_15 = new JTextField();
		GridBagConstraints gbc_textField_15 = new GridBagConstraints();
		gbc_textField_15.insets = new Insets(0, 0, 5, 5);
		gbc_textField_15.gridx = 3;
		gbc_textField_15.gridy = 4;
		panel_9_center.add(textField_15, gbc_textField_15);
		textField_15.setColumns(10);
		
		textField_26 = new JTextField();
		GridBagConstraints gbc_textField_26 = new GridBagConstraints();
		gbc_textField_26.insets = new Insets(0, 0, 5, 0);
		gbc_textField_26.gridx = 4;
		gbc_textField_26.gridy = 4;
		panel_9_center.add(textField_26, gbc_textField_26);
		textField_26.setColumns(10);
		
		JLabel lblVar = new JLabel("Var 1");
		GridBagConstraints gbc_lblVar = new GridBagConstraints();
		gbc_lblVar.anchor = GridBagConstraints.EAST;
		gbc_lblVar.insets = new Insets(0, 0, 5, 5);
		gbc_lblVar.gridx = 0;
		gbc_lblVar.gridy = 5;
		panel_9_center.add(lblVar, gbc_lblVar);
		
		JCheckBox chckbxNewCheckBox_3 = new JCheckBox("");
		GridBagConstraints gbc_chckbxNewCheckBox_3 = new GridBagConstraints();
		gbc_chckbxNewCheckBox_3.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxNewCheckBox_3.gridx = 1;
		gbc_chckbxNewCheckBox_3.gridy = 5;
		panel_9_center.add(chckbxNewCheckBox_3, gbc_chckbxNewCheckBox_3);
		
		textField_5 = new JTextField();
		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
		gbc_textField_5.insets = new Insets(0, 0, 5, 5);
		gbc_textField_5.gridx = 2;
		gbc_textField_5.gridy = 5;
		panel_9_center.add(textField_5, gbc_textField_5);
		textField_5.setColumns(10);
		
		textField_16 = new JTextField();
		GridBagConstraints gbc_textField_16 = new GridBagConstraints();
		gbc_textField_16.insets = new Insets(0, 0, 5, 5);
		gbc_textField_16.gridx = 3;
		gbc_textField_16.gridy = 5;
		panel_9_center.add(textField_16, gbc_textField_16);
		textField_16.setColumns(10);
		
		textField_27 = new JTextField();
		GridBagConstraints gbc_textField_27 = new GridBagConstraints();
		gbc_textField_27.insets = new Insets(0, 0, 5, 0);
		gbc_textField_27.gridx = 4;
		gbc_textField_27.gridy = 5;
		panel_9_center.add(textField_27, gbc_textField_27);
		textField_27.setColumns(10);
		
		JLabel lblVar_1 = new JLabel("Var 2");
		GridBagConstraints gbc_lblVar_1 = new GridBagConstraints();
		gbc_lblVar_1.anchor = GridBagConstraints.EAST;
		gbc_lblVar_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblVar_1.gridx = 0;
		gbc_lblVar_1.gridy = 6;
		panel_9_center.add(lblVar_1, gbc_lblVar_1);
		
		JCheckBox chckbxNewCheckBox_4 = new JCheckBox("");
		GridBagConstraints gbc_chckbxNewCheckBox_4 = new GridBagConstraints();
		gbc_chckbxNewCheckBox_4.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxNewCheckBox_4.gridx = 1;
		gbc_chckbxNewCheckBox_4.gridy = 6;
		panel_9_center.add(chckbxNewCheckBox_4, gbc_chckbxNewCheckBox_4);
		
		textField_6 = new JTextField();
		GridBagConstraints gbc_textField_6 = new GridBagConstraints();
		gbc_textField_6.insets = new Insets(0, 0, 5, 5);
		gbc_textField_6.gridx = 2;
		gbc_textField_6.gridy = 6;
		panel_9_center.add(textField_6, gbc_textField_6);
		textField_6.setColumns(10);
		
		textField_17 = new JTextField();
		GridBagConstraints gbc_textField_17 = new GridBagConstraints();
		gbc_textField_17.insets = new Insets(0, 0, 5, 5);
		gbc_textField_17.gridx = 3;
		gbc_textField_17.gridy = 6;
		panel_9_center.add(textField_17, gbc_textField_17);
		textField_17.setColumns(10);
		
		textField_28 = new JTextField();
		GridBagConstraints gbc_textField_28 = new GridBagConstraints();
		gbc_textField_28.insets = new Insets(0, 0, 5, 0);
		gbc_textField_28.gridx = 4;
		gbc_textField_28.gridy = 6;
		panel_9_center.add(textField_28, gbc_textField_28);
		textField_28.setColumns(10);
		
		JLabel lblVar_2 = new JLabel("Var 3");
		GridBagConstraints gbc_lblVar_2 = new GridBagConstraints();
		gbc_lblVar_2.anchor = GridBagConstraints.EAST;
		gbc_lblVar_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblVar_2.gridx = 0;
		gbc_lblVar_2.gridy = 7;
		panel_9_center.add(lblVar_2, gbc_lblVar_2);
		
		JCheckBox chckbxNewCheckBox_5 = new JCheckBox("");
		GridBagConstraints gbc_chckbxNewCheckBox_5 = new GridBagConstraints();
		gbc_chckbxNewCheckBox_5.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxNewCheckBox_5.gridx = 1;
		gbc_chckbxNewCheckBox_5.gridy = 7;
		panel_9_center.add(chckbxNewCheckBox_5, gbc_chckbxNewCheckBox_5);
		
		textField_7 = new JTextField();
		GridBagConstraints gbc_textField_7 = new GridBagConstraints();
		gbc_textField_7.insets = new Insets(0, 0, 5, 5);
		gbc_textField_7.gridx = 2;
		gbc_textField_7.gridy = 7;
		panel_9_center.add(textField_7, gbc_textField_7);
		textField_7.setColumns(10);
		
		textField_18 = new JTextField();
		GridBagConstraints gbc_textField_18 = new GridBagConstraints();
		gbc_textField_18.insets = new Insets(0, 0, 5, 5);
		gbc_textField_18.gridx = 3;
		gbc_textField_18.gridy = 7;
		panel_9_center.add(textField_18, gbc_textField_18);
		textField_18.setColumns(10);
		
		textField_29 = new JTextField();
		GridBagConstraints gbc_textField_29 = new GridBagConstraints();
		gbc_textField_29.insets = new Insets(0, 0, 5, 0);
		gbc_textField_29.gridx = 4;
		gbc_textField_29.gridy = 7;
		panel_9_center.add(textField_29, gbc_textField_29);
		textField_29.setColumns(10);
		
		JLabel lblVar_3 = new JLabel("Var 4");
		GridBagConstraints gbc_lblVar_3 = new GridBagConstraints();
		gbc_lblVar_3.anchor = GridBagConstraints.EAST;
		gbc_lblVar_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblVar_3.gridx = 0;
		gbc_lblVar_3.gridy = 8;
		panel_9_center.add(lblVar_3, gbc_lblVar_3);
		
		JCheckBox chckbxNewCheckBox_6 = new JCheckBox("");
		GridBagConstraints gbc_chckbxNewCheckBox_6 = new GridBagConstraints();
		gbc_chckbxNewCheckBox_6.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxNewCheckBox_6.gridx = 1;
		gbc_chckbxNewCheckBox_6.gridy = 8;
		panel_9_center.add(chckbxNewCheckBox_6, gbc_chckbxNewCheckBox_6);
		
		textField_8 = new JTextField();
		GridBagConstraints gbc_textField_8 = new GridBagConstraints();
		gbc_textField_8.insets = new Insets(0, 0, 5, 5);
		gbc_textField_8.gridx = 2;
		gbc_textField_8.gridy = 8;
		panel_9_center.add(textField_8, gbc_textField_8);
		textField_8.setColumns(10);
		
		textField_19 = new JTextField();
		GridBagConstraints gbc_textField_19 = new GridBagConstraints();
		gbc_textField_19.insets = new Insets(0, 0, 5, 5);
		gbc_textField_19.gridx = 3;
		gbc_textField_19.gridy = 8;
		panel_9_center.add(textField_19, gbc_textField_19);
		textField_19.setColumns(10);
		
		textField_30 = new JTextField();
		GridBagConstraints gbc_textField_30 = new GridBagConstraints();
		gbc_textField_30.insets = new Insets(0, 0, 5, 0);
		gbc_textField_30.gridx = 4;
		gbc_textField_30.gridy = 8;
		panel_9_center.add(textField_30, gbc_textField_30);
		textField_30.setColumns(10);
		
		JLabel lblVar_4 = new JLabel("Var 5");
		GridBagConstraints gbc_lblVar_4 = new GridBagConstraints();
		gbc_lblVar_4.anchor = GridBagConstraints.EAST;
		gbc_lblVar_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblVar_4.gridx = 0;
		gbc_lblVar_4.gridy = 9;
		panel_9_center.add(lblVar_4, gbc_lblVar_4);
		
		JCheckBox chckbxNewCheckBox_7 = new JCheckBox("");
		GridBagConstraints gbc_chckbxNewCheckBox_7 = new GridBagConstraints();
		gbc_chckbxNewCheckBox_7.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxNewCheckBox_7.gridx = 1;
		gbc_chckbxNewCheckBox_7.gridy = 9;
		panel_9_center.add(chckbxNewCheckBox_7, gbc_chckbxNewCheckBox_7);
		
		textField_9 = new JTextField();
		GridBagConstraints gbc_textField_9 = new GridBagConstraints();
		gbc_textField_9.insets = new Insets(0, 0, 5, 5);
		gbc_textField_9.gridx = 2;
		gbc_textField_9.gridy = 9;
		panel_9_center.add(textField_9, gbc_textField_9);
		textField_9.setColumns(10);
		
		textField_20 = new JTextField();
		GridBagConstraints gbc_textField_20 = new GridBagConstraints();
		gbc_textField_20.insets = new Insets(0, 0, 5, 5);
		gbc_textField_20.gridx = 3;
		gbc_textField_20.gridy = 9;
		panel_9_center.add(textField_20, gbc_textField_20);
		textField_20.setColumns(10);
		
		textField_31 = new JTextField();
		GridBagConstraints gbc_textField_31 = new GridBagConstraints();
		gbc_textField_31.insets = new Insets(0, 0, 5, 0);
		gbc_textField_31.gridx = 4;
		gbc_textField_31.gridy = 9;
		panel_9_center.add(textField_31, gbc_textField_31);
		textField_31.setColumns(10);
		
		JLabel lblVar_5 = new JLabel("Var 6");
		GridBagConstraints gbc_lblVar_5 = new GridBagConstraints();
		gbc_lblVar_5.anchor = GridBagConstraints.EAST;
		gbc_lblVar_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblVar_5.gridx = 0;
		gbc_lblVar_5.gridy = 10;
		panel_9_center.add(lblVar_5, gbc_lblVar_5);
		
		JCheckBox chckbxNewCheckBox_8 = new JCheckBox("");
		GridBagConstraints gbc_chckbxNewCheckBox_8 = new GridBagConstraints();
		gbc_chckbxNewCheckBox_8.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxNewCheckBox_8.gridx = 1;
		gbc_chckbxNewCheckBox_8.gridy = 10;
		panel_9_center.add(chckbxNewCheckBox_8, gbc_chckbxNewCheckBox_8);
		
		textField_10 = new JTextField();
		GridBagConstraints gbc_textField_10 = new GridBagConstraints();
		gbc_textField_10.insets = new Insets(0, 0, 5, 5);
		gbc_textField_10.gridx = 2;
		gbc_textField_10.gridy = 10;
		panel_9_center.add(textField_10, gbc_textField_10);
		textField_10.setColumns(10);
		
		textField_21 = new JTextField();
		GridBagConstraints gbc_textField_21 = new GridBagConstraints();
		gbc_textField_21.insets = new Insets(0, 0, 5, 5);
		gbc_textField_21.gridx = 3;
		gbc_textField_21.gridy = 10;
		panel_9_center.add(textField_21, gbc_textField_21);
		textField_21.setColumns(10);
		
		textField_32 = new JTextField();
		GridBagConstraints gbc_textField_32 = new GridBagConstraints();
		gbc_textField_32.insets = new Insets(0, 0, 5, 0);
		gbc_textField_32.gridx = 4;
		gbc_textField_32.gridy = 10;
		panel_9_center.add(textField_32, gbc_textField_32);
		textField_32.setColumns(10);
		
		JLabel lblVar_6 = new JLabel("Var 7");
		GridBagConstraints gbc_lblVar_6 = new GridBagConstraints();
		gbc_lblVar_6.anchor = GridBagConstraints.EAST;
		gbc_lblVar_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblVar_6.gridx = 0;
		gbc_lblVar_6.gridy = 11;
		panel_9_center.add(lblVar_6, gbc_lblVar_6);
		
		JCheckBox chckbxNewCheckBox_9 = new JCheckBox("");
		GridBagConstraints gbc_chckbxNewCheckBox_9 = new GridBagConstraints();
		gbc_chckbxNewCheckBox_9.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxNewCheckBox_9.gridx = 1;
		gbc_chckbxNewCheckBox_9.gridy = 11;
		panel_9_center.add(chckbxNewCheckBox_9, gbc_chckbxNewCheckBox_9);
		
		textField_11 = new JTextField();
		GridBagConstraints gbc_textField_11 = new GridBagConstraints();
		gbc_textField_11.insets = new Insets(0, 0, 5, 5);
		gbc_textField_11.gridx = 2;
		gbc_textField_11.gridy = 11;
		panel_9_center.add(textField_11, gbc_textField_11);
		textField_11.setColumns(10);
		
		textField_22 = new JTextField();
		GridBagConstraints gbc_textField_22 = new GridBagConstraints();
		gbc_textField_22.insets = new Insets(0, 0, 5, 5);
		gbc_textField_22.gridx = 3;
		gbc_textField_22.gridy = 11;
		panel_9_center.add(textField_22, gbc_textField_22);
		textField_22.setColumns(10);
		
		textField_33 = new JTextField();
		GridBagConstraints gbc_textField_33 = new GridBagConstraints();
		gbc_textField_33.insets = new Insets(0, 0, 5, 0);
		gbc_textField_33.gridx = 4;
		gbc_textField_33.gridy = 11;
		panel_9_center.add(textField_33, gbc_textField_33);
		textField_33.setColumns(10);
		
		JLabel lblVar_7 = new JLabel("Var 8");
		GridBagConstraints gbc_lblVar_7 = new GridBagConstraints();
		gbc_lblVar_7.anchor = GridBagConstraints.EAST;
		gbc_lblVar_7.insets = new Insets(0, 0, 5, 5);
		gbc_lblVar_7.gridx = 0;
		gbc_lblVar_7.gridy = 12;
		panel_9_center.add(lblVar_7, gbc_lblVar_7);
		
		JCheckBox chckbxNewCheckBox_10 = new JCheckBox("");
		GridBagConstraints gbc_chckbxNewCheckBox_10 = new GridBagConstraints();
		gbc_chckbxNewCheckBox_10.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxNewCheckBox_10.gridx = 1;
		gbc_chckbxNewCheckBox_10.gridy = 12;
		panel_9_center.add(chckbxNewCheckBox_10, gbc_chckbxNewCheckBox_10);
		
		textField_12 = new JTextField();
		GridBagConstraints gbc_textField_12 = new GridBagConstraints();
		gbc_textField_12.insets = new Insets(0, 0, 5, 5);
		gbc_textField_12.gridx = 2;
		gbc_textField_12.gridy = 12;
		panel_9_center.add(textField_12, gbc_textField_12);
		textField_12.setColumns(10);
		
		textField_23 = new JTextField();
		GridBagConstraints gbc_textField_23 = new GridBagConstraints();
		gbc_textField_23.insets = new Insets(0, 0, 5, 5);
		gbc_textField_23.gridx = 3;
		gbc_textField_23.gridy = 12;
		panel_9_center.add(textField_23, gbc_textField_23);
		textField_23.setColumns(10);
		
		textField_34 = new JTextField();
		GridBagConstraints gbc_textField_34 = new GridBagConstraints();
		gbc_textField_34.insets = new Insets(0, 0, 5, 0);
		gbc_textField_34.gridx = 4;
		gbc_textField_34.gridy = 12;
		panel_9_center.add(textField_34, gbc_textField_34);
		textField_34.setColumns(10);
		
		JLabel lblVar_8 = new JLabel("Var 9");
		GridBagConstraints gbc_lblVar_8 = new GridBagConstraints();
		gbc_lblVar_8.anchor = GridBagConstraints.EAST;
		gbc_lblVar_8.insets = new Insets(0, 0, 5, 5);
		gbc_lblVar_8.gridx = 0;
		gbc_lblVar_8.gridy = 13;
		panel_9_center.add(lblVar_8, gbc_lblVar_8);
		
		JCheckBox chckbxNewCheckBox_11 = new JCheckBox("");
		GridBagConstraints gbc_chckbxNewCheckBox_11 = new GridBagConstraints();
		gbc_chckbxNewCheckBox_11.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxNewCheckBox_11.gridx = 1;
		gbc_chckbxNewCheckBox_11.gridy = 13;
		panel_9_center.add(chckbxNewCheckBox_11, gbc_chckbxNewCheckBox_11);
		
		textField_13 = new JTextField();
		GridBagConstraints gbc_textField_13 = new GridBagConstraints();
		gbc_textField_13.insets = new Insets(0, 0, 5, 5);
		gbc_textField_13.gridx = 2;
		gbc_textField_13.gridy = 13;
		panel_9_center.add(textField_13, gbc_textField_13);
		textField_13.setColumns(10);
		
		textField_24 = new JTextField();
		GridBagConstraints gbc_textField_24 = new GridBagConstraints();
		gbc_textField_24.insets = new Insets(0, 0, 5, 5);
		gbc_textField_24.gridx = 3;
		gbc_textField_24.gridy = 13;
		panel_9_center.add(textField_24, gbc_textField_24);
		textField_24.setColumns(10);
		
		textField_35 = new JTextField();
		GridBagConstraints gbc_textField_35 = new GridBagConstraints();
		gbc_textField_35.insets = new Insets(0, 0, 5, 0);
		gbc_textField_35.gridx = 4;
		gbc_textField_35.gridy = 13;
		panel_9_center.add(textField_35, gbc_textField_35);
		textField_35.setColumns(10);
		
		JPanel panel_10 = new JPanel();
		tabbedPane.addTab("Arrival Rates", null, panel_10, null);
		panel_10.setLayout(new BorderLayout(0, 0));
		
		
		
		JPanel panel_12 = new JPanel();
		panel_10.add(panel_12, BorderLayout.NORTH);
		
		JLabel lblNorth = new JLabel("NORTH");
		panel_12.add(lblNorth);
		
		JPanel panel_13 = new JPanel();
		panel_10.add(panel_13, BorderLayout.WEST);
		
		JLabel lblWest = new JLabel("WEST");
		panel_13.add(lblWest);
		
		JPanel panel_14 = new JPanel();
		panel_10.add(panel_14, BorderLayout.EAST);
		
		JLabel lblEasr = new JLabel("EAST");
		panel_14.add(lblEasr);
		
		JPanel panel_15 = new JPanel();
		panel_10.add(panel_15, BorderLayout.SOUTH);
		
		JLabel lblSouth = new JLabel("SOUTH");
		panel_15.add(lblSouth);
		
		JScrollPane scrollPane = new JScrollPane();
		panel_10.add(scrollPane, BorderLayout.CENTER);
		
		panel_16 = new JPanel();
		scrollPane.setViewportView(panel_16);
		
		
		//scrollPane.add(panel_16, BorderLayout.CENTER);
		GridBagLayout gbl_panel_16 = new GridBagLayout();
		gbl_panel_16.columnWidths = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		gbl_panel_16.rowHeights = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		gbl_panel_16.columnWeights = new double[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		gbl_panel_16.rowWeights = new double[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		
		panel_16.setLayout(gbl_panel_16);
		
		GridBagConstraints gbc_textField_x = new GridBagConstraints();
		gbc_textField_x.insets = new Insets(10, 10, 5, 5);
		
		gbc_textField_x.gridx = 1;
		gbc_textField_x.gridy = 0;
		panel_16.add(fillAllButton,gbc_textField_x);
		
		gbc_textField_x.gridx = 0;
		gbc_textField_x.gridy = 0;
		panel_16.add(undoButton,gbc_textField_x);
			
			for(int j = 0; j<24; j++){
			JLabel x = new JLabel("Hour " + j);
			jlabelHourArray[j] = x;
			gbc_textField_x.gridx = j + 3;
			gbc_textField_x.gridy = 0;
			//gbc_textField_x.ipadx = 20;
			panel_16.add(x,gbc_textField_x);
			}
		
		
			for(int i = 0; i < 10; i++){
				JButton b1 = new JButton("Clear Row " + i);
				JButton b2 = new JButton("Fill Row " + i);
				b1.setPreferredSize(new Dimension(120,20));
				b2.setPreferredSize(new Dimension(90,20));
				b1.addActionListener(this);
				b2.addActionListener(this);
				fillButtonVector.add(b2);
				clearButtonVector.add(b1);
				gbc_textField_x.gridx = 0;
				gbc_textField_x.gridy = i + 1;
				panel_16.add(b1,gbc_textField_x);
				
				gbc_textField_x.gridx = 1;
				gbc_textField_x.gridy = i + 1;
				panel_16.add(b2,gbc_textField_x);
				
				JLabel x = new JLabel("Floor " + i);
				jlabelFloorVector.add(x);
				gbc_textField_x.insets = new Insets(10, 10, 5, 5);
				gbc_textField_x.gridx = 2;
				gbc_textField_x.gridy = i + 1;
				//gbc_textField_x.ipadx = 20;
				panel_16.add(x,gbc_textField_x);
			}
			
		
		for(int i = 0; i < 10; i++){
			
			JTextField[] m = new JTextField[24];
			Double[] d = new Double[24];
			jtf2Dvector.add(m);
			double2DVector.add(d);
			JTextField x = null;
			Double y = null;
			
			for(int j = 0; j<24; j++){
			x = new JTextField();
			d[j] = new Double(0.0);
//			GridBagConstraints gbc_textField_x = new GridBagConstraints();
			gbc_textField_x.insets = new Insets(10, 10, 5, 5);
			gbc_textField_x.gridx = j + 3;
			gbc_textField_x.gridy = i + 1;
			//gbc_textField_x.ipadx = 20;
			m[j] = x;
			panel_16.add(x,gbc_textField_x);
			x.setColumns(3);
			}
		}
		
		panel_11 = new JPanel();
		tabbedPane.addTab("Main Default Values", null, panel_11, null);
		
		JPanel panel_18 = new JPanel();
		tabbedPane.addTab("Arrival Default Values", null, panel_18, null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmHelp = new JMenuItem("Help");
		mnFile.add(mntmHelp);
		
		
	}
	
	public static void main(String[] args){
		ElevatorMainWindow2 emw =	new ElevatorMainWindow2();
		emw.setVisible(true);

	}


	public JPanel getPanel_11() {
		return panel_11;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(arg0.getSource() == undoButton && undoReady){
			
			for(int i = 0; i < this.jtf2Dvector.size(); i++){
				for(int j = 0; j < this.jtf2Dvector.get(0).length; j++){
					
					jtf2Dvector.get(i)[j].setText(double2DVector.get(i)[j].toString());
				}
			}
		}
		
		for(int i = 0; i < this.jtf2Dvector.size(); i++){
			for(int j = 0; j < this.jtf2Dvector.get(0).length; j++){
				
				
				try {
					if(!jtf2Dvector.get(i)[j].getText().isEmpty()){
					double2DVector.get(i)[j] = Double.parseDouble(jtf2Dvector.get(i)[j].getText());
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Invalid Value is highlighted.");
					jtf2Dvector.get(i)[j].setBackground(Color.red);
				}
			}
		}
		
		
		
		undoReady = true;
		
		if(arg0.getSource() == fillAllButton){
			
			for(int i = 0; i < jtf2Dvector.size(); i++){
				
				for(int j = 1; j < 24; j++){
					
					if(jtf2Dvector.get(i)[j].getText().isEmpty()){
						jtf2Dvector.get(i)[j].setText(jtf2Dvector.get(i)[j-1].getText());
					}
					
				}
			}
			return;
		}
		
		
		if(arg0.getSource().getClass().getName().equals("javax.swing.JButton") && ((JButton)arg0.getSource()).getText().toUpperCase().contains("CLEAR")){
			int index = clearButtonVector.indexOf(arg0.getSource());
			
			for(int j = 0; j < 24; j++){
				
				jtf2Dvector.get(index)[j].setText("");
			}
		}
		
		if(arg0.getSource().getClass().getName().equals("javax.swing.JButton") && ((JButton)arg0.getSource()).getText().toUpperCase().contains("FILL")){
			int index = fillButtonVector.indexOf(arg0.getSource());
			
			for(int j = 1; j < 24; j++){
				
				if(jtf2Dvector.get(index)[j].getText().isEmpty()){
				jtf2Dvector.get(index)[j].setText(jtf2Dvector.get(index)[j-1].getText());
				}
			}
		}
		
		
	}
}
