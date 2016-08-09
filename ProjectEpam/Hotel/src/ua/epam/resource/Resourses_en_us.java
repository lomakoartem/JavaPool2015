package ua.epam.resource;

import java.util.ListResourceBundle;

/**
 * Created by lomak on 19.01.2016.
 */
public class Resourses_en_us extends ListResourceBundle {

    @Override
    public Object[][] getContents() {
        return contents;
    }

    static final Object[][] contents = {
            { "billtag.label.order", "    order    " },
            { "billtag.label.days", "      days     "},
            { "billtag.label.price",  "      price         "},
            { "billtag.label.sum", "           sum                 "},
            { "billtag.label.bill", "          Bill            "},
            { "order.from", "                  from                 "}	,
            { "order.to", "                    to                   "}	,
            { "order.places", "              places                  "},
            { "order.roomClass", "          room  class          "},
            {"user.label.accepted", "         Your order accepted          "},
            {"user.label.incorrectData","          Incorrect data input             "}
    };
}