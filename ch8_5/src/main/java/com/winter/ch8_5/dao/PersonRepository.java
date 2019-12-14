package com.winter.ch8_5.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.winter.ch8_5.domain.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
