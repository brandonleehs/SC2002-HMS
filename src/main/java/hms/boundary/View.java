package hms.boundary;

public abstract class View {
	private static final String LOGO = "\r\n" + "██╗  ██╗███╗   ███╗███████╗\r\n" + "██║  ██║████╗ ████║██╔════╝\r\n"
			+ "███████║██╔████╔██║███████╗\r\n" + "██╔══██║██║╚██╔╝██║╚════██║\r\n" + "██║  ██║██║ ╚═╝ ██║███████║\r\n"
			+ "╚═╝  ╚═╝╚═╝     ╚═╝╚══════╝\r\n" + "                           \r\n" + "";
	protected static int WIDTH = 60;

	public static void displayLogo() {
		System.out.print(LOGO);
	}

	public abstract void displayHeader();

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
