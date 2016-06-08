package dao;

public class dataAccess {
	private static String db = "sqlite";

	public static IGuitar createGuitarDao() {
		IGuitar result = null;
		switch (db) {
		case "sqlite":
			result = new sqliteDao.GuitarImpl();
			break;
		case "mysql":
			result = new mysqlDao.GuitarImpl();
			break;
		}
		return result;
	}

	public static IUser createUserDao() {
		IUser ret = null;
		switch (db) {
		case "sqlite":
			ret = new sqliteDao.UserImpl();
			break;
		case "mysql":
			ret = new mysqlDao.UserImpl();
			break;
		}
		return ret;
	}

}
