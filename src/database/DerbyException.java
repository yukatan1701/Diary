package database;

import java.sql.SQLException;

public final class DerbyException {
	public static boolean tableAlreadyExists(SQLException ex) {
		if (ex.getErrorCode() == 30000)
			return true;
		return false;
	}
	
	public static boolean shutdownMessage(SQLException ex) {
        int errorCode = ex.getErrorCode();
		if (errorCode == 45000 || errorCode == 40000 || errorCode == 50000)
			return true;
		return false;
	}
}
