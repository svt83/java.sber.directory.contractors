package ru.turkov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.turkov.entity.Contractor;
import ru.turkov.repository.ContractorRepository;
import ru.turkov.service.validation.BicAccountValidService;
import ru.turkov.service.validation.InnValidService;

import javax.persistence.EntityExistsException;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Сервис реализующий взаимодействие с БД контрагентов посредством интерфейсов JPA Repository и
 * ContractorRepository
 */
@Service
public class ContractorService {

    @Autowired
    private ContractorRepository contractorRepository;

    /**
     * Поиск всех контрагентов
     *
     * @return - список всех контрагентов в БД
     */
    public List<Contractor> findAll() {
        return contractorRepository.findAll();
    }

    /**
     * Получение контрагента по его id
     *
     * @param id - идентификатор контрагента
     * @return - контрагент
     */
    public Contractor getById(Long id) {
        return contractorRepository.getById(id);
    }

    /**
     * Процедура сохранения контрагента (для создания или модификации).
     *
     * @param contractor - контрагент
     */
    @Transactional
    public void save(Contractor contractor) {
        if (!InnValidService.checkInn(contractor.getInn())) {
            throw new IllegalArgumentException("ИНН указан неверно");
        }
        BicAccountValidService.checkKeyBicAccount(contractor.getBik(), contractor.getAccount());
        if (!contractorRepository.existsById(contractor.getId())) {
            existsByName(contractor);
            existsByBikAndAccount(contractor);
        } else {
            Contractor currentContractor = contractorRepository.getById(contractor.getId());
            if (!currentContractor.getName().equals(contractor.getName())) {
                existsByName(contractor);
            }
            if (!currentContractor.getAccount().equals(contractor.getAccount()) ||
                    !currentContractor.getBik().equals(contractor.getBik())) {
                existsByBikAndAccount(contractor);
            }
        }
        contractorRepository.save(contractor);
    }

    /**
     * Удаление контрагента по id
     *
     * @param id - идентификатор контрагента
     */
    @Transactional
    public void deleteById(Long id) {
        contractorRepository.deleteById(id);
    }

    /**
     * Поиск контрагентов по наименованию (в том числе по его части)
     *
     * @param name - наименование или его часть
     * @return - список найденных контрагентов
     */
    public List<Contractor> findAllByName(String name) {
        return contractorRepository.findByNameContainsIgnoreCase(name);
    }

    /**
     * Поиск контрагентов по БИК или номеру счета (в том числе по их части)
     *
     * @param bik     - БИК или его часть
     * @param account - номер счента или его часть
     * @return - список найденных контрагентов
     */
    public List<Contractor> findAllByBikAndAccount(String bik, String account) {
        return contractorRepository.findByBikContainsAndAccountContains(bik, account);
    }

    /**
     * Проверка существования контрагента в базе по его полному наименованию. Выбрасывает исключение,
     * в случае нахождения контрагента с таким наименованием в базе
     *
     * @param contractor - контрагент
     */
    public void existsByName(Contractor contractor) {
        String name = contractor.getName();
        if (contractorRepository.existsByName(name)) {
            throw new EntityExistsException("Контрагент с наименованием \"" + name
                    + "\" уже есть в справочнике");
        }
    }

    /**
     * Проверка существования контрагента в базе по его БИК или номеру счета. Выбрасывает исключение,
     * в случае нахождения контрагента с таким наименованием в базе
     *
     * @param contractor - контрагент
     */
    public void existsByBikAndAccount(Contractor contractor) {
        String bik = contractor.getBik();
        String account = contractor.getAccount();
        if (contractorRepository.existsByBikAndAccount(bik, account)) {
            throw new EntityExistsException("Контрагент со счетом № " + account
                    + " и БИК " + bik + " уже есть в справочнике");
        }
    }
}
