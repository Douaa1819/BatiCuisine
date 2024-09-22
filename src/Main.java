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
import Services.impl.ClientServiceImpl;
import Services.impl.DevisServiceImpl;
import Services.impl.MainOeuvreServiceImpl;
import Services.impl.MateriauxServiceImpl;
import Services.interfaces.ClientService;
import Services.interfaces.DevisService;
import Services.interfaces.MainOeuvreService;
import Services.interfaces.MateriauxService;
import View.MainGUI;
import config.DatabaseConnection;

import java.sql.SQLException;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws SQLException {
        ClientDAO clientDAO = new ClientDAOImpl();
        MainOeuvreDAO mainOeuvreDAO = new MainOeuvreDAOImpl();
        MateriauxDAO materiauxDAO = new MateriauxDAOImpl();
        DevisDAO devisDAO = new DevisDAOImpl();
        ClientService clientService = new ClientServiceImpl(new ClientRepositoryImpl(clientDAO));
        MainOeuvreService mainOeuvreService = new MainOeuvreServiceImpl(new MainOeuvreRepositoryImpl(mainOeuvreDAO));
        MateriauxService materiauxService = new MateriauxServiceImpl(new MateriauxRepositoryImpl(materiauxDAO));
        DevisService devisService = new DevisServiceImpl(new DevisRepositoryImpl(devisDAO));
        MainGUI mainGUI = new MainGUI(clientService, materiauxService, mainOeuvreService,devisService);
        mainGUI.afficherMenuPrincipal();
    }
}