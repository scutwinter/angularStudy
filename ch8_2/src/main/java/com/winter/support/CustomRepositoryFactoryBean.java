package com.winter.support;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * repository装载工厂重写
 * @param <T>
 * @param <S>
 * @param <ID>
 */
public class CustomRepositoryFactoryBean<T extends JpaRepository<S,ID>,S,ID extends Serializable> extends JpaRepositoryFactoryBean<T,S,ID> {

    /**
     * 旧的方式不需要构造函数
     * 新的springboot 需要构造函数
     * @param repositoryInterface
     */
    public CustomRepositoryFactoryBean(Class<? extends T> repositoryInterface) {
        super(repositoryInterface);
    }

    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        return new CustomRepositoryFactory(entityManager);
    }

    private static class CustomRepositoryFactory extends JpaRepositoryFactory{

        private final EntityManager entityManager;

        public CustomRepositoryFactory(EntityManager entityManager){
            super(entityManager);
            this.entityManager=entityManager;
        }
  /*
        这是旧版本springboot重写方式
        @Override
        @SuppressWarnings({"unchecked"})
        protected <T, ID extends Serializable> SimpleJpaRepository<?, ?> getTargetRepository(
                RepositoryInformation information, EntityManager entityManager) {// 4
            return new CustomRepositoryImpl<T, ID>((Class<T>) information.getDomainType(), entityManager);

        }
  */
        //设置=实现类是CustomRepositoryImpl
        @Override
        protected JpaRepositoryImplementation<?, ?> getTargetRepository(RepositoryInformation information, EntityManager entityManager) {
            JpaEntityInformation<?, Serializable> entityInformation = this.getEntityInformation(information.getDomainType());
            Object repository = this.getTargetRepositoryViaReflection(information, new Object[]{entityInformation, entityManager});
            Assert.isInstanceOf(CustomRepositoryImpl.class, repository);
            return (JpaRepositoryImplementation)repository;
        }



        @Override
        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
            return CustomRepositoryImpl.class;
        }
    }
}
