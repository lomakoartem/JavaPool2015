package ua.epam.web.command;

import ua.epam.resource.ConfigurationManager;
import ua.epam.dao.DaoFactory;
import ua.epam.dao.PriceDao;
import ua.epam.entities.Price;
import ua.epam.entities.RoomClass;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lomak on 19.01.2016.
 */
public class SetPriceListCommand implements Command {

    public static final String PARAM_NAME_ROOMCLASS_ID = "roomClassId";
    public static final String PARAM_NAME_BEDS = "beds";
    public static final String PARAM_NAME_PRICE = "price";

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) {

        List<Price> lst = new ArrayList<>();

        int count = 1;

        String roomClassIdStr = request.getParameter(PARAM_NAME_ROOMCLASS_ID + count);
        String bedsStr = request.getParameter(PARAM_NAME_BEDS + count);
        String priceStr = request.getParameter(PARAM_NAME_PRICE + count);
        while(roomClassIdStr != null){

            int roomClassId = Integer.parseInt(roomClassIdStr);
            RoomClass roomClass = new RoomClass();
            roomClass.setId(roomClassId);

            int beds = Integer.parseInt(bedsStr);
            int priceValue = (int) (Double.valueOf(priceStr) * 100);
            Price price = new Price();
            price.setBeds(beds);
            price.setPrice(priceValue);
            price.setRoomClass(roomClass);

            lst.add(price);

            count++;
            roomClassIdStr = request.getParameter(PARAM_NAME_ROOMCLASS_ID + count);
            bedsStr = request.getParameter(PARAM_NAME_BEDS + count);
            priceStr = request.getParameter(PARAM_NAME_PRICE + count);
        }

        DaoFactory daoFactory = DaoFactory.getInstance();
        PriceDao priceDao = daoFactory.createPriceDao();
        priceDao.setPriceList(lst);

        return ConfigurationManager.getProperty("path.page.price");
    }

}