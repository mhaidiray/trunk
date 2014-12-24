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
        <link href="css_images/Workplace List/cssworkplacelist/style.css" rel='stylesheet' type='text/css' />
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
        <!--webfonts-->
        <link href='http://fonts.googleapis.com/css?family=Oxygen:400,300,700' rel='stylesheet' type='text/css'>
        <!--//webfonts-->
    </head>
    <body>
        <div class="main">
            <h1>Lieux de travail</h1>
            <form>
                <select id="soflow">
                    <option value="sopra1" selected>Sopra 1</option>
                    <option value="sopra2">Sopra 2</option>
                    <option value="sopra3">Sopra 3</option>
                    <option value="sopra4">Sopra 4</option>
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