

<!--
        Author: W3layouts
        Author URL: http://w3layouts.com
        License: Creative Commons Attribution 3.0 Unported
        License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE html>
<html>
    <head>
        <title>Sopra Carpooling</title>
        <meta charset="utf-8">
        <link href="../css_images/Modify Personal Info/cssmodpersonalinfo/style.css" rel='stylesheet' type='text/css' />
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
        <!--webfonts-->
        <link href='http://fonts.googleapis.com/css?family=Oxygen:400,300,700' rel='stylesheet' type='text/css'>
        <!--//webfonts-->
    </head>
    <body>
        <div class="main">
            <h1>Modifiez vos informations personnelles</h1>
            <form>
                <div class="lable">
                    <div class="col_1_of_2 span_1_of_2"><input type="text" class="text" value="Nom" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Nom'; }"></div>
                    <div class="col_1_of_2 span_1_of_2"><input type="text" class="text" value="Prénom" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Prénom'; }"></div>
                    <div class="clear"> </div>
                </div>
                <div class="lable-2">
                    <input type="text" class="text" value="Tapez votre email" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Tapez votre email '; }">
                    <h4>Entrez votre ancien mot de passe</h4>
                    <input type="password" class="text" value="Password " onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Password '; }">
                    <h4>Entrez un nouveau mot de passe</h4>
                    <input type="password" class="text" value="Password " onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Password '; }">
                    <input type="password" class="text" value="Password " onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Password '; }">
                    <h4>Informations géographiques :</h4>
                    <input type="text" class="text" value="Commune de départ " onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Commune de départ '; }">
                    <input type="text" class="text" value="Code postal de départ " onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Code postal de départ '; }">
                    <input type="text" class="text" value="Site Sopra d'arrivée " onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Site Sopra d'arrivée';}">
                    <h4>Horaires :</h4>
                    <input type="text" class="text" value="Heure de départ matin : exemple 07h30" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Heure de départ '; }">
                    <input type="text" class="text" value="Heure de départ soir : exemple 18h30 " onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Heure de départ soir '; }">
                    <h4> Veuillez cocher les jours applicables :</h4>
                </div>
                <FORM>
                    <INPUT type="checkbox" name="choix1" value="1"> <FONT COLOR=#ffffff >Lundi</FONT>
                    <INPUT type="checkbox" name="choix2" value="2"> <FONT COLOR=#ffffff >Mardi</FONT>
                    <INPUT type="checkbox" name="choix3" value="3"> <FONT COLOR=#ffffff >Mercredi</FONT>
                    <INPUT type="checkbox" name="choix4" value="4"> <FONT COLOR=#ffffff >Jeudi</FONT>
                    <INPUT type="checkbox" name="choix2" value="2"> <FONT COLOR=#ffffff >Vendredi</FONT>
                    <INPUT type="checkbox" name="choix3" value="3"> <FONT COLOR=#ffffff >Samedi</FONT>
                    <INPUT type="checkbox" name="choix3" value="3"> <FONT COLOR=#ffffff >Dimanche</FONT>
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
                        <div class="col_1_of_2 span_1_of_2"><input type="submit" onclick="myFunction()" value="Valider les modifications"></div>
                        <div class="col_1_of_2 span_1_of_2"><input type="submit" onclick="myFunction()" value="Annuler"></div>
                        <div class="clear"> </div>
                    </div>
                </div>
                <div class="clear"> </div>
            </form>
            <!-----//end-main---->
        </div>
    </body>
</html>

