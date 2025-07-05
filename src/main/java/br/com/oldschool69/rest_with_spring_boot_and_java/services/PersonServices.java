package br.com.oldschool69.rest_with_spring_boot_and_java.services;

import br.com.oldschool69.rest_with_spring_boot_and_java.controllers.PersonController;
import br.com.oldschool69.rest_with_spring_boot_and_java.data.dto.v1.PersonDTO;
import br.com.oldschool69.rest_with_spring_boot_and_java.exception.RequiredObjectIsNullException;
import br.com.oldschool69.rest_with_spring_boot_and_java.exception.ResourceNotFoundException;
import br.com.oldschool69.rest_with_spring_boot_and_java.model.Person;
import br.com.oldschool69.rest_with_spring_boot_and_java.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static br.com.oldschool69.rest_with_spring_boot_and_java.mapper.ObjectMapper.parseListObjects;
import static br.com.oldschool69.rest_with_spring_boot_and_java.mapper.ObjectMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Service
public class PersonServices {

    private final Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;


    public PersonDTO findById(Long id){

        logger.info("Finding one person");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this Id"));

        var dto = parseObject(entity, PersonDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public PersonDTO create (PersonDTO person) {
        if (person == null) throw  new RequiredObjectIsNullException();

        logger.info("Creating new person");
        var entity = parseObject(person, Person.class);
        var dto = parseObject(repository.save(entity), PersonDTO.class) ;
        addHateoasLinks(dto);
        return dto;
    }

    public PersonDTO update (PersonDTO person) {
        if (person == null) throw  new RequiredObjectIsNullException();

        logger.info("Updating a person");
        Person entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this Id"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        var dto = parseObject(repository.save(entity), PersonDTO.class) ;
        addHateoasLinks(dto);
        return dto;
    }

    public void delete(Long id){
        logger.info("Deleting a person");
        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this Id"));

        repository.delete(entity);

    }

    @Transactional
    public PersonDTO disablePerson(Long id){
        logger.info("Disabling person");

        repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this Id"));

        repository.disablePerson(id);

        var entity = repository.findById(id).get();
        var dto = parseObject(entity, PersonDTO.class) ;
        addHateoasLinks(dto);

        return dto;

    }

    public Page<PersonDTO> findAll(Pageable pageable) {
        logger.info("Finding all people");

        var people = repository.findAll(pageable);

        var peopleWithLinks = people.map(person -> {
            var dto = parseObject(person, PersonDTO.class) ;
            addHateoasLinks(dto);
            return dto;
        });

        return peopleWithLinks;
    }

    private static void addHateoasLinks(PersonDTO dto) {
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).findAll(1, 12, "asc")).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(PersonController.class).update(dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(PersonController.class).disablePerson(dto.getId())).withRel("disable").withType("PATCH"));
        dto.add(linkTo(methodOn(PersonController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
    }
}
