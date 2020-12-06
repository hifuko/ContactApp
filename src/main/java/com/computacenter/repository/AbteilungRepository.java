package com.computacenter.repository;


import com.computacenter.pojo.Abteilung;
import com.computacenter.pojo.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbteilungRepository extends JpaRepository<Abteilung, Long> {

}
