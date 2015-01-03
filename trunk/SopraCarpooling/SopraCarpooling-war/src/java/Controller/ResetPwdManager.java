/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DatabaseManager;
import Model.SMTPManager;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.SQLException;
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
public class ResetPwdManager extends HttpServlet {
    
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
    
    public void checkMail(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        try {
            String email = request.getParameter("mail");
            System.out.println(email);
            Connection con = DatabaseManager.connectionDatabase();
            if (DatabaseManager.existMail(con, email)){
                if (email != null && email.length() != 0) {
                    if (!email.matches("[a-z0-9._-]+@[a-z0-9._-]{2,}\\.[a-z]{2,4}")) {
                        erreurs.put("mail", "E-mail incorrect, veuillez réessayer");
                        request.setAttribute("erreurs", erreurs);
                        request.setAttribute("mail", "invalid");
                        
                    }
                } else {
                    erreurs.put("mail", "Aucune entrée, veuillez réessayer");
                    request.setAttribute("erreurs", erreurs);
                    request.setAttribute("mail", "invalid");
                }
            } else {
                erreurs.put("mail", "Email inexistant dans la base de données, veuillez réessayer");
                    request.setAttribute("erreurs", erreurs);
                    request.setAttribute("mail", "invalid");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ResetPwdManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        /* TODO output your page here. You may use following sample code. */
        this.getServletContext().getRequestDispatcher("/WEB-INF/resetpwd.jsp").forward(request, response);
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
        processRequest(request, response);
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
        try {
            String email = request.getParameter("mail");
            checkMail(request,response);
            Connection con = DatabaseManager.connectionDatabase();
            if (erreurs.isEmpty()){
                SecureRandom random = new SecureRandom();
                String pwd = new BigInteger(60, random).toString(32);
                DatabaseManager.modifPwd(con, email, pwd);
                SMTPManager.sendNewPassword(email, pwd);
                response.sendRedirect("/SopraCarpooling-war/login");
            } else {
                this.getServletContext().getRequestDispatcher("/WEB-INF/resetpwd.jsp").forward(request, response);
                erreurs.clear();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ResetPwdManager.class.getName()).log(Level.SEVERE, null, ex);
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
