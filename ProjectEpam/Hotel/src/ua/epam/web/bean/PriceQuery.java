package ua.epam.web.bean;

import ua.epam.dao.DaoFactory;
import ua.epam.dao.PriceDao;
import ua.epam.entities.Price;

import java.util.List;

/**
 * Created by lomak on 19.01.2016.
 */

public class PriceQuery {

    /**
     * Makes bean to data source and receives price list
     * @return price list
     */
    public List<Price> getPriceList(){
        DaoFactory daoFactory = DaoFactory.getInstance();
        PriceDao priceDao = daoFactory.createPriceDao();
        return  priceDao.findAll();
    }
}