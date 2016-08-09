package ua.epam.dao.implementation;


	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.ArrayList;
	import java.util.List;
	import ua.epam.dao.*;
	import ua.epam.entities.*;
	import ua.epam.pool.ConnectionPool;
	import org.apache.log4j.Logger;

	public class FlightDaoImpl extends FlightDao {

		private static final Logger LOGGER = Logger.getLogger(FlightDao.class);
		private static final String SQL_QUERY_FIND_ALL_FLIGHTS = "SELECT * FROM flight";
		private static final String SQL_QUERY_FIND_PLANE_BY_ID = "SELECT * FROM plane_staff WHERE plane=? ";
		private static final String SQL_QUERY_FIND_FLIGHT_BY_ID = "SELECT * FROM flight WHERE id=? ";
		private static final String SQL_QUERY_CHANGE_STATUS_TO_ON_AIR = "UPDATE flight SET status = 1 WHERE id = ?";
		private static final String SQL_QUERY_CHANGE_STATUS_TO_COMPLETED = "UPDATE flight SET status = 2 WHERE id = ?";
		private static final String SQL_QUERY_FIND_UNFORMED_FLIGHTS = "SELECT * FROM flight WHERE status=0";
		private static final String SQL_QUERY_FIND_FLIGHTS_BY_STATUS_AND_START_ELEMENT = "SELECT * FROM flight WHERE status=? LIMIT ?,2";
		private static final String SQL_QUERY_FIND_FLIGHTS_BY_STATUS = "SELECT * FROM flight WHERE status=?";
		private static final String SQL_QUERY_ADD_FLIGHT = "INSERT INTO flight VALUES (?,?,?,?,?,0)";
		private static final String SQL_QUERY_FIND_ALL_PLANES = "SELECT * FROM plane_staff";
		private static final String SQL_QUERY_DELETE_FLIGHT_BY_ID = "DELETE FROM flight WHERE id=?";
		private static final String SQL_QUERY_DELETE_CREW_BY_ID = "DELETE FROM crew WHERE flight_id=?";

		@Override
		public List<Flight> findAllFlights() throws DaoException {
			ConnectionPool pool = null;
			Connection connection = null;
			Statement statement = null;
			ResultSet resultSet = null;
			List<Flight> flights = new ArrayList<Flight>();
			try {
				pool = ConnectionPool.getInstance();
				connection = pool.getConnection();
				statement = connection.createStatement();
				resultSet = statement.executeQuery(SQL_QUERY_FIND_ALL_FLIGHTS);
				while (resultSet.next()) {
					Flight flight = createFlight(resultSet);
					flights.add(flight);
				}
			} catch (SQLException e) {
				throw new DaoException(e);
			} finally {
				AbstractDao.close(statement);
				pool.close(connection);
			}
			return flights;
		}

		private Flight createFlight(ResultSet resultSet) throws SQLException {
			Flight flight = new Flight();
			flight.setId(resultSet.getInt(1));
			flight.setDate(resultSet.getDate(2));
			flight.setFrom(resultSet.getString(3));
			flight.setTo(resultSet.getString(4));
			flight.setStatus(resultSet.getInt(6));
			int id = resultSet.getInt(5);
			Plane plane = findPlaneById(id);
			flight.setPlane(plane);
			return flight;
		}

		@Override
		public Flight findFlightById(int id) throws DaoException {
			ConnectionPool pool = null;
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			Flight flight = null;
			try {
				pool = ConnectionPool.getInstance();
				connection = pool.getConnection();
				preparedStatement = connection
						.prepareStatement(SQL_QUERY_FIND_FLIGHT_BY_ID);
				preparedStatement.setInt(1, id);
				resultSet = preparedStatement.executeQuery();
				resultSet.next();
				flight = createFlight(resultSet);
			} catch (SQLException e) {
				throw new DaoException(e);
			} finally {
				AbstractDao.close(preparedStatement);
				pool.close(connection);
			}
			return flight;
		}

		@Override
		public Plane findPlaneById(int id) throws DaoException {
			ConnectionPool pool = null;
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			Plane plane = null;
			try {
				pool = ConnectionPool.getInstance();
				connection = pool.getConnection();
				preparedStatement = connection
						.prepareStatement(SQL_QUERY_FIND_PLANE_BY_ID);
				preparedStatement.setInt(1, id);
				resultSet = preparedStatement.executeQuery();
				resultSet.next();
				plane = createPlane(resultSet);
			} catch (SQLException e) {
				throw new DaoException(e);
			} finally {
				AbstractDao.close(preparedStatement);
				pool.close(connection);
			}
			return plane;
		}

		private Plane createPlane(ResultSet resultSet) throws SQLException {
			Plane plane = new Plane();
			plane.setId(resultSet.getInt(1));
			plane.setPilot(resultSet.getInt(2));
			plane.setNavigator(resultSet.getInt(3));
			plane.setWirelessOperator(resultSet.getInt(4));
			plane.setStewardess(resultSet.getInt(5));
			return plane;
		}

		@Override
		public boolean setFlightOnAir(int id) {
			ConnectionPool pool = null;
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			boolean flag = false;
			try {
				pool = ConnectionPool.getInstance();
				connection = pool.getConnection();
				preparedStatement = connection
						.prepareStatement(SQL_QUERY_CHANGE_STATUS_TO_ON_AIR);
				preparedStatement.setInt(1, id);
				preparedStatement.executeUpdate();
				flag = true;
			} catch (SQLException e) {
				LOGGER.error("TechnicalException", e);
			} finally {
				AbstractDao.close(preparedStatement);
				pool.close(connection);
			}
			return flag;
		}

		@Override
		public List<Flight> findUnformedFlights() throws DaoException {
			ConnectionPool pool = null;
			Connection connection = null;
			Statement statement = null;
			ResultSet resultSet = null;
			List<Flight> flights = new ArrayList<Flight>();
			try {
				pool = ConnectionPool.getInstance();
				connection = pool.getConnection();
				statement = connection.createStatement();
				resultSet = statement.executeQuery(SQL_QUERY_FIND_UNFORMED_FLIGHTS);
				while (resultSet.next()) {
					Flight flight = createFlight(resultSet);
					flights.add(flight);
				}
			} catch (SQLException e) {
				throw new DaoException(e);
			} finally {
				AbstractDao.close(statement);
				pool.close(connection);
			}
			return flights;
		}

		@Override
		public boolean setFlightCompleted(int id) {
			ConnectionPool pool = null;
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			boolean flag = false;
			try {
				pool = ConnectionPool.getInstance();
				connection = pool.getConnection();
				preparedStatement = connection
						.prepareStatement(SQL_QUERY_CHANGE_STATUS_TO_COMPLETED);
				preparedStatement.setInt(1, id);
				preparedStatement.executeUpdate();
				flag = true;
			} catch (SQLException e) {
				LOGGER.error("TechnicalException", e);
			} finally {
				AbstractDao.close(preparedStatement);
				pool.close(connection);
			}
			return flag;
		}

		@Override
		public boolean addFlight(int flightId, String to, String from, String date,
				int plane) {
			ConnectionPool pool = null;
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			boolean flag = false;
			try {
				pool = ConnectionPool.getInstance();
				connection = pool.getConnection();
				preparedStatement = connection
						.prepareStatement(SQL_QUERY_ADD_FLIGHT);
				preparedStatement.setInt(1, flightId);
				preparedStatement.setString(2, date);
				preparedStatement.setString(3, from);
				preparedStatement.setString(4, to);
				preparedStatement.setInt(5, plane);
				preparedStatement.executeUpdate();
				flag = true;
			} catch (SQLException e) {
				LOGGER.error("TechnicalException", e);
			} finally {
				AbstractDao.close(preparedStatement);
				pool.close(connection);
			}
			return flag;
		}

		@Override
		public List<Plane> findAllPlanes() throws DaoException {
			ConnectionPool pool = null;
			Connection connection = null;
			Statement statement = null;
			ResultSet resultSet = null;
			List<Plane> planes = new ArrayList<Plane>();
			try {
				pool = ConnectionPool.getInstance();
				connection = pool.getConnection();
				statement = connection.createStatement();
				resultSet = statement.executeQuery(SQL_QUERY_FIND_ALL_PLANES);
				while (resultSet.next()) {
					Plane plane = createPlane(resultSet);
					planes.add(plane);
				}
			} catch (SQLException e) {
				throw new DaoException(e);
			} finally {
				AbstractDao.close(statement);
				pool.close(connection);
			}
			return planes;
		}

		@Override
		public boolean deleteFlight(int flightId) {
			ConnectionPool pool = null;
			Connection connection = null;
			PreparedStatement preparedStatementFlight = null;
			PreparedStatement preparedStatementCrew = null;
			boolean flag = false;
			try {
				pool = ConnectionPool.getInstance();
				connection = pool.getConnection();

				preparedStatementCrew = connection
						.prepareStatement(SQL_QUERY_DELETE_CREW_BY_ID);
				preparedStatementCrew.setInt(1, flightId);
				preparedStatementCrew.executeUpdate();
				preparedStatementFlight = connection
						.prepareStatement(SQL_QUERY_DELETE_FLIGHT_BY_ID);
				preparedStatementFlight.setInt(1, flightId);
				preparedStatementFlight.executeUpdate();
				flag = true;
			} catch (SQLException e) {
				LOGGER.error("TechnicalException", e);
			} finally {
				AbstractDao.close(preparedStatementFlight);
				AbstractDao.close(preparedStatementCrew);
				pool.close(connection);
			}
			return flag;
		}

		@Override
		public List<Flight> findFlightsByStatus(int status, int page)
				throws DaoException {
			ConnectionPool pool = null;
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			List<Flight> flights = new ArrayList<Flight>();
			try {
				pool = ConnectionPool.getInstance();
				connection = pool.getConnection();
				preparedStatement = connection
						.prepareStatement(SQL_QUERY_FIND_FLIGHTS_BY_STATUS_AND_START_ELEMENT);
				preparedStatement.setInt(1, status);
				preparedStatement.setInt(2, page);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					Flight flight = createFlight(resultSet);
					flights.add(flight);
				}
			} catch (SQLException e) {
				throw new DaoException(e);
			} finally {
				AbstractDao.close(preparedStatement);
				pool.close(connection);
			}
			return flights;
		}

		@Override
		public List<Flight> findStatusFlights(int status) throws DaoException {
			ConnectionPool pool = null;
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			List<Flight> flights = new ArrayList<Flight>();
			try {
				pool = ConnectionPool.getInstance();
				connection = pool.getConnection();
				preparedStatement = connection
						.prepareStatement(SQL_QUERY_FIND_FLIGHTS_BY_STATUS);
				preparedStatement.setInt(1, status);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					Flight flight = createFlight(resultSet);
					flights.add(flight);
				}
			} catch (SQLException e) {
				throw new DaoException(e);
			} finally {
				AbstractDao.close(preparedStatement);
				pool.close(connection);
			}
			return flights;
		}

	}