package ygq.study.javase.bases.innerclass;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
/**
 * 常见系统内部类的使用实例
 * @author yang
 */
public class SwingInnerClassTest {

	public static void main(String[] args) {

		JFrame frame = new JFrame("JFrame");

		JButton button = new JButton("click me");

		button.addActionListener(new MyListener());
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("welcome");
			}
			
		});
		
		frame.setSize(320, 240);
		
		frame.getContentPane().add(button);

		frame.setVisible(true);

	}
}

class MyListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("hello world");
	}
}