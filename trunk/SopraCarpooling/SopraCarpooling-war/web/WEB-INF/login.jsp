<%@page import="java.security.NoSuchAlgorithmException"%>
<%@page import="java.security.MessageDigest"%>
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
        <link href="css_images/Log in/csslogin/style.css" rel='stylesheet' type='text/css' />
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
        <!--webfonts-->
        <link href='http://fonts.googleapis.com/css?family=Open+Sans:600italic,400,300,600,700' rel='stylesheet' type='text/css'>
        <!--//webfonts-->
    </head>
    <body>
    <center>
            <IMG SRC="css_images/LogoApp.png">
        </center>
        <!-----start-main---->
        <div class="login-form">
            <h1>Connexion</h1>
            <h2><a href="newcpt">Nouveau compte</a></h2>
            <form method="post">
                <span class="erreur">${erreurs['mail']}</span>
                <li>
                    <input type="text" class="text" value="Tapez votre email" name="mail" onfocus="if (this.value == 'Tapez votre email') {
                                this.value = '';
                            }" onblur="if (this.value == '') {
                                        this.value = 'Tapez votre email';
                                    }"><a href="#" class=" icon user"></a>
                </li>

                <li>
                    <input type="password" value="Mot de passe" name="mdp" onfocus="this.value = '';" onblur="if (this.value == '') {
                                this.value = 'Mot de passe';
                            }"><a href="#" class=" icon lock"></a>
                </li>
                
                <div class ="forgot">
                    <h3><a href="resetpwd">Mot de passe oublié ?</a></h3>
                    <input type="submit"  value="   Se connecter" > <a href="#" class=" icon arrow"></a>                                                                                                                                                                                                                                 </h4>
                </div>
            </form>
        </div>
        <!--//End-login-form-->
    </body>
</html>
