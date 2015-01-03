/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Model.DatabaseManager.usersSameJourney;
import Model.Model;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Samih
 */
public class WorkplacesManager extends HttpServlet {

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
        if (request.getParameter("deco") != null) {
            response.sendRedirect("/SopraCarpooling-war/login");
        } else if (request.getParameter("del") != null) {
            // Methode pour supprimer de la base de donnée le site sopra selectionné
            request.getParameter("sitesopra");
            response.sendRedirect("/SopraCarpooling-war/adminhome");
        } else if (request.getParameter("add") != null) {

            response.sendRedirect("/SopraCarpooling-war/wrkplceedit");
        } else if (request.getParameter("modif") != null) {
            response.sendRedirect("/SopraCarpooling-war/wrkplceedit");
        } else {
            this.getServletContext().getRequestDispatcher("/WEB-INF/wrkplcelist.jsp").forward(request, response);
        }
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
                if (MonCookie.getName().equals("admin")) {
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
