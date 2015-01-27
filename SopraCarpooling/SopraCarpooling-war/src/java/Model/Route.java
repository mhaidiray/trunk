/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Cette classe sert à modéliser les routes (départ+arrivée).
 * @author Machd
 */
public class Route {

    private String workplace;

    public int getNbcond() {
        return nbcond;
    }

    public int getNbpass() {
        return nbpass;
    }
    
    private int zipcode;
    private int nbcond;
    private int nbpass;

    public Route(int zipcode, String workplace) {
        this.workplace = workplace;
        this.zipcode = zipcode;
        nbcond = 0;
        nbpass = 0;
        Connection con;
        try {
            con = DatabaseManager.connectionDatabase();
            ArrayList<Model.User> listUser = DatabaseManager.getAllUsers(con);
            ArrayList<String> listPlaces = DatabaseManager.getAllWorkplaces(con);
            for (Model.User u : listUser) {
                if (zipcode == u.getZipcode() && workplace.equals(listPlaces.get(u.getWorkplace()-1))) {
                    if (u.getDriver() == 1) {
                        nbcond++;
                    } else {
                        nbpass++;
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Route.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getWorkplace() {
        return workplace;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

}
