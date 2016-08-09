package ua.epam.entities;

/**
 * Created by lomak on 18.01.2016.
 */
public class Price {

    RoomClass roomClass;
    int beds;
    int price;


    /**
     *
     * @return room class for price
     */
    public RoomClass getRoomClass() {
        return roomClass;
    }


    /**
     *
     * @param roomClass room class of price
     */
    public void setRoomClass(RoomClass roomClass) {
        this.roomClass = roomClass;
    }


    /**
     *
     * @return count of beds for price
     */
    public int getBeds() {
        return beds;
    }


    /**
     *
     * @param beds count of beds for price
     */
    public void setBeds(int beds) {
        this.beds = beds;
    }


    /**
     *
     * @return price multiplied on 100
     */
    public int getPrice() {
        return price;
    }

    /**
     *
     * @param price multiplied on 100
     */
    public void setPrice(int price) {
        this.price = price;
    }



}
