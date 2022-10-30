package com.jndev.desingPattern.model;

/**
 * @Autor Jairo Nascimento
 * @Created 30/10/2022 - 08:36
 */
public class Customer {

    private String uid;
    private String name;

    public Customer() {
    }

    public Customer(String uid, String name) {
        this.uid = uid;
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
