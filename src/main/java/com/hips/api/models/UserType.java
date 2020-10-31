package com.hips.api.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "user_type")
public class UserType extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "user_type_id")
    private Integer id;

    @Column(name = "user_type_name")
    private String name;

    @OneToMany(mappedBy = "userType")
    private List<UserSubscription> userSubscriptions;

    public UserType() {
    }

    public UserType(String name) {
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
