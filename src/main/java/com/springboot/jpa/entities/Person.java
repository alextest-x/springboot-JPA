package com.springboot.jpa.entities;

import jakarta.persistence.*;


@Entity
@Table(name="persons")
public class Person {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;

    private String lastname;

    @Column(name="programming_language")
    private String programmingLanguage;



    // si tenemos un contsructor con atributos entonces ponemos uno vacio
    //jpa usa el constructor vacio para crear la instancia
    //hibernate lo utiliza

    public Person() {
    }


    //constructor para crear un registro
    //para hibernate jpa hay que tener un constructor con paramaretros y uno vacio


    public Person(Long id, String name, String lastname, String programmingLanguage) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.programmingLanguage = programmingLanguage;
    }


    //se crea constructor con los atributos que vamos a ocupar en el select
    //en PersonRepository
    public Person(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }





    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", programmingLanguage='" + programmingLanguage + '\'' +
                '}';
    }

}
