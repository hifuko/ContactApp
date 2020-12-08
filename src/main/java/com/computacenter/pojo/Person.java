package com.computacenter.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

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

    @NotBlank(message = "Vorname darf nicht leer sein!")
    @Length(max = 15, message = "Vorname ist zu lang!")
    @Pattern(regexp = "[a-zA-Z\\u00C0-\\u017F]*")
    private String vorname;

    @NotBlank(message = "Nachname darf nicht leer sein!")
    @Length(max = 15, message = "Nachname ist zu lang!")
    @Pattern(regexp = "[a-zA-Z\\u00C0-\\u017F]*")
    private String nachname;

    @Email(message = "Mailadresse nicht korrekt!")
    @NotBlank(message = "Mailadresse darf nicht leer sein!")
    private String mailadresse;

    @Length(min = 8, max = 13, message = "Die Länge der Telefonnummer muss zwischen 8 und 13 liegen!")
    @Pattern(regexp = "[0-9]*")
    private String telefonnummer;

    @ManyToOne
    private Abteilung abteilung;

}
