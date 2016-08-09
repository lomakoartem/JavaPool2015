package ua.epam.dao.jdbc;

import ua.epam.dao.*;

/**
 * Created by lomak on 18.01.2016.
 */
public class JDBCDaoFactory extends DaoFactory {

    @Override
    public OrderDao createOrderDao() {

        return new JDBCOrderDao();
    }


    @Override
    public RoomClassDao createRoomClassDao() {

        return new JDBCRoomClassDao();
    }


    @Override
    public RoomDao createRoomDao() {

        return new JDBCRoomDao();
    }


    @Override
    public UserDao createUserDao() {

        return new JDBCUserDao();
    }


    @Override
    public PriceDao createPriceDao() {

        return new JDBCPrice();
    }

}