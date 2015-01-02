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
            <div class="lable">
                <div class="col_1_of_2 span_1_of_2"><h1>Gestion des lieux de travail</h1></div>
                <div class="col_1_of_2 span_1_of_2"><div class="deco"><input type="submit" onclick="myFunction()" value="Se déconnecter"></div></div>
                <div class="clear"> </div>
            </div>	
            <form>
                <div class="lable">
                    <div class="col_1_of_3 span_1_of_2"><input type="text" class="text" name="nom" value="Nom du site Sopra" onfocus="if (this.value == 'Nom du site Sopra') {
                                this.value = '';
                            }" onblur="if (this.value == '') {
                                        this.value = 'Nom du site Sopra';
                                    }"></div>
                    <div class="col_1_of_3 span_1_of_2"><input type="text" class="text" name="adresse" value="Adresse du site Sopra" onfocus="if (this.value == 'Adresse du site Sopra') {
                                this.value = '';
                            }" onblur="if (this.value == '') {
                                        this.value = 'Adresse du site Sopra';
                                    }"></div>
                    <div class="col_1_of_3 span_1_of_2"><input type="text" class="text" name="tel" value="Num de téléphone du site Sopra" onfocus="if (this.value == 'Num de téléphone du site Sopra') {
                                this.value = '';
                            }" onblur="if (this.value == '') {
                                        this.value = 'Num de téléphone du site Sopra';
                                    }"></div>
                    <div class="clear"> </div>
                </div>



                <div class="lable">
                    <div class="col_1_of_2 span_1_of_3">
                        <div class="submit">
                            <input type="submit" value="Valider" >
                        </div>
                    </div>
                    <div class="col_1_of_2 span_1_of_3">
                        <div class="submit">
                            <input type="submit" value="Annuler" >
                        </div>
                    </div>
                </div>
                <div class="clear"></div>
            </form>
            <!-----//end-main---->
        </div>
    </body>
</html>