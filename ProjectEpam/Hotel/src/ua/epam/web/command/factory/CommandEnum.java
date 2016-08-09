package ua.epam.web.command.factory;

import ua.epam.web.command.*;

/**
 * Created by lomak on 19.01.2016.
 */
public enum CommandEnum {

    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    LOGOUT{
        {
            this.command = new LogoutCommand();
        }
    },
    ORDER{
        {
            this.command = new OrderCommand();
        }
    },
    UNPROCESSEDORDERS{
        {
            this.command = new UnprocessedOrders();
        }
    },
    PRICE{
        {
            this.command = new PriceCommand();
        }
    },
    PICKUPROOM{
        {
            this.command = new PickUpRoomCommand();
        }
    },
    ROOMCLASSCATALOG{
        {
            this.command = new RoomClassCatalogCommand();
        }
    },
    ROOMSCATALOG{
        {
            this.command = new RoomsCatalogCommand();
        }
    },
    SETLOCALE{
        {
            this.command = new SetLocale();
        }
    },
    SETROOM{
        {
            this.command = new SetRoomCommand();
        }
    },
    CREATEROOMCLASS{
        {
            this.command = new CreateRoomClassCommand();
        }
    },
    CREATEROOM{
        {
            this.command = new CreateRoomCommand();
        }
    },
    DELETEROOMCLASS{
        {
            this.command = new DeleteRoomClassCommand();
        }
    },
    DELETEROOM{
        {
            this.command = new DeleteRoomCommand();
        }
    },
    DELETEUSER{
        {
            this.command = new DeleteUserCommand();
        }
    },
    REGISTER{
        {
            this.command = new RegisterCommand();
        }
    },
    SETPRICELIST{
        {
            this.command = new SetPriceListCommand();
        }
    },
    USERSLIST{
        {
            this.command = new UsersListCommand();
        }
    }
    ;

    Command command;

    public Command getCurrentCommand(){
        return command;
    }
}