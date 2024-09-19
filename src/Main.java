import DAO.impl.ClientDAOImpl;
import DAO.interfaces.ClientDAO;
import Repository.impl.ClientRepositoryImpl;
import Repository.interfaces.ClientRepository;
import Services.impl.ClientServiceImpl;
import Services.interfaces.ClientService;
import View.MainGUI;
import config.DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
//        MainGUI.afficherMenuPrincipal();


//DatabaseConnection.run();
//        ClientDAO ClientDAO = new ClientDAOImpl();
//        ClientRepository clientRepository = new ClientRepositoryImpl(ClientDAO);
//        ClientService clientService = new ClientServiceImpl(clientRepository);
//
//        MainGUI mainGUI = new MainGUI(clientService);
//        mainGUI.afficherMenuPrincipal();


        ClientDAO clientDAO = new ClientDAOImpl();

        ClientService clientService = new ClientServiceImpl(new ClientRepositoryImpl(clientDAO));

        // Création de l'instance de MainGUI
        MainGUI mainGUI = new MainGUI(clientService);

        // Utilisation des méthodes non statiques via l'instance de MainGUI
        mainGUI.afficherClients();
        mainGUI.ajouterNouveauClient();
        mainGUI.rechercherClient();
    }
}