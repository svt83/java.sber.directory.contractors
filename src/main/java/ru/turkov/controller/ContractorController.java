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

    @GetMapping(path = "/")
    public String index() {
        return "redirect:/contractor-list";
    }

    @GetMapping(path = "/contractor-list")
    public ModelAndView getAllContractors() {
        List<Contractor> contractorList = contractorService.findAll();
        return new ModelAndView("contractor-list", "contractorList", contractorList);
    }

    @GetMapping(path = "/contractor-create")
    public ModelAndView createContractorForm(Contractor contractor) {
        return new ModelAndView("contractor-create", "contractor", contractor);
    }

    @PostMapping(path = "/contractor-create")
    public ModelAndView createContractor(@ModelAttribute("contractor") Contractor contractor) {
        contractorService.save(contractor);
        return new ModelAndView("redirect:/contractor-list", "contractor", contractor);
    }

    @GetMapping(path = "/contractor-update/{id}")
    public String updateContractorForm(@PathVariable("id") Long id, Model model) {
        Contractor contractor = contractorService.getById(id);
        model.addAttribute("contractor", contractor);
        return "contractor-update";
    }

    @PostMapping(path = "/contractor-update/{id}")
    public String updateContractor(Contractor contractor) {
        contractorService.save(contractor);
        return "redirect:/contractor-list";
    }

    @GetMapping(value = "/contractor-delete/{id}")
    public String deleteContractor(@PathVariable("id") Long id) {
        contractorService.deleteById(id);
        return "redirect:/contractor-list";
    }

    @GetMapping(path = "/contractor-find-name")
    public ModelAndView contractorFindNameForm(Contractor contractor) {
        return new ModelAndView("contractor-find-name", "contractor", contractor);
    }

    @PostMapping(path = "/contractor-find-name")
    public ModelAndView contractorFindName(@RequestParam(value = "name") String name) {
        List<Contractor> contractorList = null;
        if (!name.isEmpty()) {
            contractorList = contractorService.findAllByName(name);
        }
        return new ModelAndView("contractor-find-name", "contractorList", contractorList);
    }

    @GetMapping(path = "/contractor-find-bik-account")
    public ModelAndView contractorFindBikAccountForm() {
        return new ModelAndView("contractor-find-bik-account");
    }

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
