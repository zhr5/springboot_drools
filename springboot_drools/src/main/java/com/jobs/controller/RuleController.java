package com.jobs.controller;

import com.jobs.entity.Calculation;
import com.jobs.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rule")
public class RuleController {
    @Autowired
    private RuleService ruleService;

    @RequestMapping("/calculate")
    public Calculation calculate(String wage) {
        Double wage1=Double.parseDouble(wage);
        try {
            Calculation calculation = new Calculation();
            calculation.setWage(wage1);
            calculation = ruleService.calculate(calculation);
            System.out.println(calculation);
            return calculation;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}