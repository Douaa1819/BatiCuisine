# 🍽️ BatiCuisine - Estimation des coûts de construction de cuisines

**BatiCuisine** est une application Java permettant aux professionnels d'estimer le coût des travaux de cuisine en prenant en compte les matériaux, la main-d'œuvre, les équipements, et les taxes.

## ⚙️ Fonctionnalités principales
- **Gestion des Projets** : Créer, afficher et modifier des projets, associer des clients, calculer les coûts (matériaux, main-d'œuvre, TVA, marges).
- **Gestion des Matériaux et Main-d'œuvre** : Ajouter et gérer les matériaux, calculer les coûts de main-d'œuvre (heures travaillées, taux horaire).
- **Gestion des Clients** : Enregistrer et gérer des clients (professionnels ou particuliers), appliquer des remises.
- **Génération de Devis** : Créer des devis détaillés incluant matériaux, main-d'œuvre, TVA, et marges bénéficiaires.

## 🗂️ Architecture du Projet
- **DAO (Data Access Object)** : Gestion des accès aux données (clients, projets, matériaux).
- **Repository** : Centralisation des accès aux données, gestion des requêtes.
- **Service** : Logique métier, gestion des appels aux DAO et repositories.

## 📂 Contenu de l'application
1. **Projets** : Ajouter des clients, matériaux, et calculer les coûts.
2. **Matériaux & Main-d'œuvre** : Gestion des coûts et calculs des marges.
3. **Clients** : Gérer les informations, appliquer des remises selon le type de client.
4. **Devis** : Génération de devis détaillés avant travaux.

## 🚀 Exécution du projet
1-Cloner le dépôt et exécuter le projet :
git clone https://github.com/Douaa1819/BatiCuisine.git

2-Naviguer vers le répertoire de sortie :
cd Bati-Cuisine/out/artifacts/Bati_Cuisine_jar

3-Exécuter le fichier .jar :
java -jar Bati-Cuisine.jar

## 📝 Licence
Ce projet est sous licence MIT. Voir le fichier [LICENSE](LICENSE) pour plus de détails.

## 📚 Exemple d'utilisation

=== Bienvenue dans BatiCuisine ===
1. Créer un nouveau projet
2. Afficher les projets existants
3. Calculer le coût d'un projet
4. Quitter
   
## 📅 Planification
Pour suivre la planification et les tâches en cours, veuillez consulter notre tableau de bord Jira : [Planification BatiCuisine](https://douaa123.atlassian.net/jira/software/c/projects/BC/boards/8/backlog)

## 🎤 Présentation
Pour une présentation complète du projet, consultez : [Présentation BatiCuisine](https://www.canva.com/design/DAGRluMyKHQ/zxiKguEgDM12ZpsvVe3QtQ/edit?utm_content=DAGRluMyKHQ&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton)

