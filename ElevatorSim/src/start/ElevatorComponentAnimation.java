package start;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ElevatorComponentAnimation extends JFrame implements Observer, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Vector<JLabel> jlabelVector = null;
	private Vector<JLabel> jlabelEVector = null;
	private JPanel clearPanel = null;
	private Vector<JLabel> floorJLabelVector = null;
	private JPanelElevatorGrid elevatorGridPanel = null;
	private JPanel rootPanel = null;
	private JPanel headerPanel = null;
	private JPanel sidePanel = null;
	BufferedImage img = null;
	ImageIcon imgBlue;
	ImageIcon imgPink;
	ImageIcon imgYellow;
	ImageIcon imgGreen;
	ImageIcon imgCyan;
	private JButton pauseButton = new JButton("PAUSE");
	private Dimension size = null;
	
	public ElevatorComponentAnimation() {

		super("Animation");
		this.setSize(700, 700);
		
		JLabel runNumberLabel = new JLabel(CentralDispatcher.getRunNumber().toString() + " / " + ElevatorSimulationMainController.numberOfRunsTotal);
		
		rootPanel = new JPanel();
		rootPanel.setLayout(new BorderLayout());
		this.add(rootPanel);
		
		headerPanel = new JPanel();
		headerPanel.setBackground(Color.cyan);
		headerPanel.add(runNumberLabel);
		headerPanel.add(pauseButton);
		sidePanel = new JPanel();
		sidePanel.setBackground(Color.yellow);
		elevatorGridPanel = new JPanelElevatorGrid(this.getSize());
		elevatorGridPanel.setOpaque(true);
		
		rootPanel.add(headerPanel, BorderLayout.NORTH);
		rootPanel.add(sidePanel, BorderLayout.EAST);
		rootPanel.add(elevatorGridPanel, BorderLayout.CENTER);
		
		elevatorGridPanel.setBackground(Color.white);
	
		jlabelEVector = new Vector<JLabel>();
		jlabelVector = new Vector<JLabel>();
		floorJLabelVector = new Vector<JLabel>();
		clearPanel = new JPanel();
		clearPanel.setLayout(null);
		clearPanel.setOpaque(false);
	    elevatorGridPanel.add(clearPanel);
	    Insets insets = elevatorGridPanel.getInsets();
	    
	    int width = (int)elevatorGridPanel.getBounds().getWidth();
	    int height = (int)elevatorGridPanel.getBounds().getHeight();
	    
	    clearPanel.setBounds(insets.left, insets.top,width,height);
		
		width = (int) (((clearPanel.getWidth()-200) / ((double) CentralDispatcher.numberOfElevators)));
		height = (int)(((clearPanel.getHeight() -200) / ((double) ElevatorSimulationMainController.numberOfFloors[0]))) - 5;
		
		try {
			img = ImageIO.read(new File("src/start/3d-box.jpg"));
			Image img2 = (Image) img.getScaledInstance(width, height,img.getType());
			imgBlue = new ImageIcon(img2);
			
			img = ImageIO.read(new File("src/start/pink.jpg"));
			img2 = (Image) img.getScaledInstance(width, height,img.getType());
			imgPink = new ImageIcon(img2);
			
			img = ImageIO.read(new File("src/start/yellow.jpg"));
			img2 = (Image) img.getScaledInstance(width, height,img.getType());
			imgYellow = new ImageIcon(img2);
			
			img = ImageIO.read(new File("src/start/green.jpg"));
			img2 = (Image) img.getScaledInstance(width, height,img.getType());
			imgGreen = new ImageIcon(img2);
			
			img = ImageIO.read(new File("src/start/cyan.jpg"));
			img2 = (Image) img.getScaledInstance(width, height,img.getType());
			imgCyan = new ImageIcon(img2);
			
		} catch (IOException e) {

		}
		
	
	 
	    insets = clearPanel.getInsets();

		for (int i = 0; i < CentralDispatcher.getNumberOfElevators(); i++) {
			
			jlabelEVector.add(new JLabel());
			clearPanel.add(jlabelEVector.get(i));
			
			jlabelEVector.get(i).setBounds(insets.left + (int) ((50 + i* ((clearPanel.getWidth()-200) / ((double) CentralDispatcher.numberOfElevators)))),
					insets.top + clearPanel.getHeight() - 80, 50,
					20);
			
			jlabelVector.add(new JLabel(imgBlue));
			jlabelVector.get(i).setVisible(false);
			clearPanel.add(jlabelVector.get(i));
			
			jlabelVector.get(i).setBounds(insets.left + (int) ((50 + i* ((clearPanel.getWidth()-200) / ((double) CentralDispatcher.numberOfElevators)))),
					insets.top + clearPanel.getHeight() - 100 - (Integer) (1) * (clearPanel.getHeight()-200)/CentralDispatcher.getNumberOfFloors(), imgBlue.getIconWidth(),
					imgBlue.getIconHeight());
			jlabelVector.get(i).setVisible(true);
		}

	    
	    
		for (int i = 0; i < CentralDispatcher.getNumberOfFloors(); i++) {

			floorJLabelVector.add(new JLabel("---"));
			clearPanel.add(floorJLabelVector.get(i));
			floorJLabelVector.get(i).setBounds(clearPanel.getWidth() - 100,
					(int) (clearPanel.getHeight() - 100 - (i+1)
							* ((clearPanel.getHeight()-200) / ((double) ElevatorSimulationMainController.numberOfFloors[0]))), 140, 20);

		}
		
		this.size = getSize();
		
		
		 this.addComponentListener(new ComponentAdapter() {
	            public void componentResized(ComponentEvent e) {
	            	
	            	
	            	double widthRatio = ((Component) e.getSource()).getBounds().getWidth()/(size.width);
	            	double heightRatio =((Component) e.getSource()).getBounds().getHeight()/(size.height);
	            	
	            	size = getSize();
	            	
	            	rootPanel.remove(elevatorGridPanel);
	            	
	            	elevatorGridPanel.revalidate();
	            	
	            	elevatorGridPanel = new JPanelElevatorGrid(size);
	            	
	            	rootPanel.add(elevatorGridPanel, BorderLayout.CENTER);
	            	
	            	elevatorGridPanel.add(clearPanel);
	         	    Insets insets = elevatorGridPanel.getInsets();
	         	   clearPanel.setBounds(insets.left, insets.top, (int)elevatorGridPanel.getBounds().getWidth(), (int)elevatorGridPanel.getBounds().getHeight());
	       		
	         	    
	            	elevatorGridPanel.revalidate();
	              
	            	Component[] jc = clearPanel.getComponents();
	            	
	            	for(Component c: jc){
	            		
//	            		if(c instanceof JLabel){
//	            			Icon icon = ((JLabel) c).getIcon();
//	            			if(icon != null){
////	            				icon.paintIcon(c, c.getGraphics(), (int)(c.getWidth()*widthRatio), (int)(c.getHeight()*heightRatio));
////	            		
////	            				((JLabel) c).setIcon(icon);
//	            			
//	            			}
//	            		}
	            		c.setBounds((int)(c.getX()*widthRatio), (int)(c.getY()*heightRatio), (int)(c.getWidth()*widthRatio), (int)(c.getHeight()*heightRatio));
	            		
	            	}
	            	
	                clearPanel.revalidate();
	            }
	        });
		
		pauseButton.addActionListener(this);
		
	}

	@Override
	public synchronized void update(Observable arg0, Object arg1) {
		
		CentralDispatcher.getEca().validate();

		if (arg0 instanceof Elevator) {
			
			if(arg1 instanceof Color){
				if(arg1.equals(Color.blue)){
				jlabelVector.get(((Elevator) arg0).getElevatorNumberId()).setIcon(imgBlue);
				}
				else if(arg1.equals(Color.green)){
					jlabelVector.get(((Elevator) arg0).getElevatorNumberId()).setIcon(imgGreen);
				}
				else if(arg1.equals(Color.yellow)){
					jlabelVector.get(((Elevator) arg0).getElevatorNumberId()).setIcon(imgYellow);
				}
				else if(arg1.equals(Color.cyan)){
					jlabelVector.get(((Elevator) arg0).getElevatorNumberId()).setIcon(imgCyan);
				}
				else if(arg1.equals(Color.pink)){
					jlabelVector.get(((Elevator) arg0).getElevatorNumberId()).setIcon(imgPink);
				}
				
				
			}
			
			if(arg1 instanceof Integer){

				
			jlabelVector.get(((Elevator) arg0).getElevatorNumberId())
					.setLocation(
							jlabelVector.get(
									((Elevator) arg0).getElevatorNumberId())
									.getX(),
							clearPanel.getHeight() - 100 - ((Integer) (arg1) + 1) * (clearPanel.getHeight()-200)/CentralDispatcher.getNumberOfFloors());
			   }
			
			
			  if(arg1 instanceof String){
				  
				  if(((String) arg1).contains("E")){
					  String newString = (String) ((String) arg1).replace("E", "");
					  Integer m =Integer.parseInt(newString);
					  
					  
		        jlabelEVector.get(((Elevator) arg0).getElevatorNumberId())
						.setText(m.toString());
								
				 			  
					  
					  
				  } else{
				  
				  int floor = ((Elevator) arg0).getCurrentFloor();
				  
				  floorJLabelVector.get(floor)
					.setText((String)arg1);
				  }
			  }
			}
		
		
		

		if (arg0 instanceof FloorOfBuilding) {
			
			floorJLabelVector.get(((FloorOfBuilding) arg0).getFloorNumber())
					.setText((String)arg1);

		}

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		try {
			if(CentralDispatcher.isSimulationPaused()){
			CentralDispatcher.setSimulationPaused(false);
			}
			else{
				CentralDispatcher.setSimulationPaused(true);
			}
			}
		
	catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
	}

}
