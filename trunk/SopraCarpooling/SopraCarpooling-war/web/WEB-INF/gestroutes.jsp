<%@page import="Model.Route"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>

    <head>
        <title>Sopra Carpooling</title>
        <meta charset="UTF-8">
        <title>Interface d'administration</title>

        <link rel="stylesheet" href="css_images/Admin Gestion Routes/cssadminroutes/normalize.css">

        <link rel="stylesheet" href="css_images/Admin Gestion Routes/cssadminroutes/style.css" media="screen" type="text/css" />

    </head>

    <body>

        <h1>Interface d'administration</h1>
        <form method="post">
            <div class="deco"><input type="submit" onclick="myFunction()" name="deco" value="Se déconnecter"></div>
            </div>
            <h4>Nombre de connexions : 5236 </h4>
            <h4>Nombre total de conducteurs : 5236 </h4>
            <h4>Nombre total de passagers : 5236 </h4>
            
        </form>
        <h2> </br>
            <h2>Les routes existantes :</h2>
            <table class="responstable">
                <tr>
                    <th>CP Départ</th>
                    <th>Site Sopra</th>
                    <th>Nombre de conducteurs</th>
                    <th>Action</th>
                </tr>
                <%
                    ArrayList<Route> list;
                    list = (ArrayList<Route>) request.getAttribute("listRoutes");

                    String siteSopra;
                    int zipcode;
                    if (list != null) {
                        for (int i = 0; i < list.size(); i++) {
                            siteSopra = (String) list.get(i).getWorkplace();
                            zipcode = list.get(i).getZipcode();

                %>
                <tr>
                    <td><%=zipcode%></td>
                    <td><%=siteSopra%></td>
                    <td>255</td>
                    <td>	
                        <div class="submit">
                            <input type="submit" onclick="myFunction()" value="Generer un rapport specifique" name="gen" >
                        </div>
                        <div class="clear"> </div>
                    </td>
                </tr>
                <%}
                         }%>
                </body>

                </html>