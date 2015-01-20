<%@page import="Model.Model"%>
<%@page import="java.util.Iterator"%>
<%@page import="Model.Model.User"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Sopra Carpooling</title>
        <meta charset="utf-8">
        <link href="css_images/Search/searchviewcss/style.css" rel='stylesheet' type='text/css' />
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href='http://fonts.googleapis.com/css?family=Oxygen:400,300,700' rel='stylesheet' type='text/css'>
        <!--//webfonts-->
    </head>
    <body>
        <center>
            <IMG SRC="css_images/LogoHorizontal.png">
        </center>
        <div class="main">
            <form method="post">

                <div class="lable">
                    <div class="col_1_of_2 span_1_of_2">
                        <h1>Recherche de covoitureurs</h1>
                    </div>
                    <div class="col_1_of_2 span_1_of_2">
                        <div class="lable">
                            <div class="col_1_of_2 span_1_of_2">
                                <input type="submit" name="accu" onclick="myFunction()" value="Accueil">
                            </div>
                            <div class="col_1_of_2 span_1_of_2">
                                <input type="submit" name="deco" onclick="myFunction()" value="Se déconnecter">
                            </div>
                        </div>
                    </div>
                </div>

                <br clear=all>
                <h2>Veuillez renseigner la commune de départ et le site Sopra d'arrivée de votre recherche</h2>
                <div class="lable">
                    <div class="col_1_of_2 span_1_of_2"><h5> Veuillez renseigner le code postal de départ :</h5>

                    </div>
                    <div class="col_1_of_2 span_1_of_2"><h5> Veuillez choisir le site Sopra d'arrivée :</h5>

                    </div>
                </div>
                <div class="lable">
                    <div class="col_1_of_2 span_1_of_2">
                        <input type="text" class="text" value="Code postal de départ" name="zipdepart" onfocus="if (this.value == 'Code postal de départ') {
                                        this.value = '';
                                    }" onblur="if (this.value == '') {
                                                this.value = 'Code postal de départ';
                                            }">
                    </div>
                    <div class="col_1_of_2 span_1_of_2">
                        <select name="sitearrivee" id="soflow">
                            <%
                                ArrayList<String> listSites;
                                listSites = (ArrayList<String>) request.getAttribute("listPlaces");
                                if (listSites != null) {
                                    for (int i = 0; i < listSites.size(); i++) {
                                        String site = (String) listSites.get(i);
                            %>    
                            <option value="<%=site%>"><%=site%></option>
                            <%}
                                    }%>
                        </select>
                    </div>
                </div>
                        
                        <br clear=all><span class="erreur"> ${erreurs['zipdepart']}</span>
                         <br clear=all> <br clear=all>
                         
                <div class="cher"><input type="submit" name="search" onclick="myFunction()" value="Chercher"></div>


                <br CLEAR="all">
                <h2>Résultats de votre recherche :</h2>
                <table class="responstable">
                    <tr>
                        <th><center>Conducteur</center></th>
                        <th><center>Nom</center></th>
                        <th><center>Prénom</center></th>
                        <th><center>Email</center></th>
                        <th><center>N° Tél</center></th>
                        <th><center>Départ</center></th>
                        <th><center>Retour</center></th>
                    </tr>
                    <%
                        ArrayList<Model.User> list;
                        list = (ArrayList<Model.User>) request.getAttribute("listUsers");
                        int cond;
                        String conduc, nom, pre, eml, tel, dep,ret = null;
                        if (list != null) {
                            for (int i = 0; i < list.size(); i++) {
                                User user = (User) list.get(i);
                                cond = (int) user.getDriver();
                                if (cond == 1) {
                                    conduc = "Oui";
                                } else {
                                    conduc = "Non";
                                }
                                nom = (String) user.getLastname();
                                pre = (String) user.getFirstname();
                                eml = (String) user.getEmail();
                                tel = (String) user.getPhone();
                                dep = (String) user.getMorning_time();
                                ret = (String) user.getAfternoon_time();
                    %>
                    <tr>
                        <td><center><%=conduc%></center></td>
                        <td><center><%=nom%></center></td>
                        <td><center><%=pre%></center></td>
                        <td><center><%=eml%></center></td>
                        <td><center><%=tel%></center></td>
                        <td><center><%=dep%></center></td>
                        <td><center><%=ret%></center></td>
                    </tr>

                    <%}
                                }%>
                </table>

                <div class="clear"></div>

        </div>
    </form>
</body>
</html>