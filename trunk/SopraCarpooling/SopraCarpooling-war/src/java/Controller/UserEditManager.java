/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DatabaseManager;
import Model.Model;
import Model.SMTPManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Samih
 */
public class UserEditManager extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    HashMap<String, String> erreurs = new HashMap<String, String>();

    public void fetchData(String email, String pwd, HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        Connection con;
        con = DatabaseManager.connectionDatabase();
        Model.User u = DatabaseManager.recupData(con, email, pwd);
        request.setAttribute("prenom", u.getFirstname());
        request.setAttribute("nom", u.getLastname());
        request.setAttribute("mail", u.getEmail());
        request.setAttribute("tel", u.getPhone());
        request.setAttribute("zipdepart", u.getZipcode());
        request.setAttribute("sitearrivee", DatabaseManager.getWorkplace(con, u.getWorkplace()).getName());
        request.setAttribute("heurematin", u.getMorning_time());
        request.setAttribute("heuresoir", u.getAfternoon_time());
        ArrayList<String> listPlaces = DatabaseManager.getAllWorkplaces(con);
        request.setAttribute("listPlaces", listPlaces);
        request.setAttribute("work", listPlaces.get(u.getWorkplace()-1));
        request.setAttribute("listPlaces", listPlaces);
        if (u.getMonday() == 1) {
            request.setAttribute("lundi", "Lundi ");
        }
        if (u.getTuesday() == 1) {
            request.setAttribute("mardi", "Mardi ");
        }
        if (u.getWednesday() == 1) {
            request.setAttribute("mercredi", "Mercredi ");
        }
        if (u.getThursday() == 1) {
            request.setAttribute("jeudi", "Jeudi ");
        }
        if (u.getFriday() == 1) {
            request.setAttribute("vendredi", "Vendredi ");
        }
        if (u.getSaturday() == 1) {
            request.setAttribute("samedi", "Samedi ");
        }
        if (u.getSunday() == 1) {
            request.setAttribute("dimanche", "Dimanche");
        }
        if (u.getDriver() == 1) {
            request.setAttribute("conducteur", "Oui");
            request.setAttribute("cond", "Oui");
        }
        if (u.getDriver() == 0) {
            request.setAttribute("conducteur", "Non");
        }
        if (u.getNotification() == 1) {
            request.setAttribute("notif", "Oui");
            request.setAttribute("not", "Oui");
        }
        if (u.getNotification() == 0) {
            request.setAttribute("notif", "Non");
        }

    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        /* TODO output your page here. You may use following sample code. */     
        request.setAttribute("titre", "Modifiez les informations personnelles de l'utilisateur");
        this.getServletContext().getRequestDispatcher("/WEB-INF/modpersadmin.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        String Valeur= null;
        String Valeur2 = null;
        if (cookies!=null){
        for(int i=0; i < cookies.length; i++) {
		Cookie MonCookie = cookies[i];
                if (MonCookie.getName().equals("admin")) {
			Valeur = MonCookie.getValue();
                }
                if(MonCookie.getName().equals("modifuser")){
                        Valeur2 = MonCookie.getValue();
                }
        }
        }
        
        if (Valeur==null||Valeur2==null){
            response.sendRedirect("/SopraCarpooling-war/login");
        }else{
            try {
                Connection con=DatabaseManager.connectionDatabase();
                String pwd=DatabaseManager.getPassword(con, Valeur2);
                fetchData(Valeur2, pwd, request, response);
                processRequest(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(UserEditManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("valider") != null) {
            Cookie[] cookies = request.getCookies();
            String Valeur = null;
            String Valeur2 = null ;
            if (cookies != null) {
                for (int i = 0; i < cookies.length; i++) {
                    Cookie MonCookie = cookies[i];
                    if (MonCookie.getName().equals("admin")) {
                        Valeur = cookies[i].getValue();
                    }
                    if (MonCookie.getName().equals("modifuser")){
                        Valeur2 = cookies[i].getValue();
                    }
                    
                }
            }

            if (Valeur == null||Valeur2 == null) {
                response.sendRedirect("/SopraCarpooling-war/login");
            } else {
                try {
                    Connection con = DatabaseManager.connectionDatabase();
                    modifUser(DatabaseManager.getPassword(con, Valeur2), request, response, Valeur2);
                } catch (SQLException ex) {
                    Logger.getLogger(PersonalDataManager.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        } else if (request.getParameter("annuler") != null) {
            response.sendRedirect("/SopraCarpooling-war/adminhome");
        } else if (request.getParameter("deco")!=null){                    
            Cookie monCookie = new Cookie("modifuser",null) ;
            monCookie.setMaxAge(0);
            response.addCookie(monCookie);
            monCookie = new Cookie("admin",null) ;
            monCookie.setMaxAge(0);
            response.addCookie(monCookie);
            response.sendRedirect("/SopraCarpooling-war/login");
        }
    }
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    
    
    public void modifUser(String pass, HttpServletRequest request, HttpServletResponse response, String mail) throws ServletException, IOException, SQLException {

        checkInfos(pass, request, response);
        if (erreurs.isEmpty()) {
            try {
                String nom = request.getParameter("nom");
                String prenom = request.getParameter("prenom");
                String email = request.getParameter("mail");
                String pwd = request.getParameter("pwd2");
                String zipdepart = request.getParameter("zipdepart");
                String sitearrivee = request.getParameter("sitearrivee");
                String heurematin = request.getParameter("heurematin");
                String heuresoir = request.getParameter("heuresoir");
                String tel = request.getParameter("tel");
                int driver;
                int monday;
                int tuesday;
                int wednesday;
                int thursday;
                int friday;
                int saturday;
                int sunday;
                int notification;
                if (request.getParameter("conducteur") == null) {
                    driver = 0;
                } else if (request.getParameter("conducteur").equals("Oui")) {
                    driver = 1;
                } else {
                    driver = 0;
                }
                if (request.getParameter("notif") == null) {
                    notification = 0;
                } else if (request.getParameter("notif").equals("Oui")) {
                    notification = 1;
                } else {
                    notification = 0;
                }
                if (request.getParameter("lundi") == null) {
                    monday = 0;
                } else if (request.getParameter("lundi").equals("on")) {
                    monday = 1;
                } else {
                    monday = 0;
                }
                if (request.getParameter("mardi") == null) {
                    tuesday = 0;
                } else if (request.getParameter("mardi").equals("on")) {
                    tuesday = 1;
                } else {
                    tuesday = 0;
                }
                if (request.getParameter("mercredi") == null) {
                    wednesday = 0;
                } else if (request.getParameter("mercredi").equals("on")) {
                    wednesday = 1;
                } else {
                    wednesday = 0;
                }
                if (request.getParameter("jeudi") == null) {
                    thursday = 0;
                } else if (request.getParameter("jeudi").equals("on")) {
                    thursday = 1;
                } else {
                    thursday = 0;
                }
                if (request.getParameter("vendredi") == null) {
                    friday = 0;
                } else if (request.getParameter("vendredi").equals("on")) {
                    friday = 1;
                } else {
                    friday = 0;
                }
                if (request.getParameter("samedi") == null) {
                    saturday = 0;
                } else if (request.getParameter("samedi").equals("on")) {
                    saturday = 1;
                } else {
                    saturday = 0;
                }
                if (request.getParameter("dimanche") == null) {
                    sunday = 0;
                } else if (request.getParameter("dimanche").equals("on")) {
                    sunday = 1;
                } else {
                    sunday = 0;
                }

                Connection con = DatabaseManager.connectionDatabase();
                Model model = new Model();
                Model.User user = model.new User(email, pass, prenom, nom, tel, Integer.parseInt(zipdepart), DatabaseManager.getJourneyId(con, sitearrivee), heurematin, heuresoir, driver, monday, tuesday, wednesday, thursday, friday, saturday, sunday, notification);
                Model.User lastUser = DatabaseManager.recupData(con, mail, pass);
                System.out.println(DatabaseManager.modifUser(con, user, lastUser));
                Cookie mookie;
                if (pwd != "" && pwd.length() > 0) {
                    DatabaseManager.modifPwd(con, email, pwd);
                    mookie = new Cookie("user", email + "@#**#@" + pwd);
                } else {
                    mookie = new Cookie("user", email + "@#**#@" + pass);
                }
                response.addCookie(mookie);
                
                if (user.getZipcode()!=lastUser.getZipcode() || user.getWorkplace() != lastUser.getWorkplace()){
                    ArrayList<Model.User> listUsers = DatabaseManager.usersSameJourneyNotif(con, Integer.parseInt(zipdepart), DatabaseManager.getJourneyId(con, sitearrivee),email);
                    for (Model.User u : listUsers) {
                        SMTPManager.sendNotification(u.getEmail(), prenom, nom);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(AccountCreateManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.sendRedirect("/SopraCarpooling-war/adminhome");
        } else {
            ArrayList<String> listPlaces = new ArrayList<String>();
            listPlaces.add("Sopra Colo 1");
            listPlaces.add("Sopra Colo 2");
            listPlaces.add("Sopra Ramassiers");
            listPlaces.add("Sopra Albi");
            fetchData(mail, pass, request, response);
            request.setAttribute("listPlaces", listPlaces);
            request.setAttribute("erreurs", erreurs);
            request.setAttribute("titre", "Modifiez les informations personnelles de l'utilisateur");
            this.getServletContext().getRequestDispatcher("/WEB-INF/modpersadmin.jsp").forward(request, response);
            erreurs.clear();
        }
    }
 
    public void checkInfos(String pass, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("mail");
        String pwd2 = request.getParameter("pwd2");
        String pwd3 = request.getParameter("pwd3");
        String zipdepart = request.getParameter("zipdepart");
        String sitearrivee = request.getParameter("sitearrivee");
        String heurematin = request.getParameter("heurematin");
        String heuresoir = request.getParameter("heuresoir");
        String tel = request.getParameter("tel");
        String driver = request.getParameter("conducteur");
        String notif = request.getParameter("notif");
        if (nom != null && nom.length() != 0) {
            if (!nom.matches("([A-Za-z0-9 ]+)")) {
                erreurs.put("nom", "Nom incorrect, veuillez réessayer");
                request.setAttribute("erreurs", erreurs);
                request.setAttribute("nom", nom);
            }
        } else {
            erreurs.put("nom", "Aucune entrée, veuillez réessayer");
            request.setAttribute("erreurs", erreurs);
        }
        if (prenom != null && prenom.length() != 0) {
            if (!prenom.matches("([A-Za-z0-9 ]+)")) {
                erreurs.put("prenom", " Prenom incorrect, veuillez réessayer");
                request.setAttribute("erreurs", erreurs);
                request.setAttribute("prenom", prenom);

            }
        } else {
            erreurs.put("prenom", "Aucune entrée, veuillez réessayer");
            request.setAttribute("erreurs", erreurs);
        }
        if (email != null && email.length() != 0) {
            if (!email.matches("[a-z0-9._-]+@[a-z0-9._-]+\\.[a-z]+")) {
                erreurs.put("mail", "E-mail incorrect, veuillez réessayer");
                request.setAttribute("erreurs", erreurs);
                request.setAttribute("mail", email);
            }
        } else {
            erreurs.put("mail", "Aucune entrée3, veuillez réessayer");
            request.setAttribute("erreurs", erreurs);
        }
        if (pwd2 != null && pwd2.length() != 0) {
            if (!pwd2.equals(pwd3)) {
                erreurs.put("pwd2", "Mots de passe différents, veuillez réessayer");
                request.setAttribute("erreurs", erreurs);
                request.setAttribute("pwd2", "invalid");
            }
        }
        if (zipdepart != null && zipdepart.length() != 0) {
            if (!zipdepart.matches("[0-9][0-9][0-9][0-9][0-9]")) {
                erreurs.put("zipdepart", "Code postal incorrect, veuillez réessayer");
                request.setAttribute("erreurs", erreurs);
                request.setAttribute("zipdepart", zipdepart);
            }
        } else {
            erreurs.put("zipdepart", "Aucune entrée, veuillez réessayer");
            request.setAttribute("erreurs", erreurs);
        }
        if (sitearrivee != null && sitearrivee.length() != 0) {
            Boolean exist = true;
            /**
             * Vérifier que le sitesopra existe
             */
            if (!exist) {
                erreurs.put("sitearrivee", "Site Sopra incorrect, veuillez réessayer");
                request.setAttribute("erreurs", erreurs);
                request.setAttribute("sitearrivee", sitearrivee);
            }
        } else {
            erreurs.put("sitearrivee", "Aucune entrée, veuillez réessayer");
            request.setAttribute("erreurs", erreurs);
        }
        if (heurematin != null && heurematin.length() != 0) {
            if (!heurematin.matches("[0-2]?[0-9]h[0-5]?[0-9]?")) {
                erreurs.put("heurematin", "L'heure est incorrecte, veuillez réessayer");
                request.setAttribute("erreurs", erreurs);
                request.setAttribute("heurematin", heurematin);
            }
        } else {
            erreurs.put("heurematin", "Aucune entrée, veuillez réessayer");
            request.setAttribute("erreurs", erreurs);
        }
        if (heuresoir != null && heuresoir.length() != 0) {
            if (!heuresoir.matches("[0-2]?[0-9]h[0-5]?[0-9]?")) {
                erreurs.put("heuresoir", "L'heure est incorrecte, veuillez réessayer");
                request.setAttribute("erreurs", erreurs);
                request.setAttribute("heuresoir", heuresoir);
            }
        } else {
            erreurs.put("heuresoir", "Aucune entrée, veuillez réessayer");
            request.setAttribute("erreurs", erreurs);
        }
        if (tel != null && tel.length() != 0) {
            if (!tel.matches("0[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]")) {
                erreurs.put("tel", "Telephone incorrect, veuillez réessayer");
                request.setAttribute("erreurs", erreurs);
                request.setAttribute("tel", tel);
            }
        } else {
            erreurs.put("tel", "Aucune entrée, veuillez réessayer");
            request.setAttribute("erreurs", erreurs);
        }

    }
  
    
}
