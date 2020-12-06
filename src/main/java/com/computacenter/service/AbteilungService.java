package com.computacenter.service;

import com.computacenter.pojo.Abteilung;
import com.computacenter.pojo.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AbteilungService {

    Abteilung getAbteilung(Long id);

    List<Abteilung> listAbteilungen();


}
