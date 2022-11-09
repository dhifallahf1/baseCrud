package com.fd.baseCrud;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee {
    public interface EnableView{};

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    @JsonView(EnableView.class)
    String nom;
    @JsonView(EnableView.class)
    int age;
    @JsonView(EnableView.class)
    Date dateNaiss;

    public Employee(String nom, int age,Date dateNaiss) {
        this.nom = nom;
        this.age = age;
        this.dateNaiss = dateNaiss;
    }
}
