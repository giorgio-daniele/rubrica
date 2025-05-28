package controller;

import java.util.ArrayList;
import java.util.HexFormat;
import java.util.List;
import javax.swing.JOptionPane;

import model.User;
import service.Database;
import view.Login;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class LoginController {
	
	// Properties
    private Login	 			login;
    private final Database 		db;
    private List<LoginListener> listeners = new ArrayList<>();

    // Constructor
    public LoginController(Database db) {
    	this.db    = db;
        this.login = new Login();
        this.initView();
    }

    // Allow observers to register
    public void addListener(LoginListener listener) {
        listeners.add(listener);
    }
    
    // Allow observers to unregister
    public void removeListener(LoginListener listener) {
        listeners.remove(listener);
    }    
    
    // Setup the UI
    private void initView() {
        this.login.getLoginButton().addActionListener(e  -> onLogin());
        this.login.setVisible(true);
    }

    // onLogin: what to do when the user
    // attempts to login
    public void onLogin() {
    	boolean success  = false;
    	String  username = this.login.getUsernameField();
    	String  password = this.login.getPasswordField();
    	User 	user 	 = db.selectUserByUsername(username);
    	
    	// Authenticate the user
    	if (user != null) {
//    		System.out.println(username);
//    		System.out.println(password);
    		String inputHash = sha256(password);
//    		System.out.println(inputHash);
//    		System.out.println(user.getHash());
    		if (inputHash.equals(user.getHash())) {
    			success = true;
    		}
    	}
    	
        if (success) {
            this.login.dispose();
            for (LoginListener listener : listeners) {
                listener.onLogin(user);
            }
        } else {
            JOptionPane.showMessageDialog(
                    this.login,
                    "Username e/o password errati.",
                    "Errore di validazione",
                    JOptionPane.ERROR_MESSAGE
                );
        }
    }
   
    private String sha256(String input) {
    	try {
    	    MessageDigest digest = MessageDigest.getInstance("SHA-256");
    	    byte[] hashBytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));
    		return HexFormat.of().formatHex(hashBytes);
    	} catch (NoSuchAlgorithmException e) {
    		throw new RuntimeException("SHA-256 algorithm not available", e);
    	}
    }
    
}
