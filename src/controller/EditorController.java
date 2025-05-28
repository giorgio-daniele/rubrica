package controller;

import model.Contact;
import service.Database;
import view.Editor;

import javax.swing.*;

/**
 * This class is used to control the lifecycle of the Editor view.
 * According to the MVC pattern, the Controller is responsible for
 * managing interactions between the Model and the View. It handles
 * user input, updates the model data, and refreshes the view accordingly.
 * 
 * The Controller initializes the Editor, responds to user actions,
 * validates input, and ensures synchronization between the UI and
 * underlying data (database)
 */

public class EditorController {

    // Reference to the editor view
    private Editor editor;
    // Reference to the database
    private final Database db;
    // Contact to be created or edited
    private Contact contact;

    public EditorController(Database db, Contact contact) {
        this.db = db;
        this.contact = (contact != null) ? contact : new Contact(); // Create new contact if null
        this.editor  = new Editor(contact);
        initUI();
    }

    // Initialize the editor UI: attach listeners and show the window
    private void initUI() {
        this.editor.getSaveButton().addActionListener(e -> saveAction());
        this.editor.getCancelButton().addActionListener(e -> cancelAction());
        this.editor.setVisible(true);
    }

    // Action triggered when the "Salva" button is clicked
    private void saveAction() {
        // Read values from the input fields
        String firstName = editor.getFirstName();
        String lastName  = editor.getLastName();
        String address   = editor.getAddress();
        String phone     = editor.getPhone();
        String age       = editor.getAge();

//        // Validate: first name and last name are required
//        if (firstName.isEmpty() || lastName.isEmpty()) {
//            JOptionPane.showMessageDialog(
//                this.editor,
//                "Nome e/o cognome obbligatori.",
//                "Errore di validazione",
//                JOptionPane.ERROR_MESSAGE
//            );
//            return;
//        }
//
//        // Validate: phone number is required
//        if (phone.isEmpty()) {
//            JOptionPane.showMessageDialog(
//                this.editor,
//                "Il numero di telefono è obbligatorio.",
//                "Errore di validazione",
//                JOptionPane.ERROR_MESSAGE
//            );
//            return;
//        }

        // Validate: age must be a valid integer if provided
        int ageValue = 0;
        try {
            if (!age.isEmpty()) {
                ageValue = Integer.parseInt(age);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                this.editor,
                "Formato dell'età non valido.",
                "Errore di validazione",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        // Set the values to the contact object
        contact.setFirstName(firstName);
        contact.setLastName(lastName);
        contact.setAddress(address);
        contact.setPhone(phone);
        contact.setAge(ageValue);

        // Save or update the contact in the database
        db.insertOrUpdate(contact);

        // Notify user and close the editor
        JOptionPane.showMessageDialog(this.editor, "Contatto salvato con successo.");
        this.editor.dispose();
    }

    // Action triggered when the "Annulla" button is clicked
    private void cancelAction() {
        this.editor.dispose();
    }
}
