package hms.boundary;

public abstract class View {
//	protected static final PatientRepository patientRepository = new PatientRepository();
//	protected static final DoctorRepository doctorRepository = new DoctorRepository();
//	protected static final PharmacistRepository pharmacistRepository = new PharmacistRepository();
//	protected static final AdministratorRepository administratorRepository = new AdministratorRepository();

	private static final String LOGO = "\r\n" + "██╗  ██╗███╗   ███╗███████╗\r\n" + "██║  ██║████╗ ████║██╔════╝\r\n"
			+ "███████║██╔████╔██║███████╗\r\n" + "██╔══██║██║╚██╔╝██║╚════██║\r\n" + "██║  ██║██║ ╚═╝ ██║███████║\r\n"
			+ "╚═╝  ╚═╝╚═╝     ╚═╝╚══════╝\r\n" + "                           \r\n" + "";
//	protected static final Scanner scanner = new Scanner(System.in);
	protected static int WIDTH = 60;

	public static void displayLogo() {
		System.out.print(LOGO);
	}

//	public abstract void show();

//	public static void close() {
//		scanner.close();
//	}

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
