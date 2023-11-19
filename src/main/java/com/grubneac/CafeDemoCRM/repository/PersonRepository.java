package com.grubneac.CafeDemoCRM.repository;

import com.grubneac.CafeDemoCRM.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    List<Person> findByFirstName(String example);
    List<Person> findByLastName(String example);
}
