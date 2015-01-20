<!DOCTYPE html>
<html>

    <head>
        <title>Sopra Carpooling</title>
        <meta charset="utf-8">
        <link href="css_images/Reset password/csspwdreset/style.css" rel='stylesheet' type='text/css' />
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
           
            <h1>Récupération de mot de passe</h1>
            <form method="post">
                <span class="erreur">${erreurs['mail']}</span>
                <li>
                        <input type="text" class="text" value="Tapez votre email" name="mail" onfocus="if (this.value == 'Tapez votre email') {
                                    this.value = '';
                                }" onblur="if (this.value == '') {
                                            this.value = 'Tapez votre email';
                                        }"><a href="#" class=" icon user"></a>
                </li>

                <div class ="forgot">
                    <input type="submit" onclick="myFunction()" value="Recevoir un nouveau mot de passe">                                                                                                                                                                                                                           </h4>
                </div>
            </form>
        </div>
    </body>
</html>