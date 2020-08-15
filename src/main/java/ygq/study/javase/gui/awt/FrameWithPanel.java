package ygq.study.javase.gui.awt;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;

public class FrameWithPanel extends Frame{

	private static final long serialVersionUID = 5128188484574033657L;

	public FrameWithPanel(String title) {
		super(title);
	}
	
	public static void main(String[] args) {
		
		FrameWithPanel frame = new FrameWithPanel("title");
		
		frame.setSize(200,200);
		frame.setBackground(Color.BLACK);
		frame.setLayout(null);
		
		Panel panel = new Panel();
		
		panel.setSize(100,100);
		panel.setBackground(Color.YELLOW);
		
		frame.add(panel);
		frame.setVisible(true);
		
		frame.setVisible(true);
		
	}
}
