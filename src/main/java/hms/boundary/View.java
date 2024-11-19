package hms.boundary;

/**
 * An abstract class representing a general view in the system. It provides
 * functionality to display a logo and header, as well as methods for displaying
 * bordered text with customizable width.
 */
public abstract class View {
	private static final String LOGO = "\r\n" + "██╗  ██╗███╗   ███╗███████╗\r\n" + "██║  ██║████╗ ████║██╔════╝\r\n"
			+ "███████║██╔████╔██║███████╗\r\n" + "██╔══██║██║╚██╔╝██║╚════██║\r\n" + "██║  ██║██║ ╚═╝ ██║███████║\r\n"
			+ "╚═╝  ╚═╝╚═╝     ╚═╝╚══════╝\r\n" + "                           \r\n" + "";
	protected static int WIDTH = 70;

	/**
	 * Displays the logo of the system in ASCII art.
	 */
	public static void displayLogo() {
		System.out.print(LOGO);
	}

	/**
	 * An abstract method that must be implemented by subclasses to display a
	 * specific header for the view.
	 */
	public abstract void displayHeader();

	/**
	 * Displays a given text inside a bordered frame with a specified width. The
	 * text is centered horizontally within the frame, and padding is added to
	 * ensure the frame has the correct width.
	 * 
	 * @param width The width of the frame.
	 * @param text  The text to be displayed inside the frame.
	 */
	protected static void displayBorderedText(int width, String text) {
		int leftPadding = (width - text.length() - 2) / 2;
		int rightPadding = leftPadding;
		if ((width - text.length()) % 2 != 0) {
			rightPadding++;
		}
		String border = "+" + "=".repeat(width - 2) + "+";
		String output = "|" + " ".repeat(leftPadding) + text + " ".repeat(rightPadding) + "|";
		System.out.println(border);
		System.out.println(output);
		System.out.println(border);
	}

}
