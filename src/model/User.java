package model;

public class User {
    private String username;
    private String hash;

    public User(String username, String hash) {
        this.username = username;
        this.hash 	  = hash;
    }

    public String getUsername() {
        return username;
    }

    public String getHash() {
        return hash;
    }
}
