<%@page import="java.util.ArrayList"%>
<%@page import="Model.Model.User"%>
<%@page import="Model.Model"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Sopra Carpooling</title>
    <div class="deco"><input type="submit" onclick="myFunction()" value="Se d�connecter"></div>
    <meta charset="utf-8">
    <link href="css_images/User Home/cssuserhome/style.css" rel='stylesheet' type='text/css' />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <!--webfonts-->
    <link href='http://fonts.googleapis.com/css?family=Oxygen:400,300,700' rel='stylesheet' type='text/css'>
    <!--//webfonts-->
</head>
<body>
    <div class="main">
        <h1>Bienvenue sur votre interface personnelle, ${prenom} ${nom}</h1>
        <form>
            <h2>Que souhaitez-vous faire aujourd'hui ?</h2>
            <div class="lable-2">
                <div class="col_1_of_2 span_1_of_2">
                    <div class="submit">
                        <input type="submit" value="Consulter mes informations personnelles" name="persinfo">
                    </div>
                </div>
                <div class="col_1_of_2 span_1_of_2">
                    <div class="submit">
                        <input type="submit" value="Chercher un trajet personnalis�" name="trajet" >
                    </div>
                </div>
                <div class="clear"></div>

            </div>
            </br>

        </form>
        <h2> </br>
            <h2>Voici les utilisateurs ayant le m�me trajet que vous :</h2>
            <table class="responstable">
                <tr>
                    <th>Conducteur</th>
                    <th>Nom</th>
                    <th>Pr�nom</th>
                    <th>Email</th>
                    <th>N� T�l</th>
                </tr>
                <%
                        ArrayList<Model.User> list;
                        list = (ArrayList<Model.User>) request.getAttribute("listUsers");
                        ArrayList<User> listUsers = new ArrayList<User>();

                        int cond;
                        String nom, pre, eml, tel, dep = null;
                        if (list != null) {
                            for (int i = 0; i < list.size(); i++) {
                                User user = (User) list.get(i);
                                cond = (int) user.getDriver();
                                nom = (String) user.getLastname();
                                pre = (String) user.getFirstname();
                                eml = (String) user.getEmail();
                                tel = (String) user.getPhone();
                    %>
                    <tr>
                        <td><%=cond%></td>
                        <td><%=nom%></td>
                        <td><%=pre%></td>
                        <td><%=eml%></td>
                        <td><%=tel%></td>
                    </tr>
                <%}}%>
                

            </table>

            <div class="clear"></div>
    </div>
</body>
</html>