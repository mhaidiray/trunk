/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Machd
 */
public class Route {
    
    private String workplace;
    private int zipcode;

    public Route(int zipcode, String workplace) {
        this.workplace = workplace;
        this.zipcode = zipcode;
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
