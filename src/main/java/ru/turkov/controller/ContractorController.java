package ru.turkov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ru.turkov.entity.Contractor;
import ru.turkov.entity.ContractorInputForm;
import ru.turkov.service.ContractorService;

import java.util.List;

@RestController
public class ContractorController {

    @Autowired
    private ContractorService contractorService;

    @RequestMapping(path = "/contractors", method = RequestMethod.GET)
    public ModelAndView getAllContractors(){
        List<Contractor> contractorList = contractorService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("contractors");
        modelAndView.addObject("contractorList", contractorList);
        return modelAndView;
    }

    @RequestMapping(path = "/contractors", method = RequestMethod.POST)
    public String addContractor(ContractorInputForm contractorInputForm) {
        Contractor newContractor = Contractor.from(contractorInputForm);
        contractorService.save(newContractor);
        return "redirect:/contractors";
    }
}
