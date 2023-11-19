package com.grubneac.CafeDemoCRM.controller;

import com.grubneac.CafeDemoCRM.model.Person;
import com.grubneac.CafeDemoCRM.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @GetMapping("/persons")
    public ResponseEntity<List<Person>> getAllPersons(){
        try {
            List<Person> allPersons = personRepository.findAll();
            if (Objects.requireNonNull(allPersons).isEmpty()){
                return new ResponseEntity(NO_CONTENT);
            } else {
                return ResponseEntity.ok(allPersons);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, INTERNAL_SERVER_ERROR);
        }
    }
}
