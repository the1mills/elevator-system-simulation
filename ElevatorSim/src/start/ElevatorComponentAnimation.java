package start;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ElevatorComponentAnimation extends JFrame implements Observer {

	private BufferedImage buffimage = null;
	private CentralDispatcher cd = null;
	private Vector<JLabel> jlabelVector = null;
	private JPanel panel = null;
	private Insets insets = null;
	private Vector<JLabel> floorJLabelVector = null;
	private JPanelElevatorGrid aJPanelElevatorGrid = null;
	private JPanel rootPanel = null;
	private JPanel headerPanel = null;
	private JPanel sidePanel = null;
	BufferedImage img = null;
	ImageIcon imgBlue;
	ImageIcon imgPink;
	ImageIcon imgYellow;
	ImageIcon imgGreen;
	ImageIcon imgCyan;
	
	public ElevatorComponentAnimation(CentralDispatcher cd) {

		super("Animation");
		this.setSize(700, 700);
		this.cd = cd;
		
		rootPanel = new JPanel();
		rootPanel.setLayout(new BorderLayout());
		this.add(rootPanel);
		
		headerPanel = new JPanel();
		headerPanel.setBackground(Color.cyan);
		sidePanel = new JPanel();
		sidePanel.setBackground(Color.yellow);
		aJPanelElevatorGrid = new JPanelElevatorGrid(this.getSize());
		aJPanelElevatorGrid.setOpaque(true);
		
		rootPanel.add(headerPanel, BorderLayout.NORTH);
		rootPanel.add(sidePanel, BorderLayout.EAST);
		rootPanel.add(aJPanelElevatorGrid, BorderLayout.CENTER);
	
		jlabelVector = new Vector<JLabel>();
		floorJLabelVector = new Vector<JLabel>();
		panel = new JPanel();
		panel.setLayout(null);
		panel.setOpaque(false);
		panel.setBackground(Color.black);
	    aJPanelElevatorGrid.add(panel);
	    Insets insets = aJPanelElevatorGrid.getInsets();
	    panel.setBounds(insets.left, insets.top, 700, 700);
		
		Integer width = (int) (((panel.getWidth()-200) / ((double) CentralDispatcher.numberOfElevators)));
		Integer height = (int)(((panel.getHeight() -200) / ((double) ElevatorSimulationMainController.numberOfFloors))) - 5;
		
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
		
	
	 
	    insets = panel.getInsets();

		for (int i = 0; i < cd.getNumberOfElevators(); i++) {
			jlabelVector.add(new JLabel(imgBlue));
			jlabelVector.get(i).setVisible(false);
			panel.add(jlabelVector.get(i));
			
			jlabelVector.get(i).setBounds(insets.left + (int) ((50 + i* ((panel.getWidth()-200) / ((double) CentralDispatcher.numberOfElevators)))),
					insets.top + panel.getHeight() - 100 - (Integer) (1) * (panel.getHeight()-200)/this.cd.getNumberOfFloors(), imgBlue.getIconWidth(),
					imgBlue.getIconHeight());
			jlabelVector.get(i).setVisible(true);
		}

		for (int i = 0; i < cd.getNumberOfFloors(); i++) {

			floorJLabelVector.add(new JLabel(""));
			panel.add(floorJLabelVector.get(i));
			floorJLabelVector.get(i).setBounds(insets.left + 600,
					(int) (panel.getHeight() - 100 - (i+1)
							* ((panel.getHeight()-200) / ((double) ElevatorSimulationMainController.numberOfFloors))), 40, 20);

		}

	}

	@Override
	public synchronized void update(Observable arg0, Object arg1) {

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
							panel.getHeight() - 100 - ((Integer) (arg1) + 1) * (panel.getHeight()-200)/this.cd.getNumberOfFloors());
			}
			}

		if (arg0 instanceof FloorOfBuilding) {
			
			floorJLabelVector.get(((FloorOfBuilding) arg0).getFloorNumber())
					.setText(((Integer) arg1).toString());

		}

	}

}
