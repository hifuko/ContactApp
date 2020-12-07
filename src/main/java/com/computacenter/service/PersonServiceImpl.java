package com.computacenter.service;

import com.computacenter.pojo.Person;
import com.computacenter.repository.PersonRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService{

    @Autowired
    private PersonRepository repository;


    @Override
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
    public Person updatePerson(Person person) {
        return repository.save(person);
    }

    @Override
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
