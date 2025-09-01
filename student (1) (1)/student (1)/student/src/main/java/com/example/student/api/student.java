package com.example.student.api;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name ="Student")
public class student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rollno;
    @Column(name = "student_name")
    private String name;
    @Column
    private float percentage;
    @Column
    private String branch;

    // --- THE PERMANENT SOLUTION ---
    // This creates a direct, unbreakable link to the user's login account.
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "registration_id", referencedColumnName = "id")
    @JsonIgnore // Prevents infinite loops when sending data to the UI
    private Registration registration;
    // --- END OF SOLUTION ---

    // Getters and Setters for the new field
    public Registration getRegistration() {
        return registration;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRollno() {
        return rollno;
    }

    public void setRollno(int rollno) {
        this.rollno = rollno;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public student() {

    }

    public student(int rollno, String name, float percentage, String branch) {
        this.rollno = rollno;
        this.name = name;
        this.percentage = percentage;
        this.branch = branch;
    }

    @Override
    public String toString() {
        return "Student{" +
                "rollno=" + rollno +
                ", name='" + name + '\'' +
                ", percentage=" + percentage +
                ", branch='" + branch + '\'' +
                '}';
    }
}

