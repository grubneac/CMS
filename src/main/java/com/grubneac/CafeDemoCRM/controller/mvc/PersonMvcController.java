package com.grubneac.CafeDemoCRM.controller.mvc;

import com.grubneac.CafeDemoCRM.model.Person;
import com.grubneac.CafeDemoCRM.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/persons")
@AllArgsConstructor
public class PersonMvcController {

    private final PersonRepository repository;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('person:read')")
    public String listPersons(Model model) {
        List<Person> personList = repository.findAllByOrderByFirstNameAsc();
        model.addAttribute("persons", personList);

        return "persons/list-persons";
    }

    @GetMapping("/showFormAdd")
    @PreAuthorize("hasAuthority('person:write')")
    public String showFormAdd(Model model) {
        Person person = new Person();
        model.addAttribute("person", person);

        return "persons/person-form";
    }

    @PostMapping("/save")
    public String savePerson(@ModelAttribute("person") Person person) {
        repository.save(person);

        return "redirect:/persons/list";
    }

    @GetMapping("showFormUpdate")
    @PreAuthorize("hasAuthority('person:update')")
    public String showFromUpdate(@RequestParam("personId") int id, Model model) {
        Person person = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Did not find person id - " + id));
        model.addAttribute("person", person);

        return "persons/person-form";
    }

    @GetMapping("/delete")
    @PreAuthorize("hasAuthority('person:delete')")
    public String delete(@RequestParam("personId") int id) {
        repository.deleteById(id);

        return "redirect:/persons/list";
    }
}
