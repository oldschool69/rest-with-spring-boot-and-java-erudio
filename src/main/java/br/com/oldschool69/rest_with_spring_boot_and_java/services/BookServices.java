package br.com.oldschool69.rest_with_spring_boot_and_java.services;

import br.com.oldschool69.rest_with_spring_boot_and_java.controllers.BookController;
import br.com.oldschool69.rest_with_spring_boot_and_java.controllers.PersonController;
import br.com.oldschool69.rest_with_spring_boot_and_java.data.dto.v1.BookDTO;
import br.com.oldschool69.rest_with_spring_boot_and_java.data.dto.v1.PersonDTO;
import br.com.oldschool69.rest_with_spring_boot_and_java.exception.RequiredObjectIsNullException;
import br.com.oldschool69.rest_with_spring_boot_and_java.exception.ResourceNotFoundException;
import br.com.oldschool69.rest_with_spring_boot_and_java.model.Book;
import br.com.oldschool69.rest_with_spring_boot_and_java.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.oldschool69.rest_with_spring_boot_and_java.mapper.ObjectMapper.parseListObjects;
import static br.com.oldschool69.rest_with_spring_boot_and_java.mapper.ObjectMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookServices {

    private final Logger logger = LoggerFactory.getLogger(BookServices.class.getName());

    @Autowired
    private BookRepository repository;

    @Autowired
    PagedResourcesAssembler<BookDTO> assembler;

    public BookDTO findById(Long id){
        logger.info("Finding a book by its id");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this Id"));

        var dto = parseObject(entity, BookDTO.class);

        addHateoasLinks(dto);

        return  dto;
    }

    public PagedModel<EntityModel<BookDTO>> findAll(Pageable pageable){
        logger.info("Find all books available");


        var books = repository.findAll(pageable);

        var booksWithLinks = books.map(book -> {
            var dto = parseObject(book, BookDTO.class) ;
            addHateoasLinks(dto);
            return dto;
        });

        Link findAllLinks = WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(BookController.class)
                                .findAll(
                                        pageable.getPageNumber(),
                                        pageable.getPageSize(),
                                        String.valueOf(pageable.getSort())))
                .withSelfRel();

        return assembler.toModel(booksWithLinks, findAllLinks);
    }

    public BookDTO create (BookDTO book) {
        if (book == null) throw  new RequiredObjectIsNullException();
        logger.info("Creating new book");
        var entity = parseObject(book, Book.class);
        var dto = parseObject(repository.save(entity), BookDTO.class) ;
        addHateoasLinks(dto);
        return dto;
    }

    public BookDTO update (BookDTO book) {
        if (book == null) throw  new RequiredObjectIsNullException();

        logger.info("Updating a person");
        Book entity = repository.findById(book.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this Id"));

        entity.setTitle(book.getTitle());
        entity.setAuthor (book.getAuthor());
        entity.setPrice(book.getPrice());
        entity.setLaunchDate(book.getLaunchDate());
        var dto = parseObject(repository.save(entity), BookDTO.class) ;
        addHateoasLinks(dto);
        return dto;
    }

    public void delete(Long id){
        logger.info("Deleting a book");
        Book entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this Id"));

        repository.delete(entity);

    }

    private static void addHateoasLinks(BookDTO dto) {
        dto.add(linkTo(methodOn(BookController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(BookController.class).findAll(1, 12, "asc")).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(BookController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(BookController.class).update(dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(BookController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
    }
}
