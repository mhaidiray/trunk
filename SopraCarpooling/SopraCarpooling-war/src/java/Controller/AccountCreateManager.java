/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Samih
 */
public class AccountCreateManager extends HttpServlet {

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
    public void checkInfos(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("mail");
        String pwd1 = request.getParameter("pwd1");
        String pwd2 = request.getParameter("pwd2");
        String commdepart = request.getParameter("commdepart");
        String zipdepart = request.getParameter("zipdepart");
        String sitearrivee = request.getParameter("sitearrivee");
        String heurematin = request.getParameter("heurematin");
        String heuresoir = request.getParameter("heuresoir");
        String tel = request.getParameter("tel");
        if (nom != null && nom.length() != 0) {
            if (!nom.matches("([A-Z]+|[A-Z]?[a-z]+)('\'s([A-Z]+|[A-Z]?[a-z]+))?")) {
                erreurs.put("nom", "Nom incorrect, veuillez réessayer");
                request.setAttribute("erreurs", erreurs);
                request.setAttribute("nom", "invalid");
                return ;
            }
        }else {
                erreurs.put("nom", "Aucune entrée, veuillez réessayer");
                request.setAttribute("erreurs", erreurs);
                request.setAttribute("nom", "invalid");
                return ;
        }
        if (prenom != null && prenom.length() != 0) {
            if (!prenom.matches("([A-Z]+|[A-Z]?[a-z]+)")) {
                erreurs.put("prenom", "Prenom incorrect, veuillez réessayer");
                request.setAttribute("erreurs", erreurs);
                request.setAttribute("prenom", "invalid");
                return ;
                
            }
        }else {
                erreurs.put("prenom", "Aucune entrée, veuillez réessayer");
                request.setAttribute("erreurs", erreurs);
                request.setAttribute("prenom", "invalid");
                return ;
        }
        if (email != null && email.length() != 0) {
            if (!email.matches("[a-z0-9._-]+@[a-z0-9._-]{2,}\\.[a-z]{2,4}")) {
                erreurs.put("mail", "E-mail incorrect, veuillez réessayer");
                request.setAttribute("erreurs", erreurs);
                request.setAttribute("mail", "invalid");    
                return ;      
            }
        }else {
                erreurs.put("mail", "Aucune entrée3, veuillez réessayer");
                request.setAttribute("erreurs", erreurs);
                request.setAttribute("mail", "invalid");
                return ;
        }
        if (pwd1 != null && pwd1.length() != 0) {
            if (pwd1!=pwd2){
                erreurs.put("pwd2", "Mots de passe différents, veuillez réessayer");
                request.setAttribute("erreurs", erreurs);
                request.setAttribute("pwd2", "invalid");
                return ;
            }
        }else {
                erreurs.put("pwd2", "Aucune entrée, veuillez réessayer");
                request.setAttribute("erreurs", erreurs);
                request.setAttribute("pwd2", "invalid");
                return ;
        }
        if (commdepart != null && commdepart.length() != 0) {
            if (!commdepart.matches("([A-Z]+|[A-Z]?[a-z]+)")) {
                erreurs.put("commdepart", "Commune de départ incorrecte, veuillez réessayer");
                request.setAttribute("erreurs", erreurs);
                request.setAttribute("commdepart", "invalid");
                return ;
            }
        }else {
                erreurs.put("commdepart", "Aucune entrée, veuillez réessayer");
                request.setAttribute("erreurs", erreurs);
                request.setAttribute("commdepart", "invalid");
                return ;
        }
        if (zipdepart != null && zipdepart.length() != 0) {
            if (!zipdepart.matches("[0-9]{5}")) {
                erreurs.put("zipdepart", "Code postal incorrect, veuillez réessayer");
                request.setAttribute("erreurs", erreurs);
                request.setAttribute("zipdepart", "invalid");   
                return ;       
            }
        }
        if (sitearrivee != null && sitearrivee.length() != 0) {
            Boolean exist = true ;
            /** Vérifier que le sitesopra existe */
            if (!exist) {
                erreurs.put("sitearrivee", "Site Sopra incorrect, veuillez réessayer");
                request.setAttribute("erreurs", erreurs);
                request.setAttribute("sitearrivee", "invalid");      
                return ;    
            }
        }
        if (heurematin != null && heurematin.length() != 0) {
            if (!heurematin.matches("[0-9]?[0-9]h[0-9]?[0-9]?")) {
                erreurs.put("heurematin", "L'heure est incorrecte, veuillez réessayer");
                request.setAttribute("erreurs", erreurs);
                request.setAttribute("heurematin", "invalid"); 
                return ;         
            }
        }
        if (heuresoir != null && heuresoir.length() != 0) {
            if (!heuresoir.matches("[0-9]?[0-9]h[0-9]?[0-9]?")) {
                erreurs.put("heuresoir", "L'heure est incorrecte, veuillez réessayer");
                request.setAttribute("erreurs", erreurs);
                request.setAttribute("heuresoir", "invalid"); 
                return ;         
            }
        }
        if (tel != null && tel.length() != 0) {
            if (!tel.matches("0[0-9]{9}")) {
                erreurs.put("tel", "E-mail incorrect, veuillez réessayer");
                request.setAttribute("erreurs", erreurs);
                request.setAttribute("tel", "invalid");    
                return ;      
            }
        }
   
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        /* TODO output your page here. You may use following sample code. */
        this.getServletContext().getRequestDispatcher("/WEB-INF/acccreate.jsp").forward(request, response);
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
        checkInfos(request, response);
        if (erreurs.isEmpty()){
            /**Création de cet utilisateur dans la base de données*/
            System.out.println("Coco");
            response.sendRedirect("/SopraCarpooling-war/login");
        } else { 
            this.getServletContext().getRequestDispatcher("/WEB-INF/acccreate.jsp").forward(request, response);
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
