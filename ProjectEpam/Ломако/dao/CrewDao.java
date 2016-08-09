package ua.epam.dao;

import java.util.List;
import java.util.Set;

import ua.epam.entities.Employee;

public abstract class CrewDao extends AbstractDao {

	public abstract boolean addEmployee(String name, String surname, int position);

	public abstract Employee findEmployeeById(int id) throws DaoException;

	public abstract List<Employee> findAvailableEmployees() throws DaoException;

	public abstract boolean addToFlight(int id);

	public abstract Set<Employee> findCrewByFlightId(int flightId) throws DaoException;

	public abstract boolean releaseEmployee(int employeeId);

	public abstract boolean formCrew(int flightId, List<Employee> crew);

	public abstract boolean modifyEmployee(int id, String name, String surname, int position);

}
