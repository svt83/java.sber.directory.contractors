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

    /**
     * Проверка существования контрагента в базе по его полному наименованию. Выбрасывает исключение,
     * в случае нахождения контрагента с таким наименованием в базе
     *
     * @param name - наименование контрагента (поле name таблицы БД контрагентов)
     * @return - true, если контрагент с переданным наименованием существует в БД
     */
    boolean existsByName(String name);

    /**
     * Проверка существования контрагента в базе по его БИК или номеру счета. Выбрасывает исключение,
     * в случае нахождения контрагента с таким наименованием в базе
     *
     * @param bik     - БИК контрагента (поле bik таблицы БД контрагентов)
     * @param account - номер счета контрагента (поле account таблицы БД контрагентов)
     * @return - true, если контрагент с переданным БИК и номером счета существует в БД
     */
    boolean existsByBikAndAccount(String bik, String account);

    /**
     * Поиск контрагентов по наименованию (в том числе по его части)
     *
     * @param name - наименование контрагента (поле name таблицы БД контрагентов)
     * @return - список найденных контрагентов
     */
    List<Contractor> findByNameContainsIgnoreCase(String name);

    /**
     * Поиск контрагентов по БИК или номеру счета (в том числе по их части)
     *
     * @param bik      - БИК контрагента (поле bik таблицы БД контрагентов)
     * @param account- номер счета контрагента (поле account таблицы БД контрагентов)
     * @return - список найденных контрагентов
     */
    List<Contractor> findByBikContainsAndAccountContains(String bik, String account);
}