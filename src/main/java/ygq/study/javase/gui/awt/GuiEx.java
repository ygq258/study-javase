package ygq.study.javase.gui.awt;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;

public class GuiEx {

	private Frame frame;
	
	private Button button1;
	private Button button2;
	
	public void go(){
		
		frame = new Frame("guid example");
		frame.setLayout(new BorderLayout());
		button1 = new Button("press me");
		button2 = new Button("Don't press me");
		
		frame.add(button1, BorderLayout.NORTH);
		frame.add(button2, BorderLayout.SOUTH);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		GuiEx window = new GuiEx();
		
//		window.go();
//		JWindow w = new JWindow();
		JFrame w = new JFrame();
		JMenu menu = new JMenu();
		menu.setName("xix ");
		JButton b = new JButton();
		b.setSize(100, 50);
		b.setName("");
		b.setText("提交");
		w.add(b);
		w.add(menu);
		w.setName("哈哈");
		w.setSize(200, 300);
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLUE);
		w.add(panel);
		w.setVisible(true);
		
		
	}

}
