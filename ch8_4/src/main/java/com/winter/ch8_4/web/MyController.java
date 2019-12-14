package com.winter.ch8_4.web;

import com.winter.ch8_4.service.DemoService;
import com.winter.ch8_4.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    @Autowired
    DemoService demoService;

    @RequestMapping("/rollback")
    public Person rollback(Person p){
        return demoService.savePersonWithRollBack(p);
    }

    @RequestMapping("/norollback")
    public Person noRollback(Person person){
        return demoService.savePersonWithoutRollBack(person);
    }
}
