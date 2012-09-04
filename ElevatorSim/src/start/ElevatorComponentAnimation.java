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
	
		
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("src/start/3d-box.jpg"));
		} catch (IOException e) {
		}
		// buffimage = BufferedImage.resize(img, 200);
		Image img2 = (Image) img.getScaledInstance(30, 50,img.getType());
		ImageIcon imgi = new ImageIcon(img2);
		jlabelVector = new Vector<JLabel>();
		floorJLabelVector = new Vector<JLabel>();
		panel = new JPanel();
		panel.setLayout(null);
		panel.setOpaque(false);
		panel.setBackground(Color.black);
	    aJPanelElevatorGrid.add(panel);
	    Insets insets = aJPanelElevatorGrid.getInsets();
	    panel.setBounds(insets.left, insets.top, 700, 600);
	 
	    insets = panel.getInsets();

		for (int i = 0; i < cd.getNumberOfElevators(); i++) {
			jlabelVector.add(new JLabel(imgi));
			jlabelVector.get(i).setVisible(false);
			panel.add(jlabelVector.get(i));
			jlabelVector.get(i).setBounds(insets.left + ((this.getWidth()- 100)/this.cd.getNumberOfElevators()) * i + 50,
					insets.top + this.getHeight() - 30, imgi.getIconWidth(),
					imgi.getIconHeight());
			jlabelVector.get(i).setVisible(true);
		}

		for (int i = 0; i < cd.getNumberOfFloors(); i++) {

			floorJLabelVector.add(new JLabel("rrrr"));
			panel.add(floorJLabelVector.get(i));
			floorJLabelVector.get(i).setBounds(insets.left + 300,
					panel.getHeight() - 30 - 30 * i, 40, 20);

		}

	}

	@Override
	public synchronized void update(Observable arg0, Object arg1) {

		if (arg0 instanceof Elevator) {
			System.out.println(arg1.getClass().getDeclaredMethods());
			System.out.println(arg1.getClass().getName());
			System.out.println(arg1.getClass().getEnclosingMethod());
			
			
			jlabelVector.get(((Elevator) arg0).getElevatorNumberId())
					.setLocation(
							jlabelVector.get(
									((Elevator) arg0).getElevatorNumberId())
									.getX(),
							panel.getHeight() - 30 - (Integer) arg1 * (this.getHeight()/this.cd.getNumberOfFloors()));
		}

		if (arg0 instanceof FloorOfBuilding) {
			System.out.println(arg1.getClass().getDeclaredMethods());
			System.out.println(arg1.getClass().getName());
				System.out.println(arg1.getClass().getEnclosingMethod());
			floorJLabelVector.get(((FloorOfBuilding) arg0).getFloorNumber())
					.setText(((Integer) arg1).toString() + "$$$$");
			System.out.println(arg1.toString() + "TTTTTTTTTTTTTTT");
		}

	}

}
