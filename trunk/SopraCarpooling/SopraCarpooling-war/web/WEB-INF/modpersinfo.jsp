<%@page import="java.security.NoSuchAlgorithmException"%>
<%@page import="java.security.MessageDigest"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Sopra Carpooling</title>
        <meta charset="utf-8">
        <link href="css_images/Modify Personal Info/cssmodpersonalinfo/style.css" rel='stylesheet' type='text/css' />
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
        <!--webfonts-->
        <link href='http://fonts.googleapis.com/css?family=Oxygen:400,300,700' rel='stylesheet' type='text/css'>
        <!--//webfonts-->
    </head>
    <body>
        <div class="main">
            <form method="post">
            <div class="lable">
                <div class="col_1_of_2 span_1_of_2"><h1>${titre}</h1></div>
                <div class="col_1_of_2 span_1_of_2"><div class="deco"><input type="submit" name="deco" onclick="myFunction()" value="Se déconnecter"></div></div>
                <div class="clear"> </div>
            </div>	
            
                <div class="lable">
                    
                    <div class="col_1_of_2 span_1_of_2"><input type="text" class="text" name="nom" value="${nom}"><span class="erreur">${erreurs['nom']}</span></div>
                    
                    <div class="col_1_of_2 span_1_of_2"><input type="text" class="text" name="prenom" value="${prenom}"><span class="erreur">${erreurs['prenom']}</span></div>
                    <div class="clear"> </div>
                </div>
               <div class="lable-2">
                    <div>
                        <input type="text" class="text" name="mail" value="${mail}"><span class="erreur">${erreurs['mail']}</span></div>
                    <h4>Entrez votre ancien mot de passe</h4>
                    <span class="erreur">${erreurs['pwd1']}</span>
                    <input type="password" class="text" value="Password" name="pwdd1" onfocus="if (this.value == 'Password') {
                                this.value = '';
                            }" onblur="if (this.value == '') {
                                        this.value = 'Password';
                                    }">
                    <h4>Entrez un nouveau mot de passe (facultatif)</h4>
                    <span class="erreur">${erreurs['pwd2']}</span>
                    <input type="password" class="text" value="" name="pwdd2" onfocus="if (this.value == '') {
                                this.value = '';
                            }" onblur="if (this.value == '') {
                                        this.value = '';
                                    }">
                    <input type="password" class="text" value="" name="pwdd3" onfocus="if (this.value == '') {
                                this.value = '';
                            }" onblur="if (this.value == '') {
                                        this.value = '';
                                    }">
                    
                                        
                    
                    
                                <%!
                private static String encode(String mdp, String email)
                {
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
            %>
                   <%   
                        String pwd1 = request.getParameter("pwdd1"); 
                        String pwd2 = request.getParameter("pwdd2"); 
                        String pwd3 = request.getParameter("pwdd3"); 
                        String email = request.getParameter("mail");
                        String chiffre1 = encode(pwd1,email);
                        String chiffre2 = encode(pwd2,email);
                        String chiffre3 = encode(pwd3,email);
                   %>

                    <input type="hidden" name="pwd1" value="<%=chiffre1%>">
                    <input type="hidden" name="pwd2" value="<%=chiffre2%>">
                    <input type="hidden" name="pwd3" value="<%=chiffre3%>">
                    <h4>Informations géographiques :</h4>
                    <span class="erreur">${erreurs['zipdepart']}</span>
                    <input type="text" class="text" value="${zipdepart}" name="zipdepart">
                    <h5> Veuillez choisir le site Sopra d'arrivée :</h5>
                    <span class="erreur">${erreurs['sitearrivee']}</span>
                    <select name="sitearrivee" id="soflow">
                        <%
                            ArrayList<String> listSites;
                            listSites = (ArrayList<String>) request.getAttribute("listPlaces");
                            System.out.println(listSites.toString());
                            if (listSites != null) {
                                for (int i = 0; i < listSites.size(); i++) {
                                    String site = (String) listSites.get(i);
                                    if (site.equals(request.getAttribute("work"))) {
                        %>    
                        <option selected value="<%=site%>"><%=site%></option>
                        <%}
                                    else { %>
                        <option value="<%=site%>"><%=site%></option>            
                            <%}
                                }
                            }%>
                    </select>
                    <h4>Horaires :</h4>
                    <input type="text" class="text" name="heurematin" value="${heurematin}">
                    <div><span class="erreur">${erreurs['heurematin']}</span></div>
                    <input type="text" class="text" name="heuresoir" value="${heuresoir}">
                    <span class="erreur">${erreurs['heuresoir']}</span>
                    <h4>Contact :</h4>
                    
                    <input type="text" class="text" value="${tel}" name="tel">
                    <span class="erreur">${erreurs['tel']}</span>

                    <h5> Veuillez cocher les jours applicables :</h5>		

                </div>
                    <% if (request.getAttribute("lundi")!=null){ %>
                <INPUT type="checkbox" name="lun" value="1" checked=""> <FONT COLOR=#ffffff >Lundi</FONT>
                <% } else {%>
                <INPUT type="checkbox" name="lun" value="1"> <FONT COLOR=#ffffff >Lundi</FONT>
                <% } if (request.getAttribute("mardi")!=null){ %>
                <INPUT type="checkbox" name="mar" value="1" checked=""> <FONT COLOR=#ffffff >Mardi</FONT>
                <% } else {%>
                <INPUT type="checkbox" name="mar" value="1"> <FONT COLOR=#ffffff >Mardi</FONT>
                <% } if (request.getAttribute("mercredi")!=null){ %>
                <INPUT type="checkbox" name="mer" value="1" checked=""> <FONT COLOR=#ffffff >Mercredi</FONT>
                <% } else {%>
                <INPUT type="checkbox" name="mer" value="1"> <FONT COLOR=#ffffff >Mercredi</FONT>
                <% } if (request.getAttribute("jeudi")!=null){ %>
                <INPUT type="checkbox" name="jeu" value="1" checked=""> <FONT COLOR=#ffffff >Jeudi</FONT>
                <% } else {%>
                <INPUT type="checkbox" name="jeu" value="1"> <FONT COLOR=#ffffff >Jeudi</FONT>
                <% } if (request.getAttribute("vendredi")!=null){ %>
                <INPUT type="checkbox" name="ven" value="1" checked=""> <FONT COLOR=#ffffff >Vendredi</FONT>
                <% } else {%>
                <INPUT type="checkbox" name="ven" value="1"> <FONT COLOR=#ffffff >Vendredi</FONT>
                <% } if (request.getAttribute("samedi")!=null){ %>
                <INPUT type="checkbox" name="sam" value="1" checked=""> <FONT COLOR=#ffffff >Samedi</FONT>
                <% } else {%>
                <INPUT type="checkbox" name="sam" value="1"> <FONT COLOR=#ffffff >Samedi</FONT>
                <% } if (request.getAttribute("dimanche")!=null){ %>
                <INPUT type="checkbox" name="dim" value="1" checked=""> <FONT COLOR=#ffffff >Dimanche</FONT>
                <% } else {%>
                <INPUT type="checkbox" name="dim" value="1"> <FONT COLOR=#ffffff >Dimanche</FONT>
                <% } %>
                <br>

                <h5> Êtes-vous conducteur ?</h5>
                <% if (request.getAttribute("cond")!=null){ %>
                <INPUT type="radio" name="conducteur" value="Oui" checked="checked"> <FONT COLOR=#ffffff >Oui</FONT>
                <INPUT type="radio" name="conducteur" value="Non"> <FONT COLOR=#ffffff >Non</FONT>
                <% } else {%>
                <INPUT type="radio" name="conducteur" value="Oui" > <FONT COLOR=#ffffff >Oui</FONT>
                <INPUT type="radio" name="conducteur" value="Non" checked="checked"> <FONT COLOR=#ffffff >Non</FONT>
                <% } %>
                <br>

                <h5> Souhaitez-vous être notifié lors de l'ajout d'un trajet pouvant vous intéresser ?</h5>
                <% if (request.getAttribute("not")!=null){ %>
                <INPUT type="radio" name="notif" value="Oui" checked="checked"> <FONT COLOR=#ffffff >Oui</FONT>
                <INPUT type="radio" name="notif" value="Non"> <FONT COLOR=#ffffff >Non</FONT>
                <% } else {%>
                <INPUT type="radio" name="notif" value="Oui" > <FONT COLOR=#ffffff >Oui</FONT>
                <INPUT type="radio" name="notif" value="Non" checked="checked"> <FONT COLOR=#ffffff >Non</FONT>
                <% } %>				   
                <br>
                <div class="submit">
                    <div class="lable">
                        <div class="col_1_of_2 span_1_of_2"><input type="submit" value="Valider les modifications" name="valider"></div>
                        <div class="col_1_of_2 span_1_of_2"><input type="submit" value="Annuler" name="annuler"></div>
                        <div class="clear"> </div>
                    </div>
                </div>
                <div class="clear"> </div>
            </form>
            <!-----//end-main---->
        </div>
    </body>
</html>