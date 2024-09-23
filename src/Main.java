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
import Services.impl.*;
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
        ServiceManager serviceManager = new ServiceManager();
        MainGUI mainGUI = new MainGUI(
                serviceManager.getClientService(),
                serviceManager.getMateriauxService(),
                serviceManager.getMainOeuvreService(),
                serviceManager.getDevisService()
        );
        mainGUI.afficherMenuPrincipal();
    }
}