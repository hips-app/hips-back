package com.hips.api.models;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name = "account")
public class Account extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    int id;
    @Column(length = 100, unique = true)
    String uid;
    @ManyToOne(fetch = FetchType.LAZY)
    AccountType type;
    @Column(length = 100, unique = true)
    String email;
    @Column(length = 100)
    String firstName;
    @Column(length = 100)
    String lastName;
    @Column(columnDefinition = "TEXT")
    String profilePicture;
    @Column(columnDefinition = "TEXT")
    String password;
    @Column(columnDefinition = "TEXT")
    String salt;

    public Account() {
    }

    public Account(String uid, AccountType type, String email, String firstName,
            String lastName, String password, String salt, String profilePicture) {
        this.uid = uid;
        this.type = type;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profilePicture = profilePicture;
        this.password = password;
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
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
