package ru.stqa.pft.mantis.model;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "mantis_user_table")
public class UserData {

    @Id
    private int id;
    private String username;
    private String email;
    private String password;
    @Type(type = "byte")
    private byte enabled;
    @Column(name = "access_level")
    @Type(type = "short")
    private short accessLevel;

    public int getId() {
        return id;
    }
    public UserData withId(int id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }
    public UserData withUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }
    public UserData withEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }
    public UserData withPassword(String password) {
        this.password = password;
        return this;
    }

    public byte getEnabled() {
        return enabled;
    }
    public UserData withEnabled(byte enabled) {
        this.enabled = enabled;
        return this;
    }

    public short getAccessLevel() {
        return accessLevel;
    }

    public UserData withAccessLevel(short access) {
        this.accessLevel = access;
        return this;
    }
    @Override
    public String toString() {
        return "UserData{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", enabled=" + enabled +
                ", access=" + accessLevel +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserData userData = (UserData) o;
        return Objects.equals(username, userData.username) &&
                Objects.equals(email, userData.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email);
    }

}
