<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Sopra Carpooling</title>
        <meta charset="utf-8">
        <link href="css_images/Workplace List/cssworkplacelist/style.css" rel='stylesheet' type='text/css' />
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
        <!--webfonts-->
        <link href='http://fonts.googleapis.com/css?family=Oxygen:400,300,700' rel='stylesheet' type='text/css'>
        <!--//webfonts-->
    </head>
    <body>
        <div class="main">
            <form>
                <div class="lable">
                    <div class="col_1_of_2 span_1_of_2">
                        <h1>Gestion des lieux de travail</h1>
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
                <select name="sitesopra" id="soflow">
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
                <div class="lable">
                    <div class="col_1_of_2 span_1_of_3">
                        <div class="submit">
                            <input type="submit" onclick="create()" value="Ajouter" name="add">
                        </div>
                    </div>
                    <div class="col_1_of_2 span_1_of_3">
                        <div class="submit">
                            <input type="submit" onclick="modify()" value="Modifier" name="modif">
                        </div>
                    </div>
                    <div class="col_1_of_2 span_1_of_3">
                        <div class="submit">
                            <input type="submit" onclick="create()" value="Supprimer" name="del">
                        </div>
                    </div>
                </div>
                <div class="clear"></div>
            </form>
            <!-----//end-main---->
        </div>
    </body>
</html>