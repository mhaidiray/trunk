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
        System.out.println(zipdepart);
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
            response.sendRedirect("/SopraCarpooling-war/homeuser");
        } else {
            int positionAt = Valeur.indexOf("@#**#@");
            String email = Valeur.substring(0, positionAt);
            String password = Valeur.substring(positionAt + 6);
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
            //Connection c1 = DatabaseManager.connectionDatabase();
            ArrayList<Model.User> listUsers = new ArrayList<Model.User>();
            //String zipcode = request.getParameter("zipcode");
            //String sitesopra = request.getParameter("sitesopra");
            //listUsers = usersSameJourney(c1,31400,1);
            Model model = new Model();
            Model.User user1 = model.new User("number1@test.com", "pass1", "USER1", "lastname1", "0606060606", 31400, 1, "6h30", "18h30", 1, 1, 1, 1, 1, 1, 1, 1, 1);
            Model.User user2 = model.new User("number2@test.com", "pass2", "USER2", "lastname2", "0606060606", 31400, 1, "2h30", "18h30", 1, 1, 1, 1, 1, 1, 1, 1, 1);
            Model.User user3 = model.new User("number3@test.com", "pass3", "USER3", "lastname3", "0606060606", 31400, 1, "9h30", "18h30", 0, 1, 1, 1, 1, 1, 1, 1, 1);
            Model.User user4 = model.new User("number4@test.com", "pass4", "USER4", "lastname4", "0606060606", 31400, 2, "5h30", "18h30", 0, 1, 1, 1, 1, 1, 1, 1, 1);
            Model.User user5 = model.new User("number5@test.com", "pass5", "USER5", "lastname5", "0606060606", 31600, 1, "4h30", "18h30", 1, 1, 1, 1, 1, 1, 1, 1, 1);
            Model.User user6 = model.new User("number6@test.com", "pass6", "USER6", "lastname6", "0606060606", 31500, 1, "7h30", "18h30", 1, 1, 1, 1, 1, 1, 1, 1, 1);
            Model.User user7 = model.new User("number7@test.com", "pass7", "USER7", "lastname7", "0606060606", 31400, 1, "7h30", "18h30", 1, 1, 1, 1, 1, 1, 1, 1, 1);
            listUsers.add(user1);
            listUsers.add(user2);
            listUsers.add(user3);
            listUsers.add(user4);
            listUsers.add(user5);
            listUsers.add(user6);
            listUsers.add(user7);
            request.setAttribute("listUsers", listUsers);
            processRequest(request, response);

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
