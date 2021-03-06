/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DatabaseManager;
import Model.Model;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cette classe gère tous les éléments liés à la page d'accueil de l'utilisateur.
 * @author Samih
 */
public class HomeUserManager extends HttpServlet {

    /**
     * Fonction appelée lorsqu'une requête est adressée au sereur, n'importe laquelle.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void genList(String email, String pass, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        Connection con;
        con = DatabaseManager.connectionDatabase();
        Model.User u = DatabaseManager.recupData(con, email, pass);
        ArrayList<Model.User> listUsers = DatabaseManager.usersSameJourney(con, u.getZipcode(), u.getWorkplace(),email);
        request.setAttribute("prenom", u.getFirstname());
        request.setAttribute("nom", u.getLastname());
        request.setAttribute("listUsers", listUsers);
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        /* TODO output your page here. You may use following sample code. */

        if (request.getParameter("trajet") != null) {
            response.sendRedirect("/SopraCarpooling-war/search");
        } else if (request.getParameter("persinfo") != null) {
            response.sendRedirect("/SopraCarpooling-war/persinfo");
        }else if (request.getParameter("deco")!=null){                    
            Cookie monCookie = new Cookie("user",null) ;
            monCookie.setMaxAge(0);
            response.addCookie(monCookie);
            response.sendRedirect("/SopraCarpooling-war/login");
        }else {
            this.getServletContext().getRequestDispatcher("/WEB-INF/userhome.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     *
     * Fonction gérant les requêtes GET adressées au serveur.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        String Valeur = null;
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie MonCookie = cookies[i];
                if (MonCookie.getName().equals("user")) {
                    Valeur = cookies[i].getValue();
                }
            }
        }

        if (Valeur == null) {
            response.sendRedirect("/SopraCarpooling-war/login");
        } else {
            int positionAt = Valeur.indexOf("@#**#@");
            String email = Valeur.substring(0, positionAt);
            String password = Valeur.substring(positionAt + 6);
            try {
                genList(email, password, request, response);
            } catch (SQLException ex) {
                Logger.getLogger(HomeUserManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     *
     * Fonction gérant les requêtes POST adressées au serveur.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

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
