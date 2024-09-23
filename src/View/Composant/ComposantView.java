package View.Composant;

import Services.impl.ProjetServiceImpl;
import Services.interfaces.*;
import View.Projet.ProjetView;
import models.MainOeuvre;
import models.Materiaux;
import models.Projet;
import utils.ValidationUtils;

import java.sql.SQLException;
import java.util.UUID;

import static enums.TypeComposant.MAIN_D_OEUVRE;
import static enums.TypeComposant.MATERIAUX;

public class ComposantView {

    private  static MateriauxService materiauxService ;
    private static   MainOeuvreService mainOeuvreService ;
    private static ClientService clientService;
    private static DevisService devisService;

    public ComposantView(ClientService clientService,MateriauxService materiauxService,MainOeuvreService mainOeuvreService, DevisService devisService) {
        this.materiauxService = materiauxService;
        this.mainOeuvreService = mainOeuvreService;
        this.clientService=clientService;
        this.devisService=devisService;
    }

    private final static ProjetService projetService = new ProjetServiceImpl();

    public  static void ajouterMateriaux(Projet projet) throws SQLException {
        System.out.println("\n--- Ajout des matériaux ---");
        while (true) {
            System.out.print("Entrez le nom du matériau : ");
            String nomMateriau = ValidationUtils.readString();


            System.out.println("Entrez la quantité de ce matériau (en m²): ");
            double quantite = ValidationUtils.readDouble();


            System.out.println("Entrez le coût unitaire de ce matériau (€/m²) : ");
            double coutUnitaire = ValidationUtils.readDouble();

            System.out.println("Entrez le taux de tva (%) : ");
            double tva = ValidationUtils.readValidTva();

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


    public static void ajouterMainOeuvre(Projet projet) throws SQLException {
        System.out.println("\n--- Ajout de la main-d'œuvre ---");
        boolean ajouterAutre = true;
        while (ajouterAutre) {
            System.out.println("Entrez le nom du main-d'œuvre : ");
            String nomMainDoeuvre = ValidationUtils.readString();

            System.out.println("Entrez le taux de tva (%) : ");
            double tva = ValidationUtils.readValidTva();

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

        ProjetView projetView = new ProjetView(clientService, materiauxService, mainOeuvreService, devisService);
        projetView.calculerCoutTotal(projet);
    }


}
