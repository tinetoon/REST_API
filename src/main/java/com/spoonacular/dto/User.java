package com.spoonacular.dto;

public class User {

    /*private String username = "tinetoon";
    private String firstName = "q";
    private String lastName = "q";
    private String email = "tinetoon@mail.ru";*/

    private String username;
    private String firstName;
    private String lastName;
    private String email;

    // Конструктор
    public User(String username, String firstName, String lastName, String email) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // Геттеры
    public String getUsername() {
        return username;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmail() {
        return email;
    }
}
