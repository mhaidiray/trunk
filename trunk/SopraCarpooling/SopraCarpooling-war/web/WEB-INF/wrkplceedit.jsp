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
        <center>
            <IMG SRC="css_images/LogoHorizontal.png">
        </center>
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
            <input type="submit" name="deco" onclick="myFunction()" value="Se d�connecter">
            </div>
            </div>
            </div>
            </div>
                <span class="erreur">${erreurs['site']}</span>
                <%
                    String nomsite = (String) request.getAttribute("nomsite");
                    String addrsite = (String) request.getAttribute("addrsite");
                    if ((nomsite != null) && (addrsite != null)) {%>
                <div class="lable">
                    <div class="col_1_of_3 span_1_of_2"><input type="text" class="text" name="nom" value="<%=nomsite%>" onfocus="if (this.value == '<%=nomsite%>') {
                                this.value = '';
                            }" onblur="if (this.value == '') {
                                        this.value = '<%=nomsite%>';
                                    }"></div>
                    <span class="erreur">${erreurs['adr']}</span>
                    <div class="col_1_of_3 span_1_of_2"><input type="text" class="text" name="adresse" value="<%=addrsite%>" onfocus="if (this.value == '<%=addrsite%>') {
                                this.value = '';
                            }" onblur="if (this.value == '') {
                                        this.value = '<%=addrsite%>';
                                    }"></div>
                    <div class="clear"> </div>
                </div>

                <%} else {%>
                <div class="lable">
                    <div class="col_1_of_3 span_1_of_2"><input type="text" class="text" name="nom" value="Nom du site Sopra � ajouter" onfocus="if (this.value == 'Nom du site Sopra � ajouter') {
                                this.value = '';
                            }" onblur="if (this.value == '') {
                                        this.value = 'Nom du site Sopra � ajouter';
                                    }"></div>
                    <span class="erreur">${erreurs['adr']}</span>
                    <div class="col_1_of_3 span_1_of_2"><input type="text" class="text" name="adresse" value="Adresse du site Sopra" onfocus="if (this.value == 'Adresse du site Sopra') {
                                this.value = '';
                            }" onblur="if (this.value == '') {
                                        this.value = 'Adresse du site Sopra';
                                    }"></div>
                    <div class="clear"> </div>
                </div>
                <%}%>


                <div class="lable">
                    <div class="col_1_of_2 span_1_of_3">
                        <div class="submit">
                            <input type="submit" value="Valider" name="valide" >
                        </div>
                    </div>
                    <div class="col_1_of_2 span_1_of_3">
                        <div class="submit">
                            <input type="submit" value="Annuler" name ="annule" >
                        </div>
                    </div>
                </div>
                <div class="clear"></div>
            </form>
            <br clear="all">
            <h10>${erreurs['modifier']}</h10>
            <h10>${erreurs['creer']}</h10>

            <!-----//end-main---->
        </div>
    </body>
</html>