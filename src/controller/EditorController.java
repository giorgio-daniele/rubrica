package controller;

import model.Contact;
import model.User;
import service.Database;
import view.Editor;

import javax.swing.*;


public class EditorController {
	
	// Properties
    private final Editor   editor;
    private final Database db;
    private Contact 	   contact;
    private User		   user;
    
    // Constructor
    public EditorController(Database db, Contact contact, User user) {
        //System.out.println(contact.getFirstName());
    	
        this.db       = db;
        this.contact  = (contact != null) ? contact : new Contact();
		this.user 	  = user;
        this.editor   = new Editor(contact);
        this.initView();
     
    }
    
    // Initialize the editor UI: attach listeners and show the window
    private void initView() {
        this.editor.getSaveButton().addActionListener(e -> onSave());
        this.editor.getCancelButton().addActionListener(e -> onCancel());
        this.editor.setVisible(true);
    }
    
    // onCancel: what to do when the user
    // wants to abort
    private void onCancel() {
        this.editor.dispose();
    }
  
    // onCancel: what to do when the user
    // wants to commit
    private void onSave() {
        String firstName = editor.getFirstName();
        String lastName  = editor.getLastName();
        String address   = editor.getAddress();
        String phone     = editor.getPhone();
        String age       = editor.getAge();
        
        
        // Validate lastName - it must be
        // mandatory
        if (lastName.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this.editor,
                    "Cognome obbligatorio.",
                    "Errore di validazione",
                    JOptionPane.ERROR_MESSAGE
                );
                return;
        }
        
        // Validate phone number. It should be:
        // mandatory and compatible with a real
        // number - all digits.
        if (phone.isEmpty()) {
            JOptionPane.showMessageDialog(
                this.editor,
                "Il numero di telefono è obbligatorio.",
                "Errore di validazione",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        } else {
        	// Check all chars are digits
        	if (!phone.matches("[0-9]+")) {
                JOptionPane.showMessageDialog(
                        this.editor,
                        "Numero di telefono invalido.",
                        "Errore di validazione",
                        JOptionPane.ERROR_MESSAGE
                    );
                return;
        	}
        }

        // Validate the age number. It should be
        // a number. Otherwise, the value can not
        // be converted to an int - all digits.
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
        db.insertUpdateContact(contact, this.user.getUsername());

        // Notify user and close the editor
        JOptionPane.showMessageDialog(this.editor, "Contatto salvato con successo.");
        this.editor.dispose();
    }
    
}
