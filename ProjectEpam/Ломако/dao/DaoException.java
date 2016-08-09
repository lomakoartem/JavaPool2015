package ua.epam.dao;

import java.sql.SQLException;

public class DaoException extends SQLException {
	
    private static final long serialVersionUID = 1L;

    public DaoException() {
        super();
    }

    public DaoException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public DaoException(String arg0) {
        super(arg0);
    }

    public DaoException(Throwable arg0) {
        super(arg0);
    }

}
