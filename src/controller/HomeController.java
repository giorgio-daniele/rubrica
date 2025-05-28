package controller;

import model.Contact;
import model.ContactTable;
import service.Database;
import service.DatabaseListener;
import view.Home;

import javax.swing.*;
import java.util.List;

/**
 * This class is used to control the lifecycle of the Home view.
 * According to the MVC pattern, the Controller is responsible for
 * managing interactions between the Model and the View. It handles
 * user input, updates the model data, and refreshes the view accordingly.
 * 
 * The Controller initializes the Home, responds to user actions,
 * validates input, and ensures synchronization between the UI and
 * underlying data (database).
 */

public class HomeController implements DatabaseListener {

    // Reference to the main UI window
    private final Home home;
    // Reference to the data layer
    private final Database db;
    // In-memory list of contacts
    private List<Contact> contacts;

    // Constructor
    public HomeController(Database db) {
        this.db       = db;
        this.contacts = db.selectAllContacts();
        this.home     = new Home();

        this.db.addListener(this); 	  // Register for database change notifications
        initUI();
    }

    // Set up UI listeners and show initial data
    private void initUI() {
        home.getAddButton().addActionListener(e    -> openAddEditor());
        home.getEditButton().addActionListener(e   -> openEditEditor());
        home.getDeleteButton().addActionListener(e -> deleteSelectedContact());

        updateTable();
        home.setVisible(true);
    }

    // Refresh the table with the latest data from the database
    private void updateTable() {
        this.contacts = db.selectAllContacts();
        home.setTableModel(new ContactTable(this.contacts));
    }

    // Callback from the database to notify data changes
    @Override
    public void onChange() {
        this.updateTable();
    }

    // Open editor to add a new contact
    private void openAddEditor() {
        new EditorController(db, null);
    }

    // Open editor to modify the selected contact
    private void openEditEditor() {
        Contact selected = getSelectedContact();

        if (selected == null) {
            JOptionPane.showMessageDialog(
                home,
                "Per modificare è necessario selezionare una persona.",
                "Errore",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        new EditorController(this.db, selected);
    }

    // Delete the currently selected contact with confirmation
    private void deleteSelectedContact() {
        Contact selected = getSelectedContact();

        if (selected == null) {
            JOptionPane.showMessageDialog(
                home,
                "Per eliminare è necessario selezionare una persona.",
                "Errore",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
            home,
            "Eliminare la persona " + selected.getFirstName() + " " + selected.getLastName() + "?",
            "Conferma eliminazione",
            JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            db.deleteByPhone(selected.getPhone());
        }
    }

    // Get the contact selected in the JTable
    private Contact getSelectedContact() {
        int selectedRow = home.getTable().getSelectedRow();
        if (selectedRow == -1)
            return null;

        int modelIndex = home.getTable().convertRowIndexToModel(selectedRow);
        return contacts.get(modelIndex);
    }
}
