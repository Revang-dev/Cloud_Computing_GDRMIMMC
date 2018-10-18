# Cloud_Computing_GDRMIM
Depot git du projet Cloud Computing de Mohamed Chennouf, Meersman Rudy, Duminy Gaetan et Ivan Picard Marchetto

# JSON BODY
> ```{userID ,videoPath ,videoSize ,dateRequest}```
 
# Noob : Push.Queue
>Plus simple à gérer à cause des demandes uniques du « Noob ». On sait si une personne a déjà upload. Si on mélange tous les types de compte dans une Push.Queue il y aura un problème. Si la personne peut faire du multi upload c’est bon mais si c’est un noob, le temps que la requête aboutisse à l’envoie du mail et donc la mise à jour des donnée de l’utilisateur, celui-ci peut facilement envoyer une autre demande entre temps qui sera faite par le système. 

# Autre : Pull.Queue 
> Grace à celle-ci nous pouvons faire en sorte que le système gère facilement les requêtes et en parallèle d’une autre. De plus, vu que les deux type d’utilisateur (un seul upload vs multi upload) sont différencié par le type de queue, pas besoin de traiter le cas du « Noob ». Le système pourra mettre à jour facilement les données du user ou les regarder afin de faire attention aux limites imposé par le statut du compte.

# User Story 
-	Bob veut upload un fichier et possède le statut de Noob
-	Il se connecte au service avec son identifiant
-	Il fait une requête au système avec le path de la vidéo
-	Le system prend la requête en compte et upload le fichier sur le google cloud
-	Une fois uploadé, le système envoie à Bob un mail de confirmation avec le lien vers son fichier
-	Le Système calcul le nombre de point que Bob à gagner grâce à la taille du fichier uploadé

# Architecture
 > voir le Architecure.pnj
