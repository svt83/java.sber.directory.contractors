package ru.turkov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import ru.turkov.entity.Contractor;
import ru.turkov.service.ContractorService;

import javax.persistence.EntityExistsException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@ComponentScan("ru.turkov")
@ContextConfiguration(classes = {ContractorService.class})
public class ContractorServiceTest {
    private final Contractor[] CONTRACTOR_OK = {Contractor.builder().name("ИП Петров А.А.").inn("616608929424")
            .kpp("123456789").bik("044525225").account("40702810938000000001").build(),
            Contractor.builder().name("ИП Иванов А.А.").inn("773576240338").kpp("770401001")
                    .bik("044525600").account("40702810400260004426").build(),
            Contractor.builder().name("ИП Изыргина ЕА").inn("540132550698").kpp("123456789")
                    .bik("044525631").account("40802810700000008286").build()};

    private final Contractor DUPLICATE_BY_NAME = Contractor.builder().name("ИП Петров А.А.")
            .inn("616608929424").kpp("123456789").bik("044525225").account("40702810938000000001").build();

    private final Contractor DUPLICATE_BY_BIK_ACCOUNT = Contractor.builder().name("ИП Сидоров В.В.")
            .inn("616608929424").kpp("123456789").bik("044525225").account("40702810938000000001").build();

    private final Contractor INVALID_INN = Contractor.builder().name("ООО Рога и копыта")
            .inn("1234567890").kpp("123456789").bik("044525225").account("40702810938000000001").build();

    private final Contractor INVALID_BIK = Contractor.builder().name("ООО Рога и копыта")
            .inn("773576240338").kpp("123456789").bik("123456789").account("40702810938000000001").build();

    private final Contractor INVALID_ACCOUNT = Contractor.builder().name("ООО Рога и копыта")
            .inn("773576240338").kpp("123456789").bik("044525225").account("12345678900987654321").build();

    @Autowired
    private ContractorService contractorService;

    /**
     * Тест на создание одной записи контрагента
     */
    @Test
    public void testCreateOneContractorOK() throws IllegalAccessException {
        long id = contractorService.save(CONTRACTOR_OK[1]).getId();
        Contractor contractor = contractorService.getById(id);
        for (Field field : Contractor.class.getDeclaredFields()) {
            field.setAccessible(true);
            Assertions.assertEquals(field.get(contractor), field.get(CONTRACTOR_OK[1]));
        }
    }

    /**
     * Тест на создание нескольких записей контрагентов
     */
    @Test
    public void testCreateManyContractorsOK() {
        Arrays.stream(CONTRACTOR_OK).forEach(contractor -> contractorService.save(contractor));
        contractorService.save(CONTRACTOR_OK[0]);
        assertThat(contractorService.findAll()).hasSize(CONTRACTOR_OK.length);
    }

    /**
     * Тест на обновление записи (наименование) контрагента
     */
    @Test
    public void testUpdateContractor() {
        long id = contractorService.save(CONTRACTOR_OK[1]).getId();
        Contractor existContractor = contractorService.getById(id);
        Assertions.assertEquals("ИП Иванов А.А.", existContractor.getName());
        existContractor.setName("ИП А.А. Иванов");
        contractorService.save(existContractor);
        Assertions.assertEquals("ИП А.А. Иванов", contractorService.getById(id).getName());
    }

    /**
     * Тест на удаление записей контрагентов из таблицы
     */
    @Test
    public void testDelete() {
        long id1 = contractorService.save(CONTRACTOR_OK[0]).getId();
        long id2 = contractorService.save(CONTRACTOR_OK[1]).getId();
        assertThat(contractorService.findAll()).hasSize(2);
        contractorService.deleteById(id1);
        assertThat(contractorService.findAll()).hasSize(1);
        contractorService.deleteById(id2);
        assertThat(contractorService.findAll()).isEmpty();
    }

    /**
     * Тест на попытку добавления контрагента с повторяющимся именем
     */
    @Test
    public void testDuplicateContractorByName() {
        contractorService.save(CONTRACTOR_OK[0]);
        Exception exception_name = Assertions.assertThrows(EntityExistsException.class,
                () -> contractorService.save(DUPLICATE_BY_NAME));
        Assertions.assertEquals("Контрагент с наименованием \"ИП Петров А.А.\" уже есть в справочнике",
                exception_name.getMessage());
    }

    /**
     * Тест на попытку добавления контрагента с повторяющимся БИК и номером счета
     */
    @Test
    public void testDuplicateContractorByBicAccount() {
        contractorService.save(CONTRACTOR_OK[0]);
        Exception exception_bik = Assertions.assertThrows(EntityExistsException.class,
                () -> contractorService.save(DUPLICATE_BY_BIK_ACCOUNT));
        Assertions.assertEquals("Контрагент со счетом № 40702810938000000001 и БИК 044525225 уже есть в справочнике",
                exception_bik.getMessage());
    }

    /**
     * Тест на поиск контрагента по имени
     */
    @Test
    public void testFindContractorByName() {
        Arrays.stream(CONTRACTOR_OK).forEach(contractor -> contractorService.save(contractor));
        List<Contractor> contractorListOne = contractorService.findAllByName("ИП Петров А.А.");
        Assertions.assertEquals(1, contractorListOne.size());
        Assertions.assertEquals("ИП Петров А.А.", contractorListOne.get(0).getName());
        List<Contractor> contractorListTwo = contractorService.findAllByName("Not exist");
        Assertions.assertTrue(contractorListTwo.isEmpty());
    }

    /**
     * Тест на поиск контрагента по БИК и номеру счета
     */
    @Test
    public void testFindContractorByBicAndAccount() {
        Arrays.stream(CONTRACTOR_OK).forEach(contractor -> contractorService.save(contractor));
        List<Contractor> result = contractorService.findAllByBikAndAccount("", "40702810938000000001");
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("40702810938000000001", result.get(0).getAccount());
        result = contractorService.findAllByBikAndAccount("044525225", "");
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("044525225", result.get(0).getBik());
        result = contractorService.findAllByBikAndAccount("1234567890", "");
        Assertions.assertTrue(result.isEmpty());
        result = contractorService.findAllByBikAndAccount("", "1234567890");
        Assertions.assertTrue(result.isEmpty());
    }

    /**
     * Тест на попытку добавления контрагента с невалидным ИНН
     */
    @Test
    public void testBadInnCreateContractor() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> contractorService.save(INVALID_INN));
        Assertions.assertEquals("ИНН указан неверно", exception.getMessage());
    }

    /**
     * Тест на попытку добавления контрагента с невалидным БИК
     */
    @Test
    public void testBadBikCreateContractor() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> contractorService.save(INVALID_BIK));
        Assertions.assertEquals("Номер счета или БИК указан неверно", exception.getMessage());
    }

    /**
     * Тест на попытку добавления контрагента с невалидным БИК
     */
    @Test
    public void testBadAccountCreateContractor() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> contractorService.save(INVALID_ACCOUNT));
        Assertions.assertEquals("Номер счета или БИК указан неверно", exception.getMessage());
    }
}