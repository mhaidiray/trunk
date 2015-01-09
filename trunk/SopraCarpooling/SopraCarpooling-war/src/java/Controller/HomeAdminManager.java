/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DatabaseManager;
import Model.Model;
import Model.Model.Workplace;
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
 *
 * @author Samih
 */
public class HomeAdminManager extends HttpServlet {

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
        Connection con;
        int i = 0;
        try {
            con = DatabaseManager.connectionDatabase();
            ArrayList<Model.User> listUsers = DatabaseManager.getAllUsers(con);
            i = listUsers.size();
        
        response.setContentType("text/html;charset=UTF-8");
        /* TODO output your page here. You may use following sample code. */
        if (request.getParameter("disconnect") != null) {
            Cookie monCookie = new Cookie("user", null);
            monCookie.setMaxAge(0);
            response.addCookie(monCookie);
            response.sendRedirect("/SopraCarpooling-war/login");
        } else if (request.getParameter("handlePath") != null) {
            response.sendRedirect("/SopraCarpooling-war/wrkplcelist");
        } /*API de création de PDF
         else if (request.getParameter("generateGeneralReport")!=null){
         response.sendRedirect("/SopraCarpooling-war/persinfo"); génération d"un pdf pas de servlet
         } */ else if (request.getParameter("generate") != null) {
            response.sendRedirect("/SopraCarpooling-war/gestroutes");
        } else {
             boolean tek=true;
            for (int j = 0; j < i; j++) {
                if (request.getParameter("mod" + j) != null) {
                    Cookie monCookie = new Cookie("modifuser", listUsers.get(j).getEmail());
                    response.addCookie(monCookie);
                    tek=false;
                    response.sendRedirect("/SopraCarpooling-war/useredit");
                } else if (request.getParameter("sup" + j) != null) {
                    DatabaseManager.deleteUsers(con, listUsers.get(j).getEmail());
                    tek=false;
                    response.sendRedirect("/SopraCarpooling-war/adminhome");
                }
            }
            if (tek){
            this.getServletContext().getRequestDispatcher("/WEB-INF/adminhome.jsp").forward(request, response);
            }
        }
        } catch (SQLException ex) {
            Logger.getLogger(HomeAdminManager.class.getName()).log(Level.SEVERE, null, ex);
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
            update(request, response);
        }
    }

    public void update(HttpServletRequest request, HttpServletResponse response) {
        try {
            Connection con;
            con = DatabaseManager.connectionDatabase();
            ArrayList<Model.User> listUsers = DatabaseManager.getAllUsers(con);
            ArrayList<String> listWk = DatabaseManager.getAllWorkplaces(con);
            request.setAttribute("listUsers", listUsers);
            request.setAttribute("work", listWk);
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(HomeAdminManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(HomeAdminManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HomeAdminManager.class.getName()).log(Level.SEVERE, null, ex);
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
