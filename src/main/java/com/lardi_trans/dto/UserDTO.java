package com.lardi_trans.dto;


import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by nata on 15.03.2017.
 */
public class UserDTO
{
    @Size(min = 5, message = "size name should be min 5")
    String name;

    @Size(min = 5, message = "size surname should be min 5")
    String surname;

    @Size(min = 5, message = "size patronymic should be min 5")
    String patronymic;

    //  @NotNull(message = "Field login cannot be null")
    @Pattern(regexp = "[\\w]{3,}", message = "incorrect login, size should be min 3 ")
    String login;

    // @NotNull(message = "Field password cannot be null")
    @Size(min = 5, message = "size password should be min 5")
    String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }
}
