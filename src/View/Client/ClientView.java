package View.Client;

import Services.impl.ProjetServiceImpl;
import Services.interfaces.*;
import View.Projet.ProjetView;
import models.Client;
import utils.ValidationUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;


    public class ClientView {
        private ClientService clientService;
        private DevisService devisService;
        private MateriauxService materiauxService;
        private MainOeuvreService mainOeuvreService;

        public ClientView(ClientService clientService, MateriauxService materiauxService, MainOeuvreService mainOeuvreService, DevisService devisService) {
            this.clientService = clientService;
            this.materiauxService = materiauxService;
            this.mainOeuvreService = mainOeuvreService;
            this.devisService = devisService;
        }

    public void rechercherClient() {
        System.out.println("\n--- Recherche de client existant par nom ---");
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
                    try {
                        ProjetView projetView = new ProjetView(clientService, materiauxService, mainOeuvreService, devisService);
                        projetView.creerProjetPourClient(client.getId());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }, () -> System.out.println("Client non trouvé."));
            }
        }
    }




    public void ajouterNouveauClient() {
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
                ProjetView projetView = new ProjetView(clientService, materiauxService, mainOeuvreService, devisService);
               projetView.creerProjetPourClient(createdClient.getId());
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de l'ajout du client : " + e.getMessage());
            e.printStackTrace();
        }

    }

    public  void afficherClients() {
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





}
