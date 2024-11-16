package hms.serializer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

abstract class Serializer {
	protected BufferedReader br;
//	protected Scanner scanner;

//	public Workbook getWorkbook(String filepath) {
//		try (InputStream in = this.getClass().getClassLoader().getResourceAsStream(filepath);
//				OPCPackage pkg = OPCPackage.open(in);) {
//			Workbook wb = new XSSFWorkbook(pkg);
//			pkg.close();
//			in.close();
//			return wb;
//		} catch (InvalidFormatException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}
	public Serializer(String filepath) {
		try {
			this.br = new BufferedReader(new FileReader(filepath));
//				this.scanner = new Scanner(new File(filepath));
//			scanner.useDelimiter(",");
		} catch (FileNotFoundException e) {
			if (!(filepath.contains("Appointment_List") || filepath.contains("AppointmentOutcomeRecord_List"))) {
				e.printStackTrace();
			}
		}
	}

//	protected void ignoreHeader(int ROW_LENGTH) {
//		for (int i = 0; i < ROW_LENGTH - 1; i++) {
//			scanner.next();
//		}
//		this.scanner.nextLine(); // clear newline
//	}
}
