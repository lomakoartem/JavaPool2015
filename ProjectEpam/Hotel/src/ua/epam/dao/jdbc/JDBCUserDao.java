package ua.epam.dao.jdbc;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.epam.dao.UserDao;
import ua.epam.entities.User;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lomak on 18.01.2016.
 */
public class JDBCUserDao implements UserDao {
    @Override
    public void create(User user) {

        try(Connection connection = JDBCConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO users (login, name, email, password_hash, isAdministrator) " +
                            "VALUES(?,?,?,?,?)" , Statement.RETURN_GENERATED_KEYS)
        ){

            statement.setNString(1,user.getLogin());
            statement.setNString(2,user.getName());
            statement.setNString(3,user.getEmail());
            statement.setInt(4, user.getPasswordHash());
            statement.setInt(5, user.getAdministrator() ? 1 : 0);
            statement.executeUpdate();
            ResultSet key = statement.getGeneratedKeys();
            int id = 0;
            if(key.next()){
                id = key.getInt(1);
                user.setId(id);
            }

        }catch( SQLException ex ){

            Logger logger = LogManager.getLogger(JDBCUserDao.class.getName());
            logger.error("Error creation of new user " + ex);

        }
    }

    @Override
    public void delete(int id) {

        try(Connection connection = JDBCConnection.getConnection();
            Statement statement = connection.createStatement()){

            statement.execute("DELETE FROM users WHERE id=" + id);

        }catch( SQLException ex ){

            Logger logger = LogManager.getLogger(JDBCUserDao.class.getName());
            logger.error("Error deleting user by id " + ex);

        }
    }

    @Override
    public boolean login(User user) {
        boolean result = false;
        Connection connection = JDBCConnection.getConnection();
        try(PreparedStatement statement = connection.prepareStatement(
                "SELECT id, name, email,isAdministrator FROM users " +
                        "WHERE login = ? AND password_hash = ?" )
        ){
            statement.setNString(1, user.getLogin());
            statement.setInt(2, user.getPasswordHash());
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                result = true;
                user.setId(rs.getInt(1));
                user.setName(rs.getNString(2));
                user.setEmail(rs.getNString(3));
                user.setAdministrator( rs.getInt(4) == 0 ? false : true);
            }
            connection.close();

        }catch( SQLException ex ){

            Logger logger = LogManager.getLogger(JDBCUserDao.class.getName());
            logger.error("Error logining " + ex);

        }
        return result;

    }

    @Override
    public boolean isLoginFree(User user) {
        // TODO Auto-generated method stub
        boolean result = true;
        Connection connection = JDBCConnection.getConnection();
        try(PreparedStatement statement = connection.prepareStatement(
                "SELECT COUNT(*) FROM users " +
                        "WHERE name = ?" )
        ){
            statement.setNString(1, user.getName());
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                int count = rs.getInt(1);
                if(count > 0){
                    result = false;
                }
            }
            connection.close();

        }catch( SQLException ex ){

            Logger logger = LogManager.getLogger(JDBCUserDao.class.getName());
            logger.error("Error checkin login " + ex);

        }
        return result;
    }

    @Override
    public List<User> findAll() {

        List<User> lst = new ArrayList<>();
        try(Connection connection = JDBCConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                        "SELECT id, name, email, login, isAdministrator  FROM users "
                )
        ){

            ResultSet rs = statement.executeQuery();
            while (rs.next()){

                User user = new User();
                user.setId(rs.getInt(1));
                user.setName(rs.getNString(2));
                user.setEmail(rs.getNString(3));
                user.setLogin(rs.getNString(4));
                user.setAdministrator( rs.getInt(5) == 0 ? false : true);

                lst.add(user);
            }
        }catch( SQLException ex ){

            Logger logger = LogManager.getLogger(JDBCUserDao.class.getName());
            logger.error("Error receiving list of all users " + ex);

        }
        return lst;
    }



}
