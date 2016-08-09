package ua.epam.dao;

import java.util.List;

import ua.epam.entities.*;

public abstract class FlightDao extends AbstractDao {

	public abstract List<Flight> findAllFlights() throws DaoException;

	public abstract Plane findPlaneById(int id) throws DaoException;

	public abstract Flight findFlightById(int id) throws DaoException;

	public abstract boolean setFlightOnAir(int id);

	public abstract boolean setFlightCompleted(int id);

	public abstract List<Flight> findUnformedFlights() throws DaoException;

	public abstract List<Flight> findStatusFlights(int status) throws DaoException;

	public abstract List<Flight> findFlightsByStatus(int status, int page) throws DaoException;

	public abstract boolean addFlight(int flightId, String to, String from, String date, int plane);

	public abstract List<Plane> findAllPlanes() throws DaoException;

	public abstract boolean deleteFlight(int flightId);

}
