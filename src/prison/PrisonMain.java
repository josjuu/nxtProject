package prison;

import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import libary.SonarEye;
import libary.Enums.RotationDirection;
import libary.Rover;
import libary.Wheel;

/**
 * This class will let the rover drive around until it thinks it is cornered.
 * 
 * @author Jos Mutter
 *
 */
public class PrisonMain {
	public final int minimumDistance = 15;
	public RotationDirection direction = RotationDirection.Clockwise;
	public Rover rover;

	public static void main(String args[]) {
		(new PrisonMain()).run();
	}

	/**
	 * Constructs the rover.
	 */
	public PrisonMain() {
		initializeRover();
	}

	/**
	 * Contains the logic of the exercise.
	 */
	private void run() {
		boolean cornered = false;
		System.out.println("Press the button to start.");
		Button.waitForAnyPress();

		rover.moveForward();

		while (!cornered) {
			rover.sonarEye.scanDistance();

			if (rover.sonarEye.getLastDistance() <= minimumDistance) {
				rover.smallTurnRight(90);
				rover.sonarEye.scanDistance();

				if (rover.sonarEye.getLastDistance() >= minimumDistance) {
					rover.moveForward();
					continue;
				}

				rover.smallTurnRight(180);
				rover.sonarEye.scanDistance();

				if (rover.sonarEye.getLastDistance() <= minimumDistance) {
					cornered = true;
				} else {
					rover.moveForward();
					continue;
				}
			}
		}

		System.out.println("Looks like I am cornered...");
		Button.waitForAnyPress();
	}

	/**
	 * Creates the rover.
	 */
	private void initializeRover() {
		Wheel wheelLeft = new Wheel(Motor.A, 250);
		Wheel wheelRight = new Wheel(Motor.C, 250);
		SonarEye sonarEye = new SonarEye(SensorPort.S4);

		rover = new Rover(wheelLeft, wheelRight, sonarEye);
	}
}
