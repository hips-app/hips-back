package com.hips.api.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "account_type")
public class AccountType extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    int id;
    @Column(length = 100, unique = true)
    String name;

    public AccountType() {
    }

    public AccountType(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
