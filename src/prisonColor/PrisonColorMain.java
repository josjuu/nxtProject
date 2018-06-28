package prisonColor;

import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.robotics.Color;
import libary.ColorEye;
import libary.Rover;
import libary.Wheel;

/**
 * This class will let the rover drive around until it moves over the colour assigned to escaping.
 * 
 * @author Jos Mutter
 *
 */
public class PrisonColorMain {
	public byte escapeColor = Color.RED;
	public Rover rover;

	public static void main(String[] args) {
		(new PrisonColorMain()).run();
	}

	/**
	 * This will create the rover and sets the escape colour.
	 */
	public PrisonColorMain() {
		initializeRover();
		setEscapeColor();
	}

	/**
	 * This contains the logic of exercise.
	 */
	public void run() {
		System.out.println("Press any button to start.");
		Button.waitForAnyPress();

		rover.moveForward();

		boolean foundColor = false;
		while (!foundColor) {
			rover.colorEye.scanForColor();
			if (contains(PrisonColors.Floor, (int) rover.colorEye.getLastSeenColorId())) {
				continue;
			} else if (!contains(PrisonColors.Floor, (int) rover.colorEye.getLastSeenColorId())
					&& rover.colorEye.getLastSeenColorId() != escapeColor) {
				this.wait(150);
				rover.colorEye.scanForColor();
				System.out.println(rover.colorEye.getColorName(rover.colorEye.getLastSeenColorId()));
				if (!contains(PrisonColors.Floor, (int) rover.colorEye.getLastSeenColorId())
						&& rover.colorEye.getLastSeenColorId() != escapeColor) {
					stopAndTurn();					
				}
				
				
			} else if (rover.colorEye.getLastSeenColorId() == escapeColor) {
				this.wait(5);
				rover.colorEye.scanForColor();
				if (rover.colorEye.getLastSeenColorId() == escapeColor) {
					foundColor = true;
					endGame();
				}
			}
		}

		rover.stop();

	}

	/**
	 * This will stop the program with a message.
	 */
	public void endGame() {
		this.wait(500);
		rover.stop();
		System.out.println("I have escaped!");
		Button.waitForAnyPress();
	}

	/**
	 * Stops the rover and makes a turn.
	 */
	public void stopAndTurn() {
		rover.stop();
		rover.moveBackward();

		this.wait(500);

		rover.turnRight(45);
		rover.moveForward();
		this.wait(1);
	}

	/**
	 * Creates a rover instance.
	 */
	public void initializeRover() {
		Wheel wheelLeft = new Wheel(Motor.A, 250);
		Wheel wheelRight = new Wheel(Motor.C, 250);
		ColorEye colorEye = new ColorEye(SensorPort.S2);

		rover = new Rover(wheelLeft, wheelRight, colorEye);
	}

	/**
	 * Sets the escape colour by the color eye.
	 */
	public void setEscapeColor() {
		System.out.println("Press any button to scan the escape color");
		Button.waitForAnyPress();
		escapeColor = rover.colorEye.scanForColor();
		System.out.println("Escape color is " + rover.colorEye.getColorName(rover.colorEye.getLastSeenColorId()));
	}

	/**
	 * Lets the rover wait for a given amount of time.
	 * 
	 * @param timeout
	 * 		The time of waiting in milliseconds. 
	 */
	public void wait(int timeout) {
		try {
			Thread.sleep(timeout);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Checks if a given array has the given int value.
	 * 
	 * @param array
	 * 		The array to check in.
	 * @param v
	 * 		The value to check for.
	 * @return
	 * 		Returns true if the value is in the array. Otherwise it will return false.
	 */
	public static boolean contains(final int[] array, final int v) {

		boolean result = false;

		for (int i : array) {
			if (i == v) {
				result = true;
				break;
			}
		}

		return result;
	}
}
