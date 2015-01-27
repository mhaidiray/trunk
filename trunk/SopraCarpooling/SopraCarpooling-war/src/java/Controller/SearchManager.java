/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DatabaseManager;
import static Model.DatabaseManager.usersSameJourney;
import Model.Model;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Salah
 */
public class SearchManager extends HttpServlet {

    HashMap<String, String> erreurs = new HashMap<String, String>();

    /**
     * Cette méthode vérifie que l'admin a saisi un code postale composé de 5 chiffre dans le champ
     * resérvé. S'il ne l'a pas bien saisi et qu'il clique sur un bouton, une
     * erreur est envoyé à la jsp pour lui demander de réessayer la saisie.
     *
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
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
     * Cette méthode est appelée lors du premier affiche de la jsp searchview (l'interface de recherche de trajet
     * de l'utilisateur). Elle utilise les Cookies pour s'assurer que l'utilisateur s'est déja connecté, si oui
     * elle invoque processRequest pour afficher la jsp searchview.
     * 
     * Handles the HTTP <code>GET</code> method.
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
            try {
                Connection con = DatabaseManager.connectionDatabase();
                ArrayList<String> listPlaces = DatabaseManager.getAllWorkplaces(con);
                request.setAttribute("listPlaces", listPlaces);
                processRequest(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(SearchManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
 /**
     * Cette méthode est appelée lors de l'appuie sur un des boutons de la jsp searchview. 
     * Elle effectue un traitemen différent selon le bouton sur lequel l'utilisateur a appuyé
     * (chercher, se déconnecter ou retour à l'accueil)
     * 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("search") != null) {
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
                checkZip(request, response);
                if (erreurs.isEmpty()) {
                    try {
                        int positionAt = Valeur.indexOf("@#**#@");
                        String email = Valeur.substring(0, positionAt);
                        Connection c1 = DatabaseManager.connectionDatabase();
                        ArrayList<Model.User> listUsers = new ArrayList<Model.User>();
                        String zipcode = request.getParameter("zipdepart");
                        String sitesopra = request.getParameter("sitearrivee");
                        int i;
                        i = Integer.parseInt(zipcode);
                        listUsers = usersSameJourney(c1, i, sitesopra, email);
                        request.setAttribute("listUsers", listUsers);
                        ArrayList<String> listPlaces = DatabaseManager.getAllWorkplaces(c1);
                        request.setAttribute("listPlaces", listPlaces);
                        processRequest(request, response);
                    } catch (SQLException ex) {
                        Logger.getLogger(SearchManager.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    try {
                        Connection c1 = DatabaseManager.connectionDatabase();
                        ArrayList<String> listPlaces = DatabaseManager.getAllWorkplaces(c1);
                        request.setAttribute("listPlaces", listPlaces);
                        processRequest(request, response);
                        erreurs.clear();
                    } catch (SQLException ex) {
                        Logger.getLogger(SearchManager.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } else if (request.getParameter("deco") != null) {
            Cookie monCookie = new Cookie("user", null);
            monCookie.setMaxAge(0);
            response.addCookie(monCookie);
            response.sendRedirect("/SopraCarpooling-war/login");
        } else if (request.getParameter("accu") != null) {
            response.sendRedirect("/SopraCarpooling-war/homeuser");
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
