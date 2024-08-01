package com.springboot.jpa.dto;

public class PersonDto {

    private String name;
    private String lastname;


    /***
     EL Dto es una clase simplificada de la clase entity
     que vamos a regresar al cliente o la va a consumir nuestra API
     En esta clase Dto no es necesario tener un constructor vacio como las clases entity

    los datos los poblamos con el constructor
     con el operador New y no JPA
     no utiliza un constructor vacio como en las clase entity o JPA
     Jpa lo utiliza para hacer la instancia
     ***/

    public PersonDto(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
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


    @Override
    public String toString() {
        return "PersonDto{" +
                "name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
