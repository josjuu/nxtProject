package meter;

import lejos.nxt.Motor;
import lejos.util.Delay;
import libary.Rover;
import libary.Wheel;

/**
 * This class will let the rover driver a meter.
 * 
 * @author Jos Mutter
 *
 */
public class DriveAMeterMain {
	public static void main(String args[]) {
		Wheel wheelLeft = new Wheel(Motor.A);
		Wheel wheelRight = new Wheel(Motor.C);

		Rover rover = new Rover(wheelLeft, wheelRight);
		
		rover.moveForward();
		Delay.msDelay(7400);
		rover.stop();
	}
}
