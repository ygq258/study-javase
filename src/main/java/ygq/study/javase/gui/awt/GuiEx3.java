package ygq.study.javase.gui.awt;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Panel;

public class GuiEx3 {

	private Frame frame;
	private Panel panel;

	private Button b1, b2, b3, b4;

	public void go() {

		frame = new Frame("complex layout");
		b1 = new Button("West");
		b2 = new Button("Hello");
		b3 = new Button("world");
		b4 = new Button("welcome");
		
		frame.add(b1,BorderLayout.WEST);
		frame.add(b2,BorderLayout.CENTER);
		
		panel = new Panel(); 
		
		panel.add(b3);
		panel.add(b4);
		
		frame.add(panel, BorderLayout.NORTH);
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		
		GuiEx3 gui = new GuiEx3();
		gui.go();
		
		
	}

}
