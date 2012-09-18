package start;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.JScrollBar;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JProgressBar;
import javax.swing.JMenuBar;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.JList;
import java.awt.Font;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Choice;
import javax.swing.JDesktopPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JSlider;
import java.awt.Panel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JButton;
import java.awt.Label;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.JToggleButton;
import javax.swing.JCheckBox;

/**
 * 
 */

/**
 * @author denman
 *
 */

public class ElevatorMainWindow extends JFrame {
	private JTextField textField;
	private JTextField txtMin;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	 
	public ElevatorMainWindow() {
		setAlwaysOnTop(true);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1, 200, 0, 0, 0, 0, 0, 0, 86, 0, 10, 0};
		gridBagLayout.rowHeights = new int[]{1, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 24, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 0;
		getContentPane().add(separator, gbc_separator);
		
		JProgressBar progressBar = new JProgressBar();
		GridBagConstraints gbc_progressBar = new GridBagConstraints();
		gbc_progressBar.insets = new Insets(0, 0, 5, 5);
		gbc_progressBar.gridx = 1;
		gbc_progressBar.gridy = 1;
		getContentPane().add(progressBar, gbc_progressBar);
		
		JButton btnRun = new JButton("RUN");
		GridBagConstraints gbc_btnRun = new GridBagConstraints();
		gbc_btnRun.insets = new Insets(0, 0, 5, 5);
		gbc_btnRun.gridx = 9;
		gbc_btnRun.gridy = 2;
		getContentPane().add(btnRun, gbc_btnRun);
		
		JLabel lblElevatorSimulation = new JLabel("ELEVATOR SIMULATION");
		GridBagConstraints gbc_lblElevatorSimulation = new GridBagConstraints();
		gbc_lblElevatorSimulation.insets = new Insets(0, 0, 5, 5);
		gbc_lblElevatorSimulation.gridx = 1;
		gbc_lblElevatorSimulation.gridy = 3;
		getContentPane().add(lblElevatorSimulation, gbc_lblElevatorSimulation);
		
		JToggleButton tglbtnNewToggleButton = new JToggleButton("New toggle button");
		GridBagConstraints gbc_tglbtnNewToggleButton = new GridBagConstraints();
		gbc_tglbtnNewToggleButton.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnNewToggleButton.gridx = 9;
		gbc_tglbtnNewToggleButton.gridy = 4;
		getContentPane().add(tglbtnNewToggleButton, gbc_tglbtnNewToggleButton);
		
		JLabel lblFixed = new JLabel("Fixed?");
		GridBagConstraints gbc_lblFixed = new GridBagConstraints();
		gbc_lblFixed.insets = new Insets(0, 0, 5, 5);
		gbc_lblFixed.gridx = 2;
		gbc_lblFixed.gridy = 6;
		getContentPane().add(lblFixed, gbc_lblFixed);
		
		JLabel lblMin = new JLabel("Min");
		GridBagConstraints gbc_lblMin = new GridBagConstraints();
		gbc_lblMin.insets = new Insets(0, 0, 5, 5);
		gbc_lblMin.gridx = 3;
		gbc_lblMin.gridy = 6;
		getContentPane().add(lblMin, gbc_lblMin);
		
		JLabel lblMax = new JLabel("Max");
		GridBagConstraints gbc_lblMax = new GridBagConstraints();
		gbc_lblMax.insets = new Insets(0, 0, 5, 5);
		gbc_lblMax.gridx = 4;
		gbc_lblMax.gridy = 6;
		getContentPane().add(lblMax, gbc_lblMax);
		
		JLabel lblStepsize = new JLabel("Stepsize");
		GridBagConstraints gbc_lblStepsize = new GridBagConstraints();
		gbc_lblStepsize.insets = new Insets(0, 0, 5, 5);
		gbc_lblStepsize.gridx = 5;
		gbc_lblStepsize.gridy = 6;
		getContentPane().add(lblStepsize, gbc_lblStepsize);
		
		Label label_1 = new Label("Number of Elevators");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 1;
		gbc_label_1.gridy = 7;
		getContentPane().add(label_1, gbc_label_1);
		
		JSlider slider_1 = new JSlider();
		slider_1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				
				    JSlider source = (JSlider)arg0.getSource();
				    if (!source.getValueIsAdjusting()) {
				        Integer fps = (int)source.getValue();
				     
				     //     textField.setText(fps.toString());
				      //    txtMin.setBackground(Color.red);
				
				    }
				    else{
				    	 Integer fps = (int)source.getValue();
					     
				        //  textField.setText(fps.toString());
				         // txtMin.setBackground(Color.red);
				    }
				}

		});
		slider_1.setMinorTickSpacing(1);
		slider_1.setToolTipText("Adjust Number of Elevators");
		slider_1.setSnapToTicks(true);
		slider_1.setPaintLabels(true);
		slider_1.setMaximum(50);
		slider_1.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				
				textField.setText("Poo");
			}
		});
		GridBagConstraints gbc_slider_1 = new GridBagConstraints();
		gbc_slider_1.insets = new Insets(0, 0, 5, 5);
		gbc_slider_1.gridx = 1;
		gbc_slider_1.gridy = 8;
		getContentPane().add(slider_1, gbc_slider_1);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("");
		GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
		gbc_chckbxNewCheckBox.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxNewCheckBox.gridx = 2;
		gbc_chckbxNewCheckBox.gridy = 8;
		getContentPane().add(chckbxNewCheckBox, gbc_chckbxNewCheckBox);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 3;
		gbc_textField_1.gridy = 8;
		getContentPane().add(textField_1, gbc_textField_1);
		textField_1.setColumns(40);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 4;
		gbc_textField_2.gridy = 8;
		getContentPane().add(textField_2, gbc_textField_2);
		textField_2.setColumns(100);
		
		textField_3 = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 5;
		gbc_textField_3.gridy = 8;
		getContentPane().add(textField_3, gbc_textField_3);
		textField_3.setColumns(100);
		
		txtMin = new JTextField();
		txtMin.setText("MIN");
		GridBagConstraints gbc_txtMin = new GridBagConstraints();
		gbc_txtMin.insets = new Insets(0, 0, 5, 5);
		gbc_txtMin.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMin.gridx = 6;
		gbc_txtMin.gridy = 8;
		getContentPane().add(txtMin, gbc_txtMin);
		txtMin.setColumns(10);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 7;
		gbc_textField.gridy = 8;
		getContentPane().add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblNumberOfFloors = new JLabel("Number Of Floors");
		GridBagConstraints gbc_lblNumberOfFloors = new GridBagConstraints();
		gbc_lblNumberOfFloors.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumberOfFloors.gridx = 1;
		gbc_lblNumberOfFloors.gridy = 9;
		getContentPane().add(lblNumberOfFloors, gbc_lblNumberOfFloors);
		
		JSlider slider = new JSlider();
		slider.setToolTipText("Adjust Number of Floors");
		slider.setMinimum(1);
		slider.setMaximum(150);
		GridBagConstraints gbc_slider = new GridBagConstraints();
		gbc_slider.insets = new Insets(0, 0, 5, 5);
		gbc_slider.gridx = 1;
		gbc_slider.gridy = 10;
		getContentPane().add(slider, gbc_slider);
		
		JCheckBox checkBox = new JCheckBox("");
		GridBagConstraints gbc_checkBox = new GridBagConstraints();
		gbc_checkBox.insets = new Insets(0, 0, 5, 5);
		gbc_checkBox.gridx = 2;
		gbc_checkBox.gridy = 10;
		getContentPane().add(checkBox, gbc_checkBox);
		
		Panel panel = new Panel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridx = 10;
		gbc_panel.gridy = 15;
		getContentPane().add(panel, gbc_panel);
		
		Panel panel_2 = new Panel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 0, 5);
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 20;
		getContentPane().add(panel_2, gbc_panel_2);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnYes = new JMenu("File");
		menuBar.add(mnYes);
		
		JMenuItem mntmSaveRun = new JMenuItem("Save Run");
		mnYes.add(mntmSaveRun);
		
}
	
	public static void main(String[] args){
		ElevatorMainWindow emw =	new ElevatorMainWindow();
		emw.setVisible(true);
		emw.setMaximumSize(new Dimension(600,600));
		emw.setMinimumSize(new Dimension(300,300));
	}

	public JTextField getTextField() {
		return textField;
	}
	
	private static void addPopup(Component component, final JPopupMenu popup) {
	}
}