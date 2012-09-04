package start;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.RenderingHints;

import javax.swing.JPanel;


public class JPanelElevatorGrid extends JPanel {
	
	Graphics g = null;
	Graphics2D g2d = null;
	Dimension size = null;

	public JPanelElevatorGrid(Dimension size) {
	
		this.setLayout(null);
		this.setOpaque(true);
		this.size = size;
	}

	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		this.setOpaque(true);
			g2d = (Graphics2D) g;
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			int width = 2;
			g2d.setColor(Color.gray);
			g2d.setStroke(new BasicStroke(width));

			Font font1 = new Font("HELVETICA", Font.BOLD, (int) (Math.min(Math.max(
					11, 300 / ElevatorSimulationMainController.numberOfFloors), 40)));
			g2d.setFont(font1);

			// HORIZONTAL LINES
			for (Integer i = 0; i < ElevatorSimulationMainController.numberOfFloors + 1; i++) {
				g2d.drawLine(50, (int) (size.getHeight() - 100 - i
						* ((size.getHeight()-200) / ((double) ElevatorSimulationMainController.numberOfFloors))), (int) size.getWidth() - 150, (int) (size
						.getHeight() - 100 - i
						* ((size.getHeight()-200)/ ((double) ElevatorSimulationMainController.numberOfFloors))));

				// DRAW Floor NUMBERS
				if (i == ElevatorSimulationMainController.numberOfFloors) {
					break;
				}
				g2d
						.drawString(
								i.toString(),
								20,
								(int)size.getHeight()
										- 100
										- (int) (i * ((size.getHeight()-200)/ (double) ElevatorSimulationMainController.numberOfFloors)));
			}

			width = 2;
			g2d.setColor(Color.gray);
			g2d.setStroke(new BasicStroke(width));

			// VERTICAL LINES
			for (Integer i = 0; i < CentralDispatcher.numberOfElevators + 1; i++) {
				g2d.drawLine((int) (50 + i* ((size.getWidth()-200) / ((double) CentralDispatcher.numberOfElevators))), 
						(int)size.getHeight() - 100, 
						(int) (50 + i* ((size.getWidth()-200) / ((double) CentralDispatcher.numberOfElevators))), 
						150);
			}
			
			g.finalize();
			g2d.finalize();
		}
	}

