package org.jointheleague.stephen.practice;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DuelingButtons implements ActionListener {
	
	public static void main(String[] args) {
		new DuelingButtons().createUI();
	}

	JButton leftButton = new JButton();
	JButton rightButton = new JButton();
	
	Dimension BIG = new Dimension(400,400);
	Dimension SMALL = new Dimension(200,200);
	
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();

	private void createUI() {
		frame.add(panel);
		frame.setVisible(true);
		leftButton.setText("Click Me!");
		rightButton.setText("Click Me!");
		leftButton.addActionListener(this);
		rightButton.addActionListener(this);
		panel.add(leftButton);
		panel.add(rightButton);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Demanding Buttons");
		frame.pack();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton buttonPressed = (JButton) arg0.getSource();
		
		
		if (buttonPressed.equals(leftButton)) {
			rightButton.setText("No, Click Me!");
			rightButton.setPreferredSize(BIG);
			leftButton.setText("Click Me!");
			leftButton.setPreferredSize(SMALL);
		}
		if (buttonPressed.equals(rightButton)) {
			leftButton.setText("No, Click Me!");
			leftButton.setPreferredSize(BIG);
			rightButton.setText("Click Me!");
			rightButton.setPreferredSize(SMALL);
		}
		frame.pack();
	}
}

