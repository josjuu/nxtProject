package line;

import lejos.nxt.ColorSensor.Color;
import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import libary.ColorEye;
import libary.Rover;
import libary.Wheel;

/**
 * This class will let the rover drive around to follow a line.
 * 
 * @author Jos Mutter
 *
 */
public class LineMain {
	public Rover rover;
	public byte colorToFollow = (byte) Color.GREEN;

	public static void main(String[] args) {
		(new LineMain()).run();
	}

	/**
	 * Upon construction of the class, it will create a rover and sets the colour to follow.
	 */
	public LineMain() {
		initializeRover();
		setFollowColor();
	}

	/**
	 * Contains the logic of the exercise.
	 */
	private void run() {
		boolean triedLeft = false;
		boolean tryingLeft = false;
		boolean triedRight = false;
		boolean tryingRight = false;
		boolean firstTryRight = true;
		int degree = 0;

		Button.waitForAnyPress();

		rover.moveForward();

		while (true) {
			rover.colorEye.scanForColor();

			if (rover.colorEye.getLastSeenColorId() == colorToFollow) {
				if(tryingLeft) {
					rover.smallTurnLeft(5);
				}
				
				if(tryingRight) {
					rover.smallTurnRight(5);
				}
				
				rover.moveForward();
				triedLeft = false;
				triedRight = false;
				tryingLeft = false;
				tryingRight = false;
				firstTryRight = true;
				degree = 0;
				continue;
			}

			rover.stop();
			if (!triedLeft) {
				
				
				tryingLeft = true;
				tryingRight = false;
				rover.smallTurnLeft(5);

				degree += 5;
				if (degree == 95) {
					triedLeft = true;
					// rover.smallTurnRight(90);
					degree = 0;
					continue;
				}
			}

			if (triedLeft && !triedRight) {
				if(firstTryRight) {
					rover.smallTurnRight(90);
					firstTryRight = false;
				}
				
				tryingLeft = false;
				tryingRight = true;
				
				rover.smallTurnRight(5);

				if (degree == 95) {
					triedRight = true;
				}
				degree += 5;
			}
		}
	}

	/**
	 * Sets the colour to follow by using the colorEye.
	 */
	private void setFollowColor() {
		System.out.println("Press any button to scan the follow color");
		Button.waitForAnyPress();
		colorToFollow = rover.colorEye.scanForColor();
		System.out.println("Follow color is " + rover.colorEye.getColorName(rover.colorEye.getLastSeenColorId()));
	}

	/**
	 * Creates the rover.
	 */
	private void initializeRover() {
		Wheel wheelLeft = new Wheel(Motor.A, 250);
		Wheel wheelRight = new Wheel(Motor.C, 250);
		ColorEye colorEye = new ColorEye(SensorPort.S2);

		rover = new Rover(wheelLeft, wheelRight, colorEye);
	}
}
