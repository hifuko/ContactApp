package com.computacenter.service;

import com.computacenter.entity.Abteilung;

import java.util.List;

public interface AbteilungService {

    Abteilung getAbteilung(Long id);

    List<Abteilung> listAbteilungen();


}
