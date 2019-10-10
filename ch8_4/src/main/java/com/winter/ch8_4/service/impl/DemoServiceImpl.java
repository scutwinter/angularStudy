package com.winter.ch8_4.service.impl;

import com.winter.ch8_4.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.winter.ch8_4.dao.PersonRepository;
import com.winter.ch8_4.domain.Person;

@Service
public class DemoServiceImpl implements DemoService {
    @Autowired
    PersonRepository personRepository; //1

    @Transactional(rollbackFor={IllegalArgumentException.class})
    public Person savePersonWithRollBack(Person person){
        Person p=personRepository.save(person);
        if(person.getName().equals("汪云飞")){
            throw new IllegalArgumentException("汪云飞已经存在，数据将回滚");
        }
        return p;
    }

    @Transactional(noRollbackFor = {IllegalArgumentException.class})
    public Person savePersonWithoutRollBack(Person person){
        Person p=personRepository.save(person);
        if(person.getName().equals("汪云飞")){
            throw new IllegalArgumentException("汪云飞已经存在，数据将不回滚");
        }
        return p;
    }
}
