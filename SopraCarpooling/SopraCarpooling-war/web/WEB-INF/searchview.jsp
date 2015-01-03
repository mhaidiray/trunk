<%@page import="Model.Model"%>
<%@page import="java.util.Iterator"%>
<%@page import="Model.Model.User"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <link href="css_images/Search/searchviewcss/style.css" rel='stylesheet' type='text/css' />
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
        <!--webfonts-->
        <link href='http://fonts.googleapis.com/css?family=Oxygen:400,300,700' rel='stylesheet' type='text/css'>
        <!--//webfonts-->
    </head>
    <body>
        <div class="main">
            <h1>Bienvenue sur votre interface personnelle, ${prenom} ${nom}</h1>
            <div class="deco"><input type="submit" onclick="myFunction()" value="Se déconnecter"></div>
            <form method="post">
                <h2>Veuillez renseigner la commune de départ et le site Sopra d'arrivée de votre recherche</h2>
                <div class="lable">
                    <div class="col_1_of_2 span_1_of_2"><h5> Veuillez renseigner le code postal de départ :</h5>
                        <input type="text" class="text" value="Code postal de départ" name="zipdepart" onfocus="if (this.value == 'Code postal de départ') {
                                    this.value = '';
                                }" onblur="if (this.value == '') {
                                            this.value = 'Code postal de départ';
                                        }">
                    </div>
                    <div class="col_1_of_2 span_1_of_2"><h5> Veuillez choisir le site Sopra d'arrivée :</h5>
                        <select name="sitearrivee" id="soflow">
                            <option value="Sopra Colo 1" selected>Sopra Colo 1</option>
                            <option value="Sopra Colo 2">Sopra Colo 2</option>
                            <option value="Sopra Ramassiers">Sopra Ramassiers</option>
                            <option value="Sopra Albi">Sopra Albi</option>
                        </select>
                    </div>
                    <span class="erreur">${erreurs['zipdepart']}</span>
                    <div class="cher"><input type="submit" onclick="myFunction()" value="Chercher"></div>
            </form>

            <br CLEAR="all">
            <h2>Résultats de votre recherche :</h2>
            <table class="responstable">
                <tr>
                    <th>Conducteur</th>
                    <th>Nom</th>
                    <th>Prénom</th>
                    <th>Email</th>
                    <th>N° Tél</th>
                    <th>Départ à</th>               
                </tr>
                <%
                    ArrayList<Model.User> list;
                    list = (ArrayList<Model.User>) request.getAttribute("listUsers");
                    ArrayList<User> listUsers = new ArrayList<User>();

                    int cond;
                    String conduc, nom, pre, eml, tel, dep = null;
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
                %>
                <tr>
                    <td><%=conduc%></td>
                    <td><%=nom%></td>
                    <td><%=pre%></td>
                    <td><%=eml%></td>
                    <td><%=tel%></td>
                    <td><%=dep%></td>
                </tr>

                <%}
                    }%>
            </table>

            <div class="clear"></div>
        </div>
    </body>
</html>