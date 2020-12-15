package com.computacenter.service;

import com.computacenter.entity.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PersonService {
    Person savePerson(Person person);

    Person getPerson(Long id);

    Page<Person> listPersons(Pageable pageable);

    Page<Person> listPersons(String query, Pageable pageable);

    Page<Person> listPersons(Long ab_id, Pageable pageable);


    Person updatePerson(Person person);

    void deletePerson(Long id);

    public Person getByMailadresse(String mailadresse);

    public Person getById(Long id);


}
