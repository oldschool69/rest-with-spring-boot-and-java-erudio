package br.com.oldschool69.rest_with_spring_boot_and_java;

import br.com.oldschool69.rest_with_spring_boot_and_java.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private final Logger logger = Logger.getLogger(PersonServices.class.getName());

    public Person findById(String id){

        logger.info("Finding one person");

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Flavio");
        person.setLastName("Oliveira");
        person.setAddress("Campinas - São Paulo - Brazil");
        person.setGender("male");
        return person;
    }

    public Person create (Person person) {
        logger.info("Creating new person");
        return person;
    }

    public Person update (Person person) {
        logger.info("Updating a person");
        return person;
    }

    public void delete(String id){
        logger.info("Deleting a person");
    }

    public List<Person> findAll() {
        logger.info("Finding all people");
        List<Person> persons = new ArrayList<>();

        for (int i = 1; i < 9; i++) {
            Person person = mockPerson(i);
            persons.add(person);
        }

        return persons;
    }

    private Person mockPerson(int i) {
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Firstname " +  i);
        person.setLastName("Lastname " + i);
        person.setAddress("Some address in Brazil");
        person.setGender("male");
        return person;
    }
}
