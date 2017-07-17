package com.lardi_trans.domain;


import javax.persistence.*;
import java.util.List;

/**
 * Created by nata on 19.03.2017.
 */

@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "login", unique = true)
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "patronymic")
    private String patronymic;

    public List<UserContact> getUserContactList() {
        return UserContactList;
    }

    public void setUserContactList(List<UserContact> userContactList) {
        UserContactList = userContactList;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserContact> UserContactList;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

       @Override

    public String toString(){

        return getName() + ", "+getSurname()+", "+getPatronymic();

    }

}
