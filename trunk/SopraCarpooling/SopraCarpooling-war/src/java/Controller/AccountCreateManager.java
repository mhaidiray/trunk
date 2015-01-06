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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Samih
 */
public class AccountCreateManager extends HttpServlet {

    HashMap<String, String> erreurs = new HashMap<String, String>();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void checkInfos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("mail");
        String pwd1 = request.getParameter("pwd1");
        String pwd2 = request.getParameter("pwd2");
        String zipdepart = request.getParameter("zipdepart");
        String sitearrivee = request.getParameter("sitearrivee");
        String heurematin = request.getParameter("heurematin");
        String heuresoir = request.getParameter("heuresoir");
        String tel = request.getParameter("tel");
        String driver = request.getParameter("conducteur");
        String notif = request.getParameter("notif");
        if (nom != null && nom.length() != 0) {
            if (!nom.matches("([A-Z]+|[A-Z]?[a-z]+)('\'s([A-Z]+|[A-Z]?[a-z]+))?")) {
                erreurs.put("nom", "Nom incorrect, veuillez réessayer");
                request.setAttribute("erreurs", erreurs);
                request.setAttribute("nom", "invalid");
            }
        } else {
            erreurs.put("nom", "Aucune entrée, veuillez réessayer");
            request.setAttribute("erreurs", erreurs);
            request.setAttribute("nom", "invalid");
        }
        if (prenom != null && prenom.length() != 0) {
            if (!prenom.matches("([A-Z]+|[A-Z]?[a-z]+)")) {
                erreurs.put("prenom", " Prenom incorrect, veuillez réessayer");
                request.setAttribute("erreurs", erreurs);
                request.setAttribute("prenom", "invalid");

            }
        } else {
            erreurs.put("prenom", "Aucune entrée, veuillez réessayer");
            request.setAttribute("erreurs", erreurs);
            request.setAttribute("prenom", "invalid");
        }
        if (email != null && email.length() != 0) {
            if (!email.matches("[a-z0-9._-]+@[a-z0-9._-]{2,}\\.[a-z]{2,4}")) {
                erreurs.put("mail", "E-mail incorrect, veuillez réessayer");
                request.setAttribute("erreurs", erreurs);
                request.setAttribute("mail", "invalid");
            }
        } else {
            erreurs.put("mail", "Aucune entrée3, veuillez réessayer");
            request.setAttribute("erreurs", erreurs);
            request.setAttribute("mail", "invalid");
        }
        if (pwd1 != null && pwd1.length() != 0) {
            if (!pwd1.equals(pwd2)) {
                erreurs.put("pwd2", "Mots de passe différents, veuillez réessayer");
                request.setAttribute("erreurs", erreurs);
                request.setAttribute("pwd2", "invalid");
            }
        } else {
            erreurs.put("pwd2", "Aucune entrée, veuillez réessayer");
            request.setAttribute("erreurs", erreurs);
            request.setAttribute("pwd2", "invalid");
        }
        if (zipdepart != null && zipdepart.length() != 0) {
            if (!zipdepart.matches("[0-9]{5}")) {
                erreurs.put("zipdepart", "Code postal incorrect, veuillez réessayer");
                request.setAttribute("erreurs", erreurs);
                request.setAttribute("zipdepart", "invalid");
            }
        } else {
            erreurs.put("zipdepart", "Aucune entrée, veuillez réessayer");
            request.setAttribute("erreurs", erreurs);
            request.setAttribute("commdepart", "invalid");
        }
        if (sitearrivee != null && sitearrivee.length() != 0) {
            Boolean exist = true;
            /**
             * Vérifier que le sitesopra existe
             */
            if (!exist) {
                erreurs.put("sitearrivee", "Site Sopra incorrect, veuillez réessayer");
                request.setAttribute("erreurs", erreurs);
                request.setAttribute("sitearrivee", "invalid");
            }
        } else {
            erreurs.put("sitearrivee", "Aucune entrée, veuillez réessayer");
            request.setAttribute("erreurs", erreurs);
            request.setAttribute("commdepart", "invalid");
        }
        if (heurematin != null && heurematin.length() != 0) {
            if (!heurematin.matches("[0-9]?[0-9]h[0-9]?[0-9]?")) {
                erreurs.put("heurematin", "L'heure est incorrecte, veuillez réessayer");
                request.setAttribute("erreurs", erreurs);
                request.setAttribute("heurematin", "invalid");
            }
        } else {
            erreurs.put("heurematin", "Aucune entrée, veuillez réessayer");
            request.setAttribute("erreurs", erreurs);
            request.setAttribute("commdepart", "invalid");
        }
        if (heuresoir != null && heuresoir.length() != 0) {
            if (!heuresoir.matches("[0-9]?[0-9]h[0-9]?[0-9]?")) {
                erreurs.put("heuresoir", "L'heure est incorrecte, veuillez réessayer");
                request.setAttribute("erreurs", erreurs);
                request.setAttribute("heuresoir", "invalid");
            }
        } else {
            erreurs.put("heuresoir", "Aucune entrée, veuillez réessayer");
            request.setAttribute("erreurs", erreurs);
            request.setAttribute("commdepart", "invalid");
        }
        if (tel != null && tel.length() != 0) {
            if (!tel.matches("0[0-9]{9}")) {
                erreurs.put("tel", "Telephone incorrect, veuillez réessayer");
                request.setAttribute("erreurs", erreurs);
                request.setAttribute("tel", "invalid");
            }
        } else {
            erreurs.put("tel", "Aucune entrée, veuillez réessayer");
            request.setAttribute("erreurs", erreurs);
            request.setAttribute("commdepart", "invalid");
        }

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        /* TODO output your page here. You may use following sample code. */
        this.getServletContext().getRequestDispatcher("/WEB-INF/acccreate.jsp").forward(request, response);
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
        try {
            Connection con = DatabaseManager.connectionDatabase();
            ArrayList<String> listPlaces = DatabaseManager.getAllWorkplaces(con);
            request.setAttribute("listPlaces", listPlaces);
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AccountCreateManager.class.getName()).log(Level.SEVERE, null, ex);
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
        checkInfos(request, response);
        if (erreurs.isEmpty()) {
            try {
                String nom = request.getParameter("nom");
                String prenom = request.getParameter("prenom");
                String email = request.getParameter("mail");
                String pwd = request.getParameter("pwd1");
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
                Model.User user = model.new User(email, pwd, prenom, nom, tel, Integer.parseInt(zipdepart), DatabaseManager.getJourneyId(con, sitearrivee), heurematin, heuresoir, driver, monday, tuesday, wednesday, thursday, friday, saturday, sunday, notification);
                DatabaseManager.createUser(con, user);
                SMTPManager.sendCreateConfirmation(email, pwd);
                ArrayList<Model.User> listUsers = DatabaseManager.usersSameJourneyNotif(con, Integer.parseInt(zipdepart), DatabaseManager.getJourneyId(con, sitearrivee),email);
                for (Model.User u : listUsers) {
                    SMTPManager.sendNotification(u.getEmail(), prenom, nom);
                }
            } catch (SQLException ex) {
                Logger.getLogger(AccountCreateManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.sendRedirect("/SopraCarpooling-war/login");
        } else {
            Connection con;
            try {
                con = DatabaseManager.connectionDatabase();
            
            ArrayList<String> listPlaces = DatabaseManager.getAllWorkplaces(con);
            request.setAttribute("listPlaces", listPlaces);
            this.getServletContext().getRequestDispatcher("/WEB-INF/acccreate.jsp").forward(request, response);
            erreurs.clear();
            } catch (SQLException ex) {
                Logger.getLogger(AccountCreateManager.class.getName()).log(Level.SEVERE, null, ex);
            }
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

}
