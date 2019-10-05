package com.winter.ch8_3.dao;
import com.winter.ch8_3.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path = "people")
public interface PersonRepository extends JpaRepository<Person,Long> {

    @RestResource(path="nameStartsWith",rel="nameStartsWithRel")
    Person findByNameStartsWith(@Param("name") String name);
}
