/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Samih
 * Cette classe a pour utilité de faciliter les échanges d'informations entre les composants
 * du système, grâce à des sous-classes contenant toutes les informations des utilisateurs.
 */
public class Model {
    
    class Workplace {
        private String name;
        private String address;
        private String phone;
        
        public Workplace(String n,String a,String p){
            this.name=n;
            this.address=a;
            this.phone=p;
        }
    }
    
    class User {
        private String firstname;
        private String lastname;
        private String address;
        private String email;
        private String phone;
        private boolean driver;
        private boolean notify;
        private boolean[] days;
        private int[] morningschedule;
        private int[] eveningschedule;
        private Workplace workplace;
        private String commune;
        private int zipcode;
        
        public User(String fn,String ln,String ad,String em, String ph,boolean dr,boolean notif,boolean[] days,int[] morn,int[] even, Workplace wp, String com,int zip) {
            this.firstname=fn;
            this.lastname=ln;
            this.address=ad;
            this.email=em;
            this.phone=ph;
            this.driver=dr;
            this.notify=notif;
            this.days=days;
            this.morningschedule=morn;
            this.eveningschedule=even;
            this.workplace=wp;
            this.commune=com;
            this.zipcode=zip;
        }
    }
    
    class Admin {
        private String name;
        private String mail;
        
        public Admin(String n, String m) {
            this.name=n;
            this.mail=m;
        }
    }
}
