package service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DatabaseConfig {
    private String url;
    private String user;
    private String password;

    public DatabaseConfig(String filePath) {
        Properties props = new Properties();
        try (FileInputStream input = new FileInputStream(filePath)) {
            props.load(input);
            url      = props.getProperty("db.ip-server-mysql");
            user     = props.getProperty("db.user");
            password = props.getProperty("db.password");
            
            System.out.println("[DEBUG]  Connecting to database...");
            System.out.printf("[DEBUG]   URL=%s%n", url);
            System.out.printf("[DEBUG]   User=%s%n", user);

        } catch (IOException ex) {
        	System.err.println("[ERROR] Failed to load database config: " + ex.getMessage());
        }
    }

    public String getUrl()      { return url; }
    public String getUser()     { return user; }
    public String getPassword() { return password; }
}
