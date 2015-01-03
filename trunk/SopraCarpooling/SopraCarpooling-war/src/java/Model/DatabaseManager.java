/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.Model.User;
import Model.Model.Workplace;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mohamedsqualli
 */
public class DatabaseManager {
    private static String userName = "root" ;
    private static String password = "coco" ;
    private static String serverName = "localhost";
    private static String portNumber="3306";
    private static String dbName ="SopraCarpooling";
    
    /** Retourne le lien de connection après l'avoir établi */
    public static Connection connectionDatabase() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Connection conn = null;
        conn = DriverManager.getConnection("jdbc:mysql://"+serverName+":"+portNumber+"/"+dbName, userName, password);
        System.out.println("Connected to database");
        return conn;
    }
    
    /** Retourne null si cet utilisateur n'existe pas, sinon retourne "admin" ou "user"  */
    public static String verifConnection(Connection con,String email,String mdp){
        String exist = null ;
        try {
            
            Statement smt = con.createStatement() ;
            ResultSet resultset = smt.executeQuery("SELECT ID_Member FROM Member WHERE email = '"+email+"' AND  password = '"+mdp+"'");
            if (resultset.next()){
                int i = resultset.getInt("ID_Member");
                resultset =smt.executeQuery("SELECT ID_Member FROM Admin WHERE ID_Member = "+i);
                if (!resultset.next()){
                    resultset =smt.executeQuery("SELECT ID_Member FROM User WHERE ID_Member = "+i);
                    if (resultset.next()){
                        exist = "user";
                    }
                }else {
                    exist = "admin";
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exist ;
    }
    
    /** Vérifie l'existence du mail dans la table des utilisateurs, renvoie "true" s'il existe, "false" sinon */
    public static Boolean existMail(Connection con, String email){
        Boolean exist = false ;
        try {
            Statement smt = con.createStatement();
            ResultSet resultset =smt.executeQuery("SELECT Member.ID_Member FROM Member JOIN User ON Member.ID_Member=User.ID_Member WHERE email = '"+email+"'");
            if (resultset.next()){
                exist=true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exist ;
    }
    
    /** Modifie le mot de passe de l'utilisateur ayant l'email "email" avec le nouveau mot de passe "mdp" et renvoie "true" si l'email existe dans la base de données */
    public static Boolean modifPwd(Connection con,String email, String mdp){
        Boolean exist = false;
        try {
            if (existMail(con,email)){
            Statement smt = con.createStatement() ;
            smt.executeUpdate("UPDATE Member SET password='"+mdp+"' WHERE email='"+email+"'");
            exist = true ;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exist ;
    }
    
    /** Crée un nouvel utilisateur avec les données contenues dans "user" et renvoie "true" si l'email n'existe pas déja dans la base de données  */
    public static Boolean createUser(Connection con,User user){
        Boolean notexist = false ;
        try {
            if (!existMail(con,user.getEmail())){
            Statement smt = con.createStatement() ;
            smt.executeUpdate("INSERT INTO Member (email,password) VALUES ('"+user.getEmail()+"','"+user.getPassword()+"')");
            ResultSet resultset = smt.executeQuery("SELECT ID_Member FROM Member WHERE email = '"+user.getEmail()+"'");
            System.out.println("le resultat est "+resultset.next());
            int i = resultset.getInt("ID_Member");
            
            smt.executeUpdate("INSERT INTO User VALUES ('"+i+"','"+user.getFirstname()+"','"+user.getLastname()+"','"+user.getPhone()+"','"+user.getZipcode()+"','"+user.getWorkplace()+"','"+user.getMorning_time()+"','"+user.getAfternoon_time()+"','"+user.getDriver()+"','"+user.getMonday()+"','"+user.getTuesday()+"','"+user.getWednesday()+"','"+user.getThursday()+"','"+user.getFriday()+"','"+user.getSaturday()+"','"+user.getSunday()+"','"+user.getNotification()+"')");
            notexist = true ;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return notexist ;
    }
    
    /** Modifie les données de l'utilisateur ayant le profil "lastUser" par les nouvelles données contenues dans "user" et renvoie true si l'utilisateur existe */
    public static Boolean modifUser(Connection con,User user,User lastUser){
        String lastEmail = lastUser.getEmail();
        String lastMdp = lastUser.getPassword();
        Boolean exist = false ;
        try {
            if (verifConnection(con, lastEmail, lastMdp)=="user"){
            Statement smt = con.createStatement() ;
            ResultSet resultset =smt.executeQuery("SELECT ID_Member FROM Member WHERE email = '"+lastEmail+"' AND password='"+lastMdp+"'");
            System.out.println("le resultat est "+resultset.next());
            int i = resultset.getInt("ID_Member");
            smt.executeUpdate("UPDATE Member SET email='"+user.getEmail()+"',password='"+user.getPassword()+"' WHERE ID_Member="+i);
            smt.executeUpdate("UPDATE User SET firstname='"+user.getFirstname()+"', lastname='"+user.getLastname()+"',phone='"+user.getPhone()+"',zipcode="+user.getZipcode()+",workplace="+user.getWorkplace()+",morning_time='"+user.getMorning_time()+"',afternoon_time='"+user.getAfternoon_time()+"',driver="+user.getDriver()+",monday="+user.getMonday()+",tuesday="+user.getTuesday()+",wednesday="+user.getWednesday()+",thursday="+user.getThursday()+",friday="+user.getFriday()+",saturday="+user.getSaturday()+",sunday="+user.getSunday()+",notification="+user.getNotification()+" WHERE ID_Member="+i);
            exist = true ;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exist ;
    }
    
    /** Retourne un objet User contenant les données de l'utilisateur ayant comme email "email" et comme mot de passe "mdp" */
    public static User recupData(Connection con,String email,String mdp){
        Model model = new Model();
        Model.User user = null; //model.new User(
        try {
            if (verifConnection(con, email, mdp)=="user"){
            Statement smt = con.createStatement() ;
            ResultSet resultset =smt.executeQuery("SELECT * FROM Member JOIN User ON Member.ID_Member=User.ID_Member WHERE email = '"+email+"' AND password='"+mdp+"'");
            System.out.println("le resultat est "+resultset.next());
            user = model.new User(resultset.getString("email"),resultset.getString("password"),resultset.getString("firstname"),resultset.getString("lastname"),resultset.getString("phone"),resultset.getInt("zipcode"),resultset.getInt("workplace"),resultset.getString("morning_time"),resultset.getString("afternoon_time"),resultset.getInt("driver"),resultset.getInt("monday"),resultset.getInt("tuesday"),resultset.getInt("wednesday"),resultset.getInt("thursday"),resultset.getInt("friday"),resultset.getInt("saturday"),resultset.getInt("sunday"),resultset.getInt("notification"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user ;
    } 
    
    /** Retourne une liste d'objets User ayant le même trajet source="zipcode" et workplace="sitesopra" */
    public static ArrayList<User> usersSameJourney(Connection con,int zipcode,int sitesopra){
        
        ArrayList<User> listUsers = new ArrayList<User>();
        Model model = new Model();
        Model.User user = null; //model.new User(
        try {
            
            Statement smt = con.createStatement() ;
            ResultSet resultset =smt.executeQuery("SELECT * FROM User JOIN Member ON User.ID_Member=Member.ID_Member WHERE User.zipcode = "+zipcode+" AND User.workplace="+sitesopra);
            //ResultSet resultset =smt.executeQuery("SELECT ID_Member FROM Member WHERE email = '"+lastEmail+"' AND password='"+lastMdp+"'");
            while(resultset.next()){
            user = model.new User(resultset.getString("email"),"none",resultset.getString("firstname"),resultset.getString("lastname"),resultset.getString("phone"),resultset.getInt("zipcode"),resultset.getInt("workplace"),resultset.getString("morning_time"),resultset.getString("afternoon_time"),resultset.getInt("driver"),resultset.getInt("monday"),resultset.getInt("tuesday"),resultset.getInt("wednesday"),resultset.getInt("thursday"),resultset.getInt("friday"),resultset.getInt("saturday"),resultset.getInt("sunday"),resultset.getInt("notification"));
            listUsers.add(user);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listUsers ;
        
    }
    
    /** Retourne une liste d'objets User ayant le même trajet source="zipcode" et workplace="sitesopra" */
    public static ArrayList<User> usersSameJourney(Connection con,int zipcode,String sitesopra){
        ArrayList<User> listUsers = new ArrayList<User>();
        Model model = new Model();
        Model.User user = null; //model.new User(
        try {
            
            Statement smt = con.createStatement() ;
            ResultSet resultset =smt.executeQuery("SELECT ID_WD FROM Work_Destination WHERE Site = '"+sitesopra+"'");
            if(resultset.next()){
                int id_site = resultset.getInt("ID_WD");
                resultset =smt.executeQuery("SELECT * FROM User JOIN Member ON User.ID_Member=Member.ID_Member WHERE User.zipcode = "+zipcode+" AND User.workplace="+id_site);
                //ResultSet resultset =smt.executeQuery("SELECT ID_Member FROM Member WHERE email = '"+lastEmail+"' AND password='"+lastMdp+"'");
                while(resultset.next()){
                    user = model.new User(resultset.getString("email"),"none",resultset.getString("firstname"),resultset.getString("lastname"),resultset.getString("phone"),resultset.getInt("zipcode"),resultset.getInt("workplace"),resultset.getString("morning_time"),resultset.getString("afternoon_time"),resultset.getInt("driver"),resultset.getInt("monday"),resultset.getInt("tuesday"),resultset.getInt("wednesday"),resultset.getInt("thursday"),resultset.getInt("friday"),resultset.getInt("saturday"),resultset.getInt("sunday"),resultset.getInt("notification"));
                    listUsers.add(user);
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listUsers ;
    }
    
    /** Retourne une liste d'objets User ayant le même trajet source="zipcode" et workplace="sitesopra" et souhaitant être notifiés */
    public static ArrayList<User> usersSameJourneyNotif(Connection con,int zipcode,int sitesopra){
        
        ArrayList<User> listUsers = new ArrayList<User>();
        Model model = new Model();
        Model.User user = null; //model.new User(
        try {
            
            Statement smt = con.createStatement() ;
            ResultSet resultset =smt.executeQuery("SELECT * FROM User JOIN Member ON User.ID_Member=Member.ID_Member WHERE User.zipcode = "+zipcode+" AND User.workplace="+sitesopra+" AND User.notification=1");
            //ResultSet resultset =smt.executeQuery("SELECT ID_Member FROM Member WHERE email = '"+lastEmail+"' AND password='"+lastMdp+"'");
            while(resultset.next()){
            user = model.new User(resultset.getString("email"),"none",resultset.getString("firstname"),resultset.getString("lastname"),resultset.getString("phone"),resultset.getInt("zipcode"),resultset.getInt("workplace"),resultset.getString("morning_time"),resultset.getString("afternoon_time"),resultset.getInt("driver"),resultset.getInt("monday"),resultset.getInt("tuesday"),resultset.getInt("wednesday"),resultset.getInt("thursday"),resultset.getInt("friday"),resultset.getInt("saturday"),resultset.getInt("sunday"),resultset.getInt("notification"));
            listUsers.add(user);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listUsers ;
        
    }
    
    /** Retourne l'ID d'un site Sopra connaissant son nom complet "sitesopra" */
    public static int getJourneyId(Connection con,String sitesopra){
        int id = 0;
        try {
            Statement smt = con.createStatement() ;
            ResultSet resultset =smt.executeQuery("SELECT ID_WD FROM Work_Destination WHERE Site = '"+sitesopra+"'");
            if(resultset.next()){
                id = resultset.getInt("ID_WD");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id ;
        
    }
    
    /** Retourne une liste d'objets User ayant le même trajet source="zipcode" et workplace="sitesopra" et étants conducteurs */
    public static ArrayList<User> driversSameJourney(Connection con,int zipcode,int sitesopra){
        
        ArrayList<User> listUsers = new ArrayList<User>();
        Model model = new Model();
        Model.User user = null;
        try {
            
            Statement smt = con.createStatement() ;
            ResultSet resultset =smt.executeQuery("SELECT * FROM User WHERE zipcode = "+zipcode+" AND workplace="+sitesopra+" AND driver=1");
            while(resultset.next()){
            user = model.new User("none","none",resultset.getString("firstname"),resultset.getString("lastname"),resultset.getString("phone"),resultset.getInt("zipcode"),resultset.getInt("workplace"),resultset.getString("morning_time"),resultset.getString("afternoon_time"),resultset.getInt("driver"),resultset.getInt("monday"),resultset.getInt("tuesday"),resultset.getInt("wednesday"),resultset.getInt("thursday"),resultset.getInt("friday"),resultset.getInt("saturday"),resultset.getInt("sunday"),resultset.getInt("notification"));
            listUsers.add(user);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listUsers ;
        
    }
    
    /** Retourne une liste d'objets User de tous les utilisateurs de l'application */
    public static ArrayList<User>  getAllUsers(Connection con){
        
        ArrayList<User> listUsers = new ArrayList<User>();
        Model model = new Model();
        Model.User user = null; 
        try {
            
            Statement smt = con.createStatement() ;
            ResultSet resultset =smt.executeQuery("SELECT * FROM Member JOIN User ON Member.ID_Member=User.ID_Member");
            while(resultset.next()){
            user = model.new User(resultset.getString("email"),resultset.getString("password"),resultset.getString("firstname"),resultset.getString("lastname"),resultset.getString("phone"),resultset.getInt("zipcode"),resultset.getInt("workplace"),resultset.getString("morning_time"),resultset.getString("afternoon_time"),resultset.getInt("driver"),resultset.getInt("monday"),resultset.getInt("tuesday"),resultset.getInt("wednesday"),resultset.getInt("thursday"),resultset.getInt("friday"),resultset.getInt("saturday"),resultset.getInt("sunday"),resultset.getInt("notification"));
            listUsers.add(user);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listUsers ;
        
    }
    
    /** Supprime l'utilisateur ayant l'email "email" et renvoie "true" si cet utilisateur existait bien, "false" sinon */
    public static Boolean  deleteUsers(Connection con, String email){
        Boolean exist = false ;
        try {
            if (existMail(con, email)){
                Statement smt = con.createStatement() ;
                ResultSet resultset =smt.executeQuery("SELECT ID_Member FROM Member WHERE email = '"+email+"'");
                resultset.next();
                int i = resultset.getInt("ID_Member");
                smt.executeUpdate("DELETE FROM User WHERE ID_Member="+i);
                smt.executeUpdate("DELETE FROM Member WHERE ID_Member="+i);
                exist = true ;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exist ;
        
    }
    
    
    
    /** Ce main sert uniquement se test, s'y référer pour voir comment appeler les fonctions */
    public static void main(String[] args){
        try {
            
            Connection c1 = DatabaseManager.connectionDatabase() ;
            /**
            System.out.println(DatabaseManager.verifConnection(c1,"mhaidir@gmail.com", "aaa"));
            System.out.println(DatabaseManager.verifConnection(c1,"mhaidir@gmail.com", "azp"));
            System.out.println(DatabaseManager.verifConnection(c1,"mhiidir@gmail.com", "aaa"));
            System.out.println(DatabaseManager.verifConnection(c1,"aaa@gmail.com", "aaa"));
            System.out.println(DatabaseManager.verifConnection(c1,"mhiidir@gmail.com", "azp"));
            System.out.println(DatabaseManager.modifPwd(c1, "aa@gmail.com", "dlkgqds"));
            
            Model model = new Model();
            Model.User user2 = model.new User("mhaidir@test.com", "password", "firstname1", "lastname1", "0606060606", 31400, 1, "7h30", "18h30", 1,1,1,1,1,1,1,1,1);
            Model.User user = model.new User("ayoub@test.com", "pass", "first", "last", "0606060606", 31400, 1, "7h30", "18h30", 1,1,1,1,1,1,1,1,1);
            Model.setUser(user2);
            createUser(c1,user);
            modifUser(c1,user,Model.getUser());
            
            Model model = new Model();
            Model.User user1 = model.new User("number1@test.com", "pass1", "USER1", "lastname1", "0606060606", 31400, 1, "6h30", "18h30", 1,1,1,1,1,1,1,1,1);
            Model.User user2 = model.new User("number2@test.com", "pass2", "USER2", "lastname2", "0606060606", 31400, 1, "2h30", "18h30", 1,1,1,1,1,1,1,1,1);
            Model.User user3 = model.new User("number3@test.com", "pass3", "USER3", "lastname3", "0606060606", 31400, 1, "9h30", "18h30", 0,1,1,1,1,1,1,1,1);
            Model.User user4 = model.new User("number4@test.com", "pass4", "USER4", "lastname4", "0606060606", 31400, 2, "5h30", "18h30", 0,1,1,1,1,1,1,1,1);
            Model.User user5 = model.new User("number5@test.com", "pass5", "USER5", "lastname5", "0606060606", 31600, 1, "4h30", "18h30", 1,1,1,1,1,1,1,1,1);
            Model.User user6 = model.new User("number6@test.com", "pass6", "USER6", "lastname6", "0606060606", 31500, 1, "7h30", "18h30", 1,1,1,1,1,1,1,1,1);
            Model.User user7 = model.new User("number7@test.com", "pass7", "USER7", "lastname7", "0606060606", 31400, 1, "7h30", "18h30", 1,1,1,1,1,1,1,1,1);
            createUser(c1,user1);
            createUser(c1,user2);
            createUser(c1,user3);
            createUser(c1,user4);
            createUser(c1,user5);
            createUser(c1,user6);
            createUser(c1,user7);
            */
            
            ArrayList<User> allUsers = usersSameJourneyNotif(c1,31400,1);
            for (User u : allUsers){
                System.out.println(u.getEmail());
            }
            
            /**
            ArrayList<User> allDrivers = driversSameJourney(c1,31400,1);
            ArrayList<User> all = getAllUsers(c1);
            
            System.out.println("On affiche mnt tous les users !!! ");
            for(User u : allUsers){
                System.out.println(u.getFirstname());
            }
            System.out.println("On affiche mnt que les drivers !!!");
            for(User u : allDrivers){
                System.out.println(u.getFirstname());
            }
            System.out.println("On affiche mnt TOUT !!!");
            for(User u : all){
                System.out.println(u.getFirstname());
            }
            
            System.out.println(recupData(c1,"ayoub@test.com", "pass").getFirstname());
            * */
            deleteUsers(c1,"number1@test.com");
            c1.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
