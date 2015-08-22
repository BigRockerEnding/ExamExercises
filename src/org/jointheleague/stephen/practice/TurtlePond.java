package org.jointheleague.stephen.practice;

import java.awt.Color;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Label;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.teachingextensions.logo.Colors;
import org.teachingextensions.logo.Tortoise;
import org.teachingextensions.logo.TurtlePanel;

public class TurtlePond implements KeyEventDispatcher, Runnable {

	int cookieX = 50;
	int cookieY = 50;

	int speed = 10;

	void setup() {
		JOptionPane.showMessageDialog(null,
				"Use the the arrow keys to find the hidden food before you starve!"
						+ "\nDon't touch your own path!");
		showFood();
	}

	private void goUp() {
		Tortoise.setAngle(0);
		Tortoise.move(speed);
	}

	private void goDown() {
		Tortoise.setAngle(180);
		Tortoise.move(speed);
	}

	private void goLeft() {
		Tortoise.setAngle(270);
		Tortoise.move(speed);
	}

	private void goRight() {
		Tortoise.setAngle(90);
		Tortoise.move(speed);
	}

	private void checkForFood() throws Exception {
		int tortoiseLocationX = Tortoise.getX();
		int tortoiseLocationY = Tortoise.getY();
		double distance = Point2D.distance(cookieX, cookieY, tortoiseLocationX,
				tortoiseLocationY);

		if (distance > 100) {
			this.setBackgroundColor(Colors.Grays.DarkGray);
		}
		if (distance < 100) {
			this.setBackgroundColor(Colors.Yellows.Yellow);
		}
		if (distance < 50) {
			this.setBackgroundColor(Colors.Oranges.Orange);
		}
		if (distance < 20) {
			this.setBackgroundColor(Colors.Reds.Red);
		}
		if (distance < 5) {
			JOptionPane.showMessageDialog(null, "Congratulations!");
			System.exit(0);
		}
		if (wasHereBefore(tortoiseLocationX, tortoiseLocationY)) {
			Tortoise.setX(previousLocations.get(0).x);
			Tortoise.setY(previousLocations.get(0).y);
			Tortoise.clear();
			this.setBackgroundColor(Colors.Grays.DarkGray);
			previousLocations.clear();
		}

		if (getTimeElapsed() > 20) {
			JOptionPane.showMessageDialog(null, "The turtle died of hunger.",
					"Game Over", JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}
	}

	private long getTimeElapsed() {
		Date currentTime = new Date();
		return (currentTime.getTime() - startTime.getTime()) / 1000;
	}

	void setBackgroundColor(Color color) {
		Tortoise.getBackgroundWindow().setBackground(color);
	}

	private void hideFood() {
		window.remove(component);
	}

	private void showFood() {
		// If the food doesn't show up, make sure you are on Java 1.6
		component.setLocation(cookieX, cookieY);
		window.add(component);
	}

	private boolean wasHereBefore(int xPosition, int yPosition) {
		if (previousLocations.contains(new Point(xPosition, yPosition)))
			return true;
		else
			return false;
	}

	/*********************** don't worry about the stuff under here ******************/

	TurtlePanel window = Tortoise.getBackgroundWindow();
	Label component = new Label("*");
	Date startTime = new Date();

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new TurtlePond());
	}

	@Override
	public void run() {
		controlTheTortoise();
		setup();
	}

	private void controlTheTortoise() {
		KeyboardFocusManager.getCurrentKeyboardFocusManager()
				.addKeyEventDispatcher(this);
		Tortoise.show();
		Tortoise.setPenColor(Colors.Purples.DarkMagenta);
		Tortoise.getBackgroundWindow().setBackground(Colors.Grays.SlateGray);
		Tortoise.setSpeed(10);
	}

	public boolean dispatchKeyEvent(KeyEvent e) {
		if (e.getID() == KeyEvent.KEY_PRESSED) {

			if (e.getKeyCode() == KeyEvent.VK_RIGHT)
				goRight();
			if (e.getKeyCode() == KeyEvent.VK_LEFT)
				goLeft();
			if (e.getKeyCode() == KeyEvent.VK_UP)
				goUp();
			if (e.getKeyCode() == KeyEvent.VK_DOWN)
				goDown();

			try {
				checkForFood();
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			savePosition(Tortoise.getX(), Tortoise.getY());
		}
		return false;
	}

	ArrayList<Point> previousLocations = new ArrayList<Point>();

	private void savePosition(int xPosition, int yPosition) {
		previousLocations.add(new Point(xPosition, yPosition));
	}
}
