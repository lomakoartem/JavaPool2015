package ua.epam.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.log4j.Logger;
import ua.epam.entities.Employee;
import ua.epam.entities.Position;
import ua.epam.dao.DaoException;
import ua.epam.dao.CrewDao;
import ua.epam.dao.AbstractDao;
import ua.epam.pool.ConnectionPool;

public class CrewDaoImpl extends CrewDao {
	private static final Logger LOGGER = Logger.getLogger(CrewDao.class);
	private static final String SQL_QUERY_FIND_EMPLOYEE_BY_ID = "SELECT employee.id, employee.name, employee.surname, position.position FROM employee LEFT JOIN position on employee.position_id = position.id  WHERE employee.id=?";
	private static final String SQL_QUERY_FIND_AVAILABLE_EMPLOYEES = "SELECT employee.id, employee.name, employee.surname, position.position FROM employee LEFT JOIN position on employee.position_id = position.id WHERE employee.status=0 ORDER BY position.id";
	private static final String SQL_QUERY_FIND_CREW_BY_FLIGHT_ID = "SELECT employee.id, employee.name, employee.surname, position.position FROM crew LEFT JOIN employee on crew.employee_id=employee.id LEFT JOIN position on employee.position_id = position.id WHERE flight_id=?";
	private static final String SQL_QUERY_ADD_EMPLOYEE = "INSERT INTO employee (name, surname, position_id) VALUES (?,?,?)";
	private static final String SQL_QUERY_MODIFY_EMPLOYEE = "UPDATE employee SET name=?, surname=?, position_id=? WHERE id=?";
	private static final String SQL_QUERY_ADD_CREW = "INSERT INTO crew (flight_id, employee_id) VALUES (?,?)";
	private static final String SQL_QUERY_CHANGE_STATUS_TO_BUSY = "UPDATE employee SET status = 1 WHERE id = ?";
	private static final String SQL_QUERY_CHANGE_STATUS_TO_AVAILABLE = "UPDATE employee SET status = 0 WHERE id = ?";

	@Override
	public boolean addEmployee(String name, String surname, int position) {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		boolean flag = false;
		try {
			pool = ConnectionPool.getInstance();
			connection = pool.getConnection();
			preparedStatement = connection.prepareStatement(SQL_QUERY_ADD_EMPLOYEE);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, surname);
			preparedStatement.setInt(3, position);
			preparedStatement.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			LOGGER.error("DAOException", e);
		} finally {
			AbstractDao.close(preparedStatement);
			pool.close(connection);
		}
		return flag;
	}

	@Override
	public Employee findEmployeeById(int id) throws DaoException {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Employee employee = null;
		try {
			pool = ConnectionPool.getInstance();
			connection = pool.getConnection();
			preparedStatement = connection.prepareStatement(SQL_QUERY_FIND_EMPLOYEE_BY_ID);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			employee = createEmployee(resultSet);
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			AbstractDao.close(preparedStatement);
			pool.close(connection);
		}
		return employee;
	}

	private Employee createEmployee(ResultSet resultSet) throws SQLException {
		Employee employee = new Employee();
		employee.setId(resultSet.getInt(1));
		employee.setName(resultSet.getString(2));
		employee.setSurname(resultSet.getString(3));
		employee.setPosition(Position.valueOf((resultSet.getString(4)).toUpperCase()));
		return employee;
	}

	@Override
	public List<Employee> findAvailableEmployees() throws DaoException {
		ConnectionPool pool = null;
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		List<Employee> employees = new ArrayList<Employee>();
		try {
			pool = ConnectionPool.getInstance();
			connection = pool.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL_QUERY_FIND_AVAILABLE_EMPLOYEES);
			while (resultSet.next()) {
				Employee employee = createEmployee(resultSet);
				employees.add(employee);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			AbstractDao.close(statement);
			pool.close(connection);
		}
		return employees;
	}

	@Override
	public boolean addToFlight(int id) {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		boolean flag = false;
		try {
			pool = ConnectionPool.getInstance();
			connection = pool.getConnection();
			preparedStatement = connection.prepareStatement(SQL_QUERY_CHANGE_STATUS_TO_BUSY);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			LOGGER.error("DAOException", e);
		} finally {
			AbstractDao.close(preparedStatement);
			pool.close(connection);
		}
		return flag;
	}

	@Override
	public Set<Employee> findCrewByFlightId(int flightId) throws DaoException {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Set<Employee> employees = new HashSet<Employee>();
		try {
			pool = ConnectionPool.getInstance();
			connection = pool.getConnection();
			preparedStatement = connection.prepareStatement(SQL_QUERY_FIND_CREW_BY_FLIGHT_ID);
			preparedStatement.setInt(1, flightId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Employee employee = createEmployee(resultSet);
				employees.add(employee);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			AbstractDao.close(preparedStatement);
			pool.close(connection);
		}
		return employees;
	}

	@Override
	public boolean releaseEmployee(int employeeId) {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		boolean flag = false;
		try {
			pool = ConnectionPool.getInstance();
			connection = pool.getConnection();
			preparedStatement = connection.prepareStatement(SQL_QUERY_CHANGE_STATUS_TO_AVAILABLE);
			preparedStatement.setInt(1, employeeId);
			preparedStatement.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			LOGGER.error("DAOException", e);
		} finally {
			AbstractDao.close(preparedStatement);
			pool.close(connection);
		}
		return flag;

	}

	@Override
	public boolean formCrew(int flightId, List<Employee> crew) {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		boolean flag = false;
		try {
			pool = ConnectionPool.getInstance();
			connection = pool.getConnection();
			preparedStatement = connection.prepareStatement(SQL_QUERY_ADD_CREW);
			for (Employee employee : crew) {
				int employeeId = employee.getId();
				preparedStatement.setInt(1, flightId);
				preparedStatement.setInt(2, employeeId);
				preparedStatement.executeUpdate();
			}
			flag = true;
		} catch (SQLException e) {
			LOGGER.error("DAOException", e);
		} finally {
			AbstractDao.close(preparedStatement);
			pool.close(connection);
		}
		return flag;
	}

	@Override
	public boolean modifyEmployee(int id, String name, String surname, int position) {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		boolean flag = false;
		try {
			pool = ConnectionPool.getInstance();
			connection = pool.getConnection();
			preparedStatement = connection.prepareStatement(SQL_QUERY_MODIFY_EMPLOYEE);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, surname);
			preparedStatement.setInt(3, position);
			preparedStatement.setInt(4, id);
			preparedStatement.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			LOGGER.error("DAOException", e);
		} finally {
			AbstractDao.close(preparedStatement);
			pool.close(connection);
		}
		return flag;
	}
}