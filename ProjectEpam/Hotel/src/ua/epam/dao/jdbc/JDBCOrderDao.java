package ua.epam.dao.jdbc;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.epam.dao.OrderDao;
import ua.epam.entities.Order;
import ua.epam.entities.Room;
import ua.epam.entities.RoomClass;
import ua.epam.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lomak on 18.01.2016.
 */
public class JDBCOrderDao implements OrderDao {
    @Override
    public void create(Order order) {

        try(Connection connection = JDBCConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO orders (arraival, departure, user_id, beds, class_id) " +
                            "VALUES(?,?,?,?,?)" , Statement.RETURN_GENERATED_KEYS)
        ){
            statement.setDate(1, new Date(order.getArraival().getTime()));
            statement.setDate(2,new Date(order.getDeparture().getTime()));
            statement.setInt(3, order.getUser().getId());
            statement.setInt(4, order.getBeds());
            statement.setInt(5, order.getRoomClass().getId());
            statement.executeUpdate();
            ResultSet key = statement.getGeneratedKeys();
            int id = 0;
            if(key.next()){
                id = key.getInt(1);
                order.setId(id);

            }

        }catch( SQLException ex ){

            Logger logger = LogManager.getLogger(JDBCOrderDao.class.getName());
            logger.error("Error creating new order " + ex);

        }

    }

    @Override
    public void delete(int id) {

        try(Connection connection = JDBCConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM orders " +
                            "WHERE id = ?" )
        ){
            statement.setInt(1, id);
            statement.executeUpdate();


        }catch( SQLException ex ){
            Logger logger = LogManager.getLogger(JDBCOrderDao.class.getName());
            logger.error("Error creating new order " + ex);
        }


    }

    @Override
    public List<Order> findUnprocessed() {

        List<Order> lst = new ArrayList<>();

        try(Connection connection = JDBCConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT orders.id, " +
                            "   orders.arraival,  " +
                            "   orders.departure, " +
                            "   orders.beds, " +
                            "   room_class.name, " +
                            "   orders.class_id, " +
                            "   orders.user_id, " +
                            "   users.name,  " +
                            "   users.email " +
                            "FROM (hoteldb.orders orders " +
                            "     LEFT OUTER JOIN hoteldb.users users ON (orders.user_id = users.id)) " +
                            "     LEFT OUTER JOIN hoteldb.room_class room_class " +
                            "     ON (orders.class_id = room_class.id) " +
                            "WHERE (orders.room_id IS NULL) "
            )){
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Order order = new Order();
                order.setId(rs.getInt(1));
                order.setArraival(rs.getDate(2));
                order.setDeparture(rs.getDate(3));
                order.setBeds(rs.getInt(4));

                RoomClass roomClass = new RoomClass();
                roomClass.setName(rs.getNString(5));
                roomClass.setId(rs.getInt(6));

                order.setRoomClass(roomClass);

                User user = new User();
                user.setId(rs.getInt(7));
                user.setName(rs.getNString(8));
                user.setEmail(rs.getNString(9));

                order.setUser(user);

                lst.add(order);
            }

        }catch( SQLException ex ){

            Logger logger = LogManager.getLogger(JDBCOrderDao.class.getName());
            logger.error("Error finding unprocessed orders " + ex);

        }
        return lst;
    }

    @Override
    public List<Order> findByUser(User user) {

        List<Order> lst = new ArrayList<>();

        try(Connection connection = JDBCConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    " SELECT orders.id, " +
                            "      orders.arraival,  " +
                            "      orders.departure, " +
                            "      rooms.class_id, " +
                            "      room_class.name, " +
                            "      rooms.beds " +
                            " FROM ((hoteldb.rooms rooms " +
                            "        INNER JOIN hoteldb.room_class room_class " +
                            "           ON (rooms.class_id = room_class.id)) " +
                            "       RIGHT OUTER JOIN hoteldb.orders orders ON (orders.room_id = rooms.id)) " +
                            "WHERE NOT ISNULL(orders.room_id) AND orders.user_id = ? "
            )){
            statement.setInt(1, user.getId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Order order = new Order();
                order.setId(rs.getInt(1));
                order.setArraival(rs.getDate(2));
                order.setDeparture(rs.getDate(3));
                order.setBeds(rs.getInt(6));

                RoomClass roomClass = new RoomClass();
                roomClass.setId(rs.getInt(4));
                roomClass.setName(rs.getNString(5));

                order.setRoomClass(roomClass);

                Room room = new Room();
                room.setBeds(rs.getInt(6));
                room.setRoomClass(roomClass);

                order.setRoom(room);

                lst.add(order);
            }

        }catch( SQLException ex ){

            Logger logger = LogManager.getLogger(JDBCOrderDao.class.getName());
            logger.error("Error finding users by order " + ex);

        }
        return lst;
    }

    @Override
    public void setRoom(Order order, int roomId) {

        try(Connection connection = JDBCConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE orders " +
                            "SET room_id = ? " +
                            "WHERE id = ?" )
        ){
            statement.setInt(1, roomId);
            statement.setInt(2, order.getId());
            statement.executeUpdate();


        }catch( SQLException ex ){

            Logger logger = LogManager.getLogger(JDBCOrderDao.class.getName());
            logger.error("Error settion room " + ex);

        }

    }
}
