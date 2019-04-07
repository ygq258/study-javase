package ygq.study.javase.gui.awt;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonSample {

	public static void main(String[] args) {
		
		
		Frame frame = new Frame("Test Button");
		
		Button button = new Button("Press Me!");
		
		//增加事件处理器
		button.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(e.getActionCommand());
				System.out.println(e.getID());
				System.out.println(e.getModifiers());
				System.out.println(e.getSource());
				System.out.println(e.getWhen());
			}
		});
		
		frame.add(button, BorderLayout.CENTER);
		
		frame.pack();
		frame.setVisible(true);
//		Container
	}
	
}
