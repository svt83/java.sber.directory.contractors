package ru.turkov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.turkov.entity.Contractor;

import java.util.List;

/**
 * Интерфейс описывающий необходимые операции с базой данных контрагентов при помощи префиксов
 */
@Repository
public interface ContractorRepository extends JpaRepository<Contractor, Long> {

    boolean existsByName(String name);

    boolean existsByBikAndAccount(String bik, String account);

    List<Contractor> findByNameContainsIgnoreCase(String name);

    List<Contractor> findByBikContainsAndAccountContains(String bik, String account);
}