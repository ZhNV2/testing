package ru.spbau.zhidkov.utils;

public class User {

    final private String login;
    final private String password;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }


    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
