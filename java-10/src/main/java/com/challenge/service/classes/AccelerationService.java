package com.challenge.service.classes;

import com.challenge.entity.Acceleration;
import com.challenge.repository.AccelerationRepository;
import com.challenge.service.interfaces.AccelerationServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccelerationService implements AccelerationServiceInterface {
    @Autowired
    private AccelerationRepository accelerationRepository;

    public Optional<Acceleration> findAccelerationById(Long id){
        return this.accelerationRepository.findAccelerationById(id);
    }

    public Optional<Acceleration> findAccelerationByName(String name){
        return this.accelerationRepository.findAccelerationByName(name);
    }

    @Override
    public Optional<Acceleration> findById(Long id) {
        return this.accelerationRepository.findById(id);
    }

    @Override
    public List<Acceleration> findByCompanyId(Long companyId) {
        return this.accelerationRepository.findByCompanyId(companyId);
    }

    @Override
    public Acceleration save(Acceleration acceleration) {
        return this.accelerationRepository.save(acceleration);
    }
}
