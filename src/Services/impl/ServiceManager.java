package Services.impl;

import DAO.impl.ClientDAOImpl;
import DAO.impl.DevisDAOImpl;
import DAO.impl.MainOeuvreDAOImpl;
import DAO.impl.MateriauxDAOImpl;
import DAO.interfaces.ClientDAO;
import DAO.interfaces.DevisDAO;
import DAO.interfaces.MainOeuvreDAO;
import DAO.interfaces.MateriauxDAO;
import Repository.impl.ClientRepositoryImpl;
import Repository.impl.DevisRepositoryImpl;
import Repository.impl.MainOeuvreRepositoryImpl;
import Repository.impl.MateriauxRepositoryImpl;
import Services.interfaces.*;
import View.MainGUI;

import java.sql.SQLException;

public class ServiceManager {

    // Instances des services
    private ClientService clientService;
    private MateriauxService materiauxService;
    private MainOeuvreService mainOeuvreService;
    private DevisService devisService;
    private ProjetService projetService;

    // Constructeur pour initialiser les services
    public ServiceManager() throws SQLException {
        // Initialisation des DAO
        ClientDAO clientDAO = new ClientDAOImpl();
        MainOeuvreDAO mainOeuvreDAO = new MainOeuvreDAOImpl();
        MateriauxDAO materiauxDAO = new MateriauxDAOImpl();
        DevisDAO devisDAO = new DevisDAOImpl();

        // Initialisation des services avec les DAO
        clientService = new ClientServiceImpl(new ClientRepositoryImpl(clientDAO));
        mainOeuvreService = new MainOeuvreServiceImpl(new MainOeuvreRepositoryImpl(mainOeuvreDAO));
        materiauxService = new MateriauxServiceImpl(new MateriauxRepositoryImpl(materiauxDAO));
        devisService = new DevisServiceImpl(new DevisRepositoryImpl(devisDAO));
        projetService = new ProjetServiceImpl();
    }

    // Méthodes pour obtenir les services
    public ClientService getClientService() {
        return clientService;
    }

    public MateriauxService getMateriauxService() {
        return materiauxService;
    }

    public MainOeuvreService getMainOeuvreService() {
        return mainOeuvreService;
    }

    public DevisService getDevisService() {
        return devisService;
    }

    public ProjetService getProjetService() {
        return projetService;
    }
}

