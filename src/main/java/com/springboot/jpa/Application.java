package com.springboot.jpa;

import com.springboot.jpa.dto.PersonDto;
import com.springboot.jpa.entities.Person;
import com.springboot.jpa.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private PersonRepository repository;


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        //list();
        //findOne();
        //findOneName();
        //findOneLikeName();
        //findByName();
        //findByNameContaining();
        //create();
        //create2();
        //update();
        //update2();
        //delete();
        //delete2();

        //personalizedQueries();
        //personalizedQueries2();

        //personaDto();

        //personalizeQueriesDistinct();

        //personallizedQueriesConcatUpperAndCase();

        //personalizeQueriesBetween();

        //personalizeQueriesByOrder();

        //personalizeQueriesBetween();

        //personalizeQueryCountMaxMin();

        //SubQuery();

        //whereIn();

        //create2();

        update2();

    }
    /** curso 147 **/

    @Transactional(readOnly = true)
    public void whereIn() {
        System.out.println("==== consulta  where in incluye ====================");
        List<Person> personas = repository.getPersonByIds(Arrays.asList(1L, 2L, 8L));
        personas.forEach(System.out::println);

        System.out.println("==== consulta  where not in no incluye ==============");
        List<Person> person = repository.getPersonByIds2(Arrays.asList(1L, 2L, 8L));
        person.forEach(System.out::println);

    }



        /** curso 146 - subconsultas consultas de jpa hibernate que estan anidadas dentro de una consulta **/

    @Transactional(readOnly = true)
    public void SubQuery(){
        System.out.println("====subconsulta del nombre mas corto y su longitud  ====================");
        List<Object[]> regs = repository.getShorterNameSubQuery();
        regs.forEach(reg -> {
            String name = (String) reg[0];
            Integer length = (Integer) reg[1];
            System.out.println("name = " + name + ", length= " + length);
        });



        System.out.println("====subconsulta del nombre mas Largo  y su longitud ====================");
        List<Object[]> reg = repository.getLargeNameSubQuery();
        reg.forEach(r -> {
            String name = (String) r[0];
            Integer length = (Integer) r[1];
            System.out.println("name = " + name + ", length= " + length);
        });


        System.out.println("=== subconsulta para obtener el ultimo registro de persona ============");
        Optional<Person> optionalPerson = repository.getLastRegistration();
        optionalPerson.ifPresent(System.out::println);


    }



    /** curso 143 - 145 Clase count, max,  min **/

    @Transactional(readOnly = true)
    public void personalizeQueryCountMaxMin(){

        System.out.println("================ consulta anotacion Query total de registros Max Min ====================");
        Long cuenta = repository.getTotalPerson();
        System.out.println("total de registros = " + cuenta);

        System.out.println("================ consulta anotacion Query Min ====================");
        Long min = repository.getMinPerson();
        System.out.println("min = " + min);

        System.out.println("================ consulta anotacion Query Max ====================");
        Long max = repository.getMaxPerson();
        System.out.println("max = " + max);

        System.out.println("================ consulta anotacion Query length ====================");
        List<Object[]>  regs = repository.getPersonNameLength();
        regs.forEach(reg -> {
            String name = (String) reg[0];
            Integer length = (Integer) reg[1];
            System.out.println("name = " + name + ", length " + length);
        });


        System.out.println("================ consulta anotacion Query min length name ====================");
        Integer minLengthName= repository.getMinLengthName();
        System.out.println("minLengthName = " + minLengthName);


        System.out.println("================ consulta anotacion Query max length name ====================");
        Integer maxLengthName = repository.getMaxLengthName();
        System.out.println("maxLengthName = " + maxLengthName);

        System.out.println("===============consulta anotacion Query resumen de funciones =================");
        Object[] resumenreg = (Object[]) repository.getResumenAgregartionFunction();
        System.out.println(" mini = " + resumenreg[0] +
                           ", max = " + resumenreg[1] +
                           ", sum = " + resumenreg[2] +
                           ", promedio = " + resumenreg[3] +
                           ", count = "  + resumenreg[4]);

    }

    


    /** curso 141 Clase orderBy **/


    /** curso 141 Clase orderBy **/
    @Transactional(readOnly = true)
    public void personalizeQueriesByOrder(){
        System.out.println("================ consulta OrderBy name ====================");
        List<Person> lista = repository.findAllBetweenIdOrder(2L,5L);
        lista.forEach(list -> {
            System.out.println("lista " + list);
        });


        System.out.println("============= consulta por OrderBy name  ================");
        List<Person> lista2 = repository.findAllBetweenNameOrder("A", "P");
        lista2.forEach(list -> {
            System.out.println("lista " + list);
        });


        System.out.println("================ consulta OrderById asc name ====================");
        List<Person> list = repository.findAllBetweenIdOrder(2L,5L);
        lista.forEach(l -> {
            System.out.println("lista " + l);
        });


        System.out.println("============= consulta por OrderByName asc name ================");
        List<Person> listaPersona = repository.findAllBetweenNameOrder("J", "P");
        listaPersona.forEach(listaP -> {
            System.out.println("lista " + listaP);
        });




    }



    /** curso 139 - 140 Clase between **/
    @Transactional(readOnly = true)
    public void personalizeQueriesBetween(){
        System.out.println("================ consulta por rangos  ================");
        List<Person> lista = repository.findAllBetweenId(2,5);
        lista.forEach(list -> {
        System.out.println("lista " + list);
    });

        System.out.println("============= consulta por caracteres  ================");
        List<Person> lista2 = repository.findAllBetweenName("A", "P");
        lista2.forEach(list -> {
            System.out.println("lista " + list);
        });


        System.out.println(" consulta por findByIdBetween con palabra cable  " +
                " campos personalizados con el nombre del metodo - Query Method ======");
        List<Person> persons = repository.findByIdBetween(2L, 5L);
        persons.forEach(list -> {
            System.out.println("lista " + list);
        });


        System.out.println(" consulta por findByNameBetween con palabra cable ======" +
                " campos personalizados con el nombre del metodo - Query Method ======");
        List<Person> person = repository.findByNameBetween("A", "P");
        person.forEach(list -> {
            System.out.println("lista " + list);
        });


        System.out.println(" consulta por findAllBetweenNameOrderAsc con palabra cable  " +
                " campos personalizados con el nombre del metodo - Query Method ======");
        List<Person> persona = repository.findByIdBetweenOrderByNameDesc(2L, 5L);
        persons.forEach(list -> {
            System.out.println("lista " + list);
        });


        System.out.println(" consulta por findAllBetweenIdOrderAsc con palabra cable ======" +
                " campos personalizados con el nombre del metodo - Query Method ======");
        List<Person> pers = repository.findByNameBetweenOrderByNameDesc("A", "P");
        pers.forEach(list -> {
            System.out.println("lista " + list);
        });

        System.out.println(" === consulta por getAllOrdered anotacion @Query ======");
        pers = repository.getAllOrdered();
        pers.forEach(list -> {
            System.out.println("lista " + list);
        });

        System.out.println(" === consulta por el nombre del metodo ======");
        pers = repository.findAllByOrderByNameAsc();
        pers.forEach(list -> {
            System.out.println("lista " + list);
        });

        System.out.println(" === consulta por el nombre del metodo ======");
        pers = repository.findAllByOrderByNameAscLastnameDesc();
        pers.forEach(list -> {
            System.out.println("lista " + list);
        });

    }



    /** curso 138 Clase **/
    @Transactional(readOnly = true)
    public void personallizedQueriesConcatUpperAndCase() {
        System.out.println("================ consulta de nombre y apellidos de personas ================");
        List<String> namesList= repository.findAllFullNameConcat();
        namesList.forEach(lista -> {
            System.out.println("lista = " + lista);
        });

        System.out.println("============  consulta de nombre y apellidos de personas con || =============");
        List<String> namesList2= repository.findAllFullNameConcat2();
        namesList2.forEach(lista2 -> {
            System.out.println("lista = " + lista2);
        });


        System.out.println("============  consulta de nombre y apellidos mayusculas  ====================");
        namesList= repository.findAllFullNameConcatUpper();
        namesList.forEach(lista -> {
            System.out.println("lista = " + lista);
        });




        System.out.println("============  consulta de nombre y apellidos minusculas  ====================");
        namesList= repository.findAllFullNameConcatLower();
        namesList.forEach(lista -> {
            System.out.println("lista = " + lista);
        });



        System.out.println("=========== consulta de nombre y apellidos mayusculas y minusculas ===========");
        List<Object[]> regs= repository.findAllPersonDataListCase();
        regs.forEach(reg -> {
                 System.out.println("id = [0] " + reg[0] + ", " +
                "nombre :" +  reg[1]    +  ", "  +
                "apellido: " + reg[2]   +  ", " +
                "Lenguaje de Programacion: "  + reg[3]);
        });



    }


    /** curso 137 Clase
     *   consulta por nombre muestra una lista
     */
    @Transactional(readOnly = true)
    public void personalizeQueriesDistinct(){
        System.out.println("Consulta con nombres de personas unicos");
        List<String> names = repository.findAllNames();
        names.forEach(System.out::println);

        System.out.println("Consulta con nombres de unicos de personas");
        names = repository.findAllNamesDistinct();
        names.forEach(System.out::println);


        System.out.println("Consulta con lenguaje de programacion unicas");
        List<String> languages = repository.findAllProgrammingLanguajeDistinct();
        languages.forEach(System.out::println);

        System.out.println("Consulta con total de lenguaje de programacion unicas");
        Long totalLanguages = repository.findAllProgrammingLanguajeDistinctCount();
        System.out.println("totalLanguages = " + totalLanguages);
        languages.forEach(System.out::println);


    }




    /** curso 136 Clase DTO personalizada con los atributos que vamos a ocupar
     *  la fecha con formato  y no regresar todo el objeto (todos los campos)
     * solo nombre o el que ocupemos
     * DTO objeto de transferencia de datos
     * creamos la clase DTO
     **/
    @Transactional
    public void personaDto(){
        System.out.println("======================================================================");
        System.out.println("consulta que puebla y regresa un objeto Dto de una clase personalizada");
        System.out.println("======================================================================");

        List<PersonDto> personDto = repository.findAllPersonDto();
        personDto.forEach(personaDto -> System.out.println(personaDto));

        /*
        si sale en la consola com.springboot.jpa.dto.PersonDto
        entonces ahy que poner el toString en ela clase Dto

         */

    }



    /** curso 135 JPQL **/
    @Transactional
    public void personalizedQueries2(){
        System.out.println("============================================================");
        System.out.println("= consulta por objeto persona y lenaguaje de programacion  =");
        System.out.println("============================================================");


        List<Object[]> personRegs = repository.findAllMixPerson();
        personRegs.forEach(reg -> {
            System.out.println("lenguage de programacion  = " + reg[1] + " , objeto-persona= " + reg[0]);
        });


        //solo regresa los campos personalizados en lugar de tener el objeto
        //y los campos que no ocupamos los pone en null
        System.out.println("consulta que puebla y regresa un objeto entity de una instancia personalizada");
        List<Person> personList = repository.findAllObjectPersonPersonalized();
        System.out.println("personList = " + personList);
        //personList.forEach(persona -> System.out.println(personList));
        personList.forEach(System.out::println); //por referencia

    }



    /** curso 133 - 134 JPQL **/
    @Transactional
    public void personalizedQueries(){

        Scanner scanner = new Scanner(System.in);

        System.out.println("============================================================");
        System.out.println("============ consulta solo el nombre por el id ==============");
        System.out.println("=============================================================");

        System.out.println("Ingrese el id para el nombre:  ");
        Long id= scanner.nextLong();
        scanner.close();

        String name= repository.getNameById(id);
        System.out.println("nombre por id:  " + name);


        System.out.println("============================================================");
        System.out.println("============ consulta solo el id por el id =================");
        System.out.println("============================================================");

        Long idDb = repository.getIdById(id);
        System.out.println("id por id:  " + idDb);

        System.out.println("============================================================");
        System.out.println("=== consulta solo el id concat el name y lasname ========");
        System.out.println("============================================================");

        String fullname = repository.getFullNameById(id);
        System.out.println("fullname:  " + fullname);


        System.out.println("============================================================");
        System.out.println("=== consulta por campos personalizadospor por el id ========");
        System.out.println("============================================================");

        //con optional
         Optional<Object> optionalReg = repository.obtenerPersonDataByIdOptional(id);
         if(optionalReg.isPresent()){
             Object[] personReg = (Object[]) optionalReg.orElseThrow();
             System.out.println("id = [0] " + personReg[0] + ", " +
                     "nombre :" +  personReg[1]    +  ", "  +
                     "apellido: " + personReg[2]   +  ", " +
                     "Lenguaje de Programacion: "  + personReg[3]);
         }


        /*
        //sin optional
        //hacemos el cast a un arreglo de objeto
        Object[] personReg = (Object[]) repository.obtenerPersonDataById(id);
        System.out.println("id = [0] " + personReg[0] + ", " +
                "nombre :" +  personReg[1]    +  ", "  +
                "apellido: " + personReg[2]   +  ", " +
                "Lenguaje de Programacion: "  + personReg[3]);
       */

        System.out.println("============================================================");
        System.out.println("=  consulta por campos personalizados lista de tipo object =");
        System.out.println("============================================================");

        List<Object[]> regs = repository.obtenerPersonDataList();
        regs.forEach(reg -> System.out.println(
                "id= " + reg[0]    +  ", " +
                "nombre:" +  reg[1]    +  ", " +
                "apellido: " + reg[2]   +  ", " +
                "Lenguaje de Programacion: "  + reg[3]));


    }




    /**
     * curso 126 - 130
     */

    public void findOne() {
        //Person person = repository.findById(1L).get();
        //Person person = repository.findById(1L).orElseThrow(); //sino esta lanza una exceptiom

        //validamos el if con el optional para encontrar el id de bd
        Person person = null;
        Optional<Person> optionalPerson = repository.findById(8L);
        if (optionalPerson.isPresent()) {
            person = optionalPerson.get();
        }
        System.out.println("person = " + person);

        //de otra forma 
        //repository.findById(1L).ifPresent(p -> System.out.println(p));

        //busca por id
        repository.findById(1L).ifPresent(System.out::println); //metodo por referencia ::

    }

    //busca por nombre
    @Transactional(readOnly = true)
    public void findOneName() {
        repository.findOneName("pepe").ifPresent(System.out::println);
    }

    //busca por like por coincidencia
    public void findOneLikeName() {
        repository.findOneLikeName("pep").ifPresent(System.out::println);
    }


    //busca con la anotacion Containing como si fuera un %like%
    public void findByNameContaining() {
        repository.findByNameContaining("hn").ifPresent(System.out::println);
    }


    //registra un registro
    public void create(){

        //crea un objeto de tipo person
        Person person = new Person(null,"lalo","doe","python");
    
        //guarda
        //id es nulo lo inserta o si esta lo actualiza
        Person personNew = repository.save(person);
        System.out.println("personNew = " + personNew);

    }


    //save o delete  @Transactional
    //select @Transactional(readOnly = true)
    @Transactional
    public void create2(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("ingrese el nombre: " );
        String name = scanner.next();

        System.out.println("ingrese el apellido: " );
        String lastname= scanner.next();

        System.out.println("ingrese el leguage de programacion: " );
        String programmingLanguaje = scanner.next();
        scanner.close();

        Person person = new Person(null,name,lastname,programmingLanguaje);

        //guarda id es nulo lo inserta o si esta lo actualiza
        Person personNew = repository.save(person);
        System.out.println("personNew = " + personNew);

        //regresa un optional
        //y para imprimir ponemos -> expresion lambda
        //es un callback
        //repository.findById(personNew.getId()).ifPresent(persona -> System.out.println(persona));

        repository.findById(personNew.getId()).ifPresent(System.out::println);

    }

    //Actualiza un registro valida si esta el registro en bd con optionalPerson.ifPresent
    @Transactional
    public void update(){
        //para actualizar hay que ir a buscar a la base de datos
        //se muestran los datos actuales para modificar
        //y se modifican con el save con el id

        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el id de la persona a editar:");

        //lo puede recibir como un string y lo convierte de tipo long
        Long id= scanner.nextLong();
        
        Optional<Person> optionalPerson = repository.findById(id);
        
        optionalPerson.ifPresent(person -> {
            System.out.println("datos de persona actuales" + person);
            System.out.println("Ingrese el lenguaje de programacion:  = ");
            String programmingLanguaje = scanner.next();

            person.setProgrammingLanguage(programmingLanguaje);
            Person personDb = repository.save(person);

            System.out.println("datos de persona modificados: " + personDb);

        });

        //cerramos el scanner
        scanner.close();

    }


    //Actualiza un registro valida si esta el registro en bd con if optionalPerson.isPresent()
    @Transactional
    public void update2(){
        //para actualizar hay que ir a buscar a la base de datos
        //se muestran los datos actuales para modificar
        //y se modifican con el save con el id

        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el id de la persona a editar:");

        //lo puede recibir como un string y lo convierte de tipo long
        Long id= scanner.nextLong();

        Optional<Person> optionalPerson = repository.findById(id);

        //con if
        if(optionalPerson.isPresent()) {

            Person personDB = optionalPerson.orElseThrow();
            System.out.println("datos de persona actuales" + personDB);

            System.out.println("Ingrese el lenguaje de programacion: ");
            String programmingLanguaje = scanner.next();

            personDB.setProgrammingLanguage(programmingLanguaje);
            Person personUpdate = repository.save(personDB);

            System.out.println("datos de persona modificados: " + personUpdate);
        }else {
            System.out.println("El id: " + id  +  " no existe!");
        }

        scanner.close();
    }



    //elimina el registro
    @Transactional
    public void delete(){

        //muestra la lista actual
        repository.findAll().forEach(System.out::println);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el id de la persona a eliminar:");

        Long id= scanner.nextLong();

        repository.deleteById(id);

        repository.findAll().forEach(System.out::println);

        //muestra la lista con el registro eliminado
        System.out.println("El id: " + id  +  " eliminado");

        scanner.close();

    }




    //elimina el registro
    @Transactional
    public void delete2(){

        //muestra la lista actual
        repository.findAll().forEach(System.out::println);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el id de la persona a eliminar:");
        Long id= scanner.nextLong();

        Optional<Person> optionalPerson = repository.findById(id);

        /*
       //elimina el id de una forma
        optionalPerson.ifPresentOrElse(
                persona -> {
                    repository.delete(persona);
                    System.out.println("eliminado con exito el id: " + id);
                },
                () -> System.out.println("No exite el id: " + id));

        */


        //elimina el id de una forma
        //optionalPerson.ifPresentOrElse(repository::delete, () -> System.out.println("No exite el id: " + id));
        //repository.findAll().forEach(System.out::println);


        //elimina el id de una forma
        optionalPerson.ifPresentOrElse(
                persona -> {
                    System.out.println("eliminado con exito el id: " + id);
                    repository.delete(persona);
                    repository.findAll().forEach(System.out::println);

                },
                () -> System.out.println("No exite el id: " + id));
                      repository.findAll().forEach(System.out::println);

        scanner.close();

    }







    /** curso 116 - 126
        */
        public void list(){

            //findAll(); regresa un iterable hay que hacer un cast
            //List<Person> persons = (List<Person>) repository.findAll();

            //List<Person> persons = (List<Person>) repository.findByProgrammingLanguage("Java");
            //persons.stream().forEach(person -> System.out.println("person = " + person));


            //List<Person> persons = (List<Person>) repository.buscarByProgrammingLanguage("Java", "Andres");

            List<Person> persons = (List<Person>) repository.findByProgrammingLanguageAndName("Java", "Andres");

            persons.stream().forEach(person -> System.out.println("person = " + person));

        /*
        List<Object []> personsValues = repository.obtenerPersonData();
        personsValues.stream().forEach(person -> {
            System.out.println(person[0] + "  es experto en " + person[1]);
        });
        */


        /*
        List<Object []> personsValues2 = repository.obtenerPersonData("Python", "pepe");
        personsValues2.stream().forEach(person -> {
            System.out.println(person[0] + "  es experto en " + person[1]);
        });
        */


            List<Object[]> personsValues = repository.obtenerPersonData("Pepe");
            personsValues.stream().forEach(person -> {
                System.out.println(person[0] + "  es experto en " + person[1]);
            });


            List<Object[]> personsValues3 = repository.obtenerPersonDataByProgrammingLanguage("Java");
            personsValues3.stream().forEach(person -> {
                System.out.println(person[0] + "  es experto en " + person[1]);
            });


        }


    }


