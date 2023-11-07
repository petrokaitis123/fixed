package com.qa.application.controllers;

import com.qa.application.controllers.payload.response.MessageResponse;
import com.qa.application.exceptions.eRecords.ERecordsNotFoundException;
import com.qa.application.exceptions.user.UserNotFoundException;
import com.qa.application.models.eRecords.EmployeeRecords;
import com.qa.application.models.eRecords.repository.EmployeeRecordsRepository;
import com.qa.application.models.user.User;
import com.qa.application.models.user.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@EnableWebSecurity
@RequestMapping("/api")
public class ERecordsController {

    @Autowired
    EmployeeRecordsRepository employeeRecordsRepository;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/employee/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    ResponseEntity<MessageResponse> addERecords(@PathVariable Integer id, @RequestBody @Valid EmployeeRecords employeeRecords) {
        User user = userRepository.findUserById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        employeeRecordsRepository.save(employeeRecords);
        employeeRecords.setUser(user);
        employeeRecordsRepository.save(employeeRecords);
        return ResponseEntity.ok(new MessageResponse("Employee records have been successfully added!"));
    }
    @GetMapping("/employee/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<EmployeeRecords> getDetailsByUserId(@PathVariable Integer id){
        if(!employeeRecordsRepository.existsById(id)) {
            throw new ERecordsNotFoundException(id);
        }
        return ResponseEntity.ok(employeeRecordsRepository.findDetailsByUserId(id));
    }
}
