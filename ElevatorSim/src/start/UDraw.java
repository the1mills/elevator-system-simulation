package start;


import java.awt.Color;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class UDraw extends JFrame implements Runnable{
	
	private JLabel label;

	public UDraw(){
		JPanel panel = new JPanel();
		 ImageIcon image = new ImageIcon("src/box.jpg");
		label = new JLabel(image);
//		label.setForeground(Color.red);
//		label.setBackground(Color.red);
		panel.setLayout(null);
		panel.add(label);
		Insets insets = panel.getInsets();
		label.setBounds(49, 25, image.getIconWidth(), image.getIconHeight());
		label.setVisible(true);
		label.setLocation(20, 30);
		//JScrollPane jsp = new JScrollPane(panel);
		//jsp.add(panel);
		this.getContentPane().add(panel);
		
		
	}
	
	@Override
	public void run() {
		
		int x = 0;
		while(x < 500){
			
			label.setLocation(label.getX() + 1, label.getY() + 1);
			x++;
			System.out.println(label.getX());
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	public static void main (String[] args){
		
		UDraw ud = new UDraw();
		ud.setSize(400,400);
		ud.setVisible(true);
		Thread td = new Thread(ud);
		td.start();
		
		
	}
	
}
