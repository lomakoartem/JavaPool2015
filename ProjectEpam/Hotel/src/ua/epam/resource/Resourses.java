package ua.epam.resource;

import java.util.ListResourceBundle;

/**
 * Created by lomak on 19.01.2016.
 */
public class Resourses extends ListResourceBundle {

    @Override
    public Object[][] getContents() {
        return contents;
    }

    static final Object[][] contents = {
            { "billtag.label.order", "       заказ      "},
            { "billtag.label.days", "       дней      "},
            { "billtag.label.price", "      цена       "},
            { "billtag.label.sum", "      сумма      "},
            { "billtag.label.bill", "       Счёт     "},
            { "order.from", "С"}	,
            { "order.to", "    По   "}	,
            { "order.places", "      Места    "},
            { "order.roomClass", "   Класс     "},
            {"user.label.accepted", "     Заявка принята    "},
            {"user.label.incorrectData","    Неверные данные     "}

    };
}