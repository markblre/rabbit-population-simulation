# Simulation de la croissance d'une population de lapins

Ce projet a été réalisé dans le cadre de ma deuxième année de licence informatique.  

L'objectif du projet était de construire une simulation de la croissance d'une population de lapins en utilisant un générateur de nombre pseudo-aléatoires de haute qualité. Des statistiques complètes sur l'expérience étaient attendues.

## Utiliser la simulation

### Choix des paramètres de la simulation
Deux paramètres sont réglables avant le lancement de la simulation : 
- **Le nombre d'expériences indépendantes** : C'est le nombre de simulations qui seront faites durant l'expérience.
- **La durée en mois d'une simulation**

Ces deux paramètres sont modifiables dans le fichier ```Main.java``` (lignes 10 et 11).

### Compiler
```javac -sourcepath ./src/ -d ./out/ ./src/Main.java```

### Exécuter
```java -cp ./out/ Main```
