package com.benfante.examples.searchable.controller;

import java.util.List;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.benfante.examples.searchable.model.Person;
import com.benfante.examples.searchable.service.PeopleService;
import com.yourrents.services.common.searchable.Searchable;
import com.yourrents.services.common.searchable.annotation.SearchableDefault;
import com.yourrents.services.common.searchable.annotation.SearchableField;

@RestController
@RequestMapping("/ex7/people")
public class PeopleControllerEx7 {

    @Autowired
    private PeopleService peopleService;

    @GetMapping
    public List<Person> getPeople(
            @ParameterObject
            @SearchableDefault(supportedFields = {
                @SearchableField(name = "firstName", repeat = 2),
                @SearchableField(name = "lastName", repeat = 3)}) Searchable searchable) {
        return peopleService.searchPeople(searchable);
    }
}