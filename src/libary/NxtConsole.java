package libary;

/**
 * This class contains a few methods to interact with the nxt brick console.
 * 
 * @author Jos Mutter
 */
public class NxtConsole {
	/**
	 * Clears the console of messages.
	 */
	public static void clearConsole() {
		for (int i = 0; i < 10; i++) {
			System.out.println();
		}
	}

	/**
	 * Glorified system.out.println()
	 * 
	 * @param string
	 *            String to message out.
	 */
	public static void println(String string) {
		System.out.println(string);
	}
}
