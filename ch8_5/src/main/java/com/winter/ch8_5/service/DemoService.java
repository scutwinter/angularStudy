package com.winter.ch8_5.service;

import com.winter.ch8_5.domain.Person;

import java.util.List;

public interface DemoService {
    public Person save(Person person);

    public void remove(Long id);

    public Person findOne(Person person);

    public List<Person> findAll();
}
