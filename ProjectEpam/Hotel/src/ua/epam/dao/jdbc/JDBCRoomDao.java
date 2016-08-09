package ua.epam.dao.jdbc;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.epam.dao.RoomDao;
import ua.epam.entities.Order;
import ua.epam.entities.Room;
import ua.epam.entities.RoomClass;

/**
 * Created by lomak on 18.01.2016.
 */
public class JDBCRoomDao implements RoomDao {
    @Override
    public void create(Room room) {

        try(Connection connection = JDBCConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO rooms (class_id, number, beds) " +
                            "VALUES(?,?,?)")
        ){
            statement.setInt(1,room.getRoomClass().getId());
            statement.setInt(2,room.getNumber());
            statement.setInt(3,room.getBeds());
            statement.executeUpdate();

        }catch( SQLException ex ){

            Logger logger = LogManager.getLogger(JDBCRoomDao.class.getName());
            logger.error("Error creation new room "  + ex);

        }
    }

    @Override
    public void delete(int id) {

        try(Connection connection = JDBCConnection.getConnection();
            Statement statement = connection.createStatement()){

            statement.execute("DELETE FROM rooms WHERE id=" + id);

        }catch( SQLException ex ){

            Logger logger = LogManager.getLogger(JDBCRoomDao.class.getName());
            logger.error("Error deleting room by id "  + ex);

        }

    }

    @Override
    public List<Room> findFree(Order order) {

        List<Room> lst = new ArrayList<>();

        try (Connection connection = JDBCConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT rooms.number,  " +
                             "      rooms.class_id, " +
                             "      rooms.beds,   " +
                             "      rooms.id,   " +
                             "      room_class.name,  " +
                             "      IF(rooms.class_id = ?, 0, 1) AS ROOMCLASSRATE " +
                             "FROM hoteldb.rooms rooms   " +
                             "  LEFT OUTER JOIN hoteldb.room_class room_class " +
                             "  ON (rooms.class_id = room_class.id)   " +
                             "WHERE       " +
                             "(rooms.id NOT IN " +
                             "    (SELECT orders.room_id   " +
                             "     FROM hoteldb.orders orders   " +
                             "     WHERE    (   " +
                             " (orders.arraival < ? " +
                             "             AND orders.departure > ?) OR " +
                             "  ( orders.arraival >= ?   " +
                             "             AND orders.arraival < ?) OR " +
                             "  ( orders.departure > ?   " +
                             "             AND orders.departure <= ?) " +
                             "     )  " +
                             "            AND NOT ISNULL(orders.room_id) " +
                             " )) AND   " +
                             "rooms.beds >= ? " +
                             "ORDER BY  6 ASC, rooms.beds ASC" +
                             " LIMIT 10"
             )){
            statement.setInt(1,order.getRoomClass().getId());
            statement.setDate(2,new Date(order.getArraival().getTime()));
            statement.setDate(3,new Date(order.getDeparture().getTime()));
            statement.setDate(4,new Date(order.getArraival().getTime()));
            statement.setDate(5,new Date(order.getDeparture().getTime()));
            statement.setDate(6,new Date(order.getArraival().getTime()));
            statement.setDate(7,new Date(order.getDeparture().getTime()));
            statement.setInt(8,order.getBeds());

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {

                RoomClass roomClass = new RoomClass();
                roomClass.setId(rs.getInt(2));
                roomClass.setName(rs.getNString(5));

                Room room = new Room();
                room.setNumber(rs.getInt(1));
                room.setBeds(rs.getInt(3));
                room.setId(rs.getInt(4));

                room.setRoomClass(roomClass);

                lst.add(room);
            }

        } catch (SQLException ex) {

            Logger logger = LogManager.getLogger(JDBCRoomDao.class.getName());
            logger.error("Error receiving sutable rooms for order "  + ex);

        }

        return lst;


    }

    @Override
    public List<Room> findAll() {

        List<Room> lst = new ArrayList<>();

        try(Connection connection = JDBCConnection.getConnection();
            Statement statement = connection.createStatement()){

            ResultSet rs = statement.executeQuery(
                    "SELECT rooms.id, " +
                            "      rooms.class_id, " +
                            "      rooms.number, " +
                            "      rooms.beds, " +
                            "      room_class.name " +
                            "FROM hoteldb.rooms rooms " +
                            "      LEFT OUTER JOIN hoteldb.room_class room_class " +
                            "         ON (rooms.class_id = room_class.id) " +
                            "ORDER BY rooms.number ASC"
            );
            while (rs.next()){
                RoomClass roomClass = new RoomClass();
                roomClass.setId(rs.getInt(2));
                roomClass.setName(rs.getNString(5));

                Room room= new Room();
                room.setId(rs.getInt(1));
                room.setNumber(rs.getInt(3));
                room.setBeds(rs.getInt(4));
                room.setRoomClass(roomClass);

                lst.add(room);
            }

        }catch( SQLException ex ){

            Logger logger = LogManager.getLogger(JDBCRoomDao.class.getName());
            logger.error("Error receiving all rooms "  + ex);

        }
        return lst;
    }

}
