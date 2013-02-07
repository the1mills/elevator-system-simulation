package start;


import javax.swing.JFrame;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ElevatorAnimation extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;
	public static Graphics g;
	public static Graphics2D g2d = (Graphics2D) g;
	private int numberOfFloors;
	private int numberOfElevators;
	private JPanel panel = new JPanel();
	private CentralDispatcher cd;
	private int[] currentFloorArray;

	public ElevatorAnimation(CentralDispatcher cd, int numberOfFloors, int numberOfElevators) {
		super("Elevator Animation");
		this.cd = cd;
		this.setVisible(true);
		JFrame.setDefaultLookAndFeelDecorated(true);
		
		panel.setOpaque(true);
		panel.setBackground(Color.white);
		this.setContentPane(panel);
		this.numberOfFloors = numberOfFloors;
		this.numberOfElevators = numberOfElevators;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int sw = (screen.width);
		int sh = (screen.height);
		this.setBackground(Color.white);
		int w = 980;
		int h = 950;
		this.setTitle("Elevator Animation");
		this.setSize(w, h);
		this.setResizable(false);
		this.setLocation(sw / 2 - 450 / 2, sh / 2 - 300);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setVisible(true);
		//this.hide();
		
		currentFloorArray = new int[cd.getNumberOfElevators()];
		
		for(int i = 0 ; i < cd.getNumberOfElevators(); i++){
			currentFloorArray[i] = cd.getElevatorArray()[i].getCurrentFloor();
		}
		
		paintBackground(panel.getGraphics());
	}

	public synchronized void paint(Graphics g, int x0, int y0, int x1, int y1,
			Color color, String numberOnElevator) {
		g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(color);
	//	g2d.fillRect(x0, this.getHeight() - y0, x1, y1);
		g2d.setColor(Color.black);
		int width = 1;
		g2d.setStroke(new BasicStroke(width));
		Font font1 = new Font("HELVETICA", Font.BOLD, 10);
		g2d.setFont(font1);
		g2d.drawString(numberOnElevator.toString(), x0 + x1/10, this.getHeight() - y0 + y1*2/3);
	}

	
	public synchronized void paint(Graphics g, int y0,
			Integer numberOfPeopleWaiting, int horizDist, int letterSize) {
		g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		int width = 1;
		g2d.setStroke(new BasicStroke(width));
		Font font1 = new Font("HELVETICA", Font.BOLD, letterSize);
		g2d.setFont(font1);
		g2d.setColor(Color.blue);
		g2d.drawString(numberOfPeopleWaiting.toString(), 765  + letterSize + horizDist, this.getHeight()
				- 100
				- (int) (y0 * ((double) 700 / (double) this.numberOfFloors)));
	}
	
	
	public synchronized void paintBackground(Graphics g) {

		g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		int width = 2;
		g2d.setColor(Color.black);
		g2d.setStroke(new BasicStroke(width));

		Font font1 = new Font("HELVETICA", Font.BOLD, (int) (Math.min(Math.max(
				11, 300 / this.numberOfFloors), 40)));
		g2d.setFont(font1);

		// HORIZONTAL LINES
		for (Integer i = 0; i < this.numberOfFloors + 1; i++) {
			g2d.drawLine(50, (int) (this.getHeight() - 100 - i
					* (700 / ((double) this.numberOfFloors))), 750, (int) (this
					.getHeight() - 100 - i
					* (700 / ((double) this.numberOfFloors))));

			// DRAW Floor NUMBERS
			if (i == this.numberOfFloors) {
				break;
			}
			g2d
					.drawString(
							i.toString(),
							20,
							this.getHeight()
									- 100
									- (int) (i * ((double) 700 / (double) this.numberOfFloors)));
		}

		width = 2;
		g2d.setColor(Color.black);
		g2d.setStroke(new BasicStroke(width));

		// VERTICAL LINES
		for (Integer i = 0; i < this.numberOfElevators + 1; i++) {
			g2d.drawLine((int) (50 + i
					* (700 / ((double) this.numberOfElevators))), this
					.getHeight() - 100, (int) (50 + i
					* (700 / ((double) this.numberOfElevators))), 50);
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public void run() {
//	
//		while(CentralDispatcher.isKeepLooping()){
//			
//			try {
//				Thread.sleep(10);
//				this.validate();
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			for(int i =0; i < cd.getNumberOfElevators(); i++){
//				if(currentFloorArray[i] != cd.getElevatorArray()[i].getCurrentFloor()){
//					g = this.getGraphics();
//					g2d = (Graphics2D) g;
//					g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//					int width = 1;
//					g2d.setColor(Color.white);
//					g2d.setStroke(new BasicStroke(width));
//					g2d.fillRect(760, this.getHeight()
//							- 100
//							- (int) ((currentFloorArray[i]+1) * ((double) 700 / (double) this.numberOfFloors)), 300, (int)((double) 700 / (double) this.numberOfFloors));
//				
//					g2d.setColor(Color.blue);
//					
//					currentFloorArray[i] = cd.getElevatorArray()[i].getCurrentFloor();
//				
//					g2d.fillRect(760, this.getHeight()
//							- 100
//							- (int) ((currentFloorArray[i]+1) * ((double) 700 / (double) this.numberOfFloors)), 300, (int)((double) 700 / (double) this.numberOfFloors));
//				
//				}
//			}
//			
//		}
//	}
}
