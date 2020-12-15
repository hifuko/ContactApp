package com.computacenter.entity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "t_user")
@Accessors(chain = true)
@ApiModel("User entity class")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String username;

    @NotBlank
    private String password;


}
