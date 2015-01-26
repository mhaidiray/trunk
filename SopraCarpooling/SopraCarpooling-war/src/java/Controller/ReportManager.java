/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DatabaseManager;
import Model.Model;
import Model.Route;
import java.io.IOException;
import java.io.PrintWriter;
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
 *Cette classe permet à un administrateur de visualiser un certain nombre d'informations  et de statistiques concernant le système.
 * @author Salah
 */
public class ReportManager extends HttpServlet {

    
    
    
     /**
      * Foncton permettant de générer la liste de tous les utilisateurs enregistrés dans le système.
      * @param request
      * @param response
      * @throws ServletException
      * @throws IOException
      * @throws SQLException 
      */
      public void genList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        Connection con;
        con = DatabaseManager.connectionDatabase();
        ArrayList<Route> listRoutes = DatabaseManager.getRoutes(con);
        ArrayList<Model.User> listUser = DatabaseManager.getAllUsers(con);
        request.setAttribute("listRoutes", listRoutes);
        int cond=0;
        int pass=0;
        for (int i=0;i<listUser.size();i++){
            if (listUser.get(i).getDriver()==0){
                pass++;
            } else {
                cond++;
            }
        }
        request.setAttribute("cond", cond);
        request.setAttribute("pass", pass);
        request.setAttribute("conn", DatabaseManager.getNbConn(con));
        processRequest(request, response);
    }
      
      /**
     * Fonction appelée lorsqu'une requête est adressée au sereur, n'importe laquelle.
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
        if (request.getParameter("deco")!=null){                    
            Cookie monCookie = new Cookie("user",null) ;
            monCookie.setMaxAge(0);
            response.addCookie(monCookie);
            response.sendRedirect("/SopraCarpooling-war/login");
        }else if (request.getParameter("acc")!=null){           
            response.sendRedirect("/SopraCarpooling-war/adminhome");
        }else {
        this.getServletContext().getRequestDispatcher("/WEB-INF/genrapport.jsp").forward(request, response);
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Gère les requêtes GET adressées au serveur.
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
            try {
                genList(request, response);
           
            } catch (SQLException ex) {
                Logger.getLogger(ReportManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Gère les requêtes POST adressées au serveur.
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
     * Renvoie une description du servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
