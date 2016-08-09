package ua.epam.web.command;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.epam.resource.*;
import ua.epam.resource.ConfigurationManager;
import ua.epam.dao.DaoFactory;
import ua.epam.dao.OrderDao;
import ua.epam.entities.Order;
import ua.epam.entities.RoomClass;
import ua.epam.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by lomak on 19.01.2016.
 */
public class OrderCommand implements Command{

    public static final String PARAM_NAME_ARRIVAL = "arraival";
    public static final String PARAM_NAME_DEPARTURE = "departure";
    public static final String PARAM_NAME_ROOMCLASS = "roomclass";
    public static final String PARAM_NAME_PERSONS = "persons";

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) {

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date arraival = null;
        int roomClass_id = 0;
        Date departure = null;
        int persons = 0;

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        Locale locale = (Locale) Config.get(session, Config.FMT_LOCALE);
        ResourceBundle resourses = null;
        if(locale == null){
            resourses = ResourceBundle.getBundle("ua.epam.resource.Resourses");
        } else {
            resourses = ResourceBundle.getBundle("ua.epam.resource.Resourses", locale);
        }


        try {
            arraival = format.parse(request.getParameter(PARAM_NAME_ARRIVAL));
            roomClass_id = Integer.parseInt(request.getParameter(PARAM_NAME_ROOMCLASS));
            departure = format.parse(request.getParameter(PARAM_NAME_DEPARTURE));
            persons = Integer.parseInt(request.getParameter(PARAM_NAME_PERSONS));

            RoomClass roomClass = new RoomClass();
            roomClass.setId(roomClass_id);

            Order order = new Order();
            order.setBeds(persons);
            order.setArraival(arraival);
            order.setDeparture(departure);
            order.setRoomClass(roomClass);
            order.setUser(user);

            DaoFactory daoFactory = DaoFactory.getInstance();
            OrderDao orderDao = daoFactory.createOrderDao();
            orderDao.create(order);

            request.setAttribute("acceptOrderMessage", resourses.getObject("user.label.accepted"));

        } catch (NumberFormatException |ParseException e) {

            request.setAttribute("acceptOrderMessage", resourses.getObject("user.label.incorrectData"));
            Logger logger = LogManager.getLogger(OrderCommand.class.getName());
            logger.error("Error creation election of room class " + e);

        }
        return ConfigurationManager.getProperty("path.page.user");
    }

}