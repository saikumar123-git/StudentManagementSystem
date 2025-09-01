package com.example.student.service;

import com.example.student.api.Registration;
import com.example.student.api.student;
import com.example.student.repo.RegistrationRepository;
import com.example.student.repo.studentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private studentRepository studentRepo;

    @Autowired
    private RegistrationRepository registrationRepo; // Ensure you have this repository injected.

    /**
     * Securely retrieves the academic details for the currently authenticated user.
     * @param authentication The security principal for the logged-in user, provided by Spring Security.
     * @return An Optional containing the student's academic details if found, otherwise an empty Optional.
     */
    public Optional<student> getDetailsForAuthenticatedUser(Authentication authentication) {
        // 1. Get the username of the logged-in user from the security context. This is secure.
        String username = authentication.getName();

        // 2. Find the user's registration record using their username to get their unique ID.
        //    NOTE: Your RegistrationRepository needs a 'findByUsername' method for this to work.
        //    Example:
        //    public interface RegistrationRepository extends JpaRepository<Registration, Integer> {
        //        Registration findByUsername(String username); // Can return null
        //    }
        //    FIX: Wrap the result in Optional.ofNullable to handle potential nulls safely.
        Optional<Registration> userRegistrationOpt = Optional.ofNullable(registrationRepo.findByUsername(username));

        if (userRegistrationOpt.isPresent()) {
            // 3. Extract the ID from the user's registration record.
            int userId = userRegistrationOpt.get().getId();

            // 4. Use this secure, server-retrieved ID to fetch the corresponding student academic record.
            return studentRepo.findById(userId);
        }

        // If the user's registration record doesn't exist for some reason, return an empty result.
        return Optional.empty();
    }
}

