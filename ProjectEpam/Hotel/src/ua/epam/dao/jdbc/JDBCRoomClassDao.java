package ua.epam.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import ua.epam.dao.RoomClassDao;
import ua.epam.entities.RoomClass;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
/**
 * Created by lomak on 18.01.2016.
 */
public class JDBCRoomClassDao implements RoomClassDao {

    @Override
    public void create(RoomClass roomClass) {


        try(Connection connection = JDBCConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO room_class (name) " +
                            "VALUES(?)")
        ){
            statement.setNString(1, roomClass.getName());
            statement.executeUpdate();
            connection.close();

        }catch( SQLException ex ){

            Logger logger = LogManager.getLogger(JDBCRoomClassDao.class.getName());
            logger.error("Error creation new class room " + ex);

        }
    }

    @Override
    public List<RoomClass> findAll() {
        List<RoomClass> lst = new ArrayList<>();
        try(	Connection connection = JDBCConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT id, name  FROM room_class "
                )
        ){

            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                RoomClass roomClass = new RoomClass();
                roomClass.setId(rs.getInt(1));
                roomClass.setName(rs.getNString(2));

                lst.add(roomClass);
            }
        }catch( SQLException ex ){

            Logger logger = LogManager.getLogger(JDBCRoomClassDao.class.getName());
            logger.error("Error receiveing all classes of rooms " + ex);

        }
        return lst;
    }


    @Override
    public void delete(int id) {
        Connection connection = JDBCConnection.getConnection();
        try(Statement statement = connection.createStatement()
        ){

            statement.execute("DELETE FROM room_class WHERE id=" + id);
            connection.close();

        }catch( SQLException ex ){

            Logger logger = LogManager.getLogger(JDBCRoomClassDao.class.getName());
            logger.error("Error deleting room by id " + ex);

        }
    }

}