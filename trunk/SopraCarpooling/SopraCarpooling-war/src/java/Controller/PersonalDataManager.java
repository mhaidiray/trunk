/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DatabaseManager;
import Model.Model;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
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
public class PersonalDataManager extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void fetchData(String email, String pwd, HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        Connection con;
        con = DatabaseManager.connectionDatabase();
        Model.User u = DatabaseManager.recupData(con, email, pwd);
        request.setAttribute("prenom", u.getFirstname());
        request.setAttribute("nom", u.getLastname());
        request.setAttribute("email", u.getEmail());
        request.setAttribute("phone", u.getPhone());
        request.setAttribute("zip", u.getZipcode());
        request.setAttribute("site", u.getWorkplace());
        request.setAttribute("matin", u.getMorning_time());
        request.setAttribute("soir", u.getAfternoon_time());
        if (u.getMonday()==1){request.setAttribute("lun", "Lundi ");}
        if (u.getTuesday()==1){request.setAttribute("mar", "Mardi ");}
        if (u.getWednesday()==1){request.setAttribute("mer", "Mercredi ");}
        if (u.getThursday()==1){request.setAttribute("jeu", "Jeudi ");}
        if (u.getFriday()==1){request.setAttribute("ven", "Vendredi ");}
        if (u.getSaturday()==1){request.setAttribute("sam", "Samedi ");}
        if (u.getSunday()==1){request.setAttribute("dim", "Dimanche");}
        if (u.getDriver()==1){request.setAttribute("driver", "Oui");}
        if (u.getDriver()==0){request.setAttribute("driver", "Non");}
        if (u.getNotification()==1){request.setAttribute("notif", "Oui");}
        if (u.getNotification()==0){request.setAttribute("notif", "Non");}
        processRequest(request,response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        /* TODO output your page here. You may use following sample code. */

        if (request.getParameter("accueil") != null) {
            response.sendRedirect("/SopraCarpooling-war/homeuser");
        } else if (request.getParameter("mod") != null) {
            this.getServletContext().getRequestDispatcher("/WEB-INF/modpersinfo.jsp").forward(request, response);
        } else {
            this.getServletContext().getRequestDispatcher("/WEB-INF/persinfo.jsp").forward(request, response);
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
                fetchData(email, password, request, response);
            } catch (SQLException ex) {
                Logger.getLogger(PersonalDataManager.class.getName()).log(Level.SEVERE, null, ex);
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
