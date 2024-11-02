package hms.boundary;

abstract class Menu {
	// Obtained from figlet's ANSI-Shadow
	protected static final String LOGO = "\r\n" + "██╗  ██╗███╗   ███╗███████╗\r\n" + "██║  ██║████╗ ████║██╔════╝\r\n"
			+ "███████║██╔████╔██║███████╗\r\n" + "██╔══██║██║╚██╔╝██║╚════██║\r\n" + "██║  ██║██║ ╚═╝ ██║███████║\r\n"
			+ "╚═╝  ╚═╝╚═╝     ╚═╝╚══════╝\r\n" + "                           \r\n" + "";

	public void displayLogo() {
		System.out.print(LOGO);
	}

	abstract public void displayMenu(String name);

	abstract public void displayHeader(String name);

	abstract public void displayOptions();

	protected void displayBorderedText(int width, String text) {
		width = (width - text.length()) % 2 == 0 ? width : width + 1;
		int padding = (width - text.length() - 2) / 2;
		String border = "+" + "=".repeat(width - 2) + "+";
		String output = "|" + " ".repeat(padding) + text + " ".repeat(padding) + "|";
		System.out.println(border);
		System.out.println(output);
		System.out.println(border);
	}
}
