package Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Model.DatabaseManager;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cette classe traite les requêtes liées à la connexion d'un utilisateur ou d'un administrateur. Elle affiche l'écran de connexion de l'application.
 * @author Samih
 */
@WebServlet(urlPatterns = {"/ConnectManager"})
public class ConnectManager extends HttpServlet {

    
    
    /**
    * HashMap stockant les erreurs de saisie. Elle permet de notifier l'utilisateur lorsqu'il saisit une information incorrecte.
    */
    HashMap<String, String> erreurs = new HashMap<String, String>();

    /**
     * Fonction de vérification de l'email rentré dans l'écran de connexion.
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException 
     */
    public String checkMail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("mail");
        System.out.println(email);
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
        return email;
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
        this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Fonction gérant les requêtes GET adressées au serveur.
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
     *
     * Fonction gérant les requêtes POST adressées au serveur.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String email = checkMail(request, response);
            //ajout ici de la vérification de la validité du couple email/mdp
            String mdp=ResetPwdManager.encode(request.getParameter("mdp"),email);
            Connection con;
            con = DatabaseManager.connectionDatabase();
            if (erreurs.isEmpty()) {
                //ajout ici de la redirection vers les pages d'accueil
                if (DatabaseManager.verifConnection(con, email, mdp)==null){
                    erreurs.put("mail", "Combinaison e-mail/mot de passe incorrecte");
                    request.setAttribute("erreurs", erreurs);
                    request.setAttribute("mail", "nodb");
                    this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                    erreurs.clear();
                } else if (DatabaseManager.verifConnection(con, email, mdp).equals("user")) {
                    DatabaseManager.setNbConn(con);
                    Cookie monCookie = new Cookie("user",email+"@#**#@"+mdp) ;
                    response.addCookie(monCookie);
                    response.sendRedirect("/SopraCarpooling-war/homeuser");
                } else if (DatabaseManager.verifConnection(con, email, mdp).equals("admin")) {
                    Cookie monCookie = new Cookie("admin",email+"@#**#@"+mdp) ;
                    response.addCookie(monCookie);
                    response.sendRedirect("/SopraCarpooling-war/adminhome");
                } 
            } else {
                this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                erreurs.clear();
            }
        } catch (SQLException ex) {
            erreurs.put("mail", "Base de données inaccessible, réessayez dans quelques minutes");
            request.setAttribute("erreurs", erreurs);
            request.setAttribute("mail", "nodb");
            this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            erreurs.clear();
        }

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
