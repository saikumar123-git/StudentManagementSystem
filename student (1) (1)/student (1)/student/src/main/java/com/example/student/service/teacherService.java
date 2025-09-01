package com.example.student.service;

import com.example.student.api.Registration;
import com.example.student.api.student;
import com.example.student.repo.RegistrationRepository;
import com.example.student.repo.studentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class teacherService {

    @Autowired
    private studentRepository repository;
    @Autowired
    private RegistrationRepository registrationRepo;

    @Transactional
    public student addStudent(int registrationId, student studentDetails) {
        Registration registration = registrationRepo.findById(registrationId)
                .orElseThrow(() -> new EntityNotFoundException("Registration with ID " + registrationId + " not found."));

        // Set the permanent link on the new student record.
        studentDetails.setRegistration(registration);

        // CRITICAL FIX: The name now comes from the UI request body.
        // The line that automatically set the name from the username has been removed.

        return repository.save(studentDetails);
    }

    public Optional<student> GetStudent(int rollno) {
        return repository.findById(rollno);
    }

    public Iterable<student> getAll() {
        return repository.findAll();
    }

    /**
     * CORRECTED UPDATE LOGIC to prevent StaleObjectStateException.
     * @param rollno The ID of the student to update.
     * @param updatedDetails The object with the new data.
     * @return The updated student.
     */
    @Transactional // Ensures this operation is safe and consistent.
    public student update(int rollno, student updatedDetails) {
        // 1. Find the existing student in the database. This is the crucial first step.
        student existingStudent = repository.findById(rollno)
                .orElseThrow(() -> new EntityNotFoundException("Student with rollno " + rollno + " not found"));

        // 2. Copy the new details from the request onto the existing database record.
        existingStudent.setName(updatedDetails.getName());
        existingStudent.setPercentage(updatedDetails.getPercentage());
        existingStudent.setBranch(updatedDetails.getBranch());

        // 3. Save the modified, existing record. Hibernate will now correctly perform an UPDATE.
        return repository.save(existingStudent);
    }

    public boolean DeleteStudent(int rollno) {
        if (repository.existsById(rollno)) {
            repository.deleteById(rollno);
            return true;
        }
        return false;
    }
}

