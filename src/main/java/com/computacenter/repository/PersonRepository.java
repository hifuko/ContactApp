package com.computacenter.repository;


import com.computacenter.entity.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PersonRepository extends JpaRepository<Person, Long> {

    public Person getByMailadresse(String mailadresse);

    public Person getById(Long id);

    //jpql(better than sql cuz it is more abstract)
    @Query("select p from Person p where p.nachname like ?1 or p.vorname like ?1 or p.mailadresse like ?1 " +
            "or p.abteilung.name like ?1 or p.telefonnummer like ?1")
    public Page<Person> findByQeury(String query, Pageable pageable);

    public Page<Person> findByAbteilung_Id(Long id, Pageable pageable);


}
