package com.example.javaapi.models;

import javax.persistence.*;

@Entity
@Table(name = "rolebean")
public class Rolebean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role")
    private int idRole;
    @Column(name = "name")
    private String name;

    public Rolebean() {
    }

    public Rolebean(int idRole, String name) {
        super();
        this.idRole = idRole;
        this.name = name;
    }

    public Rolebean(String name) {
        super();
        this.name = name;
    }


    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
