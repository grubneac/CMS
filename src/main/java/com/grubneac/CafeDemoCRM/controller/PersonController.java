package com.grubneac.CafeDemoCRM.controller;

import com.grubneac.CafeDemoCRM.model.Person;
import com.grubneac.CafeDemoCRM.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<Person>> getAllPersons() {
        try {
            List<Person> allPersons = personRepository.findAll();
            if (Objects.requireNonNull(allPersons).isEmpty()) {
                return new ResponseEntity<>(NO_CONTENT);
            } else {
                return ResponseEntity.ok(allPersons);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/persons/{id}")
    public ResponseEntity<Person> getPersonNyId(@PathVariable("id") Integer personId) throws Exception {
        Person person = personRepository.findById(personId).
                orElseThrow(() -> new Exception("Person " + personId + " not found"));
        return ResponseEntity.ok(person);
    }

    @PostMapping("/persons")
    public Person createPerson(@Validated @RequestBody Person person) {
        return personRepository.save(person);
    }

    @PutMapping("/persons/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable("id") Integer personId,
                                               @Validated @RequestBody Person personDetail) throws Exception {
        Person person = personRepository.findById(personId).
                orElseThrow(() -> new Exception("Person " + personId + " not found"));
        person.setFirstName(personDetail.getFirstName());
        person.setLastName(personDetail.getLastName());
        Person updatedPerson = personRepository.save(person);
        return ResponseEntity.ok(updatedPerson);
    }

    @DeleteMapping("persons/{id}")
    public Boolean deletePerson(@PathVariable("id") Integer personId) throws Exception {
        Person person = personRepository.findById(personId).
                orElseThrow(() -> new Exception("Person " + personId + " not found"));
        personRepository.delete(person);
        return true;
    }

}
