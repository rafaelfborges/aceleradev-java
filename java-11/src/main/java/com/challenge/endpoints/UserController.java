package com.challenge.endpoints;

import com.challenge.exceptions.ResourceNotFoundException;
import com.challenge.entity.User;
import com.challenge.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.findById(id).orElseThrow(() -> new ResourceNotFoundException("User")));
    }

    @GetMapping
    public ResponseEntity<List<User>> findByAccelerationNameOrCompany(
            @RequestParam(required = false) String accelerationName,
            @RequestParam(required = false) Long companyId) {

        if (accelerationName != null)
            return ResponseEntity.ok(userService.findByAccelerationName(accelerationName));
        if (companyId != null)
            return ResponseEntity.ok(userService.findByCompanyId(companyId));

        return ResponseEntity.notFound().build();
    }
}
