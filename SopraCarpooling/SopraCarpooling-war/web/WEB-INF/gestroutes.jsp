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
    <div class="main">
    
        <form method="post">

        <div class="lable">
            <div class="col_1_of_2 span_1_of_2">
                <h1>Rapport</h1>
            </div>

            <div class="col_1_of_2 span_1_of_2">
                <div class="lable">
                    <div class="col_1_of_2 span_1_of_2">
                        <input type="submit" name="acc" onclick="myFunction()" value="Accueil">
                    </div>
                    <div class="col_1_of_2 span_1_of_2">
                        <input type="submit" name="deco" onclick="myFunction()" value="Se déconnecter">
                    </div>
                </div>
            </div>
        </div>
    
        <br clear="all">
        <h5>Nombre de connexions : ${conn} </h5>
        <h5>Nombre total de conducteurs : ${cond} </h5>
        <h5>Nombre total de passagers : ${pass} </h5>

    </form>
    <h2>
        <h2>Routes existantes :</h2>
        <table class="responstable">
            <tr>
                <th><center>CP Départ</center></th>
                <th><center>Site Sopra</center></th>
                <th><center>Conducteurs sur cette route</center></th>
                <th><center>Passagers sur cette route</center></th>
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
                <td><center><%=zipcode%></center></td>
                <td><center><%=siteSopra%></center></td>
                <td><center><%=cond%></center></td>
                <td><center><%=pass%></center></td>
            </tr>
            <%}
                    }%>
            </div>
            </body>

            </html>