package ua.epam.web.command.factory;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.epam.web.command.Command;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lomak on 19.01.2016.
 */
public class CommandFactory {
    public Command defineCommand(HttpServletRequest request){
        Command current = null;
        String action = request.getParameter("command");
        if(action != null){
            try{
                CommandEnum currenEnum = CommandEnum.valueOf(action.toUpperCase());
                current = currenEnum.getCurrentCommand();
                return current;
            } catch (IllegalArgumentException e){
                Logger logger = LogManager.getLogger(CommandFactory.class.getName());
                logger.error("Incorrect command " + e);
            }
        }
        return current;
    }
}