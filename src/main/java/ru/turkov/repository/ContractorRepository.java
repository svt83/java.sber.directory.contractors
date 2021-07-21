package ru.turkov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.turkov.entity.Contractor;

public interface ContractorRepository extends JpaRepository<Contractor, Long> {
}