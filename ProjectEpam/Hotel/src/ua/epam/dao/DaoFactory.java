package ua.epam.dao;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.epam.resource.*;

/**
 * Created by lomak on 18.01.2016.
 */
public abstract class DaoFactory {

    /**
     * Creates object to accsess source of orders data
     * @return object with interface to access order
     */
    public abstract OrderDao createOrderDao();

    /**
     * Creates object to accsess source of room classes data
     * @return object with interface to access room class
     */
    public abstract RoomClassDao createRoomClassDao();

    /**
     * Creates object to accsess source of rooms data
     * @return object with interface to access room
     */
    public abstract RoomDao createRoomDao();

    /**
     * Creates object to accsess source of users data
     * @return object with interface to access user
     */
    public abstract UserDao createUserDao();

    /**
     * Creates object to accsess source of prices data
     * @return object with interface to access price
     */
    public abstract PriceDao createPriceDao();

    /**
     * Takes class name from configuration file and
     * creates instanse of DAO factory
     *
     * @return factory of data access objects
     */
    public static DaoFactory getInstance(){
        try {
            return (DaoFactory) Class.forName(ConfigurationManager.getProperty("dao.factory")).newInstance();
        } catch (Exception ex) {
            Logger logger = LogManager.getLogger(DaoFactory.class.getName());
            logger.error("Error creation of instanse of DAO factory");
            return null;
        }

    }


}