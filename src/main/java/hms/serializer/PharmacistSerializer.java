package hms.serializer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import hms.entity.user.Pharmacist;
import hms.entity.user.attributes.Gender;

public class PharmacistSerializer extends UserSerializer<Pharmacist> {

	public PharmacistSerializer(String filepath) {
		super(filepath);
	}

	@Override
	public Map<String, Pharmacist> getMap() {
		Map<String, Pharmacist> pharmacistMap = new HashMap<String, Pharmacist>();

		try {
			this.br.readLine();
			String line;
			while ((line = br.readLine()) != null) {
				String[] row = line.split(",");
				String role = row[2];
				if (role.equals("Pharmacist")) {
					Pharmacist pharmacist = getPharmacistFromRow(row);
					pharmacistMap.put(pharmacist.getId(), pharmacist);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pharmacistMap;
	}

	private Pharmacist getPharmacistFromRow(String[] row) {
		String id = row[0];
		String name = row[1];
		Gender gender = row[3].equals("Male") ? Gender.MALE : Gender.FEMALE;
		int age = Integer.parseInt(row[4]);
		if (row.length < 6) {
			return new Pharmacist(id, "password", name, gender, age);
		}
		String passwordHash = row[5];
		Pharmacist pharmacist = new Pharmacist(id, "password", name, gender, age);
		pharmacist.setPasswordHash(passwordHash);
		return pharmacist;
	}

//	private Pharmacist getPharmacistFromRow() {
//		String id = this.scanner.next();
//		String name = this.scanner.next();
//		String role = this.scanner.next();
//		Gender gender = this.scanner.next().equals("Male") ? Gender.MALE : Gender.FEMALE;
//		scanner.skip(",");
//		int age = Integer.parseInt(this.scanner.nextLine());
//		if (role.equals("Pharmacist")) {
//			return new Pharmacist(id, "password", name, gender, age);
//		}
//		return null;
//	}
//	@Override
//	protected Map<String, Pharmacist> readWorkbook(Workbook wb) {
//		Map<String, Pharmacist> pharmacistMap = new HashMap<String, Pharmacist>();
//		for (Sheet sheet : wb) {
//			for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
//				Pharmacist pharmacist = getPharmacistFromRow(sheet.getRow(rowNum));
//				if (pharmacist != null) {
//					pharmacistMap.put(pharmacist.getId(), pharmacist);
//				}
//			}
//		}
//		return pharmacistMap;
//	}
//
//	private Pharmacist getPharmacistFromRow(Row row) {
//		DataFormatter formatter = new DataFormatter();
//		String role = formatter.formatCellValue(row.getCell(2));
//		if (role.equals("Pharmacist")) {
//			String id = formatter.formatCellValue(row.getCell(0));
//			String name = formatter.formatCellValue(row.getCell(1));
//			Gender gender = formatter.formatCellValue(row.getCell(3)).equals("Male") ? Gender.MALE : Gender.FEMALE;
//			int age = Integer.parseInt(formatter.formatCellValue(row.getCell(4)));
//			return new Pharmacist(id, "password", name, gender, age);
//		}
//		return null;
//	}

}
