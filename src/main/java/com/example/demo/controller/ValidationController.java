package com.example.demo.controller;

import com.example.demo.DTO.ValidationDTO;
import com.example.demo.service.TestAsync;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/validation")
@RestController
public class ValidationController {

    @Autowired
    TestAsync testAsync;
    @RequestMapping("/validateVo.json")
    public void test(@Validated ValidationDTO validation, BindingResult bindingResult ){
        testAsync.testAsync();
        if (bindingResult.hasErrors()){
            List<FieldError> list2 = bindingResult.getFieldErrors();
            list2.forEach(fieldError -> {
                System.out.println(fieldError.getField()+fieldError.getDefaultMessage());
            });
        }
        System.out.println("main is done");
    }


}
