/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DatabaseManager;
import static Model.DatabaseManager.usersSameJourney;
import Model.Model;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.registry.infomodel.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Samih
 */
public class SearchManager extends HttpServlet {

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
    public void checkZip(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String zipdepart = request.getParameter("zipdepart");
        if (zipdepart != null && zipdepart.length() != 0) {
            if (!zipdepart.matches("[0-9]{5}")) {
                erreurs.put("zipdepart", "Code postal incorrect, veuillez réessayer");
                request.setAttribute("erreurs", erreurs);
            }
        }
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        /* TODO output your page here. You may use following sample code. */
        this.getServletContext().getRequestDispatcher("/WEB-INF/searchview.jsp").forward(request, response);
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
            //interrogation de la base de données au lieu de créer manuellement la liste (fonction en attente)
            ArrayList<String> listPlaces = new ArrayList<String>();
            listPlaces.add("Sopra Colo 1");
            listPlaces.add("Sopra Colo 2");
            listPlaces.add("Sopra Ramassiers");
            listPlaces.add("Sopra Albi");
            request.setAttribute("listPlaces", listPlaces);
            processRequest(request, response);
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

        checkZip(request, response);
        if (erreurs.isEmpty()) {
            try {
                Connection c1 = DatabaseManager.connectionDatabase();
                ArrayList<Model.User> listUsers = new ArrayList<Model.User>();
                String zipcode = request.getParameter("zipdepart");
                String sitesopra = request.getParameter("sitearrivee");
                int i;
                i = Integer.parseInt(zipcode);
                listUsers = usersSameJourney(c1, i, sitesopra);
                request.setAttribute("listUsers", listUsers);
                processRequest(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(SearchManager.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            processRequest(request, response);
            erreurs.clear();
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
