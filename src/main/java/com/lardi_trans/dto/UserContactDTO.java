package com.lardi_trans.dto;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by nata on 12.07.2017.
 */
public class UserContactDTO {

    private Long id;

    @Size(min = 4, message = "size name should be min 4")
    private   String name;

    @Size(min = 4, message = "size surname should be min 4")
    private String surname;

    @Size(min = 4, message = "size patronymic should be min 4")
    private String patronymic;

    @Pattern(regexp = "\\+380[\\d]{9}", message = "invalid phone number")
    private  String phoneMobile;

    private String phoneHome;

    private  String address;

    @Email(message = "invalid e-mail")
    private   String email;

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

    public String getPhoneMobile() {
        return phoneMobile;
    }

    public void setPhoneMobile(String phoneMobile) {
        this.phoneMobile = phoneMobile;
    }

    public String getPhoneHome() {
        return phoneHome;
    }

    public void setPhoneHome(String phoneHome) {
        this.phoneHome = phoneHome;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
