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
                    <div class="col_1_of_2 span_1_of_2"><h3>Nom</h3></div>
                    <div class="col_1_of_2 span_1_of_2"><h3>Prénom</h3></div>
                    <div class="clear"> </div>
                </div>
                <div class="lable-2">
                    <h3>E-mail</h3>
                    <h4>Informations géographiques :</h4>
                    <div class="lable-2">
                        <div class="col_1_of_2 span_1_of_2"><h3>Commune de départ</h3></div>
                        <div class="col_1_of_2 span_1_of_2"><h3>Code postal de départ</h3></div>
                        <div class="clear"> </div>
                    </div>



                    <h3>Site SOPRA d'arrivée</h3>
                    <h4>Horaires :</h4>
                    <h3>Heure de départ matin</h3>
                    <h3>Heure de départ soir</h3>
                    <h4> Jours applicables :</h4>
                </div>
                <FORM>
                    <INPUT type="checkbox" name="choix1" value="1"> <FONT COLOR=#ffffff disabled="disabled">Lundi</FONT>
                    <INPUT type="checkbox" name="choix2" value="2"> <FONT COLOR=#ffffff disabled>Mardi</FONT>
                    <INPUT type="checkbox" name="choix3" value="3"> <FONT COLOR=#ffffff disabled>Mercredi</FONT>
                    <INPUT type="checkbox" name="choix4" value="4"> <FONT COLOR=#ffffff disabled>Jeudi</FONT>
                    <INPUT type="checkbox" name="choix2" value="2"> <FONT COLOR=#ffffff disabled>Vendredi</FONT>
                    <INPUT type="checkbox" name="choix3" value="3"> <FONT COLOR=#ffffff disabled>Samedi</FONT>
                    <INPUT type="checkbox" name="choix3" value="3"> <FONT COLOR=#ffffff disabled>Dimanche</FONT>
                </FORM>
                <h4> Êtes-vous conducteur ?</h4>
                <FORM>
                    <INPUT type="radio" name="conducteur" value="Oui"> <FONT COLOR=#ffffff >Oui</FONT>
                    <INPUT type="radio" name="conducteur" value="Non"> <FONT COLOR=#ffffff >Non</FONT>
                </FORM>
                <h4> Souhaitez-vous être notifié lors de l'ajout d'un trajet pouvant vous intéresser ?</h4>
                <FORM>
                    <INPUT type="radio" name="notif" value="Oui"> <FONT COLOR=#ffffff >Oui</FONT> 
                    <INPUT type="radio" name="notif" value="Non"> <FONT COLOR=#ffffff >Non</FONT> 
                </FORM>
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