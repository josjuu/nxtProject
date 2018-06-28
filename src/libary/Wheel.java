package libary;

import lejos.nxt.NXTRegulatedMotor;

/**
 * This is a glorified NXT regulated motor
 * 
 * @author Jos Mutter
 */
public class Wheel {
	public NXTRegulatedMotor motor;
	protected int speed;

	/**
	 * Initialises the object.
	 * 
	 * @param motor
	 *            The port of where the cable is plugged in. This can be either A, B
	 *            or C.
	 */
	public Wheel(NXTRegulatedMotor motor) {
		this.motor = motor;
		this.speed = 360;
	}

	/**
	 * Initialises the object.
	 * 
	 * @param motor
	 *            The port of where the cable is plugged in. This can be either A, B
	 *            or C.
	 * @param speed
	 *            Sets the speed of rotations of the motor.
	 */
	public Wheel(NXTRegulatedMotor motor, int speed) {
		this.motor = motor;
		this.speed = speed;
	}

	/**
	 * Starts the motor the move forward.
	 */
	public void startMovement() {
		motor.forward();
	}

	/**
	 * Starts the motor to move either forward or backward.
	 * 
	 * @param backward
	 *            A boolean, if true it moves backward, else it moves forward.
	 */
	public void startMovement(boolean backward) {
		if (backward) {
			motor.backward();
		} else {
			this.startMovement();
		}
	}

	/**
	 * Stops the movement of the motor.
	 */
	public void stopMovement() {
		motor.stop();
	}

	/**
	 * Stops the movement of the motor.
	 * 
	 * @param immediateReturn
	 *            If true do not wait for the motor to actually stop.
	 */
	public void stopMovement(boolean immediateReturn) {
		motor.stop(immediateReturn);
	}

	/**
	 * Makes a wheel rotation to turn.
	 * 
	 * @param degrees
	 * 		The amount of degrees.
	 */
	public void makeTurn(double degrees) {
		motor.rotate((int) ((double) Constants.rotationsFor360Degrees / 360 * degrees));
	}

	/**
	 * Gets the speed of the motor.
	 * 
	 * @return
	 * 		Gets the speed.
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * Sets the speed of rotations of the wheel.
	 * 
	 * @param speed
	 * 		The new speed of the wheel.
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}
}
