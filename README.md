# Systeme-multi-agents_Projet-Batiments
L'objectif de ce projet est de réaliser une application JAVA implémentant le Framework JADE permettant de réaliser un système multi-agent, dont des agents communiquent entre eux en s'envoyant des messages.

Ici les agents sont présentés par des rectangles (Batiments) dont les positions sont générées aléatoirement ce qui engendre des collisions entre eux, l'objectif par la suite est pour chaque "Batiment" de reconnaître les autres "Batiments" qui sont en collision avec lui et de leur "demander" de se pousser, le batiment recevant un tel message doit tenter de se pousser, à petit pas, vers une direction qui évite la collision ou la minimise.

Manuel d'utilisation :
----------------------
Il faut ajouter le fichier jade.jar aux librairies du projet sous Eclipse/Netbeans
La classe principale est (IHM)
- Les touches clavier + et – permettent de d'agrandir / diminuer les dimentions des rectangles
- Les 4 touches flèches : droite, gauche, haut et bas font bouger la caméra
