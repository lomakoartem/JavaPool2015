package ua.epam.dao;

import ua.epam.entities.RoomClass;

import java.util.List;

/**
 * Created by lomak on 18.01.2016.
 */
public interface RoomClassDao {
    /**
     * Creates new room class in database based on data of object of RoomClass
     * class
     *
     * @param roomClass
     */
    void create(RoomClass roomClass);

    /**
     * Returnes list of all RoomClasses that present in database
     *
     * @return all roomclasses
     */
    List<RoomClass> findAll();

    /**
     * Deletes room class with defined id
     *
     * @param id - id of room class to delete
     */
    void delete(int id);
}