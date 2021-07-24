package ru.turkov;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.turkov.entity.Contractor;
import ru.turkov.service.ContractorService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class ContractorControllerTest {
    private final Contractor[] CONTRACTOR_OK = {Contractor.builder().name("ИП Петров А.А.").inn("616608929424")
            .kpp("123456789").bik("044525225").account("40702810938000000001").build(),
            Contractor.builder().name("ИП Иванов А.А.").inn("773576240338").kpp("770401001")
                    .bik("044525600").account("40702810400260004426").build(),
            Contractor.builder().name("ИП Изыргина ЕА").inn("540132550698").kpp("123456789")
                    .bik("044525631").account("40802810700000008286").build()};

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ContractorService contractorService;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void beforeEach() {
        contractorService.findAll().forEach(contractor -> contractorService.deleteById(contractor.getId()));
    }

    /**
     * Тест на обработку запроса при запуске справочника контрагентов (стартовая страница).
     * Получаем список всех контрагентов, имеющихся в базе.
     */
    @Test
    public void testDisplayAllContractors() throws Exception {
        List<Contractor> source = Arrays.stream(CONTRACTOR_OK).collect(Collectors.toList());
        source.forEach(contractor -> contractorService.save(contractor));
        mvc.perform(get("/contractor-list"))
                .andExpect(view().name("contractor-list"))
                .andExpect(forwardedUrl("/WEB-INF/views/contractor-list.jsp"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("contractorList", source));
    }

    /**
     * Тест на обработку запроса по добавлению контрагента.
     */
    @Test
    public void testCreateContractors() throws Exception {
        mvc.perform(post("/contractor-create")
                        .contentType("application/x-www-form-urlencoded")
                        .content("name=ИП+Иванов+А.А.&inn=773576240338&kpp=770401001&bik=044525600&account=40702810400260004426"))
                .andExpect(redirectedUrl("/contractor-list"))
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("contractor"));
    }

    /**
     * Тест на обработку запроса по изменению контрагента
     */
    @Test
    public void testUpdateContractor() throws Exception {
        Contractor contractor = contractorService.save(CONTRACTOR_OK[1]);
        contractor.setName("ИП А.А. Иванов");
        mvc.perform(post("/contractor-update/" + contractor.getId())
                        .contentType("application/x-www-form-urlencoded")
                        .content("name=ИП+А.А.+Иванов&inn=773576240338&kpp=770401001&bik=044525600&account=40702810400260004426"))
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attribute("contractor", contractor));
    }

    /**
     * Тест на обработку запроса по удалению контрагента
     */
    @Test
    public void testDeleteContractor() throws Exception {
        Contractor contractor = contractorService.save(CONTRACTOR_OK[1]);
        mvc.perform(get("/contractor-delete/" + contractor.getId())).andExpect(status().is3xxRedirection());
        Assertions.assertEquals(0, contractorService.findAll().size());
    }

    /**
     * Тест на обработку запроса поиска контрагента по наименованию
     */
    @Test
    public void testFindContractorByName() throws Exception {
        contractorService.save(CONTRACTOR_OK[0]);
        contractorService.save(CONTRACTOR_OK[1]);
        List<Contractor> result = new ArrayList<>();
        result.add(CONTRACTOR_OK[1]);
        mvc.perform(post("/contractor-find-name")
                        .contentType("application/x-www-form-urlencoded")
                        .content("name=ИП+Иванов+А.А."))
                .andExpect(status().isOk())
                .andExpect(model().attribute("contractorList", result));
    }

    /**
     * Тест на обработку запроса поиска контрагента по БИК
     */
    @Test
    public void testFindContractorByBicAndAccount() throws Exception {
        contractorService.save(CONTRACTOR_OK[0]);
        contractorService.save(CONTRACTOR_OK[1]);
        List<Contractor> result = new ArrayList<>();
        result.add(CONTRACTOR_OK[1]);
        mvc.perform(post("/contractor-find-bik-account")
                        .contentType("application/x-www-form-urlencoded")
                        .content("bik=044525600&account="))
                .andExpect(status().isOk())
                .andExpect(model().attribute("contractorList", result));
    }
}