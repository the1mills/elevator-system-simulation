package start;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class ElevatorImage extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Graphics2D g2d = null;
	private Integer width_;
	private Integer height_;
	
	public ElevatorImage(Integer width, Integer height, int currentFloor, int elevatorNumber){
		
		setOpaque(true);
		this.width_ = width;
		this.height_ = height;
		setSize(width,height);
		setBackground(Color.blue);
	}
	
	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		g2d = (Graphics2D)g;
		g2d.setColor(Color.green);
		//g2d.draw3DRect(10, 10, width_ - 10, height_ - 10, true);
		g2d.drawLine(10, 10, width_ - 10, height_ - 10);
		g2d.finalize();
	}
}
