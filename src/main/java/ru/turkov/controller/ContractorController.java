package ru.turkov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.turkov.entity.Contractor;
import ru.turkov.service.ContractorService;

import java.util.List;

@Controller
public class ContractorController {

    @Autowired
    private ContractorService contractorService;

    @GetMapping(path = "/")
    public String index() {
        return "redirect:/contractor-list";
    }
//    @GetMapping(path = "/contractor-list")
//    public String getAllContractors(Model model) {
//        List<Contractor> contractorList = contractorService.findAll();
//        model.addAttribute("contractorList", contractorList);
//        return "contractor-list";
//    }
    @GetMapping(path = "/contractor-list")
    public ModelAndView getAllContractors() {
        List<Contractor> contractorList = contractorService.findAll();
        return new ModelAndView("contractor-list", "contractorList", contractorList);
    }

    @RequestMapping(path = "/contractor-create", method = RequestMethod.GET)
    public ModelAndView createContractorForm(@ModelAttribute("contractor") Contractor contractor) {
        return new ModelAndView("contractor-create", "contractor", contractor);
    }

    @RequestMapping(path = "/contractor-create", method = RequestMethod.POST)
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

    @GetMapping("/contractor-delete/{id}")
    public String deleteContractor(@PathVariable("id") Long id) {
        contractorService.deleteById(id);
        return "redirect:/contractor-list";
    }
}
