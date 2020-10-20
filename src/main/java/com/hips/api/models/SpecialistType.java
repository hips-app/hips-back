package com.hips.api.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "specialist_type")
public class SpecialistType extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    int id;
    @Column(length = 100)
    String name;

    public SpecialistType() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
