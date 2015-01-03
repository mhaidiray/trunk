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
        <div class="deco"><input type="submit" onclick="myFunction()" value="Se d�connecter"></div>
        <form method="post">
            <h2>Veuillez renseigner le trajet pour lequel vous souhaitez g�n�rer un rapport</h2>
            <div class="lable">
                <div class="col_1_of_3 span_1_of_2"><h5> Veuillez renseigner le code postal de d�part :</h5>
                    <input type="text" class="text" value="Code postal de d�part" name="zipdepart" onfocus="if (this.value == 'Code postal de d�part') {
                                    this.value = '';
                                }" onblur="if (this.value == '') {
                                            this.value = 'Code postal de d�part';
                                        }">
                </div>
                <div class="col_1_of_2 span_1_of_2"><h5> Veuillez choisir le site Sopra d'arriv�e :</h5>
                    <select name="sitearrivee" id="soflow">
                        <%
                            ArrayList<String> listSites;
                            listSites = (ArrayList<String>) request.getAttribute("listPlaces");
                            System.out.println(listSites.toString());
                            if (listSites != null) {
                                for (int i = 0; i < listSites.size(); i++) {
                                    String site = (String) listSites.get(i);
                        %>    
                        <option value="<%=site%>"><%=site%></option>
                        <%}
                            }%>
                    </select>
                </div>
                <span class="erreur">${erreurs['zipdepart']}</span>
                <br clear="all">
                <div class="cher"><input type="submit" onclick="myFunction()" value="G�n�rer un rapport"></div>
        </form>

    </body>

</html>