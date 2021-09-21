package com.example.javaapi.models;

import javax.persistence.*;

@Entity
@Table(name = "userbean")
public class Userbean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private int idUser;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "name")
    private String name;
    @Column(name = "id_role")
    private int idRole;

    public Userbean() {
    }

    public Userbean(int idUser, String login, String password, String name, int idRole) {
        super();
        this.idUser = idUser;
        this.login = login;
        this.password = password;
        this.name = name;
        this.idRole = idRole;
    }

    public Userbean(String name, int idRole, String login) {
        super();
        this.login = login;
        this.name = name;
        this.idRole = idRole;
    }

    public Userbean(String login, String passHashed, String name, int idRole) {
        super();
        this.login = login;
        this.password = passHashed;
        this.name = name;
        this.idRole = idRole;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
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

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

}