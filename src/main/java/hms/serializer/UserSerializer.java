package hms.serializer;

import java.util.Map;

import hms.entity.user.User;

//public abstract class UserSerializer<E extends User> extends Serializer {
//
//	public Map<String, E> getMap(String filepath) {
//		return readWorkbook(getWorkbook(filepath));
//	}
//
//	protected abstract Map<String, E> readWorkbook(Workbook wb);
//}

public abstract class UserSerializer<E extends User> extends Serializer {

	protected UserSerializer(String filepath) {
		super(filepath);
	}

	public abstract Map<String, E> getMap();
}
