package com.jobs.controller;

import com.jobs.entity.Calculation;
import com.jobs.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
@RestController
@RequestMapping("/rule")
public class RuleController {
    @Autowired
    private RuleService ruleService;

    @RequestMapping("/calculate")
    public Calculation calculate(double wage) {
        try {
            Calculation calculation = new Calculation();
            calculation.setWage(wage);
            calculation = ruleService.calculate(calculation);
            System.out.println(calculation);
            return calculation;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/test")
    public void test() {
        ClassPathResource resource = new ClassPathResource("rule.drl");
        System.out.println(resource.exists());
        File file = new File("src\\/main\\/resources\\/rule.drl");
        System.out.println(file.exists());
    }
}