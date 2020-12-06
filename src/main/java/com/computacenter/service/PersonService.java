package com.computacenter.service;

import com.computacenter.pojo.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PersonService {
    Person savePerson(Person person);

    Person getPerson(Long id);

    Page<Person> listPersons(Pageable pageable);

    Person updatePerson(Person person);

    void deletePerson(Long id);

    public Person getByMailadresse(String mailadresse);

}
