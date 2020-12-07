package com.computacenter.repository;


import com.computacenter.pojo.Person;
import com.computacenter.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

    public Person getByMailadresse(String mailadresse);

    public Person getById(Long id);


}
