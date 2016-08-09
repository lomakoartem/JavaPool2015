package ua.epam.web.tag;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.epam.dao.DaoFactory;
import ua.epam.dao.RoomClassDao;
import ua.epam.entities.RoomClass;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by lomak on 19.01.2016.
 */
@SuppressWarnings("serial")
public class RoomClassChoose extends TagSupport {
    public RoomClassChoose() {
    }

    public int doStartTag() throws JspException {
        try {
            DaoFactory e = DaoFactory.getInstance();
            RoomClassDao logger1 = e.createRoomClassDao();
            List roomClasses = logger1.findAll();
            JspWriter out = this.pageContext.getOut();
            out.write("<select name=\"roomclass\">");
            Iterator var6 = roomClasses.iterator();

            while(var6.hasNext()) {
                RoomClass roomClass = (RoomClass)var6.next();
                out.write("<option value= " + roomClass.getId() + ">" + roomClass.getName() + "</option>");
            }

            out.write("</select>");
        } catch (IOException var7) {
            Logger logger = LogManager.getLogger(RoomClassChoose.class.getName());
            logger.error("Error creation election of room class " + var7);
        }

        return 0;
    }
}
