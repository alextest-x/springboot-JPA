package com.springboot.jpa.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

/*
la anotacion @Embeddable sirve para desacoplar los atributos comunes
que tenemos en la clase entity
y podemos usar en cualquier otra clase
en esta clase ponemos los setters y getters que estaban en la clase entity
*/


@Embeddable
public class Audit {

    @Column(name= "create_at")
    private LocalDateTime creatAt;


    @Column(name= "updated_at")
    private LocalDateTime updatedAt;


    //antes de insertar en la base de datos prePersist()
    //despues de guardar en la base de datos postPersist()
    //para agregar un campo de la tabla o actualizar ejemplo el campo fecha
    @PrePersist
    public void prePersist(){
        System.out.println("evento del ciclo de vida del objeto entity pre-persist");
        this.creatAt = LocalDateTime.now();
    }


    //actualiza antes de guadar en la base de datos
    //ejemplo el campo fecha
    @PreUpdate
    public void preUpdate(){
        System.out.println("evento del ciclo de vida del objeto entity pre-update");
        this.updatedAt = LocalDateTime.now();
    }


    public LocalDateTime getCreatAt() {
        return creatAt;
    }

    public void setCreatAt(LocalDateTime creatAt) {
        this.creatAt = creatAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

}
