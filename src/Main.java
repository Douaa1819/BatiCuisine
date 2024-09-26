
import Services.impl.*;
import View.MainGUI;
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