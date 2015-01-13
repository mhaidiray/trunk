<%@page import="java.security.NoSuchAlgorithmException"%>
<%@page import="java.security.MessageDigest"%>
<%@page import="java.util.ArrayList"%>
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

            <h1>Création de compte</h1>
            <form method="post" >
                <div class="lable">
                    <div class="col_1_of_2 span_1_of_2"><input type="text" class="text" name="nom" value="Nom" onfocus="if (this.value == 'Nom') {
                                this.value = '';
                            }" onblur="if (this.value == '') {
                                        this.value = 'Nom';
                                    }"><span class="erreur">${erreurs['nom']}</span></div>
                    <div class="col_1_of_2 span_1_of_2"><input type="text" class="text" name="prenom" value="Prénom" onfocus="if (this.value == 'Prénom') {
                                this.value = '';
                            }" onblur="if (this.value == '') {
                                        this.value = 'Prénom';
                                    }"><span class="erreur">${erreurs['prenom']}</span></div>
                    <div class="clear"> </div>
                </div>
                <div class="lable-2">
                    <div>
                        <input type="text" class="text" value="Tapez votre email" name="mail" onfocus="if (this.value == 'Tapez votre email') {
                                    this.value = '';
                                }" onblur="if (this.value == '') {
                                            this.value = 'Tapez votre email';
                                        }">
                        <span class="erreur">${erreurs['mail']}</span></div>
                    <input type="password" class="text" value="Password" name="pwd1" onfocus="if (this.value == 'Password') {
                                this.value = '';
                            }" onblur="if (this.value == '') {
                                        this.value = 'Password';
                                    }">
                    <span class="erreur">${erreurs['pwd1']}</span>
                    <input type="password" class="text" value="Password" name="pwd2" onfocus="if (this.value == 'Password') {
                                this.value = '';
                            }" onblur="if (this.value == '') {
                                        this.value = 'Password';
                                    }">
                    <span class="erreur">${erreurs['pwd2']}</span>
                    
                    
                    
                    <h4>Informations géographiques :</h4>
                    <input type="text" class="text" value="Code postal de départ" name="zipdepart" onfocus="if (this.value == 'Code postal de départ') {
                                this.value = '';
                            }" onblur="if (this.value == '') {
                                        this.value = 'Code postal de départ';
                                    }">
                    <span class="erreur">${erreurs['zipdepart']}</span>
                    <h5> Veuillez choisir le site Sopra d'arrivée :</h5>
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
                    <h4>Horaires :</h4>

                    <input type="text" class="text" name="heurematin" value="Heure de départ matin, par exemple: 07h30"  onfocus="if (this.value == 'Heure de départ matin, par exemple: 07h30') {
                                this.value = '';
                            }" onblur="if (this.value == '') {
                                        this.value = 'Heure de départ matin, par exemple: 07h30';
                                    }">
                    <div><span class="erreur">${erreurs['heurematin']}</span></div>
                    <input type="text" class="text" name="heuresoir" value="Heure de départ soir, par exemple: 18h30" onfocus="if (this.value == 'Heure de départ soir, par exemple: 18h30') {
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
                <INPUT type="checkbox" name="lundi" > <FONT COLOR=#ffffff >Lundi</FONT>
                <INPUT type="checkbox" name="mardi" > <FONT COLOR=#ffffff >Mardi</FONT>
                <INPUT type="checkbox" name="mercredi" > <FONT COLOR=#ffffff >Mercredi</FONT>
                <INPUT type="checkbox" name="jeudi" > <FONT COLOR=#ffffff >Jeudi</FONT>
                <INPUT type="checkbox" name="vendredi" > <FONT COLOR=#ffffff >Vendredi</FONT>
                <INPUT type="checkbox" name="samedi" > <FONT COLOR=#ffffff >Samedi</FONT>
                <INPUT type="checkbox" name="dimanche" > <FONT COLOR=#ffffff >Dimanche</FONT>
                <br>
                <br>

                <h5> Êtes-vous conducteur ?</h5>

                <INPUT type="radio" name="conducteur" value="Oui"> <FONT COLOR=#ffffff >Oui</FONT>
                <INPUT type="radio" name="conducteur" value="Non"> <FONT COLOR=#ffffff >Non</FONT>
                <br>
                <br>

                <h5> Souhaitez-vous être notifié lors de l'ajout d'un trajet pouvant vous intéresser ?</h5>
                <INPUT type="radio" name="notif" value="Oui"> <FONT COLOR=#ffffff >Oui</FONT> 
                <INPUT type="radio" name="notif" value="Non"> <FONT COLOR=#ffffff >Non</FONT> 				   
                <br>
                <br>
                <div class="submit">
                    <input type="submit" value="Créer un compte" >
                </div>
                <div class="clear"> </div>
            </form>
            <!-----//end-main---->
        </div>

    </body>
</html>