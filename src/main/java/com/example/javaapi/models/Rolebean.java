package com.example.javaapi.models;

public class Rolebean {

    private String idRole;
    private String name;

    /**
     * No args constructor for use in serialization
     *
     */
    public Rolebean() {
    }

    /**
     *
     * @param idRole
     * @param name
     */
    public Rolebean(String idRole, String name) {
        super();
        this.idRole = idRole;
        this.name = name;
    }

    public String getIdRole() {
        return idRole;
    }

    public void setIdRole(String idRole) {
        this.idRole = idRole;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
