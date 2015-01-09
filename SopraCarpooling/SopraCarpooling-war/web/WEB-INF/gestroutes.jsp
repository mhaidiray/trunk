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
        <h4>Nombre de connexions : ${conn} </h4>
        <h4>Nombre total de conducteurs : ${cond} </h4>
        <h4>Nombre total de passagers : ${pass} </h4>

    </form>
    <h2> </br>
        <h2>Routes existantes :</h2>
        <table class="responstable">
            <tr>
                <th>CP Départ</th>
                <th>Site Sopra</th>
                <th>Conducteurs sur cette route</th>
                <th>Passagers sur cette route</th>
            </tr>
            <%
                ArrayList<Route> list;
                list = (ArrayList<Route>) request.getAttribute("listRoutes");

                String siteSopra;
                int zipcode;
                int cond;
                int pass;
                if (list != null) {
                    for (int i = 0; i < list.size(); i++) {
                        siteSopra = (String) list.get(i).getWorkplace();
                        zipcode = list.get(i).getZipcode();
                        cond = list.get(i).getNbcond();
                        pass = list.get(i).getNbpass();
            %>
            <tr>
                <td><%=zipcode%></td>
                <td><%=siteSopra%></td>
                <td><%=cond%></td>
                <td><%=pass%></td>
            </tr>
            <%}
                    }%>
            </body>

            </html>