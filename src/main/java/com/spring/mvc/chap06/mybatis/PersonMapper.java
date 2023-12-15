package com.spring.mvc.chap06.mybatis;


import com.spring.mvc.chap06.entity.Person;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

// mybatis의 SQL실행을 위한 인터페이스임
@Mapper
public interface PersonMapper {

    // CRUD를 명세
    boolean save(Person p);     // INSERT

    boolean update(Person p);   // UPDATE

    boolean delete(String id);  // DELETE

    List<Person> findAll();     // SELECT

    Person findOne(String id);  // SELECT
}
