package ygq.study.javase.gui.swing;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class FrameSample {
	
	public static void main(String[] args) {
		
		JFrame frame = new JFrame("FrameSample");
		
		JLabel label = new JLabel("hello world");
		
		frame.getContentPane().add(label,BorderLayout.SOUTH);
		
		frame.addWindowListener(new MyHandler());
		
		frame.pack();
		
		frame.setVisible(true);
		
	}
}

class MyHandler extends WindowAdapter{
	
	@Override
	public void windowClosed(WindowEvent e) {
		System.exit(0);
	}
}


