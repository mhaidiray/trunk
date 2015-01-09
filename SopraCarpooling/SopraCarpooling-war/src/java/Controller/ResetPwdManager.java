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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
        String email = request.getParameter("mail");
        System.out.println(email);
        if (email != null && email.length() != 0) {
            if (!email.matches("[a-z0-9._-]+@[a-z0-9._-]{2,}\\.[a-z]{2,4}")) {
                erreurs.put("mail", "E-mail incorrect, veuillez réessayer");
                request.setAttribute("erreurs", erreurs);
                request.setAttribute("mail", "invalid");
                
            }
        }
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        /* TODO output your page here. You may use following sample code. */
        this.getServletContext().getRequestDispatcher("/WEB-INF/resetpwd.jsp").forward(request, response);
    }
    
    private static String encode(String mdp, String email){
                    String password = mdp+"SopraCarpooling"+email;
                    byte[] uniqueKey = password.getBytes();
                    byte[] hash      = null;

                    try
                    {
                        hash = MessageDigest.getInstance("MD5").digest(uniqueKey);
                    }
                    catch (NoSuchAlgorithmException e)
                    {
                        throw new Error("No MD5 support in this VM.");
                    }

                    StringBuilder hashString = new StringBuilder();
                    for (int i = 0; i < hash.length; i++)
                    {
                        String hex = Integer.toHexString(hash[i]);
                        if (hex.length() == 1)
                        {
                            hashString.append('0');
                            hashString.append(hex.charAt(hex.length() - 1));
                        }
                        else
                            hashString.append(hex.substring(hex.length() - 2));
                    }
                    return hashString.toString();
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
            Connection con = DatabaseManager.connectionDatabase();
            Boolean exist = DatabaseManager.existMail(con, email);
            checkMail(request,response);
            
            /**Vérification que l'email existe dans la base de données*/
            if (exist && erreurs.isEmpty()){
                SecureRandom random = new SecureRandom();
                String pwd = new BigInteger(60, random).toString(32);
                /**Modification du password dans la base de données*/
                SMTPManager.sendNewPassword(email, encode(pwd,email));
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
