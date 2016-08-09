package ua.epam.dao;

import ua.epam.entities.Price;
import ua.epam.entities.RoomClass;

import java.util.List;

/**
 * Created by lomak on 18.01.2016.
 */
public interface PriceDao {

    /**
     * Returns price of rooms with defined room class and defined
     * count of beds
     *
     * @param roomClass - class of room
     * @param beds - count of beds
     * @return price - price multiplied on 100
     *
     */
    int getPrice(RoomClass roomClass, int beds);

    /**
     * Finds all possible prices which can be in addiction
     * of current classes of rooms and count of beds in them
     *
     * @return prices
     */
    List<Price> findAll();

    /**
     * Sets prices for all rooms. Before setting old price list removing
     *
     * @param lst price list
     */
    void setPriceList(List<Price> lst);

}