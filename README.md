**OpenClassRooms - Parcours : Développeur d'application Java**

**Projet n°3 : Mettez votre logique à l'épreuve** 

Le projet consiste à créer une application proposant 2 jeux de logique :
* Le Mastermind
* Recherche +/-

Dans notre contexte de travail, j'ai décidé de mettre le projet maven
La compilation se fait donc par la commande : ```mvn package``` 

Le fichier jar généré contient le code source mais égalmement l'intégralité des librairies pour fonctionner correctement.

**Pour lancer le jeu :**
- Sur Windows : 
1. Faire un ```mvn package```
2. Lancer une commande MS-DOS (cmd)
3. Se placer dans le répertoire /target
4. ```java -jar projet3-1.0-SNAPSHOT-jar-with-dependencies.jar```

**Configuration**

Pour activer ou non le mode développeur, Modifier le fichier config.properties en faisant :
- Activation du mode développeur:
    - Mastermind : ```mastermind.modeDebug = true```
    - Recherche +/- : ```recherche.modeDebug = true```
- désactiver le mode développeur "champs à vide": 
    - Mastermind : ```mastermind.modeDebug =  vide ``` 
    - Recherche +/- : ```recherche.modeDebug = vide ``` 