package com.computacenter.service.impl;

import com.computacenter.entity.Person;
import com.computacenter.repository.PersonRepository;
import com.computacenter.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;


@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository repository;


    @Override
    @Transactional
    public Person savePerson(Person person) {
        return repository.save(person);
    }

    @Override
    public Person getPerson(Long id) {
         return repository.getOne(id);
    }

    @Override
    public Page<Person> listPersons(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<Person> listPersons(String query, Pageable pageable) {
        return repository.findByQeury("%"+query+"%", pageable);
    }

    @Override
    public Page<Person> listPersons(Long ab_id, Pageable pageable) {
        return repository.findByAbteilung_Id(ab_id, pageable);
    }

    @Override
    @Transactional
    public Person updatePerson(Person person) {
        return repository.save(person);
    }

    @Override
    @Transactional
    public void deletePerson(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Person getByMailadresse(String mailadresse) {
        return repository.getByMailadresse(mailadresse);
    }

    @Override
    public Person getById(Long id) {
        return repository.getById(id);
    }

}
