package hms.serializer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

abstract class Serializer<E> {
	public Map<String, E> getMap(String filepath) {
		try (InputStream in = this.getClass().getClassLoader().getResourceAsStream(filepath);
				OPCPackage pkg = OPCPackage.open(in);) {
			Workbook wb = new XSSFWorkbook(pkg);
			pkg.close();
			in.close();
			return readWorkbook(wb);
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	protected abstract Map<String, E> readWorkbook(Workbook wb);
}
