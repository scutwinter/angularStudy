package com.winter.support;


import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import static com.winter.specs.CustomerSpecs.*;
/**
 * 定义实现
 * 此类继承JpaRepository的实现类SimpleJpaRepository,让我们可以使用SimpleJpaRepository的方法；
 * 此类当然还要实现我们自定义接口的CustomRepository
 * @param <T>
 * @param <ID>
 */
public class CustomRepositoryImpl <T, ID extends Serializable>
        extends SimpleJpaRepository<T, ID>  implements CustomRepository<T,ID> {

    private final EntityManager entityManager;

    /**
     * 旧的构造方式
     * @param entityInformation
     * @param entityManager
     */
//    public CustomRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
//        super(domainClass, entityManager);
//        this.entityManager = entityManager;
//    }

    /**
     * 新构造方式
     * @param entityInformation
     * @param entityManager
     */
    public CustomRepositoryImpl(JpaEntityInformation<T, ID> entityInformation,
                         EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Page<T> findByAuto(T example, Pageable pageable) {
        return findAll(byAuto(entityManager, example),pageable);
    }


}
