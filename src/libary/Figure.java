package libary;

import Exceptions.DirectionNotFoundException;
import lejos.nxt.Button;
import lejos.util.Delay;
import libary.Enums.RotationDirection;

/**
 * The object to make a figure with.
 * 
 * @author Jos Mutter
 */
public class Figure {
	public int timeOfDriving = 1500;
	public RotationDirection direction = RotationDirection.Clockwise;

	protected Rover rover;
	protected int corners;
	protected double internalCornerDegree;

	/**
	 * Initialises the object.
	 * 
	 * @param wheelLeft
	 *            The left wheel instance of the rover.
	 * @param wheelRight
	 *            The right wheel instance of the rover.
	 * @param corners
	 *            The amount of corners the rover has to make.
	 */
	public Figure(Wheel wheelLeft, Wheel wheelRight, int corners) {
		this.rover = new Rover(wheelLeft, wheelRight);
		this.corners = corners;
		this.calculateInternalCornerDegree();
	}

	/**
	 * Drives the figure.
	 */
	public void execute() throws DirectionNotFoundException {
		System.out.println("Press any button to start...");
		Button.waitForAnyPress();

		for (int side = 1; side <= this.corners; side++) {
			rover.moveForward();
			Delay.msDelay(timeOfDriving);
			rover.stop();

			if (direction.equals(Enums.RotationDirection.Clockwise)) {
				rover.wheelLeft.makeTurn(internalCornerDegree);
			} else if (direction.equals(Enums.RotationDirection.CounterClockwise)) {
				rover.wheelRight.makeTurn(internalCornerDegree);
			} else {
				throw new DirectionNotFoundException("Direction not set.");
			}
		}
	}

	/**
	 * Calculates the degree of the internal corner.
	 */
	public void calculateInternalCornerDegree() {
		this.internalCornerDegree = 360 / this.corners;
	}

	/**
	 * Gets the time of driving for each side.
	 * 
	 * @return Return the time of driving.
	 */
	public int getTimeOfDriving() {
		return timeOfDriving;
	}

	/**
	 * Sets the time of driving for each side.
	 * 
	 * @param timeOfDriving
	 *            The new time of driving.
	 */
	public void setTimeOfDriving(int timeOfDriving) {
		this.timeOfDriving = timeOfDriving;
	}

	/**
	 * Gets the direction to turn to.
	 * 
	 * @return Returns the direction in a enum.
	 */
	public Enums.RotationDirection getDirection() {
		return direction;
	}

	/**
	 * Sets the direction.
	 * 
	 * @param direction
	 *            The new direction.
	 */
	public void setDirection(Enums.RotationDirection direction) {
		this.direction = direction;
	}
}
