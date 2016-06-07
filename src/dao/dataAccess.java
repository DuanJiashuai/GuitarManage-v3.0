package dao;

public class dataAccess {
	private static String daoName = "sqliteDao";

	public static IGuitar createGuitarDao() {
		IGuitar result = null;
		try {
			Object o = Class.forName(daoName + "." + "GuitarImpl").newInstance();
			result = (IGuitar)o;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
