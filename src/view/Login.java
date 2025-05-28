package view;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class Login extends JFrame {

    private JTextField 	   usernameField;
    private JPasswordField passwordField;
    private JButton		   loginButton;

    public Login() {
    	this.setTitle("Login");
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setResizable(false);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // padding

        // Username label and field
        panel.add(new JLabel("Username:"));
        this.usernameField = new JTextField(15);
        panel.add(usernameField);

        // Password label and field
        panel.add(new JLabel("Password:"));
        this.passwordField = new JPasswordField(15);
        panel.add(this.passwordField);

        // Add empty label to fill space, and login button
        panel.add(new JLabel());
        this.loginButton = new JButton("Login");
        panel.add(this.loginButton);

        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
    }
    
    public String getUsernameField() {
        return usernameField.getText().trim();
    }

    @SuppressWarnings("deprecation")
	public String getPasswordField() {
        return passwordField.getText().trim();
    }

    public JButton getLoginButton() {
        return loginButton;
    }
}
