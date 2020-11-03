package com.hips.api.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "week_day")
public class WeekDay extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(columnDefinition = "serial")
    int id;

    @Column( length = 100)
    String name;

    public WeekDay() {
    }

    public WeekDay(int id, String name) {
        this.id = id;
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

    @Override
    public String toString() {
        return "WeekDay{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
