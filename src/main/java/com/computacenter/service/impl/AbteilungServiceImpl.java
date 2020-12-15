package com.computacenter.service.impl;

import com.computacenter.entity.Abteilung;
import com.computacenter.repository.AbteilungRepository;
import com.computacenter.service.AbteilungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AbteilungServiceImpl implements AbteilungService {

    @Autowired
    private AbteilungRepository repository;

    @Override
    public Abteilung getAbteilung(Long id) {
        return repository.getOne(id);
    }

    @Override
    public List<Abteilung> listAbteilungen() {
        return repository.findAll();
    }
}
