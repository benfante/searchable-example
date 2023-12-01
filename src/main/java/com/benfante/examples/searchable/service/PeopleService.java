package com.benfante.examples.searchable.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.benfante.examples.searchable.model.Person;
import com.yourrents.services.common.searchable.EnumCombinator;
import com.yourrents.services.common.searchable.FilterCondition;
import com.yourrents.services.common.searchable.FilterCriteria;
import com.yourrents.services.common.searchable.Searchable;

@Service
public class PeopleService {

    private List<Person> people = List.of(
            new Person(1L, "John", "Doe", UUID.fromString("123e4567-e89b-12d3-a456-426614174000")),
            new Person(2L, "Jane", "Doe", UUID.fromString("123e4567-e89b-12d3-a456-426614174001")),
            new Person(3L, "John", "Smith", UUID.fromString("123e4567-e89b-12d3-a456-426614174002")),
            new Person(4L, "Jane", "Smith", UUID.fromString("123e4567-e89b-12d3-a456-426614174003")),
            new Person(5L, "John", "Jones", UUID.fromString("123e4567-e89b-12d3-a456-426614174004")),
            new Person(6L, "Jane", "Jones", UUID.fromString("123e4567-e89b-12d3-a456-426614174005")),
            new Person(7L, "John", "Williams", UUID.fromString("123e4567-e89b-12d3-a456-426614174006")),
            new Person(8L, "Jane", "Williams", UUID.fromString("123e4567-e89b-12d3-a456-426614174007")),
            new Person(9L, "John", "Brown", UUID.fromString("123e4567-e89b-12d3-a456-426614174008")),
            new Person(10L, "Jane", "Brown", UUID.fromString("123e4567-e89b-12d3-a456-426614174009")));

    public List<Person> searchPeople(Searchable searchable) {
        Optional<FilterCondition> firstNameCondition = getSearchCondition(searchable, "firstName");
        Optional<FilterCondition> lastNameCondition = getSearchCondition(searchable, "lastName");
        Optional<FilterCondition> uuidCondition = getSearchCondition(searchable, "uuid");
        if (searchable.getCombinator().equals(EnumCombinator.AND)) {
            return people.stream().filter(person -> {
                return firstNameCondition.map(sc -> ((String)sc.getValue()).toUpperCase().contains(person.firstName().toUpperCase()))
                        .orElse(true)
                        && lastNameCondition.map(sc -> ((String)sc.getValue()).toUpperCase().contains(person.lastName().toUpperCase()))
                                .orElse(true)
                        && uuidCondition.map(sc -> sc.getValue().equals(person.uuid()))
                                .orElse(true);
            }).toList();
        } else {
            return people.stream().filter(person -> {
                return firstNameCondition.map(sc -> ((String)sc.getValue()).toUpperCase().contains(person.firstName().toUpperCase()))
                        .orElse(false)
                        || lastNameCondition.map(sc -> ((String)sc.getValue()).toUpperCase().contains(person.lastName().toUpperCase()))
                                .orElse(false)
                        || uuidCondition.map(sc -> sc.getValue().equals(person.uuid()))
                                .orElse(false);
            }).toList();

        }
    }

    private Optional<FilterCondition> getSearchCondition(Searchable searchable, String field) {
        return ((FilterCriteria) searchable).getFilter().stream()
                .filter(sc -> sc.getField().equals(field))
                .findFirst();
    }
}