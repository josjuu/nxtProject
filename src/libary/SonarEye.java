package libary;

import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;

/**
 * This is a glorified ultrasonic sensor.
 * 
 * @author Jos Mutter
 */
public class SonarEye {
	private UltrasonicSensor sonarSensor;
	private int lastDistance;

	/**
	 * Initialises the object.
	 * 
	 * @param sensorPort
	 *            The port of where the cable is plugged in. This can be either S1,
	 *            S2, S3 or S4.
	 */
	public SonarEye(SensorPort sensorPort) {
		super();

		this.sonarSensor = new UltrasonicSensor(sensorPort);
	}

	/**
	 * Scans the distance of the sensor to the object in front of it.
	 */
	public void scanDistance() {
		int distance = sonarSensor.getDistance();
		setLastDistance(distance);
	}

	/**
	 * Gets the distance of the last scan.
	 * 
	 * @return 
	 * 			  Returns the distance.
	 */
	public int getLastDistance() {
		return lastDistance;
	}

	/**
	 * Sets the distance.
	 * 
	 * @param lastDistance
	 *            The new distance.
	 */
	private void setLastDistance(int lastDistance) {
		this.lastDistance = lastDistance;
	}
}
