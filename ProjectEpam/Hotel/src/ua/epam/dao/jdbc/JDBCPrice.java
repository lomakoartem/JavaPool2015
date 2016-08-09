package ua.epam.dao.jdbc;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.epam.dao.PriceDao;
import ua.epam.entities.Price;
import ua.epam.entities.RoomClass;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lomak on 18.01.2016.
 */
public class JDBCPrice implements PriceDao {

    @Override
    public int getPrice(RoomClass roomClass, int beds) {
        int price = 0;
        try(Connection connection = JDBCConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT price.price, price.beds, price.class_id " +
                            "FROM hoteldb.price price " +
                            "WHERE (price.beds = ?) AND (price.class_id = ?)"
            )
        ){
            statement.setInt(1, beds);
            statement.setInt(2, roomClass.getId());
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                price = rs.getInt(1);
            }
            connection.close();

        }catch( SQLException ex ){

            Logger logger = LogManager.getLogger(JDBCPrice.class.getName());
            logger.error("Error receiving price from database " + ex);

        }
        return price;
    }


    public List<Price> findAll(){
        List<Price> lst = new ArrayList<>();
        try(	Connection connection = JDBCConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT DISTINCT rooms.class_id, " +
                                "       rooms.beds, " +
                                "       price.price, " +
                                "       room_class.name " +
                                "  FROM (hoteldb.rooms rooms " +
                                "        LEFT OUTER JOIN hoteldb.room_class room_class " +
                                "           ON (rooms.class_id = room_class.id)) " +
                                "       LEFT OUTER JOIN hoteldb.price price " +
                                "          ON (rooms.class_id = price.class_id) AND (rooms.beds = price.beds) " +
                                "ORDER BY rooms.beds ASC, room_class.name ASC "
                )){

            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                RoomClass roomClass = new RoomClass();
                roomClass.setId(rs.getInt(1));
                roomClass.setName(rs.getNString(4));

                Price price = new Price();
                price.setRoomClass(roomClass);
                price.setBeds(rs.getInt(2));
                price.setPrice(rs.getInt(3));
                lst.add(price);
            }
        }catch( Exception ex ){

            Logger logger = LogManager.getLogger(JDBCPrice.class.getName());
            logger.error("Error receiving all prices from database " + ex);

        }
        return lst;
    }

    @Override
    public void setPriceList(List<Price> lst) {
        try(Connection connection = JDBCConnection.getConnection();
            Statement statement = connection.createStatement()){
            connection.setAutoCommit(false);
            statement.executeUpdate("DELETE FROM price");
            for(Price price: lst){
                statement.executeUpdate("INSERT INTO price (class_id, beds, price)"
                        +"VALUES(" + price.getRoomClass().getId() +","
                        +price.getBeds() + "," + price.getPrice() +")");
            }
            connection.commit();

        }catch( Exception ex ){

            Logger logger = LogManager.getLogger(JDBCPrice.class.getName());
            logger.error("Error setting price list " + ex);

        }
    }
}
