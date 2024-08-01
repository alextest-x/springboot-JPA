package com.springboot.jpa.repositories;


import com.springboot.jpa.entities.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.springboot.jpa.dto.PersonDto;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, Long> {


   /** curso 147 - subconsultas consultas de jpa hibernate que estan anidadas dentro de una consulta **/

   @Query("select p from Person p where p.id in ?1")
   public List<Person> getPersonByIds(List<Long> ids);

   @Query("select p from Person p where p.id not in ?1")
   public List<Person> getPersonByIds2(List<Long> ids);


   /** curso 146 - subconsultas consultas de jpa hibernate que estan anidadas dentro de una consulta **/

   @Query("select p.name, length(p.name) from Person p where length(p.name) = (select min(length(p.name)) from Person p) ")
   public List<Object[]> getShorterNameSubQuery();


   @Query("select p.name, length(p.name) from Person p where length(p.name) = (select max(length(p.name)) from Person p) ")
   public List<Object[]> getLargeNameSubQuery();


   @Query("select p from Person p where p.id = (select max(p.id) from Person p)")
   public Optional<Person> getLastRegistration();





   /** curso 145 con anotacion @Query **/
   @Query("select min(p.id), max(p.id), sum(p.id), avg(length(p.name)), count(p.id) from Person p")
   public Object getResumenAgregartionFunction();



   /** curso 143 con anotacion @Query **/


   @Query("select min(length(p.name)) from Person p")
   public Integer getMinLengthName();


   @Query("select max(length(p.name)) from Person p")
   public Integer getMaxLengthName();



   //obtiene el nombre y obtiene la longitud del nombre
   //ponemos com un object en un arreglo
   @Query("select p.name, length(p.name) from Person p")
   public List<Object[]> getPersonNameLength();


   @Query("select count(p) from Person p")
   long getTotalPerson();

   @Query("select min(p.id) from Person p")
   long getMinPerson();

   @Query("select max(p.id) from Person p")
   long getMaxPerson();









   /** curso 142 con palabra cable de Jpa**/
   @Query("select p from Person p order by p.name asc")
   List<Person> getAll();


   /** curso 141 con palabra cable de Jpa para utilizar Query Method **/

   @Query("select p from Person p where p.name between ?1 and ?2 order by  p.name")
   List<Person> findAllBetweenNameOrder(String name1, String name2);


   @Query("select p from Person p where p.id between ?1 and ?2 order by  p.name")
   List<Person> findAllBetweenIdOrder(Long id1, Long id2);


   @Query("select p from Person p where p.name between ?1 and ?2 order by  p.name, p.lastname asc ")
   List<Person> findAllBetweenNameOrderAsc(String name1, String name2);


   @Query("select p from Person p where p.id between ?1 and ?2 order by  p.name, p.lastname desc ")
   List<Person> findAllBetweenIdOrderAsc(Long id1, Long id2);

   @Query("select p from Person p order by  p.id desc, p.lastname asc")
   List<Person> getAllOrdered();

   //consulta por el nombre del metodo
   List<Person>  findAllByOrderByNameAsc();

   //consulta por el nombre del metodo
   List<Person>  findAllByOrderByNameAscLastnameDesc();



   /** curso 140 con palabra cable  de Jpa para utilizar Query Method **/
   List<Person> findByIdBetween(Long id1, Long id2);

   List<Person> findByNameBetween(String name1, String name2);



   List<Person> findByIdBetweenOrderByNameDesc(Long id1, Long id2);

   List<Person> findByNameBetweenOrderByNameDesc(String name1, String name2);



   /** curso 139 JPQL between **/

   @Query("select p from Person p where p.id between ?1 and ?2")
   List<Person> findAllBetweenId(Integer id1, Integer id2);


   @Query("select p from Person p where p.name between ?1 and ?2 ")
   List<Person> findAllBetweenName(String c1, String c2);


   //@Query("select p from Person p where p.name between  'A' and 'P' ")
   //List<Person> findAllBetweenName();

   //@Query("select p from Person p where p.id between 2 and 5")
   //List<Person> findAllBetweenId();



   /** curso 138 JPQL concat upper lower **/

   //funcion concat
   @Query("select concat(p.name, ' ', p.lastname) as fullname from Person p")
   List<String> findAllFullNameConcat();


   //concatena con ||
   @Query("select p.name || ' ' || p.lastname as fullname from Person p")
   List<String> findAllFullNameConcat2();

   //con UpperCase
   @Query("select upper(p.name || ' ' || p.lastname)  as fullnameMayusculas from Person p")
   List<String> findAllFullNameConcatUpper();

   //con Lower
   @Query("select lower(concat(p.name, ' ', p.lastname)) as fullnameMinusculas from Person p")
   List<String> findAllFullNameConcatLower();


   @Query("select p.id, upper(p.name), lower(p.lastname), upper(p.programmingLanguage)  from Person p")
   List<Object[]> findAllPersonDataListCase();



   /** curso 137 JPQL **/


   //con distinct con count
   @Query("select count(distinct (p.programmingLanguage)) from Person p")
   Long findAllProgrammingLanguajeDistinctCount();

   //con distinct
   @Query("select distinct (p.programmingLanguage) from Person p")
   List<String> findAllProgrammingLanguajeDistinct();


   //sin distinct
   //@Query("select p.programmingLanguage from Person p")
   //List<String> findAllProgrammingLanguajeDistinct();

   @Query("select p.name from Person p")
   List<String> findAllNames();


   @Query("select distinct (p.name)  from Person p")
   List<String> findAllNamesDistinct();




   /** curso 133 JPQL **/

   //obtenemos el nombre por el id
   @Query("select p.name from Person p where p.id=?1")
    String getNameById(Long id);


   //obtenemos el id por el id
   @Query("select p.id from Person p where p.id=?1")
   Long getIdById(Long id);

   //funcion concat de jpa
   @Query("select concat(p.name, ' ', p.lastname) as fullname from Person p where p.id=?1")
   String getFullNameById(Long id);


   //obtiene todos los campos en una lista de registros o arreglo solos
   @Query("select p.id, p.name, p.lastname, p.programmingLanguage from Person p")
   List<Object[]> obtenerPersonDataList();


   //obtener un solo objeto en uno solo
   //consulta por campos personalizados lista
   @Query("select p.id, p.name, p.lastname, p.programmingLanguage from Person p where p.id=?1")
   Object obtenerPersonDataById(Long id);


   //con optional
   //obtener un solo objeto en uno solo
   //consulta por campos personalizados lista
   @Query("select p.id, p.name, p.lastname, p.programmingLanguage from Person p where p.id=?1")
   Optional <Object> obtenerPersonDataByIdOptional(Long id);


   //obtiene el objeto persona y lenaguaje de programacion
   @Query("select p, p.programmingLanguage from Person p")
   List<Object[]> findAllMixPerson();


   //consulta personalizada regresa el objeto persona paros con los campos personalizados
   //tenemos que hacer enla clase entity un constructor con los atributos que vamos a ocupar
   //name y lastname con ("select new Person () ") instanciamos la clase no jpa
   //indicando los atributos que vamos a ocupar
   @Query("select new Person(p.name, p.lastname) from Person p")
   List<Person> findAllObjectPersonPersonalized();


   //person Dto con los campos personalizados
   //hay error hay que poner toda ruta package para que encuentre la ruta exacta com.springboot.jpa.dto.PersonDto
   //@Query("select new PersonDto(p.name, p.lastname) from Person p")

   @Query("select new com.springboot.jpa.dto.PersonDto(p.name, p.lastname) from Person p")
   List<PersonDto> findAllPersonDto();




   /** curso 126 - 130 **/

   //regresar un objeto de tipo person
   @Query("select p from Person p where p.id=?1")
   Optional<Person> findOne(Long id);


   //busca por nombre
   @Query("select p from Person p where p.name=?1")
   Optional<Person> findOneName(String name);


   //busca por nombre   like %?1% con % busca a la izquierda y derecha
   @Query("select p from Person p where p.name like %?1%")
   Optional<Person> findOneLikeName(String name);


   //buscando por nombre pero sin la anotacion @Query
   Optional<Person> findByName(String name);



   //buscando por nombre con la anotacion @Query
   @Query("select p from Person p where p.name like %?1%")
   Optional<Person> findByNameContaining(String name);



   /** curso 116 - 126  */

   /***
    * metodos query basados en el nombre de los nombre de los atributos
    */
   List<Person> findByProgrammingLanguage(String programmingLanguage);


   /***
    *  consulta con la anotacion @Query de JPA
    *
    *  trae todos  @Query("select p from Person p)
    */

   @Query("select p from Person p where p.programmingLanguage=?1 and p.name=?2")
   List<Person> buscarByProgrammingLanguage(String programmingLanguage, String name);


   @Query("select p from Person p where p.programmingLanguage=?1 and p.name=?2")
   List<Person> findByProgrammingLanguageAndName(String programmingLanguage, String name);


   @Query("select p.name, p.programmingLanguage from Person p")
   List<Object[]> obtenerPersonData();     //lista de un arreglo de objetos


   @Query("select p.name, p.programmingLanguage from Person p where p.name=?1")
   List<Object[]> obtenerPersonData(String name);


   @Query("select p.name from Person p where p.programmingLanguage=?1 and p.name=?2")
   List<Object[]> obtenerPersonData(String programmingLanguage, String name);


   @Query("select p.name, p.programmingLanguage from Person p where p.programmingLanguage=?1 ")
   List<Object[]> obtenerPersonDataByProgrammingLanguage(String programmingLanguage);


/*


    @Query("select p.name from Person p where p.name=?1")
    List<Object[]> obtenerPersonData1(String name );


    @Query("select p.name, p.programmingLanguage from Person p where p.programmingLanguage=?1")
    List<Object[]> obtenerPersonData1(String programmmingLanguage, String name );


    // se regresa un solo objeto de tipo person cuando se utiliza el CrudRepository con jpa
    // se recomienda usar el optional porque nos envuelve el resulatdo de la consulta si presente o no

    @Query("select p from Person p where p.id=?1")
    Optional<Person> findOne(Long id);

    //buscar por el nombre
    @Query("select p from Person p where p.name=?1")
    Optional<Person> findOneName(String name);

    //buscar por prefijo Pep con el operador like y %?1%
    //anotacion @Query
    @Query("select p from Person p where p.name like %?1%")
    Optional<Person> findOneLikeName(String name);





    //sin la anotacion Query y sin el like
    Optional<Person> findByNameContaining(String name);

 */

}
