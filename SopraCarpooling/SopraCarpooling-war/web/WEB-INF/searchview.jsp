

<!--
        Author: W3layouts
        Author URL: http://w3layouts.com
        License: Creative Commons Attribution 3.0 Unported
        License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
    <div class="deco"><input type="submit" onclick="myFunction()" value="Se d�connecter"></div>
    <link href="css_images/Search/searchviewcss/style.css" rel='stylesheet' type='text/css' />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <!--webfonts-->
    <link href='http://fonts.googleapis.com/css?family=Oxygen:400,300,700' rel='stylesheet' type='text/css'>
    <!--//webfonts-->
</head>
<body>
    <div class="main">
        <h1>Bienvenue sur votre interface personnelle</h1>
        <form>
            <h2>Veuillez renseigner la commune de d�part et le site Sopra d'arriv�e de votre recherche</h2>
            <div class="lable">

                <div class="col_1_of_2 span_1_of_2"><h5> Veuillez renseigner le code postal de d�part :</h5>
                    <input type="text" class="text" value="Code postal de d�part" name="zipdepart" onfocus="if (this.value == 'Code postal de d�part') {
                                this.value = '';
                            }" onblur="if (this.value == '') {
                                        this.value = 'Code postal de d�part';
                                    }">

                </div>

                <div class="col_1_of_2 span_1_of_2"><h5> Veuillez choisir le site Sopra d'arriv�e :</h5>
                    <select id="soflow">
                        <option value="sopra1" selected>Sopra 1</option>
                        <option value="sopra2">Sopra 2</option>
                        <option value="sopra3">Sopra 3</option>
                        <option value="sopra4">Sopra 4</option>
                    </select>

                </div>
                <div class="clear"> </div>

                <div class="cher"><input type="submit" onclick="myFunction()" value="Chercher"></div>
                <br CLEAR="all">
        </form>
        <h2>R�sultats de votre recherche :</h2>
        <table class="responstable">
            <tr>
                <th>Conducteur</th>
                <th>Nom</th>
                <th>Pr�nom</th>
                <th>Email</th>
                <th>N� T�l</th>
            </tr>

            <tr>
                <td>Steve</td>
                <td>Foo</td>
                <td>01/01/1978</td>
                <td>Policyholder</td>
                <td>Policyholder</td>
            </tr>

            <tr>
                <td>Steffie</td>
                <td>Foo</td>
                <td>01/01/1978</td>
                <td>Spouse</td>
                <td>Policyholder</td>
            </tr>

            <tr>
                <td>Stan</td>
                <td>Foo</td>
                <td>01/01/1994</td>
                <td>Son</td>
                <td>Policyholder</td>
            </tr>

            <tr>
                <td>Stella</td>
                <td>Foo</td>
                <td>01/01/1992</td>
                <td>Daughter</td>
                <td>Policyholder</td>
            </tr>

        </table>

        <div class="clear"></div>
    </div>
</body>
</html>


