package ua.epam.web.tag;

import ua.epam.entities.Order;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.jstl.core.Config;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
/**
 * Created by lomak on 19.01.2016.
 */
@SuppressWarnings("serial")
public class BillTag extends TagSupport {
    private Order order;

    public BillTag() {
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int doStartTag() throws JspException {
        Locale locale = (Locale)Config.get(this.pageContext.getSession(), "javax.servlet.jsp.jstl.fmt.locale");
        ResourceBundle resourses = null;
        if(locale == null) {
            resourses = ResourceBundle.getBundle("ua.epam.resource.Resourses");
        } else {
            resourses = ResourceBundle.getBundle("ua.epam.resource.Resourses", locale);
        }

        try {
            double e = (double)this.order.getRoom().getPrice() / 100.0D;
            JspWriter out = this.pageContext.getOut();
            out.write("<table border=1>");
            out.write("<caption>" + resourses.getObject("billtag.label.bill") + " №" + this.order.getId() + "</caption>");
            out.write("<tr><th>" + resourses.getObject("billtag.label.order") + " </th>" + "<th>" + resourses.getObject("billtag.label.days") + "</th>" + "<th>" + resourses.getObject("billtag.label.price") + "</th>" + "<th>" + resourses.getObject("billtag.label.sum") + "</th></tr>");
            out.write("<tr><td>" + this.order + "</th><td>" + this.order.getDays() + "</td><td>" + e + "</td><td>" + e * (double)this.order.getDays() + "</td></tr>");
            out.write("</table>");
        } catch (IOException var6) {
            Logger logger = LogManager.getLogger(BillTag.class.getName());
            logger.error("Error creating Bill tag " + var6);
        }

        return 0;
    }
}
