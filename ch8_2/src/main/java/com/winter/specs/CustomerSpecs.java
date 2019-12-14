package com.winter.specs;

import static com.google.common.collect.Iterables.toArray;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.SingularAttribute;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class CustomerSpecs {
    /**
     * 1 定义一个返回值为Specification的方法byAuto,这里使用的室泛型T，所以这个Specification是可以用于任意的实体类
     *   它接受的参数是entityManager和当前的包含值作为查询条件的实体类对象
     * @param entityManager
     * @param example
     * @param <T>
     * @return
     */
    public static <T> Specification<T>byAuto(final EntityManager entityManager,final T example){
        //2 获得当前实体类对象类的类型
        final Class<T> type=(Class<T>) example.getClass();
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates=new ArrayList<>(); //3 新建Predicate列表存储构造的查询条件

                //4 获得实体类的EntityType,我们可以从EntityType获得实体类的属性
                EntityType<T> entityType=entityManager.getMetamodel().entity(type);

                //5 对实体类的所有属性进行循环
                for (Attribute<T,?> attr:entityType.getDeclaredAttributes()){
                    //6 获得实体类对象某个一个属性的值
                    Object attrValue=getValue(example,attr);
                    if(attrValue!=null){
                        if(attr.getJavaType()==String.class){ //7 当前属性为字符串的时候
                            if(!StringUtils.isEmpty(attrValue)){//8 若当前字符不为空的情况下
                                //9 构造当前属性like（前后%）属性值查询条件，并添加到条件列表中
                                predicates.add(criteriaBuilder.like(root.get(attribute(entityType,attr.getName(),String.class)),pattern((String) attrValue)));
                            }
                        }else{
                            //10 其余情况下，构造属性和属性值equal查询条件，并添加到条件列表中
                            predicates.add(criteriaBuilder.equal(root.get(attribute(entityType,attr.getName(),attrValue.getClass())),attrValue));
                        }
                    }
                }
                //11 将条件列表转成Predicate
                return predicates.isEmpty()? criteriaBuilder.conjunction():criteriaBuilder.and(toArray(predicates,Predicate.class));
            }

            /**
             * 通过反射获得实体类对象对应属性的属性值
             * @param example
             * @param attr
             * @param <T>
             * @return
             */
            private <T> Object getValue(T example,Attribute<T,?> attr){
                return ReflectionUtils.getField((Field) attr.getJavaMember(),example);
            }


            /**
             * 获得实体类的当前属性的SingularAttribute,SingularAttribute包含的是实体类的某个单独属性
             * @param entity
             * @param fieldname
             * @param fieldClass
             * @param <E>
             * @param <T>
             * @return
             */
            private<E,T>SingularAttribute<T,E> attribute(EntityType<T> entity,String fieldname,Class<E> fieldClass){
                return entity.getDeclaredSingularAttribute(fieldname,fieldClass);
            }
        };
    }

    /**
     * 构造like 的查询模式，即前后加%
     * @param str
     * @return
     */
    static private String pattern(String str){
        return "%" + str + "%";
    }
}
