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
    private static User user=null;
    private static Workplace workplace=null;
    private static Admin admin=null;

    public static User getUser() {
        return user;
    }

    public static Workplace getWorkplace() {
        return workplace;
    }

    public static Admin getAdmin() {
        return admin;
    }

    public static void setUser(User User) {
        Model.user = User;
    }

    public static void setWorkplace(Workplace workplace) {
        Model.workplace = workplace;
    }

    public static void setAdmin(Admin admin) {
        Model.admin = admin;
    }
    
    public class Workplace {
        private String name;
        private String address;
        
        public Workplace(String n,String a){
            this.name=n;
            this.address=a;
        }

        public String getName() {
            return name;
        }

        public String getAddress() {
            return address;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAddress(String address) {
            this.address = address;
        }
        
        
    }
    
    public class User {
        private String email;
        private String password;
        private String firstname;
        private String lastname;
        private String phone;
        private int zipcode;
        private int workplace;
        private String morning_time;
        private String afternoon_time;
        private int driver;
        private int monday;
        private int tuesday;
        private int wednesday;
        private int thursday;
        private int friday;
        private int saturday;
        private int sunday;
        private int notification;

        public User(String email, String password, String firstname, String lastname, String phone, int zipcode, int workplace, String morning_time, String afternoon_time, int driver, int monday, int tuesday, int wednesday, int thursday, int friday, int saturday, int sunday, int notification) {
            this.email = email;
            this.password = password;
            this.firstname = firstname;
            this.lastname = lastname;
            this.phone = phone;
            this.zipcode = zipcode;
            this.workplace = workplace;
            this.morning_time = morning_time;
            this.afternoon_time = afternoon_time;
            this.driver = driver;
            this.monday = monday;
            this.tuesday = tuesday;
            this.wednesday = wednesday;
            this.thursday = thursday;
            this.friday = friday;
            this.saturday = saturday;
            this.sunday = sunday;
            this.notification = notification;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }

        public String getFirstname() {
            return firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public String getPhone() {
            return phone;
        }

        public int getZipcode() {
            return zipcode;
        }

        public int getWorkplace() {
            return workplace;
        }

        public String getMorning_time() {
            return morning_time;
        }

        public String getAfternoon_time() {
            return afternoon_time;
        }

        public int getDriver() {
            return driver;
        }

        public int getMonday() {
            return monday;
        }

        public int getTuesday() {
            return tuesday;
        }

        public int getWednesday() {
            return wednesday;
        }

        public int getThursday() {
            return thursday;
        }

        public int getFriday() {
            return friday;
        }

        public int getSaturday() {
            return saturday;
        }

        public int getSunday() {
            return sunday;
        }

        public int getNotification() {
            return notification;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public void setZipcode(int zipcode) {
            this.zipcode = zipcode;
        }

        public void setWorkplace(int workplace) {
            this.workplace = workplace;
        }

        public void setMorning_time(String morning_time) {
            this.morning_time = morning_time;
        }

        public void setAfternoon_time(String afternoon_time) {
            this.afternoon_time = afternoon_time;
        }

        public void setDriver(int driver) {
            this.driver = driver;
        }

        public void setMonday(int monday) {
            this.monday = monday;
        }

        public void setTuesday(int tuesday) {
            this.tuesday = tuesday;
        }

        public void setWednesday(int wednesday) {
            this.wednesday = wednesday;
        }

        public void setThursday(int thursday) {
            this.thursday = thursday;
        }

        public void setFriday(int friday) {
            this.friday = friday;
        }

        public void setSaturday(int saturday) {
            this.saturday = saturday;
        }

        public void setSunday(int sunday) {
            this.sunday = sunday;
        }

        public void setNotification(int notification) {
            this.notification = notification;
        }
        
        
        
        
        
        
    }
    
    public class Admin {
        private String name;
        private String mail;
        
        public Admin(String n, String m) {
            this.name=n;
            this.mail=m;
        }

        public String getName() {
            return name;
        }

        public String getMail() {
            return mail;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setMail(String mail) {
            this.mail = mail;
        }

        
    }
}
