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

    //con esta anotacion embebimos los atributos a la clase comun
    @Embedded
    private Audit audit = new Audit();

/*
    //se comenta porque se puso enla clase comun
    @Column(name= "create_at")
    private LocalDateTime creatAt;


    @Column(name= "updated_at")
    private LocalDateTime updatedAt;
*/


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


    //se crea constructor con los atributos que vamos a ocupar en el select en PersonRepository
    public Person(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }


/*
se comenta porque se llevo a la clase comun Audit
para que otros componentes lo puedan utilizar


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

*/

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
                ", creatAt=" + audit.getCreatAt() +
                ", updatedAt=" + audit.getUpdatedAt() +
                '}';
    }

}
