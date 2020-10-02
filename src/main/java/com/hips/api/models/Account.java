package com.hips.api.models;

import java.io.*;
import javax.persistence.*;


@Entity
@Table(name = "account")
public class Account extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    int id;
    @Column(length = 100)
    String uid;
    @ManyToOne(fetch = FetchType.LAZY)
    AccountType type;
    @Column(length = 100)
    String email;
    @Column(length = 100)
    String firstName;
    @Column(length = 100)
    String lastName;
    @Column(columnDefinition = "TEXT")
    String profilePicture;

    public Account() {
    }

    public Account(String uid, AccountType type, String email, String firstName,
            String lastName, String profilePicture) {
        this.uid = uid;
        this.type = type;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profilePicture = profilePicture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", uid=" + uid + ", type=" + type + 
                ", email=" + email + ", firstName=" + firstName + ", lastName=" 
                + lastName + ", profilePicture=" + profilePicture + ", isActive="
                + this.isActive() + ", createdAt=" + this.getCreatedAt() + ", updatedAt=" + this.getUpdatedAt() + '}';
    }
}
