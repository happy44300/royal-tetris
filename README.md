# royal-tetris
Ce projet a pour but de créer un jeu de tetris jouable en multijoueur, sur une seule grille.

Ce projet utilise Gradle et Java 17.

Afin de cloner ce projet, il faut utiliser le lien suivant : https://github.com/happy44300/royal-tetris.git

Afin de voir le jeu en fonctionnement avec 4 joueurs, voici le lien d'une vidéo : https://www.youtube.com/watch?v=rhZEJxGQlCQ

Pour lancer le jeu soi-même :
1) Lancer le côté serveur, cad la classe TetrisGame (ne pas oublier le parametre de lancement qui correspond au nombre de joueur, 4 maximum)
2) Lancer autant de clients que de joueur, en commençant par le Client1 puis Client2, etc. jusqu'à avoir autant de client lancés que de joueurs désirés
3) Le jeu devrait se lancer, la partie est perdue quand l'interface affiche une grille verte de 4x4