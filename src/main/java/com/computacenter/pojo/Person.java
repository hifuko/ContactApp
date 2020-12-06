package com.computacenter.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "t_person")
public class Person {
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty(message = "Vorname darf nicht leer sein.")
    private String vorname;

    @NotEmpty(message = "Nachname darf nicht leer sein.")
    private String nachname;
    @Email
    @NotEmpty(message = "Mailadresse darf nicht leer sein.")
    private String mailadresse;
    private String telefonnummer;

    @ManyToOne
    private Abteilung abteilung;

}
