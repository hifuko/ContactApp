package com.computacenter.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "t_abteilung")
public class Abteilung {
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty(message = "Vorname darf nicht leer sein.")
    private String name;

    private String beschreibung;

    @OneToMany(mappedBy = "abteilung")
    private List<Person> personList = new ArrayList<>();


}
