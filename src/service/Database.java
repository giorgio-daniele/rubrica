package service;

import model.Contact;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Database service class for managing Contact records.
 * Connects to MySQL and performs CRUD operations.
 */
public class Database {

    private final String url;
    private final String user;
    private final String password;
    
    // Objects interested on getting updates about database image
    private final List<DatabaseListener> listeners = new ArrayList<>();

    public Database(DatabaseConfig config) {
        this.url 	  = config.getUrl();
        this.user 	  = config.getUser();
        this.password = config.getPassword();
    }

    // Run a SQL query for selecting all records from
    // the Contact table
    public List<Contact> selectAllContacts() {
        List<Contact> contacts = new ArrayList<>();
        String query = "SELECT * FROM Contact ORDER BY lastName";

        try (Connection conn = DriverManager.getConnection(this.url, this.user, this.password);
             Statement stmt  = conn.createStatement();
             ResultSet rest  = stmt.executeQuery(query)) {

            while (rest.next()) {
            	//System.out.println(rest);
                contacts.add(new Contact(rest.getString("firstName"),
                						 rest.getString("lastName"),
                						 rest.getString("address"),
                						 rest.getString("phone"),
                						 rest.getInt("age")));
            }

        } catch (SQLException e) { 
        	e.printStackTrace();
        	/* There is no handling !!! */ 
        }

        return contacts;
    }

    // Run a SQL query for INSERT or UPDATE a new
    // record in database
    public void insertOrUpdate(Contact contact) {
        String sql = """
            INSERT INTO Contact (firstName, lastName, address, phone, age)
            VALUES (?, ?, ?, ?, ?)
            ON DUPLICATE KEY UPDATE
                firstName = VALUES(firstName),
                lastName  = VALUES(lastName),
                address   = VALUES(address),
                age       = VALUES(age)
            """;

        try (Connection conn = DriverManager.getConnection(this.url, this.user, this.password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, contact.getFirstName());
            pstmt.setString(2, contact.getLastName());
            pstmt.setString(3, contact.getAddress());
            pstmt.setString(4, contact.getPhone());
            pstmt.setInt(5, contact.getAge());

            pstmt.executeUpdate();
            notifyChange();

        } catch (SQLException e) { 
        	e.printStackTrace();
        	/* There is no handling !!! */ 
        }
    }

    // Run a SQL query for DELETE a record in
    // Contact database
    public void deleteByPhone(String phone) {
        String sql = "DELETE FROM Contact WHERE phone = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, phone);
            pstmt.executeUpdate();
            notifyChange();

        } catch (SQLException e) { 
        	e.printStackTrace();
        	/* There is no handling !!! */ 
        }
    }

    public void addListener(DatabaseListener listener) {
        this.listeners.add(listener);
    }

    public void removeListener(DatabaseListener listener) {
        this.listeners.remove(listener);
    }

    private void notifyChange() {
        for (DatabaseListener listener : listeners) {
            listener.onChange();
        }
    }
}
