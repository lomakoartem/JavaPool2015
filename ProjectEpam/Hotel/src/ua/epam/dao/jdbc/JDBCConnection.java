package ua.epam.dao.jdbc;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Created by lomak on 18.01.2016.
 */
public class JDBCConnection {
    public static Connection getConnection() {

        DataSource ds;
        InitialContext context;
        try {
            context = new InitialContext();
            ds = (DataSource) context.lookup("jdbc/hoteldb");

            return ds.getConnection();

        } catch (NamingException | SQLException e) {

            Logger logger = LogManager.getLogger(JDBCConnection.class.getName());
            logger.error("Error taking connection from connection pool " + e );
        }
        return null;
    }
}
