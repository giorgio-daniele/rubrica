import controller.HomeController;
import controller.LoginController;
import service.Database;
import service.DatabaseConfig;


public class App {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            DatabaseConfig  cfg = new DatabaseConfig("credenziali_database.properties");
            Database 	    db 	= new Database(cfg);
        	LoginController lc  = new LoginController(db);
            @SuppressWarnings("unused")
			HomeController  hc  =  new HomeController(db, lc);
        });
    }
}
