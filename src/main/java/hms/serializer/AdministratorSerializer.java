package hms.serializer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import hms.entity.user.Administrator;
import hms.entity.user.attributes.Gender;

public class AdministratorSerializer extends UserSerializer<Administrator> {

	public AdministratorSerializer(String filepath) {
		super(filepath);
	}

	@Override
	public Map<String, Administrator> getMap() {
		Map<String, Administrator> administratorMap = new HashMap<String, Administrator>();
		try {
			this.br.readLine();
			String line;
			while ((line = br.readLine()) != null) {
				String[] row = line.split(",");
				String role = row[2];
				if (role.equals("Administrator")) {
					Administrator administrator = getAdministratorFromRow(row);
					administratorMap.put(administrator.getId(), administrator);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return administratorMap;
	}

	private Administrator getAdministratorFromRow(String[] row) {
		String id = row[0];
		String name = row[1];
		Gender gender = row[3].equals("Male") ? Gender.MALE : Gender.FEMALE;
		int age = Integer.parseInt(row[4]);
		return new Administrator(id, "password", name, gender, age);
	}

//	private Administrator getAdministratorFromRow() {
//		String id = this.scanner.next();
//		String name = this.scanner.next();
//		String role = this.scanner.next();
//		Gender gender = this.scanner.next().equals("Male") ? Gender.MALE : Gender.FEMALE;
//		scanner.skip(",");
//		int age = Integer.parseInt(this.scanner.nextLine());
//		if (role.equals("Administrator")) {
//			return new Administrator(id, "password", name, gender, age);
//		}
//		return null;
//	}

//	@Override
//	protected Map<String, Administrator> readWorkbook(Workbook wb) {
//		Map<String, Administrator> administratorMap = new HashMap<String, Administrator>();
//		for (Sheet sheet : wb) {
//			for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
//				Administrator administrator = getAdministratorFromRow(sheet.getRow(rowNum));
//				if (administrator != null) {
//					administratorMap.put(administrator.getId(), administrator);
//				}
//			}
//		}
//		return administratorMap;
//	}
//
//	private Administrator getAdministratorFromRow(Row row) {
//		DataFormatter formatter = new DataFormatter();
//		String role = formatter.formatCellValue(row.getCell(2));
//		if (role.equals("Administrator")) {
//			String id = formatter.formatCellValue(row.getCell(0));
//			String name = formatter.formatCellValue(row.getCell(1));
//			Gender gender = formatter.formatCellValue(row.getCell(3)).equals("Male") ? Gender.MALE : Gender.FEMALE;
//			int age = Integer.parseInt(formatter.formatCellValue(row.getCell(4)));
//			return new Administrator(id, "password", name, gender, age);
//		}
//		return null;
//	}

}
