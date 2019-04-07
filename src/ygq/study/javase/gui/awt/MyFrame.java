package ygq.study.javase.gui.awt;

import java.awt.Color;
import java.awt.Frame;


@SuppressWarnings("serial")
public class MyFrame extends Frame{

	
	public MyFrame(String title) {
		super(title);
	}
	
	public static void main(String[] args) {
		
		
		MyFrame frame = new MyFrame("我是标题");
		frame.setSize(640,480);
		frame.setBackground(Color.BLUE);
		frame.setVisible(true);
	}
	
}
