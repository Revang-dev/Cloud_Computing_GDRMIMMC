# Cloud_Computing_GDRMIM
Depot git du projet Cloud Computing de Mohamed Chennouf, Meersman Rudy, Duminy Gaetan et Ivan Picard Marchetto

# Delivrable 1
19/10/18:
* Architecure.pdf

# Delivrable 2
02/11/18:
* Couche de Donnée.pdf

# Rendu final

## Lancer le projet : 

Se mettre à la racine du projet et lancer : 
* mvn clean install
* mvn appengine:deploy

## Les requetes possibles:
* La page principale
* Le fichier à la racine contenant plusieurs requetes POSTMAN :
    * /purge (GET)
    * /creation (POST)
    * /connexion (POST)
    * /upload (POST)
    * /ndownload (POST)
    * /cldownload (POST)
    * /leaderboard (GET)
* Lancement de la supression des fichiers expirés à l'aide d'un script **cron** avec Cloud Scheduler

## Les BD
* Consulter Google Cloud Platform -> Datastore -> pour les entitées : users et info_files
* Consulter Google Cloud Platform -> Stockage + Bucket : brave-sonar-218511... pour les entitées : files

## Problèmes rencontrés
* Le lien de download n'a pas d'expiration car lors de la génération de lien minuté, il nous fallait une clé ou key.json. Chose que l'on a pas trouvé. (Implémenté mais pas appelé)
* Le poids du fichier est limité pour le upload, on a pas eu le temps d'intégré un partitioneur de fichier.
