
## 🍽️ BatiCuisine - Application d'estimation des coûts de construction des cuisines
Description rapide :
BatiCuisine est une application Java destinée aux professionnels de la construction et de la rénovation de cuisines. Elle permet d'estimer le coût des travaux en prenant en compte les matériaux, la main-d'œuvre, les équipements, et les taxes.



## ⚙️ Fonctionnalités principales
1. Gestion des Projets 🏠
Créer, afficher et modifier des projets de construction ou de rénovation.
Associer un client à chaque projet.
Calculer le coût total des projets en prenant en compte la TVA, les marges bénéficiaires, et les coûts des matériaux et main-d'œuvre.
2. Gestion des Matériaux et Main-d'œuvre 🛠️
Ajouter, modifier et gérer des matériaux utilisés dans les projets.
Calculer les coûts de la main-d'œuvre en fonction des heures travaillées, du taux horaire et de la productivité.
3. Gestion des Clients 👤
Enregistrer et gérer des clients (particuliers ou professionnels).
Appliquer des remises spécifiques en fonction du type de client (professionnel ou particulier).
4. Génération de Devis 📄
Créer des devis détaillés avant les travaux avec des dates d'émission et de validité.
Afficher les coûts estimés des matériaux, main-d'œuvre et autres composants.

## 🗂️ Architecture du Projet
Le projet suit une architecture basée sur le modèle DAO (Data Access Object) pour l'accès aux données, les Services pour la logique métier, et un Repository pour une gestion centralisée des données. Voici un aperçu des couches principales du projet :

1. DAO (Data Access Object)
La couche DAO est responsable de la gestion des données et de la persistance des objets métiers. Elle s'occupe de l'accès aux données et de la manipulation des entités de base comme les projets, les clients, les matériaux, etc.

2. Repository 
Le Repository permet de centraliser les accès aux données en encapsulant la logique des requêtes SQL ou ORM. Il sert d'intermédiaire entre la couche DAO et le reste de l'application.

3. Service
La couche Service contient la logique métier de l'application. Elle orchestre les appels aux DAO et aux repositories pour répondre aux besoins de l'application tout en respectant les règles de gestion.


## 📂 Contenu de l'application

1. Gestion des Projets 🏠
Ajouter un client : Associe un client à un projet.
Ajouter des matériaux et main-d'œuvre : Gère les composants du projet.
Calculer les coûts : Calcule le coût total du projet en prenant en compte les matériaux, la main-d'œuvre, les marges et la TVA.

2. Gestion des Matériaux et Main-d'œuvre 🛠️
Matériaux : Gestion des coûts des matériaux avec prise en compte de la qualité et du transport.
Main-d'œuvre : Calcule les coûts basés sur le taux horaire, les heures travaillées et la productivité.

5. Gestion des clients
Enregistrer les informations du client : Ajoute des informations de base sur les clients (nom, adresse, téléphone, type).
Remises et TVA : Applique des remises en fonction du type de client (professionnel ou particulier).

7. Création de devis
Génère un devis estimant les coûts avant le début des travaux.
Inclut les matériaux, la main-d'œuvre, la TVA, et la marge bénéficiaire.

9. Calcul des coûts
Prend en compte les coûts des composants (matériaux, main-d'œuvre) et applique une marge bénéficiaire pour obtenir le coût final.

## 🚀 Comment exécuter le projet
Cloner le dépôt :

git clone (https://github.com/Douaa1819/BatiCuisine.git)



## 📝 Licence
Ce projet est sous licence MIT. Voir le fichier LICENSE pour plus de détails.


## 📚 Exemple d'utilisation

=== Bienvenue dans BatiCuisine ===
1. Créer un nouveau projet
2. Afficher les projets existants
3. Calculer le coût d'un projet
4. Quitter
Choisissez une option : 1
L'utilisateur peut suivre les étapes pour ajouter un client, des matériaux, et de la main-d'œuvre, puis calculer le coût total du projet avec la TVA et les marges bénéficiaires appliquées.



## Planification

Pour suivre la planification et les tâches en cours, veuillez consulter notre tableau de bord Jira : [Planification BatiCuisine](https://douaa123.atlassian.net/jira/software/c/projects/BC/boards/8/backlog)



## Presenattion

 [Presentation BatiCuisine](https://www.canva.com/design/DAGRluMyKHQ/zxiKguEgDM12ZpsvVe3QtQ/edit?utm_content=DAGRluMyKHQ&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton)
