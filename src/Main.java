import DAO.impl.ClientDAOImpl;
import DAO.impl.MainOeuvreDAOImpl;
import DAO.impl.MateriauxDAOImpl;
import DAO.interfaces.ClientDAO;
import DAO.interfaces.MainOeuvreDAO;
import DAO.interfaces.MateriauxDAO;
import Repository.impl.ClientRepositoryImpl;
import Repository.impl.MainOeuvreRepositoryImpl;
import Repository.impl.MateriauxRepositoryImpl;
import Repository.interfaces.ClientRepository;
import Repository.interfaces.MainOeuvreRepository;
import Repository.interfaces.MateriauxRepository;
import Services.impl.ClientServiceImpl;
import Services.impl.MainOeuvreServiceImpl;
import Services.impl.MateriauxServiceImpl;
import Services.interfaces.ClientService;
import Services.interfaces.MainOeuvreService;
import Services.interfaces.MateriauxService;
import View.MainGUI;
import config.DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ClientDAO clientDAO = new ClientDAOImpl();
        MainOeuvreDAO mainOeuvreDAO = new MainOeuvreDAOImpl();
        MateriauxDAO materiauxDAO = new MateriauxDAOImpl();
        ClientService clientService = new ClientServiceImpl(new ClientRepositoryImpl(clientDAO));
        MainOeuvreService mainOeuvreService = new MainOeuvreServiceImpl(new MainOeuvreRepositoryImpl(mainOeuvreDAO));
        MateriauxService materiauxService = new MateriauxServiceImpl(new MateriauxRepositoryImpl(materiauxDAO));

        MainGUI mainGUI = new MainGUI(clientService, materiauxService, mainOeuvreService);
        mainGUI.afficherMenuPrincipal();
    }
}