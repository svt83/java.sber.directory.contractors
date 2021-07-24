package ru.turkov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.turkov.entity.Contractor;
import ru.turkov.service.ContractorService;

import java.util.List;

/**
 * Класс контроллера, реализующий методы обработки запросов в приложении
 */
@Controller
public class ContractorController {

    @Autowired
    private ContractorService contractorService;

    /**
     * Перенаправление с "/" на стартовую страницу справочника
     *
     * @return - строка с именем представления стартовой страницы справочника
     */
    @GetMapping(path = "/")
    public String index() {
        return "redirect:/contractor-list";
    }

    /**
     * Обработка запроса для запуска справочника контрагентов
     *
     * @return - ModelAndView стартовой страницы справочника
     */
    @GetMapping(path = "/contractor-list")
    public ModelAndView getAllContractors() {
        List<Contractor> contractorList = contractorService.findAll();
        return new ModelAndView("contractor-list", "contractorList", contractorList);
    }

    /**
     * Обработка запроса на переход в раздел создания нового контрагента
     *
     * @param contractor - контрагент
     * @return - ModelAndView формы создания нового контрагента
     */
    @GetMapping(path = "/contractor-create")
    public ModelAndView createContractorForm(Contractor contractor) {
        return new ModelAndView("contractor-create", "contractor", contractor);
    }

    /**
     * Обработка запроса для сохранения нового контрагента
     *
     * @param contractor - контрагент
     * @return - ModelAndView сохранения нового контрагента
     */
    @PostMapping(path = "/contractor-create")
    public ModelAndView createContractor(@ModelAttribute("contractor") Contractor contractor) {
        contractor = contractorService.save(contractor);
        return new ModelAndView("redirect:/contractor-list", "contractor", contractor);
    }

    /**
     * Обработка запроса на изменение контрагента
     *
     * @param id    - идентификатор контрагента
     * @param model - модель для контрагента
     * @return - строка с именем представления страницы удаления контрагента
     */
    @GetMapping(path = "/contractor-update/{id}")
    public String updateContractorForm(@PathVariable("id") Long id, Model model) {
        Contractor contractor = contractorService.getById(id);
        model.addAttribute("contractor", contractor);
        return "contractor-update";
    }

    /**
     * Обработка POST запроса на изменение контрагента из таблицы БД
     *
     * @param contractor - контрагент
     * @return - строка с именем представления стартовой страницы справочника
     */
    @PostMapping(path = "/contractor-update/{id}")
    public ModelAndView updateContractor(Contractor contractor) {
        contractorService.save(contractor);
        return new ModelAndView("redirect:/contractor-list", "contractor", contractor);
    }

    /**
     * Обработка запроса на удаление контрагента из таблицы БД
     *
     * @param id - идентификатор контрагента
     * @return - строка с именем представления стартовой страницы справочника
     */
    @GetMapping(value = "/contractor-delete/{id}")
    public String deleteContractor(@PathVariable("id") Long id) {
        contractorService.deleteById(id);
        return "redirect:/contractor-list";
    }

    /**
     * Обработка запроса на переход в раздел поиска контрагента по наименованию
     *
     * @param contractor - контрагент
     * @return - ModelAndView страницы поиска контрагента
     */
    @GetMapping(path = "/contractor-find-name")
    public ModelAndView contractorFindNameForm(Contractor contractor) {
        return new ModelAndView("contractor-find-name", "contractor", contractor);
    }

    /**
     * Обработка запроса на результат поиска контрагента по наименованию
     *
     * @param name - наименование контрагента
     * @return - ModelAndView страницы поиска контрагента
     */
    @PostMapping(path = "/contractor-find-name")
    public ModelAndView contractorFindName(@RequestParam(value = "name") String name) {
        List<Contractor> contractorList = null;
        if (!name.isEmpty()) {
            contractorList = contractorService.findAllByName(name);
        }
        return new ModelAndView("contractor-find-name", "contractorList", contractorList);
    }

    /**
     * Обработка запроса на переход в раздел поиска контрагента по БИК и номеру счета
     *
     * @return - ModelAndView страницы поиска контрагента
     */
    @GetMapping(path = "/contractor-find-bik-account")
    public ModelAndView contractorFindBikAccountForm() {
        return new ModelAndView("contractor-find-bik-account");
    }

    /**
     * Обработка запроса на результат поиска контрагента по БИК и номеру счета
     *
     * @param bik     - БИК контрагента
     * @param account - номер счета контрагента
     * @return - ModelAndView страницы поиска контрагента
     */
    @PostMapping(path = "/contractor-find-bik-account")
    public ModelAndView contractorFindBikAndAccount(@RequestParam(value = "bik") String bik,
                                                    @RequestParam(value = "account") String account) {
        List<Contractor> contractorList = null;
        if (!bik.isEmpty() || !account.isEmpty()) {
            contractorList = contractorService.findAllByBikAndAccount(bik, account);
        }
        return new ModelAndView("contractor-find-bik-account", "contractorList", contractorList);
    }
}
