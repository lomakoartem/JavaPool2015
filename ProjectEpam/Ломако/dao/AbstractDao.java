package ua.epam.dao;

import java.sql.SQLException;
import java.sql.Statement;
import org.apache.log4j.Logger;

public abstract class AbstractDao {
	
	private static final Logger LOGGER = Logger.getLogger(AbstractDao.class);
	public static void close(Statement st) {
	try {
		if (st != null) {
			st.close();
		}
	} catch (SQLException e) {
		LOGGER.error("DAOException", e);
	}
}

}
