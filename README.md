# Plateforme de réservation de services à domicile

Ce projet est une API permettant de gérer les prestataires de services, les services offerts, et les réservations. Il inclut des fonctionnalités telles que la recherche de prestataires, la prise de 
rendez-vous, et les évaluations.

# Fonctionnalités principales

-Recherche de prestataires : Filtrer les prestataires selon les services offerts.

-Prise de rendez-vous : Permettre aux utilisateurs de réserver un service auprès d'un 
prestataire.

-Évaluations : Système de notation et d'évaluation des services fournis.

# Prérequis

Avant de commencer, assurez-vous d'avoir installé les éléments suivants :

    •Java 17
    • Un IDE (environnement de développement intégré) de préférence Intelij. 
    •PostgreSQL
    •Postman (pour tester les points d'API)

# Technologies utilisées

    -Backend: Spring Boot (Java)
    -Base de données : PostgreSQL
    -Authentication: JWT (JSON Web Tokens)
    -Documentation API : Swagger et Postman

# Installation

Suivez les étapes ci-dessous pour exécuter le projet sur votre machine locale.

# Etape 1 : Cloner le dépôt sur votre machine locale à l'aide de la commande suivante : 
    Ouvrez votre terminal et placer vous dans le repertoire souhaité et copier le code suivant : git clone https://github.com/nom-utilisateur/plateforme-reservation.git

    Tapez la commande suivante pour entrez dans le repertoire du projet : cd plateforme-reservation

# Pour un projet Java (Spring Boot) :

Assurez-vous que Maven est installé, puis exécutez la commande suivante :

    mvn clean install


# Etape 2 : Configurer la base de données

Assurez-vous que PostgreSQL est installé et en cours d'exécution sur votre machine.
Vous devez configurer la connexion à la base de données dans le fichier de configuration 
application.property . 

    Etape 4 : Exécuter le projetn avec la commande suivante mvn spring-boot:run
    Étape 5 : Accéder à l'API
Une fois le serveur en cours d'exécution, vous pouvez accéder aux différentes documentation de l'API.

    Documentation Swagger : http://localhost:8080/swagger-ui/index.html

    Documentation Postman : https://documenter.getpostman.com/view/7602674/2sAXqqd3L2


# Contribuer

Les contributions sont les bienvenues ! Suivez ces étapes pour contribuer :

    1. Cloner le projet.
    2. Créez une branche pour votre fonctionnalité (git checkout -b feature-nom).
    3. Commitez vos modifications (git commit -m 'Ajout de ma fonctionnalité').
    4. Poussez la branche (git push origin feature-nom).
    5. Ouvrez une Pull Request.