package ua.epam.entities;

/**
 * Created by lomak on 18.01.2016.
 */
public class RoomClass {


        int id;
        String name;

        /**
         *
         * @return id of room class
         */
        public int getId() {
            return id;
        }


        /**
         *
         * @param id of room class
         */
        public void setId(int id) {
            this.id = id;
        }


        /**
         *
         * @return name of room class
         */
        public String getName() {
            return name;
        }


        /**
         *
         * @param name name of room class
         */
        public void setName(String name) {
            this.name = name;
        }


        /**
         * String representing of room class
         */
        @Override
        public String toString() {
            return getName();
        }


    }

