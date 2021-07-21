package ru.turkov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.turkov.entity.Contractor;
import ru.turkov.repository.ContractorRepository;

import java.util.List;

@Service
public class ContractorService {

    @Autowired
    private ContractorRepository contractorRepository;

    public List<Contractor> findAll() {
        return contractorRepository.findAll();
    }

    public void save(Contractor contractor){
        contractorRepository.save(contractor);
    }
}
