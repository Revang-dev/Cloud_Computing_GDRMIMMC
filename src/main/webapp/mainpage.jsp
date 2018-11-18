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
            background-color : 	#00008B ;
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
        var baseURL = window.location.href


        function postaccount() {
                   var xhr = new XMLHttpRequest();
                   var url = baseURL+ "creation";
                   xhr.open("POST", url, true);
                   xhr.setRequestHeader("Content-type", "application/json");
                   var data = JSON.stringify({ "Action" : "addUser", "Body": { "userID":document.getElementById('username').value, "pass": document.getElementById('mdp').value } });
                   xhr.send(data);
                   xhr.onreadystatechange = function() {
                       if (this.readyState == 4 && this.status == 200) {
                           alert(this.responseText);
                       }
                   };
               }


         function postaccountrank() {
                            var xhr = new XMLHttpRequest();
                            var url = baseURL+ "creation";
                            console.log(url);
                            xhr.open("POST", url, true);
                            xhr.setRequestHeader("Content-type", "application/json");
                            var data = JSON.stringify({ "Action" : "addUser", "Body": { "userID":document.getElementById('username2').value, "pass": document.getElementById('mdp2').value, "level": document.getElementById('rank').value } });
                            xhr.send(data);
                            xhr.onreadystatechange = function() {
                                if (this.readyState == 4 && this.status == 200) {
                                    alert(this.responseText);
                                }
                            };
                        }

         function connexion() {
                var xhr = new XMLHttpRequest();
                var url = baseURL+ "connexion";
                console.log(url);
                xhr.open("POST", url, true);
                xhr.setRequestHeader("Content-type", "application/json");
                var data = JSON.stringify({ "Action" : "Connexion", "Body": { "userID":document.getElementById('username3').value, "pass": document.getElementById('mdp3').value }});
                xhr.send(data);
                xhr.onreadystatechange = function() {
                   if (this.readyState == 4 && this.status == 200) {
                           alert(this.responseText);
                   }
                };
           }


          function uploadFile(){
            var xhr = new XMLHttpRequest();
            var url = baseURL+ "upload";
            console.log(url);
            xhr.open("POST", url, true);
            xhr.setRequestHeader("Content-type", "application/json");
            var data = JSON.stringify({ "Action" : "Upload", "Body": { "userID":document.getElementById('username4').value, "filePath": document.getElementById('path').value, "fileSize": document.getElementById('size').value,"type": document.getElementById('type').value, "name": document.getElementById('name').value }});
            xhr.send(data);
            xhr.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                      alert(this.responseText);
                }
            };
          }


          function downloadNoobFile(){
                      var xhr = new XMLHttpRequest();
                      var url = baseURL+ "ndownload";
                      console.log(url);
                      xhr.open("POST", url, true);
                      xhr.setRequestHeader("Content-type", "application/json");
                      var data = JSON.stringify({ "Action" : "download", "Body": { "mail":document.getElementById('mail2').value, "fileName": document.getElementById('fileName2').value }});
                      xhr.send(data);
                      xhr.onreadystatechange = function() {
                      if (this.readyState == 4 && this.status == 200) {
                                alert(this.responseText);
                          }
                      };
                    }

        function downloadCasualLeetFile(){
                      var xhr = new XMLHttpRequest();
                      var url = baseURL+ "cldownload";
                      console.log(url);
                      xhr.open("POST", url, true);
                      xhr.setRequestHeader("Content-type", "application/json");
                      var data = JSON.stringify({ "Action" : "download", "Body": { "mail":document.getElementById('mail2').value, "fileName": document.getElementById('fileName2').value }});
                      xhr.send(data);
                      xhr.onreadystatechange = function() {
                      if (this.readyState == 4 && this.status == 200) {
                                alert(this.responseText);
                          }
                      };
                    }



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
   Username: <input type="text" name="enter" class="enter" value="rydy@unice.fr" id="username2"><br><br>
   Passeword: <input type="text" name="enter" class="enter" value="1234" id="mdp2"><br><br>
   Rank: <input type="text" name="enter" class="enter" value="Leet" id="rank"><br><br>
   <input type="submit"  value="Inscrire" onclick="postaccountrank();"/>


<h2> Connexion </h2>
   Username: <input type="text" name="enter" class="enter" value="rydy@unice.fr" id="username3"><br><br>
   Passeword: <input type="text" name="enter" class="enter" value="1234" id="mdp3"><br><br>
   <input type="submit"  value="Connexion" onclick="connexion();"/>

<h2>Upload un fichier</h2>
        Username: <input type="text" name="enter" class="enter" value="rydy@unice.fr" id="username4"><br><br>
        Chemin du fichier: <input type="text" name="enter" class="enter" value="User/Desktop/photo.jpg" id="path"><br><br>
        Taille du fichier: <input type="text" name="enter" class="enter" value="5" id="size"><br><br>
        Type: <input type="text" name="enter" class="enter" value="image" id="type"><br><br>
        Nom: <input type="text" name="enter" class="enter" value="video0" id="name"><br><br>
        <input type="submit"  value="Upload" onclick="uploadFile();"/>

<h2>Téléchager un fichier in Noob Queue</h2>
        Mail: <input type="text" name="enter" class="enter" value="rydy@unice.fr" id="mail2"><br><br>
        File name: <input type="text" name="enter" class="enter" value="video0" id="fileName2"><br><br>
        <input type="submit"  value="Download" onclick="downloadNoobFile();"/>

<h2>Téléchager un fichier in Casual and Leet Queue</h2>
        Mail: <input type="text" name="enter" class="enter" value="rydy@unice.fr" id="mail2"><br><br>
        File name: <input type="text" name="enter" class="enter" value="video0" id="fileName2"><br><br>
        <input type="submit"  value="Download" onclick="downloadCasualLeetFile();"/>


</div>

</body>
</html>