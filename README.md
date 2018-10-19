# Cloud_Computing_GDRMIM
Depot git du projet Cloud Computing de Mohamed Chennouf, Meersman Rudy, Duminy Gaetan et Ivan Picard Marchetto

## Architecture

 ![architecture](/image/archi.pdf)

Dans cette architecture, nous avons 5 composants : 

<b>Download</b>: Ce module télécharge les fichiers demandés par l'utilisateur. Il contient une API prenant l'email de l'utilisateur et l'identifiant du fichier à télécharger. Suivant le type utilisateur (Noob, Casual, Leet), celui-ci va mettre les requêtes dans une queue appropriée :

<ul>
<li> Les files d'attente Push exécutent des taches en transmettant des demandes aux "workeurs". Elles envoient leurs demandes à un rythme fiable et régulier et garantissent une exécution fiable des tâches. Ces taches sont traitées les unes après les autres, dans l'ordre de leur arrivée. Il est possible de contrôler le taux d'envoi des taches à partir de la file d'attente, nous permettant de minimiser le nombre de "workeur". Ce contrôle du nombre de workeur nous permet de minimiser les couts. C'est pourquoi nous avons choisi une push queue pour les "Noob" car leurs demandes sont traités séquencielement et chaque minute les utilisateurs ne peuvent faire qu'une seule demande.

<li> Les files d'attente Pull donnent plus de flexibilité quant au moment où les taches sont traitées. Ces fils d'attente fonctionnent bien lorsque l'on doit regrouper des taches pour une exécution efficace. Les pulls queue permettent aussi de prioriser certaines taches car contrairement à la push queue, on peut choisir l'ordre auquel les taches vont être exécuté. Grâce aux pull queue, on peut grouper deux taches (download 1+download 2) et les exécuter en même temps. Un workeur du pull queue pourrait se réveiller périodiquement toutes les 1 min pour exécuter les deux opérations des "Casual". De même, un autre workeur pourrait toutes les min exécuter les 4 opérations des "Leet". C'est pour cela que nous avons choisi deux pull queue, une pour les "Leet" et une pour les "Casual".
</ul> 

Une fois la tache exécutée par les workeurs, Le module download appelle le module Mail pour envoyer un mail avec le lien du fichier à télécharger.

<b>Upload</b>: Ce composant se charge d'upload les fichiers. Ce module a une API qui prend l'id de l'utilisateur(email) et la taille du fichier à upload et charge un fichier de la taille demandé dans la base de donné. Une fois upload, le module appelle le composant Mail pour envoyé le récapitulatif plus le lien à télécharger. Ce module met aussi à jour la base de donné des utilisateurs en incrémentant le score de la personne.

<b>Account</b>: Ce module permet des creer ou supprimer un compte utilisateur.

<b>LeaderBoard</b>: Ce module récupère tous utilisateurs et les classent par score.

<b>Mail</b>: Ce module se charge d'envoyer des mails.


<b>Résumé des queues</b>:
Nous avons donc 3 queues pour gérer les différentes requêtes :
    
    - une push queue pour les noobs.
 Les téléchargements demandées par les "Noob" sont mises à la chaîne à l’intérieur et traitée dans l’ordre où elles ont été émises.
    
    - une pull queue pour les Casual
 Elle permet de traiter les demandes de téléchargement 2 par 2 maximum pour un utilisateur Casual. Si cette queue est surchargée de l’ordre du double de la queue des "Leet", les prochains "Casual" seront mis dans la queues des "Leet.
    
    - une pull queue pour les Leet
 La queue réservé aux "Leet" traite leurs demandes 4 par 4 maximum, si la queue est surchargé, les "Leet" suivants seront traités dans la queue des "Casual".

## Elasticité

Nous allons mettre en place une élasticité horizontale, par augmentation du nombre de serveurs . Cela ne pose généralement pas de problème pour les infrastructures Web.

La difficulté se concentre davantage sur le code applicatif. « Il est possible d'allouer un process Java à un thread que l’on peut exécuter sur un cœur virtuel, mais pour répartir vraiment l'exécution d'une application complète sur plusieurs serveurs virtuels, cela reste compliqué ». Heuresement google est notre ami et gère tout cela avec le code suivant : 

 ```xml
<appengine-web-app xmlns="http://appengine.google.com/ns/1.0">
  <application>simple-app</application>
  <module>default</module>
  <version>uno</version>
  <threadsafe>true</threadsafe>
  <instance-class>F2</instance-class>
  <automatic-scaling>
    <min-idle-instances>4</min-idle-instances>
    <!-- ‘automatic’ is the default value. -->
    <max-idle-instances>8</max-idle-instances>
    <!-- ‘automatic’ is the default value. -->
    <min-pending-latency>30ms</min-pending-latency>
    <max-pending-latency>automatic</max-pending-latency>
    <max-concurrent-requests>50</max-concurrent-requests>
  </automatic-scaling>
</appengine-web-app>
 ```
 
Nous avons choisi d'avoir 4 instances afin de pouvoir gérer les 4 requêtes que peut faire le "Leet". Nous avons choisi d'avoir un scaling qui peut doubler notre charge  soit 8 instances au maximum.

 
 ## Calcul du coût
 
Le coût d'une instance F2 est indiqué à $0.10/h sur le site de google cloud.

Nous avons donc une base de 4 instance F2 qui tourne 24 heures sur 24 et 7 jours sur 7.
Le coût par mois est donc de : 0.10 * 4 * 24 * 31 = $297.60/mois. Lorsqu'un surplus d’utilisateur surchargent une instance, on scale et on ajoute une machine pour supporter la charge. On a un scaling qui peut au pire des cas doubler soit $595.20/mois.

On considère qu'au minimum nous avons 1000 utilisateurs, on souhaite être rentable à partir de ce nombre minimum estimé, donc cela nous donne : $0.60/mois au minimum par utilisateur.


# JSON BODY
> ```{userID ,videoPath ,videoSize ,dateRequest}```

# Noob :
 
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
