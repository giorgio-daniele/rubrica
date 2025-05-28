package service;

import model.Contact;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {


    // Table names
    private static final String TABLE_USER    = "User";
    private static final String TABLE_CONTACT = "Contact";

    // Queries
    private static final String QUERY_SELECT_USER_BY_USERNAME =
        "SELECT username, hash FROM " + TABLE_USER + " WHERE username = ?";

    private static final String QUERY_SELECT_CONTACTS_BY_USERNAME =
        "SELECT * FROM " + TABLE_CONTACT + " WHERE username = ? ORDER BY lastName";

    private static final String QUERY_INSERT_OR_UPDATE_CONTACT = String.format("""
        INSERT INTO %s (firstName, lastName, address, phone, age, username)
        VALUES (?, ?, ?, ?, ?, ?)
        ON DUPLICATE KEY UPDATE
            firstName = VALUES(firstName),
            lastName  = VALUES(lastName),
            address   = VALUES(address),
            age       = VALUES(age),
            username  = VALUES(username)
        """, TABLE_CONTACT);

    private static final String QUERY_DELETE_CONTACT_BY_PHONE =
        "DELETE FROM " + TABLE_CONTACT + " WHERE phone = ?";

    // Properties
    private final String url;
    private final String user;
    private final String password;

    // Objects interested on getting updates about database image
    private final List<DatabaseListener> listeners = new ArrayList<>();

    public Database(DatabaseConfig config) {
        this.url      = config.getUrl();
        this.user     = config.getUser();
        this.password = config.getPassword();
    }

    public User selectUserByUsername(String username) {
        User user    = null;

        try (Connection conn = DriverManager.getConnection(this.url, this.user, this.password);
             PreparedStatement pstmt = conn.prepareStatement(QUERY_SELECT_USER_BY_USERNAME)) {

            pstmt.setString(1, username);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    user = new User(rs.getString("username"),
                                    rs.getString("hash"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            /* There is no handling !!! */
        }

        return user;
    }


    public List<Contact> selectContactsByUsername(String username) {
        List<Contact> contacts = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(this.url, this.user, this.password);
             PreparedStatement pstmt = conn.prepareStatement(QUERY_SELECT_CONTACTS_BY_USERNAME)) {

            pstmt.setString(1, username);

            try (ResultSet rest = pstmt.executeQuery()) {
                while (rest.next()) {
                    contacts.add(new Contact(rest.getString("firstName"),
                                             rest.getString("lastName"),
                                             rest.getString("address"),
                                             rest.getString("phone"),
                                             rest.getInt("age")));
                }
            }

        } catch (SQLException e) { 
            e.printStackTrace();
            /* There is no handling !!! */ 
        }

        return contacts;
    }

    public void insertUpdateContact(Contact contact, String username) {
        try (Connection conn = DriverManager.getConnection(this.url, this.user, this.password);
             PreparedStatement pstmt = conn.prepareStatement(QUERY_INSERT_OR_UPDATE_CONTACT)) {

            pstmt.setString(1, contact.getFirstName());
            pstmt.setString(2, contact.getLastName());
            pstmt.setString(3, contact.getAddress());
            pstmt.setString(4, contact.getPhone());
            pstmt.setInt(5, contact.getAge());
            pstmt.setString(6, username);
            
            pstmt.executeUpdate();
            notifyChange();

        } catch (SQLException e) { 
            e.printStackTrace();
            /* There is no handling !!! */ 
        }
    }

    public void deleteContact(String phone) {
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(QUERY_DELETE_CONTACT_BY_PHONE)) {

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
