package ygq.study.javase.gui.awt;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class TwoListen implements MouseListener, MouseMotionListener {

	private Frame frame;

	private TextField textField;

	public void go() {

		frame = new Frame("Two Listeners Example");

		frame.add(new Label("click"), BorderLayout.NORTH);

		textField = new TextField(30);

		frame.add(textField);

		frame.addMouseMotionListener(this);
		//frame.addMouseListener(this);
		frame.addMouseListener(new MyAdapter());
		frame.addMouseListener(new MyMouseListener());

		frame.add(textField, BorderLayout.SOUTH);

		frame.setSize(320, 240);
		frame.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

		String str = "The mouse has left the Frame";
		this.textField.setText(str);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

		this.textField.setText("x:" + e.getX() + ",y:" + e.getY());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {

		TwoListen two = new TwoListen();

		two.go();
	}
}

class MyMouseListener implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		String str = "The mouse has entered the Frame";
		
		System.out.println(str);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
		String str = "The mouse has exited the Frame";
		System.out.println(str);
	}

}


class MyAdapter extends MouseAdapter{
	
	
	@Override
	public void mouseEntered(MouseEvent e) {
		
		String str = "The mouse has entered the Frame";
		System.out.println(str);
	}
	
	
	@Override
	public void mouseExited(MouseEvent e) {
		String str = "The mouse has exited the Frame";
		System.out.println(str);
	}
	
	
	
}
