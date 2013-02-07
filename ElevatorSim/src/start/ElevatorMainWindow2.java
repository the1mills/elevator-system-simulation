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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Vector;

import javafiles.JavaBeanStuff;

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

import org.apache.commons.lang3.text.WordUtils;

public class ElevatorMainWindow2 extends JFrame implements ActionListener, ItemListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean undoReady = false;
	private Integer numberOfDecisionVariables = null;
	private Vector<VariableVectorElement> vve = new Vector<VariableVectorElement>();
	private Vector<JTextField[]> jtf2Dvector = new Vector<JTextField[]>();
	private Vector<Double[]> double2DVector = new Vector<Double[]>();
	private JLabel[] jlabelHourArray = new JLabel[24];
	private Vector<JLabel> jlabelFloorVector = new Vector<JLabel>();
	private JButton fillAllButton = new JButton("Fill All");
	private Vector<JButton> fillButtonVector = new Vector<JButton>();
	private Vector<JButton> clearButtonVector = new Vector<JButton>();
	private JButton undoButton = new JButton("Undo Last Change");
	private JButton startButton = new JButton("Start Simulation");
	private JPanel panel_16;
	private JPanel panel_11;
	private static Hashtable<Integer,String> hashMeth = new Hashtable<Integer,String>();
	private static Hashtable<String,Vector<Double[]>> hashVect = new Hashtable<String,Vector<Double[]>>();
	private static Hashtable<String, VariableVectorElement> hashVectVve = new Hashtable<String, VariableVectorElement>();
	
	
	private void buildHashTable(){
		
		for(int i = 0; i < hashMeth.size(); i++){
			
			hashVectVve.put(hashMeth.get(i), vve.get(i));
			
		}
	}
	public ElevatorMainWindow2() {
	
		hashMeth.put(new Integer(0),"numberOfFloors");
		hashMeth.put(new Integer(1),"numberOfElevators");
		hashMeth.put(new Integer(2),"timeFactor");
		hashMeth.put(new Integer(3),"numberOfEmptySpacesToUseGoingToVariable");
		hashMeth.put(new Integer(4),"numberOfEmptySpacesToUsePassingByVariable");
		hashMeth.put(new Integer(5),"numberOfEmptySpacesToUseSameFloorVariable");
		hashMeth.put(new Integer(6),"capacityThresholdVariable");
		hashMeth.put(new Integer(7),"numberOfFloorsDifference");
		hashMeth.put(new Integer(8),"maxDistanceForUntaskedVariable");
		hashMeth.put(new Integer(9),"countUntaskedVariable");
		hashMeth.put(new Integer(10),"appendDistanceVariable");
		hashMeth.put(new Integer(11),"distanceAlreadyGoingVariable");
		hashMeth.put(new Integer(12),"capacityVariable");
		
		numberOfDecisionVariables = hashMeth.size();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 600);
		
		startButton.addActionListener(this);
		fillAllButton.addActionListener(this);
		undoButton.addActionListener(this);
		
		
		for(Integer i = 0; i <numberOfDecisionVariables; i++){
			
			if(i == 0){
				vve.add(new VariableVectorElement("Number of Floors",i));
			}
			else if(i == 1){
				vve.add(new VariableVectorElement("Number of Elevators",i));
			}
			else if(i == 2){
				vve.add(new VariableVectorElement("Elevator Capacity",i));
			}
			else{
			vve.add(new VariableVectorElement("Var " + i.toString(),i));
		}
			
		}
		
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
		
		JLabel lblNewLabel = new JLabel("EAST");
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
		
		panel_9_north.add(startButton);
		panel_9_south.add(new JLabel("SOUTH"));
		panel_9_east.add(new JLabel("EAST"));
		panel_9_west.add(new JLabel("WEST"));
		
		
		GridBagLayout gbl_panel_9 = new GridBagLayout();
		gbl_panel_9.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel_9.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_9.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
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
		
		JLabel lblFixedValue = new JLabel("Fixed Value");
		lblFixedValue.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblFixedValue = new GridBagConstraints();
		gbc_lblFixedValue.anchor = GridBagConstraints.WEST;
		gbc_lblFixedValue.insets = new Insets(0, 0, 5, 5);
		gbc_lblFixedValue.gridx = 2;
		gbc_lblFixedValue.gridy = 0;
		panel_9_center.add(lblFixedValue, gbc_lblFixedValue);
		
		JLabel lblMin = new JLabel("MIN");
		lblMin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblMin = new GridBagConstraints();
		gbc_lblMin.insets = new Insets(0, 0, 5, 5);
		gbc_lblMin.gridx = 3;
		gbc_lblMin.gridy = 0;
		panel_9_center.add(lblMin, gbc_lblMin);
		
		JLabel lblMax = new JLabel("MAX");
		lblMax.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblMax = new GridBagConstraints();
		gbc_lblMax.insets = new Insets(0, 0, 5, 5);
		gbc_lblMax.gridx = 4;
		gbc_lblMax.gridy = 0;
		panel_9_center.add(lblMax, gbc_lblMax);
		
		JLabel lblStepsize = new JLabel("Stepsize");
		lblStepsize.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblStepsize = new GridBagConstraints();
		gbc_lblStepsize.insets = new Insets(0, 0, 5, 0);
		gbc_lblStepsize.gridx = 5;
		gbc_lblStepsize.gridy = 0;
		panel_9_center.add(lblStepsize, gbc_lblStepsize);
		
//		JLabel lblNumberOfFloors = new JLabel("Number of Floors");
		JLabel lblNumberOfFloors = vve.get(0).getVariableLabel();
		GridBagConstraints gbc_lblNumberOfFloors = new GridBagConstraints();
		gbc_lblNumberOfFloors.anchor = GridBagConstraints.EAST;
		gbc_lblNumberOfFloors.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumberOfFloors.gridx = 0;
		gbc_lblNumberOfFloors.gridy = 1;
		panel_9_center.add(lblNumberOfFloors, gbc_lblNumberOfFloors);
		
//		JCheckBox chckbxNewCheckBox = new JCheckBox("");
		JCheckBox chckbxNewCheckBox = vve.get(0).getFixed();
		chckbxNewCheckBox.addItemListener(this);
		GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
		gbc_chckbxNewCheckBox.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxNewCheckBox.gridx = 1;
		gbc_chckbxNewCheckBox.gridy = 1;
		panel_9_center.add(chckbxNewCheckBox, gbc_chckbxNewCheckBox);
		
		
		JTextField textField_t_0_fixed = vve.get(0).getFixedValue();
		GridBagConstraints gbc_textField_0_fixed = new GridBagConstraints();
		gbc_textField_0_fixed.insets = new Insets(0, 0, 5, 5);
		gbc_textField_0_fixed.gridx = 2;
		gbc_textField_0_fixed.gridy = 1;
		panel_9_center.add(textField_t_0_fixed, gbc_textField_0_fixed);
		textField_t_0_fixed.setColumns(10);
		
		//textField_1 = new JTextField();
		JTextField textField_t_0_1 = vve.get(0).getMinTF();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.gridx = 3;
		gbc_textField_1.gridy = 1;
		panel_9_center.add(textField_t_0_1, gbc_textField_1);
		textField_t_0_1.setColumns(10);
		
		//textField_2 = new JTextField();
		JTextField textField_t_0_2 = vve.get(0).getMaxTF();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.gridx = 4;
		gbc_textField_2.gridy = 1;
		panel_9_center.add(textField_t_0_2, gbc_textField_2);
		textField_t_0_2.setColumns(10);
		
		//textField_3 = new JTextField();
		JTextField textField_t_0_3 = vve.get(0).getStepsizeTF();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 5, 0);
		gbc_textField_3.gridx = 5;
		gbc_textField_3.gridy = 1;
		panel_9_center.add(textField_t_0_3, gbc_textField_3);
		textField_t_0_3.setColumns(10);
		
//		JLabel lblNumberOfElevators = new JLabel("Number Of Elevators");
		JLabel lblNumberOfElevators = vve.get(1).getVariableLabel();
		GridBagConstraints gbc_lblNumberOfElevators = new GridBagConstraints();
		gbc_lblNumberOfElevators.anchor = GridBagConstraints.EAST;
		gbc_lblNumberOfElevators.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumberOfElevators.gridx = 0;
		gbc_lblNumberOfElevators.gridy = 2;
		panel_9_center.add(lblNumberOfElevators, gbc_lblNumberOfElevators);
		
		JCheckBox chckbxNewCheckBox_1 = vve.get(1).getFixed();
		chckbxNewCheckBox_1.addItemListener(this);
		GridBagConstraints gbc_chckbxNewCheckBox_1 = new GridBagConstraints();
		gbc_chckbxNewCheckBox_1.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxNewCheckBox_1.gridx = 1;
		gbc_chckbxNewCheckBox_1.gridy = 2;
		panel_9_center.add(chckbxNewCheckBox_1, gbc_chckbxNewCheckBox_1);
		
		
		JTextField textField_t_1_fixed = vve.get(1).getFixedValue();
		GridBagConstraints gbc_textField_1_fixed = new GridBagConstraints();
		gbc_textField_1_fixed.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1_fixed.gridx = 2;
		gbc_textField_1_fixed.gridy = 2;
		panel_9_center.add(textField_t_1_fixed, gbc_textField_1_fixed);
		textField_t_1_fixed.setColumns(10);
		
		//textField = new JTextField();
		JTextField textField_t_1_1 = vve.get(1).getMinTF();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 3;
		gbc_textField.gridy = 2;
		panel_9_center.add(textField_t_1_1, gbc_textField);
		textField_t_1_1.setColumns(10);
		
//		textField_14 = new JTextField();
		JTextField textField_t_1_2 = vve.get(1).getMaxTF();
		GridBagConstraints gbc_textField_14 = new GridBagConstraints();
		gbc_textField_14.insets = new Insets(0, 0, 5, 5);
		gbc_textField_14.gridx = 4;
		gbc_textField_14.gridy = 2;
		panel_9_center.add(textField_t_1_2, gbc_textField_14);
		textField_t_1_2.setColumns(10);
		
//		textField_25 = new JTextField();
		JTextField textField_t_1_3 = vve.get(1).getStepsizeTF();
		GridBagConstraints gbc_textField_25 = new GridBagConstraints();
		gbc_textField_25.insets = new Insets(0, 0, 5, 0);
		gbc_textField_25.gridx = 5;
		gbc_textField_25.gridy = 2;
		panel_9_center.add(textField_t_1_3, gbc_textField_25);
		textField_t_1_3.setColumns(10);
		
//		JLabel lblElevatorCapacity = new JLabel("Elevator Capacity");
		JLabel lblElevatorCapacity = vve.get(2).getVariableLabel();
		GridBagConstraints gbc_lblElevatorCapacity = new GridBagConstraints();
		gbc_lblElevatorCapacity.anchor = GridBagConstraints.EAST;
		gbc_lblElevatorCapacity.insets = new Insets(0, 0, 5, 5);
		gbc_lblElevatorCapacity.gridx = 0;
		gbc_lblElevatorCapacity.gridy = 3;
		panel_9_center.add(lblElevatorCapacity, gbc_lblElevatorCapacity);
		
//		JCheckBox chckbxNewCheckBox_2 = new JCheckBox("");
		JCheckBox chckbxNewCheckBox_2 = vve.get(2).getFixed();
		chckbxNewCheckBox_2.addItemListener(this);
		GridBagConstraints gbc_chckbxNewCheckBox_2 = new GridBagConstraints();
		gbc_chckbxNewCheckBox_2.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxNewCheckBox_2.gridx = 1;
		gbc_chckbxNewCheckBox_2.gridy = 3;
		panel_9_center.add(chckbxNewCheckBox_2, gbc_chckbxNewCheckBox_2);
		
		
		JTextField textField_t_2_fixed = vve.get(2).getFixedValue();
		GridBagConstraints gbc_textField_2_fixed = new GridBagConstraints();
		gbc_textField_2_fixed.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2_fixed.gridx = 2;
		gbc_textField_2_fixed.gridy = 3;
		panel_9_center.add(textField_t_2_fixed, gbc_textField_2_fixed);
		textField_t_2_fixed.setColumns(10);
		
		
//		textField_4 = new JTextField();
		JTextField textField_t_2_1 = vve.get(2).getMinTF();
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.insets = new Insets(0, 0, 5, 5);
		gbc_textField_4.gridx = 3;
		gbc_textField_4.gridy = 3;
		panel_9_center.add(textField_t_2_1, gbc_textField_4);
		textField_t_2_1.setColumns(10);
		
//		textField_15 = new JTextField();
		JTextField textField_t_2_2 = vve.get(2).getMaxTF();
		GridBagConstraints gbc_textField_15 = new GridBagConstraints();
		gbc_textField_15.insets = new Insets(0, 0, 5, 5);
		gbc_textField_15.gridx = 4;
		gbc_textField_15.gridy = 3;
		panel_9_center.add(textField_t_2_2, gbc_textField_15);
		textField_t_2_2.setColumns(10);
		
//		textField_26 = new JTextField();
		JTextField textField_t_2_3 = vve.get(2).getStepsizeTF();
		GridBagConstraints gbc_textField_26 = new GridBagConstraints();
		gbc_textField_26.insets = new Insets(0, 0, 5, 0);
		gbc_textField_26.gridx = 5;
		gbc_textField_26.gridy = 3;
		panel_9_center.add(textField_t_2_3, gbc_textField_26);
		textField_t_2_3.setColumns(10);
		
//		JLabel lblVar = new JLabel("Var 1");
		JLabel lblVar = vve.get(3).getVariableLabel();
		GridBagConstraints gbc_lblVar = new GridBagConstraints();
		gbc_lblVar.anchor = GridBagConstraints.EAST;
		gbc_lblVar.insets = new Insets(0, 0, 5, 5);
		gbc_lblVar.gridx = 0;
		gbc_lblVar.gridy = 4;
		panel_9_center.add(lblVar, gbc_lblVar);
		
//		JCheckBox chckbxNewCheckBox_3 = new JCheckBox("");
		JCheckBox chckbxNewCheckBox_3 = vve.get(3).getFixed();
		chckbxNewCheckBox_3.addItemListener(this);
		GridBagConstraints gbc_chckbxNewCheckBox_3 = new GridBagConstraints();
		gbc_chckbxNewCheckBox_3.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxNewCheckBox_3.gridx = 1;
		gbc_chckbxNewCheckBox_3.gridy = 4;
		panel_9_center.add(chckbxNewCheckBox_3, gbc_chckbxNewCheckBox_3);
		
		
		JTextField textField_t_3_fixed = vve.get(3).getFixedValue();
		GridBagConstraints gbc_textField_3_fixed = new GridBagConstraints();
		gbc_textField_3_fixed.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3_fixed.gridx = 2;
		gbc_textField_3_fixed.gridy = 4;
		panel_9_center.add(textField_t_3_fixed, gbc_textField_3_fixed);
		textField_t_3_fixed.setColumns(10);
		
		
//		textField_5 = new JTextField();
		JTextField j_t_3_1 = vve.get(3).getMinTF();
		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
		gbc_textField_5.insets = new Insets(0, 0, 5, 5);
		gbc_textField_5.gridx = 3;
		gbc_textField_5.gridy = 4;
		panel_9_center.add(j_t_3_1, gbc_textField_5);
		j_t_3_1.setColumns(10);
		
//		textField_16 = new JTextField();
		JTextField j_t_3_2 = vve.get(3).getMaxTF();
		GridBagConstraints gbc_textField_16 = new GridBagConstraints();
		gbc_textField_16.insets = new Insets(0, 0, 5, 5);
		gbc_textField_16.gridx = 4;
		gbc_textField_16.gridy = 4;
		panel_9_center.add(j_t_3_2, gbc_textField_16);
		j_t_3_2.setColumns(10);
		
//		textField_27 = new JTextField();
		JTextField j_t_3_3 = vve.get(3).getStepsizeTF();
		GridBagConstraints gbc_textField_27 = new GridBagConstraints();
		gbc_textField_27.insets = new Insets(0, 0, 5, 0);
		gbc_textField_27.gridx = 5;
		gbc_textField_27.gridy = 4;
		panel_9_center.add(j_t_3_3, gbc_textField_27);
		j_t_3_3.setColumns(10);
		
//		JLabel lblVar_1 = new JLabel("Var 2");
		JLabel lblVar_1 = vve.get(4).getVariableLabel();
		GridBagConstraints gbc_lblVar_1 = new GridBagConstraints();
		gbc_lblVar_1.anchor = GridBagConstraints.EAST;
		gbc_lblVar_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblVar_1.gridx = 0;
		gbc_lblVar_1.gridy = 5;
		panel_9_center.add(lblVar_1, gbc_lblVar_1);
		
//		JCheckBox chckbxNewCheckBox_4 = new JCheckBox("");
		JCheckBox chckbxNewCheckBox_4 = vve.get(4).getFixed();
		chckbxNewCheckBox_4.addItemListener(this);
		GridBagConstraints gbc_chckbxNewCheckBox_4 = new GridBagConstraints();
		gbc_chckbxNewCheckBox_4.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxNewCheckBox_4.gridx = 1;
		gbc_chckbxNewCheckBox_4.gridy = 5;
		panel_9_center.add(chckbxNewCheckBox_4, gbc_chckbxNewCheckBox_4);
		
		JTextField textField_t_4_fixed = vve.get(4).getFixedValue();
		GridBagConstraints gbc_textField_4_fixed = new GridBagConstraints();
		gbc_textField_4_fixed.insets = new Insets(0, 0, 5, 5);
		gbc_textField_4_fixed.gridx = 2;
		gbc_textField_4_fixed.gridy = 5;
		panel_9_center.add(textField_t_4_fixed, gbc_textField_4_fixed);
		textField_t_4_fixed.setColumns(10);
		
//		textField_6 = new JTextField();
		JTextField j_t_4_1 =  vve.get(4).getMinTF();
		GridBagConstraints gbc_textField_6 = new GridBagConstraints();
		gbc_textField_6.insets = new Insets(0, 0, 5, 5);
		gbc_textField_6.gridx = 3;
		gbc_textField_6.gridy = 5;
		panel_9_center.add(j_t_4_1, gbc_textField_6);
		j_t_4_1.setColumns(10);
		
//		textField_17 = new JTextField();
		JTextField j_t_4_2 = vve.get(4).getMaxTF();
		GridBagConstraints gbc_textField_17 = new GridBagConstraints();
		gbc_textField_17.insets = new Insets(0, 0, 5, 5);
		gbc_textField_17.gridx = 4;
		gbc_textField_17.gridy = 5;
		panel_9_center.add(j_t_4_2, gbc_textField_17);
		j_t_4_2.setColumns(10);
		
//		textField_28 = new JTextField();
		JTextField j_t_4_3 = vve.get(4).getStepsizeTF();
		GridBagConstraints gbc_textField_28 = new GridBagConstraints();
		gbc_textField_28.insets = new Insets(0, 0, 5, 0);
		gbc_textField_28.gridx = 5;
		gbc_textField_28.gridy = 5;
		panel_9_center.add(j_t_4_3, gbc_textField_28);
		j_t_4_3.setColumns(10);
		
//		JLabel lblVar_2 = new JLabel("Var 3");
		JLabel lblVar_2 = vve.get(5).getVariableLabel();
		GridBagConstraints gbc_lblVar_2 = new GridBagConstraints();
		gbc_lblVar_2.anchor = GridBagConstraints.EAST;
		gbc_lblVar_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblVar_2.gridx = 0;
		gbc_lblVar_2.gridy = 6;
		panel_9_center.add(lblVar_2, gbc_lblVar_2);
		
//		JCheckBox chckbxNewCheckBox_5 = new JCheckBox("");
		JCheckBox chckbxNewCheckBox_5 = vve.get(5).getFixed();
		chckbxNewCheckBox_5.addItemListener(this);
		GridBagConstraints gbc_chckbxNewCheckBox_5 = new GridBagConstraints();
		gbc_chckbxNewCheckBox_5.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxNewCheckBox_5.gridx = 1;
		gbc_chckbxNewCheckBox_5.gridy = 6;
		panel_9_center.add(chckbxNewCheckBox_5, gbc_chckbxNewCheckBox_5);
		
		JTextField textField_t_5_fixed = vve.get(5).getFixedValue();
		GridBagConstraints gbc_textField_5_fixed = new GridBagConstraints();
		gbc_textField_5_fixed.insets = new Insets(0, 0, 5, 5);
		gbc_textField_5_fixed.gridx = 2;
		gbc_textField_5_fixed.gridy = 6;
		panel_9_center.add(textField_t_5_fixed, gbc_textField_5_fixed);
		textField_t_5_fixed.setColumns(10);
		
//		textField_7 = new JTextField();
		JTextField j_t_5_1 = vve.get(5).getMinTF();
		GridBagConstraints gbc_textField_7 = new GridBagConstraints();
		gbc_textField_7.insets = new Insets(0, 0, 5, 5);
		gbc_textField_7.gridx = 3;
		gbc_textField_7.gridy = 6;
		panel_9_center.add(j_t_5_1, gbc_textField_7);
		j_t_5_1.setColumns(10);
		
//		textField_18 = new JTextField();
		JTextField j_t_5_2 = vve.get(5).getMaxTF();
		GridBagConstraints gbc_textField_18 = new GridBagConstraints();
		gbc_textField_18.insets = new Insets(0, 0, 5, 5);
		gbc_textField_18.gridx = 4;
		gbc_textField_18.gridy = 6;
		panel_9_center.add(j_t_5_2, gbc_textField_18);
		j_t_5_2.setColumns(10);
		
//		textField_29 = new JTextField();
		JTextField j_t_5_3 = vve.get(5).getStepsizeTF();
		GridBagConstraints gbc_textField_29 = new GridBagConstraints();
		gbc_textField_29.insets = new Insets(0, 0, 5, 0);
		gbc_textField_29.gridx = 5;
		gbc_textField_29.gridy = 6;
		panel_9_center.add(j_t_5_3, gbc_textField_29);
		j_t_5_3.setColumns(10);
		
//		JLabel lblVar_3 = new JLabel("Var 4");
		JLabel lblVar_3 = vve.get(6).getVariableLabel();
		GridBagConstraints gbc_lblVar_3 = new GridBagConstraints();
		gbc_lblVar_3.anchor = GridBagConstraints.EAST;
		gbc_lblVar_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblVar_3.gridx = 0;
		gbc_lblVar_3.gridy = 7;
		panel_9_center.add(lblVar_3, gbc_lblVar_3);
		
//		JCheckBox chckbxNewCheckBox_6 = new JCheckBox("");
		JCheckBox chckbxNewCheckBox_6 = vve.get(6).getFixed();
		chckbxNewCheckBox_6.addItemListener(this);
		GridBagConstraints gbc_chckbxNewCheckBox_6 = new GridBagConstraints();
		gbc_chckbxNewCheckBox_6.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxNewCheckBox_6.gridx = 1;
		gbc_chckbxNewCheckBox_6.gridy = 7;
		panel_9_center.add(chckbxNewCheckBox_6, gbc_chckbxNewCheckBox_6);
		
		JTextField textField_t_6_fixed = vve.get(6).getFixedValue();
		GridBagConstraints gbc_textField_6_fixed = new GridBagConstraints();
		gbc_textField_6_fixed.insets = new Insets(0, 0, 5, 5);
		gbc_textField_6_fixed.gridx = 2;
		gbc_textField_6_fixed.gridy = 7;
		panel_9_center.add(textField_t_6_fixed, gbc_textField_6_fixed);
		textField_t_6_fixed.setColumns(10);
		
		
//		textField_8 = new JTextField();
		JTextField j_t_6_1 = vve.get(6).getMinTF();
		GridBagConstraints gbc_textField_8 = new GridBagConstraints();
		gbc_textField_8.insets = new Insets(0, 0, 5, 5);
		gbc_textField_8.gridx = 3;
		gbc_textField_8.gridy = 7;
		panel_9_center.add(j_t_6_1, gbc_textField_8);
		j_t_6_1.setColumns(10);
		
//		textField_19 = new JTextField();
		JTextField j_t_6_2 = vve.get(6).getMaxTF();
		GridBagConstraints gbc_textField_19 = new GridBagConstraints();
		gbc_textField_19.insets = new Insets(0, 0, 5, 5);
		gbc_textField_19.gridx = 4;
		gbc_textField_19.gridy = 7;
		panel_9_center.add(j_t_6_2, gbc_textField_19);
		j_t_6_2.setColumns(10);
		
//		textField_30 = new JTextField();
		JTextField j_t_6_3 = vve.get(6).getStepsizeTF();
		GridBagConstraints gbc_textField_30 = new GridBagConstraints();
		gbc_textField_30.insets = new Insets(0, 0, 5, 0);
		gbc_textField_30.gridx = 5;
		gbc_textField_30.gridy = 7;
		panel_9_center.add(j_t_6_3, gbc_textField_30);
		j_t_6_3.setColumns(10);
		
//		JLabel lblVar_4 = new JLabel("Var 5");
		JLabel lblVar_4 = vve.get(7).getVariableLabel();
		GridBagConstraints gbc_lblVar_4 = new GridBagConstraints();
		gbc_lblVar_4.anchor = GridBagConstraints.EAST;
		gbc_lblVar_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblVar_4.gridx = 0;
		gbc_lblVar_4.gridy = 8;
		panel_9_center.add(lblVar_4, gbc_lblVar_4);
		
//		JCheckBox chckbxNewCheckBox_7 = new JCheckBox("");
		JCheckBox chckbxNewCheckBox_7 = vve.get(7).getFixed();
		chckbxNewCheckBox_7.addItemListener(this);
		GridBagConstraints gbc_chckbxNewCheckBox_7 = new GridBagConstraints();
		gbc_chckbxNewCheckBox_7.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxNewCheckBox_7.gridx = 1;
		gbc_chckbxNewCheckBox_7.gridy = 8;
		panel_9_center.add(chckbxNewCheckBox_7, gbc_chckbxNewCheckBox_7);
		
		
		JTextField textField_t_7_fixed = vve.get(7).getFixedValue();
		GridBagConstraints gbc_textField_7_fixed = new GridBagConstraints();
		gbc_textField_7_fixed.insets = new Insets(0, 0, 5, 5);
		gbc_textField_7_fixed.gridx = 2;
		gbc_textField_7_fixed.gridy = 8;
		panel_9_center.add(textField_t_7_fixed, gbc_textField_7_fixed);
		textField_t_7_fixed.setColumns(10);
		
//		textField_9 = new JTextField();
		JTextField j_t_7_1 = vve.get(7).getMinTF();
		GridBagConstraints gbc_textField_9 = new GridBagConstraints();
		gbc_textField_9.insets = new Insets(0, 0, 5, 5);
		gbc_textField_9.gridx = 3;
		gbc_textField_9.gridy = 8;
		panel_9_center.add(j_t_7_1, gbc_textField_9);
		j_t_7_1.setColumns(10);
		
//		textField_20 = new JTextField();
		JTextField j_t_7_2 = vve.get(7).getMaxTF();
		GridBagConstraints gbc_textField_20 = new GridBagConstraints();
		gbc_textField_20.insets = new Insets(0, 0, 5, 5);
		gbc_textField_20.gridx = 4;
		gbc_textField_20.gridy = 8;
		panel_9_center.add(j_t_7_2, gbc_textField_20);
		j_t_7_2.setColumns(10);
		
//		textField_31 = new JTextField();
		JTextField j_t_7_3 = vve.get(7).getStepsizeTF();
		GridBagConstraints gbc_textField_31 = new GridBagConstraints();
		gbc_textField_31.insets = new Insets(0, 0, 5, 0);
		gbc_textField_31.gridx = 5;
		gbc_textField_31.gridy = 8;
		panel_9_center.add(j_t_7_3, gbc_textField_31);
		j_t_7_3.setColumns(10);
		
//		JLabel lblVar_5 = new JLabel("Var 6");
		JLabel lblVar_5 = vve.get(8).getVariableLabel();
		GridBagConstraints gbc_lblVar_5 = new GridBagConstraints();
		gbc_lblVar_5.anchor = GridBagConstraints.EAST;
		gbc_lblVar_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblVar_5.gridx = 0;
		gbc_lblVar_5.gridy = 9;
		panel_9_center.add(lblVar_5, gbc_lblVar_5);
		
//		JCheckBox chckbxNewCheckBox_8 = new JCheckBox("");
		JCheckBox chckbxNewCheckBox_8 = vve.get(8).getFixed();
		chckbxNewCheckBox_8.addItemListener(this);
		GridBagConstraints gbc_chckbxNewCheckBox_8 = new GridBagConstraints();
		gbc_chckbxNewCheckBox_8.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxNewCheckBox_8.gridx = 1;
		gbc_chckbxNewCheckBox_8.gridy = 9;
		panel_9_center.add(chckbxNewCheckBox_8, gbc_chckbxNewCheckBox_8);
		
		
		JTextField textField_t_8_fixed = vve.get(8).getFixedValue();
		GridBagConstraints gbc_textField_8_fixed = new GridBagConstraints();
		gbc_textField_8_fixed.insets = new Insets(0, 0, 5, 5);
		gbc_textField_8_fixed.gridx = 2;
		gbc_textField_8_fixed.gridy = 9;
		panel_9_center.add(textField_t_8_fixed, gbc_textField_8_fixed);
		textField_t_8_fixed.setColumns(10);
		
		
//		textField_10 = new JTextField();
		JTextField j_t_8_1 = vve.get(8).getMinTF();
		GridBagConstraints gbc_textField_10 = new GridBagConstraints();
		gbc_textField_10.insets = new Insets(0, 0, 5, 5);
		gbc_textField_10.gridx = 3;
		gbc_textField_10.gridy = 9;
		panel_9_center.add(j_t_8_1, gbc_textField_10);
		j_t_8_1.setColumns(10);
		
//		textField_21 = new JTextField();
		JTextField j_t_8_2 = vve.get(8).getMaxTF();
		GridBagConstraints gbc_textField_21 = new GridBagConstraints();
		gbc_textField_21.insets = new Insets(0, 0, 5, 5);
		gbc_textField_21.gridx = 4;
		gbc_textField_21.gridy = 9;
		panel_9_center.add(j_t_8_2, gbc_textField_21);
		j_t_8_2.setColumns(10);
		
//		textField_32 = new JTextField();
		JTextField j_t_8_3 = vve.get(8).getStepsizeTF();
		GridBagConstraints gbc_textField_32 = new GridBagConstraints();
		gbc_textField_32.insets = new Insets(0, 0, 5, 0);
		gbc_textField_32.gridx = 5;
		gbc_textField_32.gridy = 9;
		panel_9_center.add(j_t_8_3, gbc_textField_32);
		j_t_8_3.setColumns(10);
		
//		JLabel lblVar_6 = new JLabel("Var 7");
		JLabel lblVar_6 = vve.get(9).getVariableLabel();
		GridBagConstraints gbc_lblVar_6 = new GridBagConstraints();
		gbc_lblVar_6.anchor = GridBagConstraints.EAST;
		gbc_lblVar_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblVar_6.gridx = 0;
		gbc_lblVar_6.gridy = 10;
		panel_9_center.add(lblVar_6, gbc_lblVar_6);
		
//		JCheckBox chckbxNewCheckBox_9 = new JCheckBox("");
		JCheckBox chckbxNewCheckBox_9 = vve.get(9).getFixed();
		chckbxNewCheckBox_9.addItemListener(this);
		GridBagConstraints gbc_chckbxNewCheckBox_9 = new GridBagConstraints();
		gbc_chckbxNewCheckBox_9.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxNewCheckBox_9.gridx = 1;
		gbc_chckbxNewCheckBox_9.gridy = 10;
		panel_9_center.add(chckbxNewCheckBox_9, gbc_chckbxNewCheckBox_9);
		
		
		
		JTextField textField_t_9_fixed = vve.get(9).getFixedValue();
		GridBagConstraints gbc_textField_9_fixed = new GridBagConstraints();
		gbc_textField_9_fixed.insets = new Insets(0, 0, 5, 5);
		gbc_textField_9_fixed.gridx = 2;
		gbc_textField_9_fixed.gridy = 10;
		panel_9_center.add(textField_t_9_fixed, gbc_textField_9_fixed);
		textField_t_9_fixed.setColumns(10);
		
//		textField_11 = new JTextField();
		JTextField j_t_9_1 = vve.get(9).getMinTF();
		GridBagConstraints gbc_textField_11 = new GridBagConstraints();
		gbc_textField_11.insets = new Insets(0, 0, 5, 5);
		gbc_textField_11.gridx = 3;
		gbc_textField_11.gridy = 10;
		panel_9_center.add(j_t_9_1, gbc_textField_11);
		j_t_9_1.setColumns(10);
		
//		textField_22 = new JTextField();
		JTextField j_t_9_2 = vve.get(9).getMaxTF();
		GridBagConstraints gbc_textField_22 = new GridBagConstraints();
		gbc_textField_22.insets = new Insets(0, 0, 5, 5);
		gbc_textField_22.gridx = 4;
		gbc_textField_22.gridy = 10;
		panel_9_center.add(j_t_9_2, gbc_textField_22);
		j_t_9_2.setColumns(10);
		
//		textField_33 = new JTextField();
		JTextField j_t_9_3 = vve.get(9).getStepsizeTF();
		GridBagConstraints gbc_textField_33 = new GridBagConstraints();
		gbc_textField_33.insets = new Insets(0, 0, 5, 0);
		gbc_textField_33.gridx = 5;
		gbc_textField_33.gridy = 10;
		panel_9_center.add(j_t_9_3, gbc_textField_33);
		j_t_9_3.setColumns(10);
		
//		JLabel lblVar_7 = new JLabel("Var 8");
		JLabel lblVar_7 = vve.get(10).getVariableLabel();
		GridBagConstraints gbc_lblVar_7 = new GridBagConstraints();
		gbc_lblVar_7.anchor = GridBagConstraints.EAST;
		gbc_lblVar_7.insets = new Insets(0, 0, 5, 5);
		gbc_lblVar_7.gridx = 0;
		gbc_lblVar_7.gridy = 11;
		panel_9_center.add(lblVar_7, gbc_lblVar_7);
		
//		JCheckBox chckbxNewCheckBox_10 = new JCheckBox("");
		JCheckBox chckbxNewCheckBox_10 = vve.get(10).getFixed();
		chckbxNewCheckBox_10.addItemListener(this);
		GridBagConstraints gbc_chckbxNewCheckBox_10 = new GridBagConstraints();
		gbc_chckbxNewCheckBox_10.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxNewCheckBox_10.gridx = 1;
		gbc_chckbxNewCheckBox_10.gridy = 11;
		panel_9_center.add(chckbxNewCheckBox_10, gbc_chckbxNewCheckBox_10);
		
		JTextField textField_t_10_fixed = vve.get(10).getFixedValue();
		GridBagConstraints gbc_textField_10_fixed = new GridBagConstraints();
		gbc_textField_10_fixed.insets = new Insets(0, 0, 5, 5);
		gbc_textField_10_fixed.gridx = 2;
		gbc_textField_10_fixed.gridy = 11;
		panel_9_center.add(textField_t_10_fixed, gbc_textField_10_fixed);
		textField_t_10_fixed.setColumns(10);
		
//		textField_12 = new JTextField();
		JTextField j_t_10_1 = vve.get(10).getMinTF();
		GridBagConstraints gbc_textField_12 = new GridBagConstraints();
		gbc_textField_12.insets = new Insets(0, 0, 5, 5);
		gbc_textField_12.gridx = 3;
		gbc_textField_12.gridy = 11;
		panel_9_center.add(j_t_10_1, gbc_textField_12);
		j_t_10_1.setColumns(10);
		
//		textField_23 = new JTextField();
		JTextField j_t_10_2 = vve.get(10).getMaxTF();
		GridBagConstraints gbc_textField_23 = new GridBagConstraints();
		gbc_textField_23.insets = new Insets(0, 0, 5, 5);
		gbc_textField_23.gridx = 4;
		gbc_textField_23.gridy = 11;
		panel_9_center.add(j_t_10_2, gbc_textField_23);
		j_t_10_2.setColumns(10);
		
//		textField_34 = new JTextField();
		JTextField j_t_10_3 = vve.get(10).getStepsizeTF();
		GridBagConstraints gbc_textField_34 = new GridBagConstraints();
		gbc_textField_34.insets = new Insets(0, 0, 5, 0);
		gbc_textField_34.gridx = 5;
		gbc_textField_34.gridy = 11;
		panel_9_center.add(j_t_10_3, gbc_textField_34);
		j_t_10_3.setColumns(10);
		
//		JLabel lblVar_8 = new JLabel("Var 9");
		JLabel lblVar_8 = vve.get(11).getVariableLabel();
		GridBagConstraints gbc_lblVar_8 = new GridBagConstraints();
		gbc_lblVar_8.anchor = GridBagConstraints.EAST;
		gbc_lblVar_8.insets = new Insets(0, 0, 5, 5);
		gbc_lblVar_8.gridx = 0;
		gbc_lblVar_8.gridy = 12;
		panel_9_center.add(lblVar_8, gbc_lblVar_8);
		
//		JCheckBox chckbxNewCheckBox_11 = new JCheckBox("");
		JCheckBox chckbxNewCheckBox_11 = vve.get(11).getFixed();
		chckbxNewCheckBox_11.addItemListener(this);
		GridBagConstraints gbc_chckbxNewCheckBox_11 = new GridBagConstraints();
		gbc_chckbxNewCheckBox_11.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxNewCheckBox_11.gridx = 1;
		gbc_chckbxNewCheckBox_11.gridy = 12;
		panel_9_center.add(chckbxNewCheckBox_11, gbc_chckbxNewCheckBox_11);
		
		JTextField textField_t_11_fixed = vve.get(11).getFixedValue();
		GridBagConstraints gbc_textField_11_fixed = new GridBagConstraints();
		gbc_textField_11_fixed.insets = new Insets(0, 0, 5, 5);
		gbc_textField_11_fixed.gridx = 2;
		gbc_textField_11_fixed.gridy = 12;
		panel_9_center.add(textField_t_11_fixed, gbc_textField_11_fixed);
		textField_t_11_fixed.setColumns(10);
		
		
//		textField_13 = new JTextField();
		JTextField j_t_11_1 = vve.get(11).getMinTF();
		GridBagConstraints gbc_textField_13 = new GridBagConstraints();
		gbc_textField_13.insets = new Insets(0, 0, 5, 5);
		gbc_textField_13.gridx = 3;
		gbc_textField_13.gridy = 12;
		panel_9_center.add(j_t_11_1, gbc_textField_13);
		j_t_11_1.setColumns(10);
		
//		textField_24 = new JTextField();
		JTextField j_t_11_2 = vve.get(11).getMaxTF();
		GridBagConstraints gbc_textField_24 = new GridBagConstraints();
		gbc_textField_24.insets = new Insets(0, 0, 5, 5);
		gbc_textField_24.gridx = 4;
		gbc_textField_24.gridy = 12;
		panel_9_center.add(j_t_11_2, gbc_textField_24);
		j_t_11_2.setColumns(10);
		
//		textField_35 = new JTextField();
		JTextField j_t_11_3 = vve.get(11).getStepsizeTF();
		GridBagConstraints gbc_textField_35 = new GridBagConstraints();
		gbc_textField_35.insets = new Insets(0, 0, 5, 0);
		gbc_textField_35.gridx = 5;
		gbc_textField_35.gridy = 12;
		panel_9_center.add(j_t_11_3, gbc_textField_35);
		j_t_11_3.setColumns(10);
		
		
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
		
		GridBagConstraints gbc_textField_x1 = new GridBagConstraints();
		gbc_textField_x1.insets = new Insets(10, 10, 5, 5);
		
		gbc_textField_x1.gridx = 0;
		gbc_textField_x1.gridy = 0;
		panel_16.add(undoButton,gbc_textField_x1);
			
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
				gbc_textField_x.gridx =   2;
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
		
		
		buildHashTable();
		
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
					return;
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
		
		if(arg0.getSource() == startButton){
			
			if(dataValidation() != null){
				
				this.dispose();
				Runnable r = new Runnable(){
				

				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						ElevatorSimulationMainController x = new ElevatorSimulationMainController();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				};
				r.run();
			}
			else{
				JOptionPane.showMessageDialog(null,"Data Input is Invalid");
			}
			
		}
		
		
	}

	private ElevatorStartData dataValidation() {
		
		ElevatorStartData esd = new ElevatorStartData();
		
		Method s = null;
		Vector<Integer> y = null;
		Field t = null;
		
		for(int i = 0; i < this.vve.size(); i++){
			
			String x = hashMeth.get(i);
			x = WordUtils.capitalize(x);
			
			try {
			//	 s = esd.getClass().getMethod("set"+x);
			//	 s = esd.getClass().getMethod("set"+x,int[].class);
				 s = esd.getClass().getMethod("get"+x,null);
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//s.invoke(obj, args);
			x = WordUtils.uncapitalize(x);
			try {
				 t = esd.getClass().getDeclaredField(x);
			
				y = new Vector<Integer>();
				
				
				Integer min = null;
				Integer max = null;
				Integer step = null;
				try {
					min = Integer.parseInt(vve.get(i).getMinTF().getText());
					max = Integer.parseInt(vve.get(i).getMaxTF().getText());
					step = Integer.parseInt(vve.get(i).getStepsizeTF().getText());
					
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
					continue;
				}
				
				for(int j = min; j <= max; j+= step){
					
					y.add(j);
				}
//				y[0] = 0;
//				y[1] = 1;
//				y[2] = 2;
//				y[3] = 3;
//				y[4] = 4;
				
				try {
					
					int[] x1 = new int[y.size()];
					for(int k = 0; k < x1.length; k++){
						x1[k] = y.get(k);
					}
					t.set(esd, x1);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			int[] z = null;
			try {
				z = (int[]) s.invoke(esd,null);
				
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			//s.invoke(obj, args);
			System.out.println(s.getName());
			for(int m = 0; m < ((int[])z).length; m++){
				System.out.println("m = " + m);
				System.out.println(((int[])z)[m]);
				System.out.println("");
			}
			System.out.println("");
		}
		
//		System.out.println("1?:  " + ElevatorStartData.getAppendDistanceVariable()[1]);
//		System.out.println("2?:  " + ElevatorStartData.getCapacityThresholdVariable()[2]);
//		System.out.println("3?:  " + ElevatorStartData.getCapacityVariable()[3]);
//		
		
		
		return esd;
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		
		if(arg0.getSource() instanceof JCheckBox){
			
			JCheckBox x = ((JCheckBox)arg0.getSource());
			
			if(x.isSelected()){
				
				for(int i = 0; i < vve.size(); i++){
					if(vve.get(i).getFixed().equals(x)){
						vve.get(i).getMaxTF().setVisible(false);
						vve.get(i).getMinTF().setVisible(false);
						vve.get(i).getStepsizeTF().setVisible(false);
						vve.get(i).getFixedValue().setVisible(true);
					}
				}
				
			}
			else{
				for(int i = 0; i < vve.size(); i++){
					if(vve.get(i).getFixed().equals(x)){
						vve.get(i).getFixedValue().setVisible(false);
						vve.get(i).getMaxTF().setVisible(true);
						vve.get(i).getMinTF().setVisible(true);
						vve.get(i).getStepsizeTF().setVisible(true);
					}
				}
			}
			
		}
		
	}
}
