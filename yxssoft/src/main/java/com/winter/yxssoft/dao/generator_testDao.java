package com.winter.yxssoft.dao;

import com.winter.yxssoft.entity.GeneratorTest;

public interface generator_testDao {
    int deleteByPrimaryKey(Long id);

    int insert(GeneratorTest record);

    int insertSelective(GeneratorTest record);

    GeneratorTest selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GeneratorTest record);

    int updateByPrimaryKey(GeneratorTest record);
}