package controller;

import model.User;

public interface LoginListener {
    void onLogin(User user);
}