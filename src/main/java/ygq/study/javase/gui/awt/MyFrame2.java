package ygq.study.javase.gui.awt;

import java.awt.Button;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Date;

public class MyFrame2 {

	public static void main(String[] args) {
		
		
		Frame frame = new Frame("My frame2");
		
		Button button = new Button("click me");
		
		button.addActionListener(new MyListener());
		
		frame.add(button);
		frame.addWindowListener(new MyWindowListener());
		
		frame.setSize(480,320);
		
		frame.setVisible(true);
		
	}
}

class MyListener implements ActionListener{
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		long milliSeconds = e.getWhen();
		
		Date data = new Date(milliSeconds);
		
		System.out.println(data.toString());
	}
}

class MyWindowListener implements WindowListener{

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		System.exit(0);
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
	}
	
}
