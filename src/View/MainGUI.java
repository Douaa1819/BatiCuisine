package View;
import Services.impl.ProjetServiceImpl;
import Services.interfaces.*;
import View.Projet.ProjetView;
import models.*;
import utils.ValidationUtils;

import java.sql.SQLException;
import java.util.*;



public class MainGUI {


    private static ClientService clientService;
    private static MateriauxService materiauxService ;
    private static MainOeuvreService mainOeuvreService ;
    private static  DevisService devisService;
    public MainGUI(ClientService clientService,MateriauxService materiauxService,MainOeuvreService mainOeuvreService, DevisService devisService) {
        this.clientService = clientService;
        this.materiauxService = materiauxService;
        this.mainOeuvreService = mainOeuvreService;
        this.devisService = devisService;
    }


    private  final static ProjetService projetService = new ProjetServiceImpl();
    public  static void afficherMenuPrincipal() throws SQLException {
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
                    ProjetView projetView = new ProjetView(clientService, materiauxService, mainOeuvreService, devisService);
                    projetView.creerNouveauProjet();
                    break;
                case 2:
                    projetView = new ProjetView(clientService, materiauxService, mainOeuvreService, devisService);
                    projetView.afficherProjetsExistants();
                    break;

                case 3:
                    List<Projet> projets = projetService.getAllProjets();
                    projetView = new ProjetView(clientService, materiauxService, mainOeuvreService, devisService);
                    projetView.choisirNomEtCalculerCoutTotal(projets);
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
}








