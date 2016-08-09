package ua.epam.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by lomak on 18.01.2016.
 */
public class Order implements Serializable {


    private static final long serialVersionUID = 7716736259087352435L;

    private int id;
        private Date arraival;
    private Date departure;
    private int beds;
    private User user;
    private RoomClass roomClass;
    private Room room;
    private Locale locale;


    /**
     *
     * @return id of order
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id of order
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     *
     * @return client arraival date
     */
    public Date getArraival() {
        return arraival;
    }


    /**
     *
     * @param arraival date
     */
    public void setArraival(Date arraival) {
        this.arraival = arraival;
    }

    /**
     *
     * @return client departure date
     */
    public Date getDeparture() {
        return departure;
    }

    /**
     *
     * @param departure date
     */
    public void setDeparture(Date departure) {
        this.departure = departure;
    }


    /**
     *
     * @return count of beds in order
     */
    public int getBeds() {
        return beds;
    }

    /**
     *
     * @param beds count of beds in order
     */
    public void setBeds(int beds) {
        this.beds = beds;
    }


    /**
     *
     * @return user which made order
     */
    public User getUser() {
        return user;
    }


    /**
     *
     * @param user user which made order
     */
    public void setUser(User user) {
        this.user = user;
    }


    /**
     *
     * @return class of ordered room
     */
    public RoomClass getRoomClass() {
        return roomClass;
    }

    /**
     *
     * @param roomClass class of ordered room
     */
    public void setRoomClass(RoomClass roomClass) {
        this.roomClass = roomClass;
    }


    /**
     *
     * @return room which administrator gave for this order
     */
    public Room getRoom() {
        return room;
    }

    /**
     *
     * @param room  which administrator gave for this order
     */
    public void setRoom(Room room) {
        this.room = room;
    }


    /**
     *
     * @return count days which custmer ordered
     */
    public int getDays(){
        long difference = departure.getTime() - arraival.getTime();
        int days = (int) difference / (24 * 60 * 60 * 1000);
        return days;
    }



    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    /**
     * String representation of order. Uses in bill
     */
    @Override
    public String toString() {
        ResourceBundle resourses = null;
        if(locale == null){
            resourses = ResourceBundle.getBundle("ua.epam.resource.Resourses");
        } else {
            resourses = ResourceBundle.getBundle("ua.epam.resource.Resourses", locale);
        }
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yy");
        return resourses.getObject("order.from") + " " + df.format(arraival) +
                resourses.getObject("order.to")   + " " + df.format(departure) +
                resourses.getObject("order.places") + " " + getBeds() +
                resourses.getObject("order.roomClass") + " " + getRoomClass();

    }

}

