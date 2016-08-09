package ua.epam.entities;

import java.io.Serializable;

/**
 * Created by lomak on 18.01.2016.
 */
public class User implements Serializable {


    private static final long serialVersionUID = 1694681803229798348L;
    private int id;
    private boolean isAdministrator;
    private String name;
    private int passwordHash;
    private String email;
    private String login;

    /**
     *
     * @return id of user
     */
    public int getId() {
        return id;
    }


    /**
     *
     * @param id of user
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     *
     * @return user name
     */
    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }


    public int getPasswordHash() {
        return passwordHash;
    }



    public void setPasswordHash(int passwordHash) {
        this.passwordHash = passwordHash;
    }


    /**
     *
     * @return e-mail of user
     */
    public String getEmail() {
        return email;
    }


    /**
     *
     * @param e-mail of user
     */
    public void setEmail(String email) {
        this.email = email;
    }


    /**
     *
     * @return user login
     */
    public String getLogin() {
        return login;
    }



    public void setLogin(String login) {
        this.login = login;
    }


    /**
     * Calculates hash code based on user password string
     * and set up parameter hash code
     *
     * @param password user password
     */
    public void setPasswordHash(String password){
        passwordHash = password.hashCode();
    }


    /**
     *
     * @return true if user is administrator and false in another case
     */
    public boolean getAdministrator() {
        return isAdministrator;
    }


    /**
     *
     * @param isAdministrator sets administrator property of user
     */
    public void setAdministrator(boolean isAdministrator) {
        this.isAdministrator = isAdministrator;
    }


    /**
     * String representing of user
     */
    @Override
    public String toString() {
        return getName();
    }



}