<!DOCTYPE html>
<html>
    <head>
        <title>Sopra Carpooling</title>
        <meta charset="utf-8">
        <link href="css_images\Check Personal Info\csspersinfo\style.css" rel='stylesheet' type='text/css' />
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
        <!--webfonts-->
        <link href='http://fonts.googleapis.com/css?family=Oxygen:400,300,700' rel='stylesheet' type='text/css'>
        <!--//webfonts-->
    </head>
    <body>
        <div class="main">
            <div class="lable">
                <div class="col_1_of_2 span_1_of_2"><h1>Consultez vos informations personnelles</h1></div>
                <div class="col_1_of_2 span_1_of_2"><div class="deco"><input type="submit" onclick="myFunction()" value="Se déconnecter"></div></div>
                <div class="clear"> </div>
            </div>	
            <form>
                <div class="lable">
                    <div class="col_1_of_2 span_1_of_2"><h3>${nom}</h3></div>
                    <div class="col_1_of_2 span_1_of_2"><h3>${prenom}</h3></div>
                    <div class="clear"> </div>
                </div>
                <div class="lable">
                    <div class="col_1_of_2 span_1_of_2"><h3>${email}</h3></div>
                    <div class="col_1_of_2 span_1_of_2"><h3>${phone}</h3></div>
                    <div class="clear"> </div>
                </div>
                <div class="lable-2">
                    <h4>Informations géographiques :</h4>
                    <div class="lable-2">
                        <div class="col_1_of_2 span_1_of_2"><h3>${zip}</h3></div>
                        <div class="col_1_of_2 span_1_of_2"><h3>${site}</h3></div>
                        <div class="clear"> </div>
                    </div>


                    <h4>Horaires :</h4>
                    <h3>${matin}</h3>
                    <h3>${soir}</h3>
                    <h4> Jours applicables : ${lun}${mar}${mer}${jeu}${ven}${sam}${dim}</h4>
                </div>
                <h4> Conducteur : ${driver}</h4>
                <h4> Reçevoir des notifications : ${notif}</h4>
                <div class="submit">
                    <div class="lable">
                        <div class="col_1_of_2 span_1_of_2"><input type="submit" value="Modifier mes informations personnelles" name="mod"></div>
                        <div class="col_1_of_2 span_1_of_2"><input type="submit" value="Retourner à l'accueil" name="accueil"></div>
                        <div class="clear"> </div>
                    </div>
                </div>
                <div class="clear"></div>
            </form>
            <!-----//end-main---->
        </div>
    </body>
</html>