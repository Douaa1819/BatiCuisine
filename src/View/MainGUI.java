package View;
import utils.ValidationUtils;

public class MainGUI {



    public static void afficherMenuPrincipal() {
        System.out.println("=== Bienvenue dans l'application de gestion des projets de rénovation de cuisines ===");
        boolean continuer = true;

        while (continuer) {
            System.out.println("\n=== Menu Principal ===");
            System.out.println("1. Créer un nouveau projet");
            System.out.println("2. Afficher les projets existants");
            System.out.println("3. Calculer le coût d'un projet");
            System.out.println("4. Quitter");
            System.out.print("Choisissez une option : ");

            int choix = ValidationUtils.readInt();
            ValidationUtils.scanner.nextLine();

            switch (choix) {
                case 1:
                    creerNouveauProjet();
                    break;
                case 2:
                    afficherProjetsExistants();
                    break;
                case 3:
                    calculerCoutProjet();
                    break;
                case 4:
                    continuer = false;
                    System.out.println("Merci d'avoir utilisé l'application. Au revoir !");
                    break;
                default:
                    System.out.println("Veuillez choisir une option valide.");
            }
        }
    }

    public static void creerNouveauProjet() {
        System.out.println("\n--- Recherche de client ---");
        System.out.println("Souhaitez-vous chercher un client existant ou en ajouter un nouveau ?");
        System.out.println("1. Chercher un client existant");
        System.out.println("2. Ajouter un nouveau client");
        System.out.print("Choisissez une option : ");

        int choixClient = ValidationUtils.readInt();
        ValidationUtils.scanner.nextLine();

        switch (choixClient) {
            case 1:
                rechercherClient();
                break;
            case 2:
                ajouterNouveauClient();
                break;
            default:
                System.out.println("Veuillez choisir une option valide.");
        }
    }

    public static void rechercherClient() {
        System.out.println("\n--- Recherche de client existant ---");
        System.out.print("Entrez le nom du client : ");
        String nomClient = ValidationUtils.readString();

        if ("Mme Dupont".equalsIgnoreCase(nomClient)) {
            System.out.println("Client trouvé !");
            System.out.println("Nom : Mme Dupont");
            System.out.println("Adresse : 12 Rue des Fleurs, Paris");
            System.out.println("Numéro de téléphone : 06 12345678");

            System.out.print("Souhaitez-vous continuer avec ce client ? (y/n) : ");
            String reponse = ValidationUtils.readString();
            if ("y".equalsIgnoreCase(reponse)) {
                creerProjetPourClient("Mme Dupont");
            }
        } else {
            System.out.println("Client non trouvé.");
        }
    }

    public static void ajouterNouveauClient() {
        System.out.println("\n--- Ajout d'un nouveau client ---");
        System.out.print("Entrez le nom du client : ");
        String nomClient = ValidationUtils.readValidName();
        System.out.print("Entrez l'adresse du client : ");
        String adresseClient = ValidationUtils.readString();
        System.out.print("Entrez le numéro de téléphone du client : ");
        String numeroTelephone = ValidationUtils.readString();

        System.out.println("Nouveau client ajouté avec succès !");
        System.out.println("Nom: " + nomClient);
        System.out.println("Adresse: " + adresseClient);
        System.out.println("Téléphone: " + numeroTelephone);
    }

    public static void creerProjetPourClient(String nomClient) {
        System.out.println("\n--- Création d'un Nouveau Projet ---");
        System.out.print("Entrez le nom du projet : ");
        String nomProjet = ValidationUtils.readValidName();
        System.out.print("Entrez la surface de la cuisine (en m²) : ");
        double surfaceCuisine = ValidationUtils.readDouble();

        ajouterMateriaux();
    }

    public static void ajouterMateriaux() {
        System.out.println("\n--- Ajout des matériaux ---");
        boolean ajouterAutre = true;

        while (ajouterAutre) {
            System.out.print("Entrez le nom du matériau : ");
            String nomMateriau = ValidationUtils.readString();
            System.out.print("Entrez la quantité de ce matériau : ");
            double quantite = ValidationUtils.readDouble();
            System.out.print("Entrez le coût unitaire de ce matériau (€) : ");
            double coutUnitaire = ValidationUtils.readDouble();
            System.out.print("Entrez le coût de transport de ce matériau (€) : ");
            double coutTransport = ValidationUtils.readDouble();
            System.out.print("Entrez le coefficient de qualité du matériau (1.0 = standard, > 1.0 = haute qualité) : ");
            double coefficientQualite = ValidationUtils.readDouble();

            System.out.println("Matériau ajouté avec succès !");
            System.out.print("Voulez-vous ajouter un autre matériau ? (y/n) : ");
            ajouterAutre = "y".equalsIgnoreCase(ValidationUtils.readString());
        }

        ajouterMainDoeuvre();
    }

    public static void ajouterMainDoeuvre() {
        System.out.println("\n--- Ajout de la main-d'œuvre ---");
        boolean ajouterAutre = true;

        while (ajouterAutre) {
            System.out.print("Entrez le type de main-d'œuvre : ");
            String typeMainDoeuvre = ValidationUtils.readString();
            System.out.print("Entrez le taux horaire de cette main-d'œuvre (€) : ");
            double tauxHoraire = ValidationUtils.readDouble();
            System.out.print("Entrez le nombre d'heures travaillées : ");
            double heuresTravaillees = ValidationUtils.readDouble();
            System.out.print("Entrez le facteur de productivité (1.0 = standard, > 1.0 = haute productivité) : ");
            double facteurProductivite = ValidationUtils.readDouble();

            System.out.println("Main-d'œuvre ajoutée avec succès !");
            System.out.print("Voulez-vous ajouter un autre type de main-d'œuvre ? (y/n) : ");
            ajouterAutre = "y".equalsIgnoreCase(ValidationUtils.readString());
        }

        calculerCoutTotal();
    }

    public static void calculerCoutTotal() {
        System.out.println("\n--- Calcul du coût total ---");
     //lOGIQUE pour calculer
    }

    public static void afficherProjetsExistants() {
        System.out.println("\n--- Afficher les projets existants ---");
        // Logique pour afficher les projets existants
    }

    public static void calculerCoutProjet() {
        System.out.println("\n--- Calculer le coût d'un projet ---");
        // Logique pour calculer le coût d'un projet déjà existant
    }
}
