/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DatabaseManager;
import static Model.DatabaseManager.modifWorkplace;
import Model.Model.Workplace;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *Cette classe gère l'édition de lieux de travail
 * @author Salah
 */
@WebServlet(name = "WrkplaceeditManager", urlPatterns = {"/WrkplaceeditManager"})
public class WorkplaceeditManager extends HttpServlet {

    HashMap<String, String> erreurs = new HashMap<String, String>();

    /**
     * Cette méthode vérifie que l'admin a bien saisi un nom dans le champ resérvé au nom. S'il ne l'a pas saisi et 
     * qu'il clique sur un bouton, une erreur est envoyé à la jsp pour lui demander de saisir un nom du site.
     *
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */

    public boolean checkSite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean erreurexist = false;
        String site = request.getParameter("nom");
        if (site.equals("Nom du site Sopra Ã  ajouter")) {
            erreurs.put("site", "Veuillez remplir le champ de nom du site Sopra");
            request.setAttribute("erreurs", erreurs);
            request.setAttribute("site", "invalid");
            erreurexist = true;
        }
        return erreurexist;
    }

    /**
     * Cette méthode vérifie que l'admin a bien saisi une adresse dans le champ d'adresse. S'il ne l'a pas saisi et 
     * qu'il clique sur un bouton, une erreur est envoyé à la jsp pour lui demander de saisir l'adresse.
     *
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */

    public boolean checkAdr(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean erreurexist = false;
        String adr = request.getParameter("adresse");
        if (adr.equals("Adresse du site Sopra")) {
            erreurs.put("adr", "Veuillez remplir le champ de l'adresse du site Sopra");
            request.setAttribute("erreurs", erreurs);
            request.setAttribute("adr", "invalid");
            erreurexist = true;
        }
        return erreurexist;
    }

    /**
     * Cette méthode est appelée par doGet ou doPost. Quand elle est appelée par
     * doGet elle effectue le traitement pour pouvoir afficher le nom et
     * l'adresse du site Sopra à modifier si l'admin avait précedemment cliqué
     * sur modifier. Si l'admin avait cliqué sur ajouter, c'est dans la JSP
     * qu'on contrôle l'affichage de champ non préremplis. Quand elle est
     * appelée par doPost, elle effectue un traitement différent selon le bouton
     * qui a été activé.
     *
     */

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        if (request.getParameter("deco") != null) {
            Cookie monCookie = new Cookie("user", null);
            monCookie.setMaxAge(0);
            response.addCookie(monCookie);
            response.sendRedirect("/SopraCarpooling-war/login");

        } else if (request.getParameter("acc") != null) {
            response.sendRedirect("/SopraCarpooling-war/adminhome");
        } else if (request.getParameter("annule") != null) {
            response.sendRedirect("/SopraCarpooling-war/wrkplcelist");

        } else if (request.getParameter("valide") != null) {
            boolean checksi = checkSite(request, response);
            boolean checkad = checkAdr(request, response);
            if (checksi || checkad) {
                this.getServletContext().getRequestDispatcher("/WEB-INF/wrkplceedit.jsp").forward(request, response);
                erreurs.clear();
            } else {
                try {
                    String site = request.getParameter("nom");
                    String adr = request.getParameter("adresse");
                    Workplace w1 = null;
                    w1 = new Model.Model().new Workplace(site, adr);
                    Connection con = DatabaseManager.connectionDatabase();
                    if (modifWorkplace(con, w1, site)) {
                        erreurs.put("modifier", "Félicitations, vous venez de modifier le site " + site);
                        request.setAttribute("erreurs", erreurs);
                        request.setAttribute("modifier", "invalid");
                    } else {
                        erreurs.put("creer", "Félicitations, Vous venez de créer le site " + site);
                        request.setAttribute("erreurs", erreurs);
                        request.setAttribute("creer", "invalid");
                    }
                    this.getServletContext().getRequestDispatcher("/WEB-INF/wrkplceedit.jsp").forward(request, response);
                    erreurs.clear();
                } catch (SQLException ex) {
                    Logger.getLogger(WorkplaceeditManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else {
            this.getServletContext().getRequestDispatcher("/WEB-INF/wrkplceedit.jsp").forward(request, response);
            erreurs.clear();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Cette méthode est appelée lors du premier affichage de la jsp wrkplceedit
     * Dans le cas où l'admin veut modifier une route, le nom de la route à
     * modifier ainsi que son adresse sont envoyés à la jsp. Le nom est extrait
     * des Cookies, et l'adresse est extraite à partir d'une requête à la base
     * de données. ProcessRequest est ensuite appelé avec les mêmes paramètres
     * plus le nom et l'adresse du site à modifier.
     *
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
        String Valeur2 = null;
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie MonCookie = cookies[i];
                if (MonCookie.getName().equals("admin")) {
                    Valeur = cookies[i].getValue();
                }
                if (MonCookie.getName().equals("modif")) {
                    Valeur2 = cookies[i].getValue();
                }
            }
        }

        if ((Valeur == null) || (Valeur2 == null)) {
            response.sendRedirect("/SopraCarpooling-war/login");
        } else {
            if (Valeur2.equals("none")) {
                processRequest(request, response);
            } else {
                try {
                    String admin = Valeur.substring(0);
                    String nomSite = Valeur2.substring(0);
                    Connection con = DatabaseManager.connectionDatabase();
                    Workplace wp = DatabaseManager.getWorkplace(con, nomSite);
                    String adr = wp.getAddress();
                    request.setAttribute("nomsite", nomSite);
                    request.setAttribute("addrsite", adr);
                    processRequest(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(WorkplaceeditManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * Cette méthode est appelée lors de l'appui sur l'un des boutons, elle
     * invoque peocessRequest avec les mêmes paramétres.
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
