package com.susar.service.specialistsserviceimplementation;

import com.susar.model.specialist.Specialist;
import com.susar.repository.SpecialistRepository;
import com.susar.service.SpecialistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialistsServiceImplementation implements SpecialistService {

    private SpecialistRepository specialistRepository;

    @Autowired
    private SpecialistsServiceImplementation(SpecialistRepository specialistRepository){
        this.specialistRepository = specialistRepository;
    }

    @Override
    public Specialist create(Specialist specialist) {
        return specialistRepository.save(specialist);
    }

    @Override
    public void update(Long specialistId, Specialist specialist) {
        //Save up to date specialsit entity in team
        specialistRepository.save(specialist);
    }

    @Override
    public void delete(Long specialistId) {
        if (specialistRepository.existsById(specialistId)) {
            specialistRepository.deleteById(specialistId);
        }
    }

    @Override
    public Iterable<Specialist> getAllSpecialists() {
        return specialistRepository.findAll();
    }

    @Override
    public List<String> getStudies() {
        return null;
    }

    @Override
    public List<String> getCountries() {
        return null;
    }
}
