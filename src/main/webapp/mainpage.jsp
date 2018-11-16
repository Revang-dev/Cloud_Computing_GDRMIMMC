<!DOCTYPE html>
<!--
  Copyright 2016 Google Inc.
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at
        http://www.apache.org/licenses/LICENSE-2.0
  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>




<head>
    <style type="text/css">

        body
        {
            background-color : brown ;
            color : white
        }


        h1
        {
            text-align: center;
        }

        h2
        {
            color : red;
        }



        div
        {
            width: 50%;
            margin: auto;
            margin-top : 10%;
        }

    </style>

    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
    <script type="text/javascript">

        var username;
        var email;
        var accountlevel;
        var fichier;
        var length;

     </script>
</head>

<body>
<h1  id="to" >Bienvenue sur notre site de partage de fichier </h1>
<div>

<h2>Créer un utilisateur</h2>
   Username: <input type="text" name="enter" class="enter" value="rydy@unice.fr" id="username"><br><br>
   Passeword: <input type="text" name="enter" class="enter" value="1234" id="mdp"><br><br>
   <input type="submit"  value="Inscrire" onclick="postaccount();"/>

<h2>Créer un utilisateur avec rank </h2>
   Username: <input type="text" name="enter" class="enter" value="rydy@unice.fr" id="username"><br><br>
   Passeword: <input type="text" name="enter" class="enter" value="1234" id="mdp"><br><br>
   Rank: <input type="text" name="enter" class="enter" value="noob" id="rank"><br><br>
   <input type="submit"  value="Inscrire" onclick="postaccountrank();"/>

<h2>Poster un fichier</h2>
        Username: <input type="text" name="enter" class="enter" value="Jean Michel" id="username"><br><br>
        Fichier: <input type="text" name="enter" class="enter" value="video1" id="video"><br><br>
        Taille du fichier: <input type="text" name="enter" class="enter" value="70" id="length"><br><br>
        <input type="submit"  value="Poster" onclick="postvideo();"/>

<h2>Téléchager un fichier</h2>
        Username: <input type="text" name="enter" class="enter" value="Jean Michel" id="usernameconv"><br><br>
        Fichier: <input type="text" name="enter" class="enter" value="video1" id="videoconv"><br><br>
        <input type="submit"  value="Convertir" onclick="postconvertvideo();"/>

</div>

</body>
</html>