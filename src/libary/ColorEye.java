package libary;

import lejos.nxt.ColorSensor;
import lejos.nxt.SensorPort;
import lejos.robotics.Color;

/**
 * This class is a glorified ColorSensor.
 * 
 * @author Jos Mutter
 */
public class ColorEye {
	public ColorSensor colorSensor;
	private byte lastSeenColorId = Color.NONE;

	/**
	 * Initialises the object.
	 * 
	 * @param sensorPort
	 *            The port of where the cable is plugged in. This can be either S1,
	 *            S2, S3 or S4.
	 */
	public ColorEye(SensorPort sensorPort) {
		super();
		this.colorSensor = new ColorSensor(sensorPort);
		this.colorSensor.setFloodlight(Color.WHITE);
	}

	/**
	 * Activates the color sensor and checks for the color.
	 * 
	 * @return Returns the id of the color.
	 */
	public byte scanForColor() {
		byte colorId = (byte) this.colorSensor.getColorID();
		setLastSeenColorId(colorId);
		return colorId;
	}

	/**
	 * Gets the name of the given color id.
	 * 
	 * @param colorId
	 *            The id of the color of whom you want the name of.
	 * @return Returns the name the color.
	 */
	public String getColorName(byte colorId) {
		String name;

		switch ((int) colorId) {
		case 0:
			name = "red";
			break;
		case 1:
			name = "green";
			break;
		case 2:
			name = "blue";
			break;
		case 3:
			name = "yellow";
			break;
		case 4:
			name = "magenta";
			break;
		case 5:
			name = "orange";
			break;
		case 6:
			name = "white";
			break;
		case 7:
			name = "black";
			break;
		case 8:
			name = "pink";
			break;
		case 9:
			name = "gray";
			break;
		case 10:
			name = "light gray";
			break;
		case 11:
			name = "dark gray";
			break;
		case 12:
			name = "cyan";
			break;
		case -1:
		default:
			name = "none";
			break;
		}

		return name;
	}

	/**
	 * Gets the last seen color.
	 * 
	 * @return Returns the id of the color.
	 */
	public byte getLastSeenColorId() {
		return lastSeenColorId;
	}

	/**
	 * Sets the last seen color.
	 * 
	 * @param lastSeenColorId
	 *            The id of the color.
	 */
	public void setLastSeenColorId(byte lastSeenColorId) {
		this.lastSeenColorId = lastSeenColorId;
	}
}
