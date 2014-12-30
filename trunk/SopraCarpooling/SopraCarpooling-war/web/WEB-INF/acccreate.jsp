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
        <link href="css_images/Account creation/cssaccountcreate/style.css" rel='stylesheet' type='text/css' />
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
        <!--webfonts-->
        <link href='http://fonts.googleapis.com/css?family=Oxygen:400,300,700' rel='stylesheet' type='text/css'>
        <!--//webfonts-->
    </head>
    <body>
        <div class="main">

            <h1>Cr�ation de compte</h1>
            <form method="post" >
                <div class="lable">
                    <div class="col_1_of_2 span_1_of_2"><input type="text" class="text" name="nom" value="Nom" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Nom'; }"><span class="erreur">${erreurs['nom']}</span></div>
                    <div class="col_1_of_2 span_1_of_2"><input type="text" class="text" name="prenom" value="Pr�nom" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Pr�nom'; }"><span class="erreur">${erreurs['prenom']}</span></div>
                    <div class="clear"> </div>
                </div>
                <div class="lable-2">
                    <div>
                    <input type="text" class="text" value="Tapez votre email" name="mail" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Tapez votre email '; }">
                    <span class="erreur">${erreurs['mail']}</span></div>
                    <input type="password" class="text" value="Password " name="pwd1" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Password '; }">
                    <span class="erreur">${erreurs['pwd1']}</span>
                    <input type="password" class="text" value="Password " name="pwd2" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Password '; }">
                    <span class="erreur">${erreurs['pwd2']}</span>
                    <h4>Informations g�ographiques :</h4>
                    <div>
                    <input type="text" class="text" value="Commune de d�part " name="commdepart" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Commune de d�part '; }">
                    <span class="erreur">${erreurs['commdepart']}</span></div>
                    <input type="text" class="text" value="Code postal de d�part " name="zipdepart" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Code postal de d�part '; }">
                    <span class="erreur">${erreurs['zipdepart']}</span>
                    <input type="text" class="text" value="Site Sopra d'arriv�e " name="sitearrivee" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Site Sopra d'arriv�e';}">
                    <span class="erreur">${erreurs['sitearrivee']}</span>
                    <h4>Horaires :</h4>
                    
                    <input type="text" class="text" value="Heure de d�part matin, par exemple: 07h30" name="heurematin" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Heure de d�part '; }">
                    <div><span class="erreur">${erreurs['heurematin']}</span></div>
                    <input type="text" class="text" value="Heure de d�part soir, par exemple: 18h30 " name="heuresoir" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Heure de d�part soir '; }">
                    <span class="erreur">${erreurs['heuresoir']}</span>
                    <h4>Contact :</h4>
                    
                    <input type="text" class="text" value="Veuillez entrer votre num�ro de t�l�phone, par exemple: 0666102030 " name="tel" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Heure de d�part '; }">
                    <span class="erreur">${erreurs['tel']}</span>
                    
                    <h5> Veuillez cocher les jours applicables :</h5>		

                </div>
                    <INPUT type="checkbox" name="choix1" value="1"> <FONT COLOR=#ffffff >Lundi</FONT>
                    <INPUT type="checkbox" name="choix2" value="2"> <FONT COLOR=#ffffff >Mardi</FONT>
                    <INPUT type="checkbox" name="choix3" value="3"> <FONT COLOR=#ffffff >Mercredi</FONT>
                    <INPUT type="checkbox" name="choix4" value="4"> <FONT COLOR=#ffffff >Jeudi</FONT>
                    <INPUT type="checkbox" name="choix2" value="2"> <FONT COLOR=#ffffff >Vendredi</FONT>
                    <INPUT type="checkbox" name="choix3" value="3"> <FONT COLOR=#ffffff >Samedi</FONT>
                    <INPUT type="checkbox" name="choix3" value="3"> <FONT COLOR=#ffffff >Dimanche</FONT>
                <br>

                <h5> Êtes-vous conducteur ?</h5>

                    <INPUT type="radio" name="conducteur" value="Oui"> <FONT COLOR=#ffffff >Oui</FONT>
                    <INPUT type="radio" name="conducteur" value="Non"> <FONT COLOR=#ffffff >Non</FONT>
                <br>

                <h5> Souhaitez-vous �tre notifi� lors de l'ajout d'un trajet pouvant vous int�resser ?</h5>
                    <INPUT type="radio" name="notif" value="Oui"> <FONT COLOR=#ffffff >Oui</FONT> 
                    <INPUT type="radio" name="notif" value="Non"> <FONT COLOR=#ffffff >Non</FONT> 				   
                <br>
                <div class="submit">
                    <input type="submit" value="Cr�er un compte" >
                    </div>
                <div class="clear"> </div>
            </form>
            <!-----//end-main---->
        </div>

    </body>
</html>
