package view;

import javax.swing.*;

import model.Contact;

import java.awt.*;

@SuppressWarnings("serial")
public class Editor extends JFrame {

    public static final int WIDTH  = 400;
    public static final int HEIGHT = 300;

    // UI components
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField addressField;
    private JTextField phoneField;
    private JTextField ageField;

    private JButton saveButton;
    private JButton cancelButton;

    // New UI component: toolbar
    private JToolBar toolBar;

    // Deprecated: bottom button panel (replaced by toolbar)
    // private JPanel buttonPanel;

    private JPanel formPanel;

    public Editor(Contact contact) {
        super("Editor");
        initUI();

        if (contact != null) {
            this.setFirstName(contact.getFirstName());
            this.setLastName(contact.getLastName());
            this.setAddress(contact.getAddress());
            this.setPhone(contact.getPhone());
            this.setAge(String.valueOf(contact.getAge()));
        }
    }

    private void initUI() {
        // Configure the window
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        // Initialize form panel
        this.formPanel = new JPanel(new GridLayout(5, 2, 10, 10));

        // First Name
        this.formPanel.add(new JLabel("Nome:"));
        this.firstNameField = new JTextField();
        this.formPanel.add(this.firstNameField);

        // Last Name
        this.formPanel.add(new JLabel("Cognome:"));
        this.lastNameField = new JTextField();
        this.formPanel.add(this.lastNameField);

        // Address
        this.formPanel.add(new JLabel("Indirizzo:"));
        this.addressField = new JTextField();
        this.formPanel.add(this.addressField);

        // Phone
        this.formPanel.add(new JLabel("Telefono:"));
        this.phoneField = new JTextField();
        this.formPanel.add(this.phoneField);

        // Age
        this.formPanel.add(new JLabel("Età:"));
        this.ageField = new JTextField();
        this.formPanel.add(this.ageField);

        // Add form panel to the center
        this.add(this.formPanel, BorderLayout.CENTER);

        // Initialize buttons
        this.saveButton = new JButton("Salva", new ImageIcon(
        		new ImageIcon(getClass().getResource("/icons/sav.png"))
        		.getImage()
        		.getScaledInstance(24, 24, Image.SCALE_SMOOTH)));
        
        this.cancelButton = new JButton("Annulla", new ImageIcon(
        		new ImageIcon(getClass().getResource("/icons/abt.png"))
        		.getImage()
        		.getScaledInstance(24, 24, Image.SCALE_SMOOTH)));
        

        // Create toolbar and add buttons
        this.toolBar = new JToolBar();
        this.toolBar.setFloatable(false); // Disable dragging
        this.toolBar.add(this.saveButton);
        this.toolBar.add(this.cancelButton);

        // Add toolbar to the bottom of the window
        this.add(this.toolBar, BorderLayout.NORTH);

        // Deprecated: bottom button panel (replaced by toolbar)
        // this.buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        // this.buttonPanel.add(this.saveButton);
        // this.buttonPanel.add(this.cancelButton);
        // this.add(this.buttonPanel, BorderLayout.SOUTH);
    }

    // Getters for input values
    public String getFirstName() { return firstNameField.getText().trim(); }
    public String getLastName()  { return lastNameField.getText().trim();  }
    public String getAddress()   { return addressField.getText().trim();   }
    public String getPhone()     { return phoneField.getText().trim();     }
    public String getAge()       { return ageField.getText().trim();       }

    // Setters for editing existing contact
    public void setFirstName(String value) { firstNameField.setText(value); }
    public void setLastName(String value)  { lastNameField.setText(value);  }
    public void setAddress(String value)   { addressField.setText(value);   }
    public void setPhone(String value)     { phoneField.setText(value);     }
    public void setAge(String value)       { ageField.setText(value);       }

    // Button access for controller
    public JButton getSaveButton()   { return saveButton; }
    public JButton getCancelButton() { return cancelButton; }

    // Getter for the toolbar
    public JToolBar getToolBar()     { return toolBar; }
}
