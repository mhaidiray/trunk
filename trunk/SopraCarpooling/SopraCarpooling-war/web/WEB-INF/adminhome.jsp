<%@page import="Model.Model"%>
<%@page import="Model.Model.User"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>

    <head>
        <title>Sopra Carpooling</title>
        <meta charset="UTF-8">

        <link rel="stylesheet" href="css_images/Admin Home page/cssadminhome/normalize.css">

        <link rel="stylesheet" href="css_images/Admin Home page/cssadminhome/style.css" media="screen" type="text/css" />

    </head>
    <body>
        <center>
            <IMG SRC="css_images/LogoHorizontal.png">
        </center>
        <div class="main">
            <form>
                <div class="lable">
                    <div class="col_1_of_2 span_1_of_2">
                        <h1>Interface d'administration</h1>
                    </div>
                    <div class="col_1_of_2 span_1_of_2">

                        <div class="deco"><input type="submit" name="deco" onclick="myFunction()" value="Se déconnecter">
                        </div>

                    </div>
                </div>

                <br clear=all>
                <h2>Fonctionnalités :</h2>      
                <div class="lable-2">
                    <div class="col_1_of_2 span_1_of_2">
                        <div class="submit">
                            <input type="submit" onclick="myFunction()" name="handlePath" value="Gérer les trajets" >
                        </div>
                    </div>
                    <div class="col_1_of_2 span_1_of_2">
                        <div class="submit">
                            <input type="submit" onclick="myFunction()" name="generate" value="Générer un rapport général" >
                        </div>
                    </div>

                </div>
                <div class="clear"></div>
            </form>   
            <br clear=all>
            <h2>Gestion des comptes :</h2>
            <form>
                <table class="responstable">

                    <tr>
                        <th><center>Nom</center></th>
                    <th><center>Prénom</center></th>
                    <th><center>N° Tel</center></th>
                    <th><center>Adresse email</center></th>
                    <th><center>CP départ</center></th>
                    <th><center>Site Sopra arrivée</center></th>
                    <th><center>Modifier</center></th>
                    <th><center>Supprimer</center></th>
                    </tr>
                    <%
                        ArrayList<Model.User> list;
                        list = (ArrayList<Model.User>) request.getAttribute("listUsers");
                        ArrayList<String> list2;
                        list2 = (ArrayList<String>) request.getAttribute("work");
                        ArrayList<User> listUsers = new ArrayList<User>();

                        int cond;
                        String nom, pre, eml, tel, zip, work;
                        if (list != null) {
                            for (int i = 0; i < list.size(); i++) {
                                User user = (User) list.get(i);
                                nom = (String) user.getLastname();
                                pre = (String) user.getFirstname();
                                tel = (String) user.getPhone();
                                eml = (String) user.getEmail();
                                zip = String.valueOf(user.getZipcode());
                                work = (String) list2.get(user.getWorkplace() - 1);

                    %>
                    <tr>
                        <td><center><%=nom%></center></td>
                    <td><center><%=pre%></center></td>
                    <td><center><%=tel%></center></td>
                    <td><center><%=eml%></center></td>
                    <td><center><%=zip%></center></td>
                    <td><center><%=work%></center></td>
                    <td>	
                        <input type="submit" id="boutontab" value="Modifier" name="mod<%=i%>" >

                    </td>

                    <td>	
                        <input type="submit" id="boutontab" value="Supprimer" name="sup<%=i%>">

                    </td>
                    </tr>
                    <%}
                        }%>

            </form>
        </div>

    </table>

</body>
</html>
