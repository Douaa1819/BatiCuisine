package View.Projet;

import Services.impl.ProjetServiceImpl;
import Services.interfaces.*;
import View.Client.ClientView;
import View.Composant.ComposantView;
import View.MainGUI;
import enums.EtatProjet;
import models.*;
import utils.ValidationUtils;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class ProjetView {

    private  ClientService clientService;
    private  MateriauxService materiauxService ;
    private  MainOeuvreService mainOeuvreService ;
    private  DevisService devisService;

    public ProjetView(ClientService clientService,MateriauxService materiauxService,MainOeuvreService mainOeuvreService, DevisService devisService) {
        this.clientService = clientService;
        this.materiauxService = materiauxService;
        this.mainOeuvreService = mainOeuvreService;
        this.devisService = devisService;
    }

    private  final static ProjetService projetService = new ProjetServiceImpl();
    public  void creerNouveauProjet() {
        System.out.println("\n--- Recherche de client ---");
        System.out.println("Souhaitez-vous chercher un client existant ou en ajouter un nouveau ?");
        System.out.println("1. Chercher un client existant");
        System.out.println("2. Afficher tous les client");
        System.out.println("3. Ajouter un nouveau client");
        System.out.print("Choisissez une option : ");

        int choixClient = ValidationUtils.readInt();
        ValidationUtils.scanner.nextLine();

        switch (choixClient) {
            case 1:
                ClientView clientView = new ClientView(clientService, materiauxService, mainOeuvreService, devisService);
                clientView.rechercherClient();
                break;

            case 2:
                clientView = new ClientView(clientService, materiauxService, mainOeuvreService, devisService);
                clientView.afficherClients();
                break;

            case 3:
                 clientView = new ClientView(clientService, materiauxService, mainOeuvreService, devisService);
                clientView.ajouterNouveauClient();
                break;
            default:
                System.out.println("Veuillez choisir une option valide.");
                creerNouveauProjet();
        }

    }



    public  void creerProjetPourClient(UUID clientId) throws SQLException {
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
        ComposantView composantView = new ComposantView(clientService, materiauxService, mainOeuvreService, devisService);
        composantView.ajouterMateriaux(projet);
        composantView.ajouterMainOeuvre(projet);
    }






    public  void calculerCoutTotal(Projet projet) throws SQLException {
        System.out.println("\n--- Calcul du coût total ---");

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
        enregistrerDevis(projet);
    }



    private  void enregistrerDevis(Projet projet) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--- Enregistrement du Devis ---");


        System.out.print("Entrez la date d'émission du devis (format : jj/mm/aaaa) : ");
        LocalDate dateEmission = ValidationUtils.readDate();

        System.out.print("Entrez la date de validité du devis (format : jj/mm/aaaa) : ");
        LocalDate dateValidee = ValidationUtils.readDate();

        System.out.print("Souhaitez-vous enregistrer le devis ? (y/n) : ");
        String reponse = scanner.nextLine();

        boolean accepte = "y".equalsIgnoreCase(reponse);

        // Créer le devis
        Devis devis = new Devis(UUID.randomUUID(), dateEmission, dateValidee, accepte, projet.getId());

        devisService.save(devis);

        try {
            if (accepte) {
                projet.setEtatProjet(EtatProjet.TERMINEE);
                projetService.mettreAJourProjet(projet);
                System.out.println("Devis enregistré avec succès ! Projet marqué comme terminé.");
            } else {
                projet.setEtatProjet(EtatProjet.ANNULEE);
                projetService.mettreAJourProjet(projet);
                System.out.println("Devis non enregistré. Projet marqué comme annulé.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour du projet : " + e.getMessage());
        }

        MainGUI.afficherMenuPrincipal();
    }

    public void choisirNomEtCalculerCoutTotal(List<Projet> projets) throws SQLException {
        if (projets.isEmpty()) {
            System.out.println("Aucun projet disponible.");
            return;
        }

        System.out.println("--- Choisissez un projet ---");
        for (int i = 0; i < projets.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, projets.get(i).getNomProjet());
        }

        System.out.print("Entrez le numéro du projet : ");
        int choix = ValidationUtils.readInt();

        if (choix < 1 || choix > projets.size()) {
            System.out.println("Choix invalide. Veuillez réessayer.");
            return;
        }

        Projet projetChoisi = projets.get(choix - 1);
        System.out.println("Vous avez choisi le projet : " + projetChoisi.getNomProjet());

        enregistrerDevis(projetChoisi);
    }

    public void afficherProjetsExistants() {
        System.out.println("\n--- Afficher les projets existants ---");

        try {
            List<Projet> projets = projetService.getAllProjets();
            if (projets.isEmpty()) {
                System.out.println("Aucun projet existant.");
            } else {
                System.out.printf("%-30s %-10s %-20s %-15s %-10s %-20s %-20s%n",
                        "Nom", "Surface", "Marge Bénéficiaire", "Coût Total", "État", "Main d'œuvre", "Matériaux");
                System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

                for (Projet projet : projets) {

                    List<MainOeuvre> mainOeuvres = mainOeuvreService.getMainOeuvreByProjetId(projet.getId());
                    List<Materiaux> materiauxList = materiauxService.getMateriauxByProjetId(projet.getId());

                    String mainOeuvresString = mainOeuvres.stream()
                            .map(MainOeuvre::getNom)
                            .collect(Collectors.joining(", "));
                    String materiauxString = materiauxList.stream()
                            .map(Materiaux::getNom)
                            .collect(Collectors.joining(", "));

                    System.out.printf("%-30s %-10.2f %-20.2f %-15.2f %-10s %-20s %-20s%n",
                            projet.getNomProjet(),
                            projet.getSurface(),
                            projet.getMargeBeneficiaire(),
                            projet.getCoutTotal(),
                            projet.getEtatProjet(),
                            mainOeuvresString.isEmpty() ? "Aucune" : mainOeuvresString,
                            materiauxString.isEmpty() ? "Aucun" : materiauxString);

                    System.out.println("");
                    System.out.println("|-----Main d'œuvre-------| : " + mainOeuvres);
                    System.out.println("|-----Matériaux-----------| : " + materiauxList);
                    System.out.println("");
                    System.out.println("____________________________________________________________________________________________________________________________________________________________________");
                }


            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des projets : " + e.getMessage());
        }
    }


}
