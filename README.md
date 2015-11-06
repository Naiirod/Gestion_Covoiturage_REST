# Gestion_Covoiturage_REST
Projet AOS REST de gestion de covoiturage


Réalisateurs:
Dorian Davi et Maxime Brecheteau


Objectif:
Lorsqu'un groupe d'amis mange tous les midi à l'extérieur de son lieu de travail, il est nécessaire de prévoir les trajets en voiture.
Pour cela, chacun prendra à son tour sa voiture pour éviter que l'un d'eux ne prenne sa voiture plus souvent que les autres.
Cependant il arrive que le groupe ne soit pas au complet lors d'un trajet (une absence, une obligation ou un imprévu).
Pour que personne ne se voit prendre sa voiture alors qu'elle n'a pas participé a tous les trajets ou qu'une autre prenne la sienne alors qu'il est toujours présent, un principe a été élaboré et est mis a disposition par ce projet.


Calculs:
Un ratio est calculé de la manière suivante : 2 variables sont prises en compte - le nombre de trajet auquel une personne a participé comprenant la position de conducteur et la position de passager -> NBTRAJETTOTAL & le nombre de trajet durant lequel une personne a conduit -> NBTRAJETCONDUIT.
Ratio = NBTRAJETCONDUIT / NBTRAJETTOTAL
L'individu ayant le plus petit ratio et participant au trajet concerné se doit de prendre son véhicule.


Fonctionnement du projet:
Le projet répond à ce principe
Une interface client permet à un utilisateur de :
- visualiser la liste des conducteurs créés dans l'outil.
- créer des conducteurs en saisissant son nom dans un formulaire.
- incrémenter le nombre de trajet participé et celui de trajet conduit par un utilisateur à l'aide de son identifiant.
- en cas d'erreur de saisie, il est possible de corriger ces compteurs en les décrémentant.
- visualiser le ratio minimum indiquant le conducteur associé afin de présenter aux utilisateurs le prochain conducteur.


Mise en place du projet:
Vous trouverez dans le même répertoire que ce fichier texte 2 répartoires : "Workspace Client" & "Workspace Serveur"
Respectivement le client et le serveur du projet contenant :
- Les fichiers HTML du client
&
- Emplacement webapps contenent : GestionCovoiturage.war et GestionCovoiturage/
- Projet GestionCovoiturage : le répertoire comportant le serveur java.

Afin de faire fonctionner ce projet le contexte suivant a été mis en place sur ma machine :
- Les fichiers HTML du client se trouvaient dans le répertoire "webapps/workspace" de Tomcat.
- Le projet Eclipse du serveur était situé en local dans le workspace dédié sur ma machine.
- Le fichier WAR du projet ainsi que le répertoire associé étant situés dans le répertoire "webapps" de Tomcat.

L'application est appelée en local par l'url suivante : http://localhost:8080/workspace/client_covoiturage.html
