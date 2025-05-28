package controller;

import model.Contact;
import model.ContactTable;
import model.User;
import service.Database;
import service.DatabaseListener;
import view.Home;

import javax.swing.*;
import java.util.List;

public class HomeController implements DatabaseListener, LoginListener {

    // Properties
    private final Home     		  view;
    private final Database 		  db;
    private final LoginController lc;
    private List<Contact>  		  contacts;
    // Logged-in credentials
    private User				  user;
    
    // onChange: what to do when the database is updated
    @Override
    public void onChange() {
        this.updateTable();
    }
    
    // onLogin: what to do when the login is successful
	@Override
	public void onLogin(User user) {
		this.user = user;
		this.initView();
	}
    
    // Constructor
    public HomeController(Database db, LoginController lc) {
        this.db    = db;
        this.lc    = lc;
        this.view  = new Home();
        
        // Awaits for notification from database
        this.db.addListener(this);
        
        // Awaits for notification from login controller
        this.lc.addListener(this);
    }

    // Setup the UI
    private void initView() {
        this.view.getAddButton().addActionListener(e  -> onAdd());
        this.view.getEditButton().addActionListener(e -> onEdit());
        this.view.getDeleteButton().addActionListener(e -> onDelete());
        this.updateTable();
        this.view.setVisible(true);
    }

    private void updateTable() {
        this.contacts = db.selectContactsByUsername(this.user.getUsername());
        this.view.setTableModel(new ContactTable(this.contacts));
    }
    
    // onAdd: what to do when the user wants
    // to add a new contact
    private void onAdd() {
        new EditorController(db, null, user);
    }

    // onEdit: what to do when the user wants
    // to edit a contact
    private void onEdit() {
        Contact contact = getSelectedContact();
        
        if (contact == null) {
            JOptionPane.showMessageDialog(
                view,
                "Per modificare è necessario selezionare una persona.",
                "Errore",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        new EditorController(this.db, contact, this.user);
    }

    // onEdit: what to do when the user wants
    // to delete a contact
    private void onDelete() {
        Contact selected = getSelectedContact();

        if (selected == null) {
            JOptionPane.showMessageDialog(
                view,
                "Per eliminare è necessario selezionare una persona.",
                "Errore",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
            view,
            "Eliminare la persona " + selected.getFirstName() + " " + selected.getLastName() + "?",
            "Conferma eliminazione",
            JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            db.deleteContact(selected.getPhone());
        }
    }

    // Get the contact selected in the JTable
    private Contact getSelectedContact() {
        int selectedRow = view.getTable().getSelectedRow();
        if (selectedRow == -1)
            return null;

        int modelIndex = view.getTable().convertRowIndexToModel(selectedRow);
        return contacts.get(modelIndex);
    }
}
