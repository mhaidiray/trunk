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


        <form>  
            <h1>Interface d'administration</h1>
            <div class="deco"><input type="submit" onclick="myFunction()" name="disconnect" value="Se déconnecter"></div>
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

        <br/>
        <h2>Gestion des comptes :</h2>
        <form>
        <table class="responstable">

            <tr>
                <th><center>Nom</center></th>
        <th><center>Prenom</center></th>
    <th><center>Téléphone</center></th>
<th><center>Email</center></th>
<th><center>Commune de départ</center></th>
<th><center>Site Sopra d'arrivée</center></th>
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
            work= (String) list2.get(user.getWorkplace()-1);
            
            %>
            <tr>
                <td><%=nom%></td>
                <td><%=pre%></td>
                <td><%=eml%></td>
                <td><%=tel%></td>
                <td><%=zip%></td>
                <td><%=work%></td>
            <td>	
                <div class="submit">
                    <input type="submit" value="Modifier" name="mod<%=i%>" >
                </div>
                <div class="clear"> </div>
            </td>

            <td>	
                <div class="submit">
                    <input type="submit" value="Supprimer" name="sup<%=i%>">
                </div>
                <div class="clear"> </div>
            </td>
            </tr>
            <%}
                        }%>

        </form>

</table>


<script src='http://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.js'></script>


</body>
</html>
