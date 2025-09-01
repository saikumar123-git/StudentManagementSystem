package com.example.student.api;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "Registration")
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "College ID is required")
    private String collageId;

    @NotBlank(message = "Role is required")
    private String role;

    public Registration() {}

    public Registration(int id, String username, String password, String collageId, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.collageId = collageId;
        this.role = role;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getCollageId() { return collageId; }
    public void setCollageId(String collageId) { this.collageId = collageId; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    @Override
    public String toString() {
        return "Registration{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", collageId='" + collageId + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
