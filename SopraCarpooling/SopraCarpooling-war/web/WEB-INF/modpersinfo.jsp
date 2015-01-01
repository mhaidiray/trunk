<!DOCTYPE html>
<html>
    <head>
        <title>Sopra Carpooling</title>
        <meta charset="utf-8">
        <link href="css_images/Modify Personal Info/cssmodpersonalinfo/style.css" rel='stylesheet' type='text/css' />
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
        <!--webfonts-->
        <link href='http://fonts.googleapis.com/css?family=Oxygen:400,300,700' rel='stylesheet' type='text/css'>
        <!--//webfonts-->
    </head>
    <body>
        <div class="main">

            <div class="lable">
                <div class="col_1_of_2 span_1_of_2"><h1>Modifiez vos informations personnelles</h1></div>
                <div class="col_1_of_2 span_1_of_2"><div class="deco"><input type="submit" onclick="myFunction()" value="Se déconnecter"></div></div>
                <div class="clear"> </div>
            </div>	
            <form>
                <div class="lable">
                    <div class="col_1_of_2 span_1_of_2"><input type="text" class="text" name="nom" value="Nom" onfocus="if (this.value == 'Nom') {
                                this.value = '';
                            }" onblur="if (this.value == '') {
                                        this.value = 'Nom';
                                    }"></div>
                    <div class="col_1_of_2 span_1_of_2"><input type="text" class="text" name="prenom" value="Prénom" onfocus="if (this.value == 'Prénom') {
                                this.value = '';
                            }" onblur="if (this.value == '') {
                                        this.value = 'Prénom';
                                    }"></div>
                    <div class="clear"> </div>
                </div>
                <div class="lable-2">
                    <input type="text" class="text" value="Tapez votre email" name="mail" onfocus="if (this.value == 'Tapez votre email') {
                                this.value = '';
                            }" onblur="if (this.value == '') {
                                        this.value = 'Tapez votre email';
                                    }">
                    <h4>Entrez votre ancien mot de passe</h4>
                    <input type="password" class="text" value="Password" name="pwd1" onfocus="if (this.value == 'Password') {
                                this.value = '';
                            }" onblur="if (this.value == '') {
                                        this.value = 'Password';
                                    }">
                    <h4>Entrez un nouveau mot de passe</h4>
                    <input type="password" class="text" value="Password" name="pwd2" onfocus="if (this.value == 'Password') {
                                this.value = '';
                            }" onblur="if (this.value == '') {
                                        this.value = 'Password';
                                    }">
                    <input type="password" class="text" value="Password" name="pwd3" onfocus="if (this.value == 'Password') {
                                this.value = '';
                            }" onblur="if (this.value == '') {
                                        this.value = 'Password';
                                    }">
                    <h4>Informations géographiques :</h4>
                    <input type="text" class="text" value="Code postal de départ" name="zipdepart" onfocus="if (this.value == 'Code postal de départ') {
                                this.value = '';
                            }" onblur="if (this.value == '') {
                                        this.value = 'Code postal de départ';
                                    }">
                    <span class="erreur">${erreurs['zipdepart']}</span>
                    <h5> Veuillez choisir le site Sopra d'arrivée :</h5>
                    <select id="soflow">
                        <option value="sopra1" selected>Sopra 1</option>
                        <option value="sopra2">Sopra 2</option>
                        <option value="sopra3">Sopra 3</option>
                        <option value="sopra4">Sopra 4</option>
                    </select>
                    <h4>Horaires :</h4>

                    <input type="text" class="text" value="Heure de départ matin, par exemple: 07h30" onfocus="if (this.value == 'Heure de départ matin, par exemple: 07h30') {
                                this.value = '';
                            }" onblur="if (this.value == '') {
                                        this.value = 'Heure de départ matin, par exemple: 07h30';
                                    }">
                    <div><span class="erreur">${erreurs['heurematin']}</span></div>
                    <input type="text" class="text" value="Heure de départ soir, par exemple: 18h30" onfocus="if (this.value == 'Heure de départ soir, par exemple: 18h30') {
                                this.value = '';
                            }" onblur="if (this.value == '') {
                                        this.value = 'Heure de départ soir, par exemple: 18h30';
                                    }">
                    <span class="erreur">${erreurs['heuresoir']}</span>
                    <h4>Contact :</h4>

                    <input type="text" class="text" value="Veuillez entrer votre numéro de téléphone, par exemple: 0666102030" name="tel" onfocus="if (this.value == 'Veuillez entrer votre numéro de téléphone, par exemple: 0666102030') {
                                this.value = '';
                            }" onblur="if (this.value == '') {
                                        this.value = 'Veuillez entrer votre numéro de téléphone, par exemple: 0666102030';
                                    }">
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

                <h5> Souhaitez-vous être notifié lors de l'ajout d'un trajet pouvant vous intéresser ?</h5>
                <INPUT type="radio" name="notif" value="Oui"> <FONT COLOR=#ffffff >Oui</FONT> 
                <INPUT type="radio" name="notif" value="Non"> <FONT COLOR=#ffffff >Non</FONT> 				   
                <br>
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