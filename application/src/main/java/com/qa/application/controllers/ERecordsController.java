package com.qa.application.controllers;

import com.qa.application.controllers.payload.response.MessageResponse;
import com.qa.application.exceptions.eRecords.ERecordsNotFoundException;
import com.qa.application.exceptions.user.UserNotFoundException;
import com.qa.application.models.eRecords.EmployeeRecords;
import com.qa.application.models.eRecords.repository.EmployeeRecordsRepository;
import com.qa.application.models.user.User;
import com.qa.application.models.user.repository.UserRepository;
import com.qa.application.service.ERecordsService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ERecordsController {


    ERecordsService eRecordsService;

    @PostMapping("/employee/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    void addERecords(@RequestBody @Valid EmployeeRecords employeeRecords) {
        eRecordsService.addERecords(employeeRecords);
    }

    @GetMapping("/employee/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    EmployeeRecords getDetailsByUserId(@PathVariable Integer id) {
        return eRecordsService.findEmployeeRecords(id);
    }

    @GetMapping("/employee/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    EmployeeRecords updateRecords(@PathVariable Integer id, @RequestBody @Valid EmployeeRecords employeeRecords){

        return eRecordsService.updateEmployeeRecords(id, employeeRecords);
    }
}
