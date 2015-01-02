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
                <div class="col_1_of_2 span_1_of_2"><h1>Lieux de travail</h1></div>
                <div class="col_1_of_2 span_1_of_2"><div class="deco"><input type="submit" onclick="myFunction()" value="Se déconnecter"></div></div>
                <div class="clear"> </div>
            </div>	
            <form>
                <select id="soflow">
                    <option value="sopra1" selected>Sopra Colo 1</option>
                    <option value="sopra2">Sopra Colo 2</option>
                    <option value="sopra3">Sopra Ramassiers</option>
                    <option value="sopra4">Sopra Albi</option>
                </select>
                <div class="lable">
                    <div class="col_1_of_2 span_1_of_3">
                        <div class="submit">
                            <input type="submit" onclick="create()" value="Ajouter" >
                        </div>
                    </div>
                    <div class="col_1_of_2 span_1_of_3">
                        <div class="submit">
                            <input type="submit" onclick="modify()" value="Modifier" >
                        </div>
                    </div>
                    <div class="col_1_of_2 span_1_of_3">
                        <div class="submit">
                            <input type="submit" onclick="create()" value="Supprimer" >
                        </div>
                    </div>
                </div>
                <div class="clear"></div>
            </form>
            <!-----//end-main---->
        </div>
    </body>
</html>