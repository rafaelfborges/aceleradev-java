package com.challenge.endpoints;

import com.challenge.exceptions.ResourceNotFoundException;
import com.challenge.entity.Company;
import com.challenge.service.impl.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/{id}")
    public ResponseEntity<Company> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(companyService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company")));
    }

    @GetMapping
    public ResponseEntity<List<Company>> findByAccelerationIdOrUserId(
            @RequestParam(required = false) Long accelerationId,
            @RequestParam(required = false) Long userId) {

        if (accelerationId != null)
            return ResponseEntity.ok(companyService.findByAccelerationId(accelerationId));
        if (userId != null)
            return ResponseEntity.ok(companyService.findByUserId(userId));

        return ResponseEntity.notFound().build();
    }
}
