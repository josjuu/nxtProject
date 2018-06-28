package figure;

import Exceptions.DirectionNotFoundException;
import lejos.nxt.Button;
import lejos.nxt.Motor;
import libary.Enums.RotationDirection;
import libary.Constants;
import libary.Figure;
import libary.Wheel;

/**
 * This class will let the rover drive around in a figure. This could be a triangle to a hexagone. 
 * 
 * @author Jos Mutter
 *
 */
public class FigureMain {

	public static void main(String[] args) {
		Wheel wheelLeft = new Wheel(Motor.A);
		Wheel wheelRight = new Wheel(Motor.C);
		int corners = 4;

		Figure figure = new Figure(wheelLeft, wheelRight, corners);
		figure.setDirection(RotationDirection.Clockwise);
		figure.setTimeOfDriving(Constants.timeForOneCentimeter * 50);

		try {
			figure.execute();
			Button.waitForAnyPress();
			figure.setDirection(RotationDirection.CounterClockwise);
			figure.execute();
		} catch (DirectionNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			Button.waitForAnyPress();
		}
	}

}
