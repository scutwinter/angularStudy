package com.winter.ch8_5.web;

import com.winter.ch8_5.domain.Person;
import com.winter.ch8_5.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CacheController {
    @Autowired
    DemoService demoService;

    @RequestMapping("/put")
    public Person put(Person person){
        return demoService.save(person);
    }

    @RequestMapping("/able")
    public Person cacheable(Person person){
        System.out.println("已经执行了第一步"+person.getId().toString());
        return demoService.findOne(person);
    }

    @RequestMapping("/evit")
    public String evit(Long id){
        demoService.remove(id);
        return "ok";
    }

    @RequestMapping("/all")
    public List<Person> findAll(){
        return demoService.findAll();
    }
}
