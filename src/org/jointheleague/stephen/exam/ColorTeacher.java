package org.jointheleague.stephen.exam;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ColorTeacher implements ActionListener {
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JButton yellowButton = new JButton();
	JButton redButton = new JButton();
	JButton blueButton = new JButton();
	JButton greenButton = new JButton();

	public static void main(String[] args) {
		new ColorTeacher().createUI();
	}

	private void createUI() {
		frame.add(panel);
		frame.setVisible(true);
		yellowButton.addActionListener(this);
		redButton.addActionListener(this);
		blueButton.addActionListener(this);
		greenButton.addActionListener(this);
		panel.add(yellowButton);
		panel.add(redButton);
		panel.add(blueButton);
		panel.add(greenButton);
		yellowButton.setBackground(Color.YELLOW);
		redButton.setBackground(Color.RED);
		blueButton.setBackground(Color.BLUE);
		greenButton.setBackground(Color.GREEN);
		yellowButton.setOpaque(true);
		redButton.setOpaque(true);
		blueButton.setOpaque(true);
		greenButton.setOpaque(true);
		yellowButton.setToolTipText("yellow");
		redButton.setToolTipText("red");
		blueButton.setToolTipText("blue");
		greenButton.setToolTipText("green");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Color Teacher");
		frame.pack();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton buttonPressed = (JButton) e.getSource();
		speak(buttonPressed.getToolTipText());
	}

	private void speak(String words) {
		try {
			Runtime.getRuntime().exec("say " + words);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
