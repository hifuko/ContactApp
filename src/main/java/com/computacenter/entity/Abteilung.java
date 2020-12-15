package com.computacenter.entity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "t_abteilung")
@ApiModel("Department entity class")
public class Abteilung {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String name;

    private String beschreibung;

    @OneToMany(mappedBy = "abteilung")
    private List<Person> personList = new ArrayList<>();


}
