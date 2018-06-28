package libary;

/**
 * The class is representation of our rover.
 * 
 * @author Jos Mutter
 */
public class Rover {
	public Wheel wheelLeft;
	public Wheel wheelRight;
	public ColorEye colorEye;
	public SonarEye sonarEye;

	/**
	 * Initialises the object with two wheels.
	 * 
	 * @param wheelLeft
	 *            The left wheel object.
	 * @param wheelRight
	 *            The right wheel object.
	 */
	public Rover(Wheel wheelLeft, Wheel wheelRight) {
		super();
		this.wheelLeft = wheelLeft;
		this.wheelRight = wheelRight;
	}

	/**
	 * Initialises the object with two wheels and a color sensor.
	 * 
	 * @param wheelLeft
	 *            The left wheel object.
	 * @param wheelRight
	 *            The right wheel object.
	 * @param colorEye
	 *            The color eye object.
	 */
	public Rover(Wheel wheelLeft, Wheel wheelRight, ColorEye colorEye) {
		this(wheelLeft, wheelRight);
		this.colorEye = colorEye;
	}

	/**
	 * Initialises the object with two wheels and a sonar sensor.
	 * 
	 * @param wheelLeft
	 *            The left wheel object.
	 * @param wheelRight
	 *            The right wheel object.
	 * @param sonarEye
	 *            The sonar eye object.
	 */
	public Rover(Wheel wheelLeft, Wheel wheelRight, SonarEye sonarEye) {
		this(wheelLeft, wheelRight);

		this.sonarEye = sonarEye;
	}

	/**
	 * Moves the rover indefinitely forward
	 */
	public void moveForward() {
		wheelRight.startMovement();
		wheelLeft.startMovement();
	}

	/**
	 * Moves the rover backwards indefinitely.
	 */
	public void moveBackward() {
		wheelLeft.startMovement(true);
		wheelRight.startMovement(true);
	}

	/**
	 * Stops the rover from any movement.
	 */
	public void stop() {
		wheelLeft.stopMovement(true);
		wheelRight.stopMovement();
	}

	/**
	 * Makes a turn to the right with one wheel.
	 * 
	 * @param degree
	 *            The amount of the degree you want to turn. Keep it within 1 and
	 *            360. More is still possible.
	 */
	public void turnRight(double degree) {
		wheelLeft.makeTurn(degree);
	}

	/**
	 * Makes a turn to the left with one wheel.
	 * 
	 * @param degree
	 *            The amount of the degree you want to turn. Keep it within 1 and
	 *            360. More is still possible.
	 */
	public void turnLeft(double degree) {
		wheelRight.makeTurn(degree);
	}

	/**
	 * Makes a turn to the right with both wheels. This will turn the rover on the
	 * same spot.
	 * 
	 * @param degree
	 *            The amount of the degree you want to turn. Keep it within 1 and
	 *            360. More is still possible.
	 */
	public void smallTurnRight(double degree) {
		int motorDegree = (int) ((double) Constants.rotationsFor360Degrees / 360 * degree) / 2;
		wheelLeft.motor.rotate(motorDegree, true);
		wheelRight.motor.rotate(motorDegree - motorDegree - motorDegree);
	}

	/**
	 * Makes a turn to the left with both wheels. This will turn the rover on the
	 * same spot.
	 * 
	 * @param degree
	 *            The amount of the degree you want to turn. Keep it within 1 and
	 *            360. More is still possible.
	 */
	public void smallTurnLeft(double degree) {
		int motorDegree = (int) ((double) Constants.rotationsFor360Degrees / 360 * degree) / 2;
		wheelLeft.motor.rotate(motorDegree - motorDegree - motorDegree, true);
		wheelRight.motor.rotate(motorDegree);
	}
}
