package ua.epam.dao;

import ua.epam.entities.Order;
import ua.epam.entities.Room;

import java.util.List;

/**
 * Created by lomak on 18.01.2016.
 */
public interface RoomDao {
    /**
     * Creates a room in database based on data of object of Room class
     * @param room
     */
    void create(Room room);

    /**
     * Deletes a room with defined id
     * @param id - id of room to be deleted
     */
    void delete(int id);

    /**
     * Returnes list of rooms that most sutable to passing order
     * Rooms in list sorted from more sutable for this orders rooms
     * to less sutable
     *
     * @param order - passing order
     * @return list of sutable rooms
     */
    List<Room> findFree(Order order);

    /**
     * Returnes list of all rooms in hotel
     *
     * @return list of rooms
     */
    List<Room> findAll();
}