package View;
import Services.impl.ProjetServiceImpl;
import Services.interfaces.ClientService;
import Services.interfaces.MainOeuvreService;
import Services.interfaces.MateriauxService;
import Services.interfaces.ProjetService;
import enums.EtatProjet;
import models.Client;
import models.MainOeuvre;
import models.Materiaux;
import models.Projet;
import utils.ValidationUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static enums.TypeComposant.MAIN_D_OEUVRE;
import static enums.TypeComposant.MATERIAUX;

public class MainGUI {

    private static ClientService clientService;
    private static MateriauxService materiauxService ;
    private static MainOeuvreService mainOeuvreService ;
    public MainGUI(ClientService clientService,MateriauxService materiauxService,MainOeuvreService mainOeuvreService) {
        this.clientService = clientService;
        this.materiauxService = materiauxService;
        this.mainOeuvreService = mainOeuvreService;
    }




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
//                    calculerCoutProjet();
                    break;
                case 4:
//                    afficherClients();
                    break;

                case 5:
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
                creerNouveauProjet();
        }

    }


    public static void rechercherClient() {
        System.out.println("\n--- Recherche de client existant par nom ---");
//        System.out.println("\n--- Liste de Nos Clients ---");
//        afficherClients();

        System.out.print("Entrez le nom du client : ");
        String nomClient = ValidationUtils.readString();
        List<Client> clientsTrouves = clientService.getClientsByName(nomClient);
        if (clientsTrouves.isEmpty()) {
            System.out.println("Aucun client trouvé avec ce nom.");
        } else {
            System.out.println("Client(s) trouvé(s) :");
            clientsTrouves.forEach(client -> {
                System.out.println("ID : " + client.getId());
                System.out.println("Nom : " + client.getNom());
                System.out.println("Numéro de téléphone : " + client.getPhone());
                System.out.println();
            });

            System.out.print("Souhaitez-vous continuer avec l'un de ces clients ? (y/n) : ");
            String reponse = ValidationUtils.readString();

            if ("y".equalsIgnoreCase(reponse)) {
                System.out.print("Entrez l'ID du client avec lequel continuer : ");
                String clientIdString = ValidationUtils.readString();
                UUID clientId = UUID.fromString(clientIdString);
                clientService.getClientById(clientId).ifPresentOrElse(client -> {
                    creerProjetPourClient(client.getId());
                }, () -> System.out.println("Client non trouvé."));
            }
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
        System.out.print("Est-ce que ce client est professionnel ? (1 pour oui, 0 pour non, par défaut 0) : ");
        int choix = ValidationUtils.readInt();
        boolean estProfessionnel = (choix == 1);

        try {
            Client client = new Client(UUID.randomUUID(), nomClient, adresseClient, numeroTelephone, estProfessionnel);
            Client createdClient = clientService.createClient(client);

            System.out.println("Nouveau client ajouté avec succès !");
            System.out.println("Nom: " + createdClient.getNom());
            System.out.println("Adresse: " + createdClient.getAdress());
            System.out.println("Téléphone: " + createdClient.getPhone());
            System.out.println("ID: " + createdClient.getId());
            System.out.print("Souhaitez-vous continuer avec ce client ? (y/n) : ");
            String reponse = ValidationUtils.readString();
            if ("y".equalsIgnoreCase(reponse)) {
                creerProjetPourClient(createdClient.getId());
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de l'ajout du client : " + e.getMessage());
            e.printStackTrace();
        }

    }

    public static void afficherClients() {
        System.out.println("\n--- Affichage des clients ---");
        List<Client> clients = clientService.getAllClients();
        if (clients.isEmpty()) {
            System.out.println("Aucun client trouvé.");
        } else {
            for (Client client : clients) {
                System.out.println("ID: " + client.getId());
                System.out.println("Nom: " + client.getNom());
                System.out.println();
            }
        }
    }


    public static void creerProjetPourClient(UUID clientId) {
        System.out.println("\n--- Création d'un Nouveau Projet ---");
        System.out.println("Entrez le nom du projet : ");
        String nomProjet = ValidationUtils.readValidName();
        System.out.println("Entrez la surface de la cuisine (en m²) : ");
        double surfaceCuisine = ValidationUtils.readDouble();


        Projet projet = new Projet(UUID.randomUUID(), nomProjet, surfaceCuisine, 0.0, 0.0, EtatProjet.ENCOURS, 0.0, clientId);
        ProjetService projetService = new ProjetServiceImpl();

        try {
            projetService.ajouterProjet(projet);
            System.out.println("Projet créé avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la création du projet : " + e.getMessage());
            e.printStackTrace();
        }

        ajouterMateriaux(projet);
        ajouterMainOeuvre(projet);
    }


    public static void ajouterMateriaux(Projet projet) {
        System.out.println("\n--- Ajout des matériaux ---");
        while (true) {
            System.out.print("Entrez le nom du matériau : ");
            String nomMateriau = ValidationUtils.readString();
            System.out.println("Nom du matériau entré : " + nomMateriau);
            System.out.println("Entrez la quantité de ce matériau (en m²): ");
            double quantite = ValidationUtils.readDouble();
            System.out.println("Entrez le coût unitaire de ce matériau (€/m²) : ");
            double coutUnitaire = ValidationUtils.readDouble();
            System.out.println("Entrez le taux de tva (%) : ");
            double tva = ValidationUtils.readDouble();
            System.out.println("Entrez le coût de transport de ce matériau (€) : ");
            double coutTransport = ValidationUtils.readDouble();
            System.out.println("Entrez le coefficient de qualité du matériau (1.0 = standard, > 1.1= haute qualité) : ");
            double coefficientQualite = ValidationUtils.readDouble();
            try {
                Materiaux materiaux = new Materiaux(UUID.randomUUID(), nomMateriau, tva, MATERIAUX, projet.getId(), coutUnitaire, quantite, coutTransport, coefficientQualite);
                materiauxService.ajouterMateriaux(materiaux);
                System.out.println("Matériau ajouté avec succès !");
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }

            System.out.print("Voulez-vous ajouter un autre matériau ? (y/n) : ");
            String reponse = ValidationUtils.readString();
            if (!reponse.equalsIgnoreCase("y")) {
                ajouterMainOeuvre(projet);
                break;
            }
        }
    }


    public static void ajouterMainOeuvre(Projet projet) {
        System.out.println("\n--- Ajout de la main-d'œuvre ---");
        boolean ajouterAutre = true;
        while (ajouterAutre) {
            System.out.println("Entrez le nom du main-d'œuvre : ");
            String nomMainDoeuvre = ValidationUtils.readString();
            System.out.println("Entrez le taux de tva (%) : ");
            double tva = ValidationUtils.readDouble();
            System.out.print("Entrez le taux horaire de cette main-d'œuvre (€/h) : ");
            double tauxHoraire = ValidationUtils.readDouble();
            System.out.print("Entrez le nombre d'heures travaillées : ");
            double heuresTravail = ValidationUtils.readDouble();
            System.out.print("Entrez le facteur de productivité (1.0 = standard, > 1.1 = haute productivité) : ");
            double productivite = ValidationUtils.readDouble();
            try {

                MainOeuvre mainOeuvre = new MainOeuvre(UUID.randomUUID(), nomMainDoeuvre, tva, MAIN_D_OEUVRE, projet.getId(), tauxHoraire, heuresTravail, productivite);
                mainOeuvreService.ajouterMainOeuvre(mainOeuvre);
                System.out.println("Heures de travail: " + heuresTravail);
                System.out.println("Facteur de productivité: " + productivite);
                System.out.println("Main-d'œuvre ajoutée avec succès !");
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
            System.out.print("Voulez-vous ajouter un autre type de main-d'œuvre ? (y/n) : ");
            ajouterAutre = "y".equalsIgnoreCase(ValidationUtils.readString());
        }

        calculerCoutTotal(projet);
    }


    public static void calculerCoutTotal(Projet projet) {
        System.out.println("\n--- Calcul du coût total ---");

        // Demande de la TVA
        System.out.print("Souhaitez-vous appliquer une TVA au projet ? (y/n) : ");
        String reponseTVA = ValidationUtils.readString();
        if ("y".equalsIgnoreCase(reponseTVA)) {
            System.out.print("Entrez le pourcentage de TVA (%) : ");
            double tva = ValidationUtils.readDouble();
            projet.setTva(tva);
        }
        System.out.print("Souhaitez-vous appliquer une marge bénéficiaire au projet ? (y/n) : ");
        String reponseMarge = ValidationUtils.readValidName();
        if ("y".equalsIgnoreCase(reponseMarge)) {
            System.out.print("Entrez le pourcentage de marge bénéficiaire (%) : ");
            double margeBeneficiaire = ValidationUtils.readDouble();
            projet.setMargeBeneficiaire(margeBeneficiaire);
        }

        Optional<Client> clientOptional = clientService.getClientById(projet.getClientId());
        String clientNom;
        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            clientNom = client.getNom();
        } else {
            clientNom = "Client introuvable";
        }
        // Résultat du Calcul
        System.out.println("\n--- Résultat du Calcul ---");
        System.out.println("Nom du projet : " + projet.getNomProjet());
        System.out.println("Client : " + clientNom);
        System.out.println("Surface : " + projet.getSurface() + " m²");


        List<Materiaux> materiauxList = materiauxService.getMateriauxByProjetId(projet.getId());
        List<MainOeuvre> mainOeuvreList = mainOeuvreService.getMainOeuvreByProjetId(projet.getId());

        double coutMateriaux = 0.0;
        System.out.println("--- Détail des Coûts ---");
        System.out.println("1. Matériaux :");
        for (Materiaux materiaux : materiauxList) {
            double coutTotalMateriau = (materiaux.getCoutUnitaire() * materiaux.getQuantite()) + materiaux.getCoutTransport();
            coutTotalMateriau *= materiaux.getCoefficientQualite();
            coutMateriaux += coutTotalMateriau;

            System.out.printf("- %s : %.2f € (quantité : %.2f, coût unitaire : %.2f €, qualité : %.2f, transport : %.2f €)%n",
                    materiaux.getNom(),
                    coutTotalMateriau,
                    materiaux.getQuantite(),
                    materiaux.getCoutUnitaire(),
                    materiaux.getCoefficientQualite(),
                    materiaux.getCoutTransport());
        }

        // Add TVA if applicable
        double coutMateriauxAvecTVA = coutMateriaux;
        if (projet.getTva() > 0) {
            coutMateriauxAvecTVA += coutMateriaux * (projet.getTva() / 100);
        }

        System.out.printf("**Coût total des matériaux avant TVA : %.2f €**%n", coutMateriaux);
        System.out.printf("**Coût total des matériaux avec TVA (%.2f%%) : %.2f €**%n", projet.getTva(), coutMateriauxAvecTVA);

        double coutMainOeuvre = 0.0;


        System.out.println("2. Main-d'œuvre :");
        for (MainOeuvre mainOeuvre : mainOeuvreList) {
            double coutTotalMainOeuvre = mainOeuvre.getTauxHoraire() * mainOeuvre.getHeuresTravail();
            coutTotalMainOeuvre *= mainOeuvre.getProductiviteOuvrier();
            coutMainOeuvre += coutTotalMainOeuvre;

            System.out.printf("- %s : %.2f € (taux horaire : %.2f €, heures travaillées : %.2f h, productivité : %.2f)%n",
                    mainOeuvre.getNom(),
                    coutTotalMainOeuvre,
                    mainOeuvre.getTauxHoraire(),
                    mainOeuvre.getHeuresTravail(),
                    mainOeuvre.getProductiviteOuvrier());
        }

        double coutMainOeuvreAvecTVA = coutMainOeuvre;
        if (projet.getTva() > 0) {
            coutMainOeuvreAvecTVA += coutMainOeuvre * (projet.getTva() / 100);
        }

        System.out.printf("**Coût total de la main-d'œuvre avant TVA : %.2f €**%n", coutMainOeuvre);
        System.out.printf("**Coût total de la main-d'œuvre avec TVA (%.2f%%) : %.2f €**%n", projet.getTva(), coutMainOeuvreAvecTVA);


        double coutTotalAvantMarge = coutMateriauxAvecTVA + coutMainOeuvreAvecTVA;
        System.out.printf("3. Coût total avant marge : %.2f €%n", coutTotalAvantMarge);

        double margeBeneficiaire = 0.0;
        if (projet.getMargeBeneficiaire() > 0) {
            margeBeneficiaire = coutTotalAvantMarge * (projet.getMargeBeneficiaire() / 100);
        }

        System.out.printf("4. Marge bénéficiaire (%.2f%%) : %.2f €%n", projet.getMargeBeneficiaire(), margeBeneficiaire);

        double coutTotalFinal = coutTotalAvantMarge + margeBeneficiaire;
        System.out.printf("**Coût total final du projet : %.2f €**%n", coutTotalFinal);


        projet.setCoutTotal(coutTotalFinal);
        ProjetService projetService = new ProjetServiceImpl();
        try {
            projetService.mettreAJourProjet(projet);
            System.out.println("--------------------------------------------");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour du projet : " + e.getMessage());
        }


    }








    public static void afficherProjetsExistants() {
        System.out.println("\n--- Afficher les projets existants ---");
        ProjetServiceImpl projetService = new ProjetServiceImpl();

        try {
            List<Projet> projets = projetService.getAllProjets();
            if (projets.isEmpty()) {
                System.out.println("Aucun projet existant.");
            } else {
                System.out.printf("%-30s %-10s %-20s %-15s %-10s%n", "Nom", "Surface", "Marge Bénéficiaire", "Coût Total", "État");
                System.out.println("---------------------------------------------------------------------------------------------");

                for (Projet projet : projets) {
                    System.out.printf("%-30s %-10.2f %-20.2f %-15.2f %-10s%n",
                            projet.getNomProjet(),
                            projet.getSurface(),
                            projet.getMargeBeneficiaire(),
                            projet.getCoutTotal(),
                            projet.getEtatProjet());
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des projets : " + e.getMessage());
        }
    }
}








