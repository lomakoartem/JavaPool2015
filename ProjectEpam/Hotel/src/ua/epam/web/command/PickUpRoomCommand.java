package ua.epam.web.command;

import ua.epam.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lomak on 19.01.2016.
 */
public class PickUpRoomCommand implements Command {

    public static final String PARAM_NAME_ORDER_ID = "id";

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) {

        String orderIdStr = request.getParameter(PARAM_NAME_ORDER_ID);
        int orderId = Integer.parseInt(orderIdStr);
        request.setAttribute("processingOrderId", orderId);

        return ConfigurationManager.getProperty("path.page.unprocessedOrders");
    }

}