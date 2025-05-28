import controller.HomeController;
import service.Database;
import service.DatabaseConfig;



public class App {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new HomeController(
            		new Database(
            				new DatabaseConfig("credenziali_database.properties")));
        });
    }
}
