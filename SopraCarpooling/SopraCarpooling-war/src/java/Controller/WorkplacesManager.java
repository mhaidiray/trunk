/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DatabaseManager;
import static Model.DatabaseManager.deleteWorkplace;
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
 *
 * @author Salah
 */
public class WorkplacesManager extends HttpServlet {

    /**
     * Méthode appelée par doGet et doPost Processes requests for both HTTP
     * <code>GET</code> and <code>POST</code> methods.
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
        /**
         * cas ou l'admin à appuyer sur le bouton de déconnexion
         */
        if (request.getParameter("deco") != null) {
            Cookie monCookie = new Cookie("user", null);
            monCookie.setMaxAge(0);
            response.addCookie(monCookie);
            response.sendRedirect("/SopraCarpooling-war/login");
            /**
             * cas ou l'admin à appuyer sur le bouton de retour à l'accueil
             */
        } else if (request.getParameter("acc") != null) {
            response.sendRedirect("/SopraCarpooling-war/adminhome");
            /**
             * cas ou l'admin à appuyer sur le bouton de suppression
             */
        } else if (request.getParameter("del") != null) {
            try {
                Connection con = DatabaseManager.connectionDatabase();
                String place = request.getParameter("sitesopra");
                deleteWorkplace(con, place);
                response.sendRedirect("/SopraCarpooling-war/adminhome");
            } catch (SQLException ex) {
                Logger.getLogger(WorkplacesManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            /**
             * cas ou l'admin à appuyer sur le bouton d'ajout
             */
        } else if (request.getParameter("add") != null) {
            Cookie monCookie = new Cookie("modif", "none");
            response.addCookie(monCookie);
            response.sendRedirect("/SopraCarpooling-war/wrkplceedit");
            /**
             * cas ou l'admin à appuyer sur le bouton de modification
             */
        } else if (request.getParameter("modif") != null) {
            String nom = request.getParameter("sitesopra");
            Cookie monCookie = new Cookie("modif", nom);
            response.addCookie(monCookie);
            response.sendRedirect("/SopraCarpooling-war/wrkplceedit");
            /**
             * cas du premier affichage de la jsp wrkplcelist, aucun bouton n'a été activé
             */
        } else {
            this.getServletContext().getRequestDispatcher("/WEB-INF/wrkplcelist.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Méthode appelé lors du premier affichage de la jsp "wrkplceedit" Handles
     * the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /**
         * On initialise les Cookies
         */
        Cookie[] cookies = request.getCookies();
        String Valeur = null;
        /**
         * S'il n'est pas vide, on les parcourt pour savoir si la personne
         * connéctée est un admin
         */
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie MonCookie = cookies[i];
                if (MonCookie.getName().equals("admin")) {
                    Valeur = cookies[i].getValue();
                }
            }
        }
        /**
         * Si Valeur==null, il ne s'agit pas d'un admin, il ne pourra pas
         * accéder à cette page et il est rediriger vers la page de connexion
         */
        if (Valeur == null) {
            response.sendRedirect("/SopraCarpooling-war/login");
            /**
             * Si c'est bien un admin, on se connecte à la base de donner pour
             * avoir la liste des workplaces à afficher dans la jsp, on l'envoie
             * en attribut à la jsp et on appelle processRequest avec les mêmes
             * paramètres plus la liste.
             */
        } else {
            try {
                int positionAt = Valeur.indexOf("@#**#@");
                String email = Valeur.substring(0, positionAt);
                String password = Valeur.substring(positionAt + 6);
                Connection con = DatabaseManager.connectionDatabase();
                ArrayList<String> listPlaces = DatabaseManager.getAllWorkplaces(con);
                request.setAttribute("listPlaces", listPlaces);
                processRequest(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(WorkplacesManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    /**
     * Méthode appelée lors de l'appuie sur un des boutons, elle invoque
     * processRequest avec les mêmes paramètres de la requête.
     *
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
