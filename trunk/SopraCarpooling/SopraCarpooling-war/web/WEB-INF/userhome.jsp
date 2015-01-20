<%@page import="java.util.ArrayList"%>
<%@page import="Model.Model.User"%>
<%@page import="Model.Model"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Sopra Carpooling</title>
        <meta charset="utf-8">
        <link href="css_images/User Home/cssuserhome/style.css" rel='stylesheet' type='text/css' />
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!--webfonts-->
        <link href='http://fonts.googleapis.com/css?family=Oxygen:400,300,700' rel='stylesheet' type='text/css'>
        <!--//webfonts-->
    </head>
    <body>
        <center>
            <IMG SRC="css_images/LogoHorizontal.png">
        </center>
        <div class="main">
            <form>
                <div class="lable">
                    <div class="col_1_of_2 span_1_of_2">
                        <h1>Bienvenue, ${prenom} ${nom}</h1>
                    </div>
                    <div class="col_1_of_2 span_1_of_2">
                        <div class="deco">
                            <input type="submit" name="deco" onclick="myFunction()" value="Se déconnecter">
                        </div>
                    </div>
                </div>
                <br clear=all>
                <h2>Que souhaitez-vous faire aujourd'hui ?</h2>
                <div class="lable-2">
                    <div class="col_1_of_2 span_1_of_2">
                        <div class="submit">
                            <input type="submit" value="Consulter mes informations personnelles" name="persinfo">
                        </div>
                    </div>
                    <div class="col_1_of_2 span_1_of_2">
                        <div class="submit">
                            <input type="submit" value="Chercher un trajet personnalisé" name="trajet" >
                        </div>
                    </div>
                    <div class="clear"></div>

                </div>
                </br>

            </form>
            <h2> </br>
                <h2>Voici les utilisateurs ayant le même trajet que vous :</h2>
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
                        ArrayList<User> listUsers = new ArrayList<User>();

                        int cond;
                        String nom, pre, eml, tel, dep,depa,ret = null, conduc = "Non";
                        if (list != null) {
                            for (int i = 0; i < list.size(); i++) {
                                User user = (User) list.get(i);
                                cond = user.getDriver();
                                if (cond == 1) {
                                    conduc = "Oui";
                                }
                                nom = (String) user.getLastname();
                                pre = (String) user.getFirstname();
                                eml = (String) user.getEmail();
                                tel = (String) user.getPhone();
                                depa = (String) user.getMorning_time();
                                ret = (String) user.getAfternoon_time();
                                
                    %>
                    <tr>
                        <td><center><%=conduc%></center></td>
                        <td><center><%=nom%></center></td>
                        <td><center><%=pre%></center></td>
                        <td><center><%=eml%></center></td>
                        <td><center><%=tel%></center></td>
                        <td><center><%=depa%></center></td>
                        <td><center><%=ret%></center></td>
                    </tr>
                    <%}
                        }%>


                </table>

                <div class="clear"></div>
        </div>
    </body>
</html>