package com.challenge.endpoints;

import com.challenge.dto.CandidateDTO;
import com.challenge.entity.Candidate;
import com.challenge.exceptions.ResourceNotFoundException;
import com.challenge.mappers.CandidateMapper;
import com.challenge.service.impl.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private CandidateMapper candidateMapper;

    @GetMapping("{userId}/{accelerationId}/{companyId}")
    public ResponseEntity<CandidateDTO> findById(@PathVariable Long userId,
                                                 @PathVariable Long companyId,
                                                 @PathVariable Long accelerationId) {
        return ResponseEntity.ok(candidateMapper.map(candidateService.findById(userId, companyId, accelerationId)
                .orElseThrow(() -> new ResourceNotFoundException("Candidate"))));
    }

    @GetMapping
    public ResponseEntity<List<CandidateDTO>> findByCompanyIdOrAccelerationId(
            @RequestParam(required = false) Long companyId,
            @RequestParam(required = false) Long accelerationId) {

        if (companyId != null)
            return ResponseEntity.ok(candidateMapper.map(candidateService.findByCompanyId(companyId)));
        if (accelerationId != null)
            return ResponseEntity.ok(candidateMapper.map(candidateService.findByAccelerationId(accelerationId)));
        return ResponseEntity.notFound().build();
    }
}
