package com.winter.web;

import com.winter.dao.PersonRepository;
import com.winter.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.Console;
import java.util.List;

@RestController
public class DataController {
    //1 Spring Data JPA已自动为你注册bean，所以可自动注入
    @Autowired
    PersonRepository personRepository;

    /**
     * 保存
     * save支持批量保存：<S extends T> Iterable<S> save(Iterable<S> entities);
     *
     * 删除：
     * 删除支持使用id，对象以，批量删除及删除全部：
     * void delete(ID id);
     * void delete(T entity);
     * void delete(Iterable<? extends T> entities);
     * void deleteAll();
     *
     */
    @RequestMapping("/save")
    public Person save(String name, String address, Integer age){
        Person p = personRepository.save(new Person(null, name, age, address));
        return p;

    }

    /**
     * 测试findByAddress
     */
    @RequestMapping("/q1")
    public List<Person> q1(String address){
        List<Person> personList=personRepository.findByAddress(address);
        return personList;
    }

    /**
     * 测试findByNameAndAddress
     * @param name
     * @param address
     * @return
     */
    @RequestMapping("/q2")
    public Person q2(String name,String address){
        Person person = personRepository.findByNameAndAddress(name,address);
        return person;
    }

    /**
     * 测试withNameAndAddressQuery
     * @param name
     * @param address
     * @return
     */
    @RequestMapping("/q3")
    public Person q3(String name,String address){
        Person person=personRepository.withNameAndAddressQuery(name,address);
        return person;
    }

    /**
     * 测试withNameAndAddressNamedQuery
     * @param name
     * @param address
     * @return
     */
    @RequestMapping("/q4")
    public Person q4(String name,String address){
        Person p=personRepository.withNameAndAddressNamedQuery(name,address);
        return p;
    }

    /**
     * 测试排序
     * @return
     */
    @RequestMapping("/sort")
    public List<Person> sort(){
        List<Person> personList=personRepository.findAll(new Sort(Direction.ASC,"age"));
        return personList;
    }

    /**
     * 测试排序
     * @return
     */
    @RequestMapping("/page")
    public Page<Person> page(){
        //替代的方法是不要new PageRequest，而是直接用 PageRequest.of这个方法
        Page<Person> pagePerson=personRepository.findAll(PageRequest.of(1,2));
        return pagePerson;
    }
}
