package com.spring.mvc.chap06.spring_jdbc;

import com.spring.mvc.chap06.entity.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// 테스트 프레임워크 : junit5 - 모든걸 default 제한자로
@SpringBootTest // 스프링이 관리하는 빈을 주입받기 위한 어노테이션
class SpringJdbcRepositoryTest {

    @Autowired
    SpringJdbcRepository repository;

    @Test
    @DisplayName("사람 정보를 DB에 저장한다")
    void saveTest() {
        //given
        Person p = new Person("99", "말똥이", 30);
        //when
        repository.save(p);
        //then
    }

    @Test
    @DisplayName("99번 회원의 이름과 나이를 수정한다.")
    void modifyTest() {
        //given
        String id = "99";
        String newName = "수정수정이";
        int newAge = 50;
        Person p = new Person(id, newName, newAge);
        //when
        repository.modify(p);
        //then
    }

    @Test
    @DisplayName("99번 회원을 삭제한다.")
    void removeTest() {
        //given
        String id = "99";
        //when
        repository.remove(id);
        //then
    }

    @Test
    @DisplayName("전체 조회를 해야한다.")
    void findAllTest() {
        //given

        //when
        List<Person> people = repository.findAll();
        //then
        //assertEquals(10, people.size());
        people.forEach(System.out::println);
    }

    @Test
    @DisplayName("30번 회원을 등록한 후 조회한다.")
    void findOneTest() {
        //given
        Person p = new Person("30", "두꺼비", 23);
        //when
        repository.save(p);

        Person foundPerson = repository.fondOne("30");
        //then
        assertEquals("두꺼비", foundPerson.getPersonName());
        System.out.println("foundPerson = " + foundPerson);
    }

}