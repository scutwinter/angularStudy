package com.winter.ch8_5.service.impl;

import com.winter.ch8_5.dao.PersonRepository;
import com.winter.ch8_5.domain.Person;
import com.winter.ch8_5.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Optional;

@Service
public class DemoServiceImpl implements DemoService {
    @Autowired
    PersonRepository personRepository;

    @Override
    /**
     * @CachePut缓存新增的或更新的数据到缓存，其中缓存名称为people,数据的key是person的id
     */
    @CachePut(value="people",key="#person.id")
    public Person save(Person person){
        Person p=personRepository.save(person);
        System.out.println("为id,key为:"+p.getId()+"数据做了缓存");
        return p;
    }

    /**
     * @CacheEvict从缓存people中删除key为id的数据
     * @param id
     */
    @Override
    @CacheEvict(value="people")
    public void remove(Long id){
        System.out.println("删除了id,key为"+id+"的数据缓存");

//        personRepository.deleteById(id);这句会导致在删除缓存的时候数据也被删除了。
    }

    /**
     * @Cacheable 缓存key为person的id数据到缓存people中
     * @param person
     * @return
     */
    @Override
    @Cacheable(value="people",key="#person.id")
    public Person findOne(Person person){
        Optional<Person> result = personRepository.findById(person.getId());
        Person p=result.isPresent()?result.get():null;
        if(p!=null) {
            System.out.println("为id,key为："+p.getId()+"数据做了缓存");
        }
        else{
            System.out.println("person取出来是null值");
        }

        return p;
    }

    @Override
    public List<Person> findAll(){
        return personRepository.findAll();
    }


}
