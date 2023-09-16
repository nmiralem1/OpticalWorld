package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * User class that represents a user with its name, surname, email and login data
 */
public class User implements Idable {

    private int id, role;
    private String firstName, lastName, email, username, password;

    public User(String firstName, String lastName, String email, int role, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User() {
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, role, username, password);
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setRole(int role) {
        this.role = role;
    }
    public int getRole() {
        return role;
    }
}