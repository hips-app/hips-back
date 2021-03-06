package com.hips.api.models;

import javax.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "specialist_type")
public class SpecialistType extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "specialist_type_id", updatable = false, nullable = false)
    int id;
    @Column(length = 100, unique = true)
    String name;

    @OneToMany(mappedBy = "type")
    private List<SpecialistAccount> specialistAccounts;

    public SpecialistType() {
    }

    public SpecialistType(String name) {
        this.name = name;
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
