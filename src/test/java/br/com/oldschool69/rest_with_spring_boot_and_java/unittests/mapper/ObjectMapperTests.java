package br.com.oldschool69.rest_with_spring_boot_and_java.unittests.mapper;

import static br.com.oldschool69.rest_with_spring_boot_and_java.mapper.ObjectMapper.parseListObjects;
import static br.com.oldschool69.rest_with_spring_boot_and_java.mapper.ObjectMapper.parseObject;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import br.com.oldschool69.rest_with_spring_boot_and_java.data.dto.v1.BookDTO;
import br.com.oldschool69.rest_with_spring_boot_and_java.data.dto.v1.PersonDTO;
import br.com.oldschool69.rest_with_spring_boot_and_java.model.Book;
import br.com.oldschool69.rest_with_spring_boot_and_java.unittests.mapper.mocks.MockBook;
import br.com.oldschool69.rest_with_spring_boot_and_java.unittests.mapper.mocks.MockPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.oldschool69.rest_with_spring_boot_and_java.model.Person;

public class ObjectMapperTests {
    MockPerson mockPerson;
    MockBook mockBook;

    @BeforeEach
    public void setUp() {
        mockPerson = new MockPerson();
        mockBook = new MockBook();
    }

    @Test
    public void parsePersonEntityToDTOTest() {
        PersonDTO output = parseObject(mockPerson.mockEntity(), PersonDTO.class);
        assertEquals(Long.valueOf(0L), output.getId());
        assertEquals("First Name Test0", output.getFirstName());
        assertEquals("Last Name Test0", output.getLastName());
        assertEquals("Address Test0", output.getAddress());
        assertEquals("Male", output.getGender());
    }

    @Test
    public void parsePersonEntityListToDTOListTest() {
        List<PersonDTO> outputList = parseListObjects(mockPerson.mockEntityList(), PersonDTO.class);
        PersonDTO outputZero = outputList.get(0);

        assertEquals(Long.valueOf(0L), outputZero.getId());
        assertEquals("First Name Test0", outputZero.getFirstName());
        assertEquals("Last Name Test0", outputZero.getLastName());
        assertEquals("Address Test0", outputZero.getAddress());
        assertEquals("Male", outputZero.getGender());

        PersonDTO outputSeven = outputList.get(7);

        assertEquals(Long.valueOf(7L), outputSeven.getId());
        assertEquals("First Name Test7", outputSeven.getFirstName());
        assertEquals("Last Name Test7", outputSeven.getLastName());
        assertEquals("Address Test7", outputSeven.getAddress());
        assertEquals("Female", outputSeven.getGender());

        PersonDTO outputTwelve = outputList.get(12);

        assertEquals(Long.valueOf(12L), outputTwelve.getId());
        assertEquals("First Name Test12", outputTwelve.getFirstName());
        assertEquals("Last Name Test12", outputTwelve.getLastName());
        assertEquals("Address Test12", outputTwelve.getAddress());
        assertEquals("Male", outputTwelve.getGender());
    }

    @Test
    public void parsePersonDTOToEntityTest() {
        Person output = parseObject(mockPerson.mockDTO(), Person.class);
        assertEquals(Long.valueOf(0L), output.getId());
        assertEquals("First Name Test0", output.getFirstName());
        assertEquals("Last Name Test0", output.getLastName());
        assertEquals("Address Test0", output.getAddress());
        assertEquals("Male", output.getGender());
    }

    @Test
    public void parserPersonDTOListToEntityListTest() {
        List<Person> outputList = parseListObjects(mockPerson.mockDTOList(), Person.class);
        Person outputZero = outputList.get(0);

        assertEquals(Long.valueOf(0L), outputZero.getId());
        assertEquals("First Name Test0", outputZero.getFirstName());
        assertEquals("Last Name Test0", outputZero.getLastName());
        assertEquals("Address Test0", outputZero.getAddress());
        assertEquals("Male", outputZero.getGender());

        Person outputSeven = outputList.get(7);

        assertEquals(Long.valueOf(7L), outputSeven.getId());
        assertEquals("First Name Test7", outputSeven.getFirstName());
        assertEquals("Last Name Test7", outputSeven.getLastName());
        assertEquals("Address Test7", outputSeven.getAddress());
        assertEquals("Female", outputSeven.getGender());

        Person outputTwelve = outputList.get(12);

        assertEquals(Long.valueOf(12L), outputTwelve.getId());
        assertEquals("First Name Test12", outputTwelve.getFirstName());
        assertEquals("Last Name Test12", outputTwelve.getLastName());
        assertEquals("Address Test12", outputTwelve.getAddress());
        assertEquals("Male", outputTwelve.getGender());
    }

    @Test
    public void parseBookEntityToDTOTest() {
        BookDTO output = parseObject(mockBook.mockEntity(), BookDTO.class);
        assertEquals(Long.valueOf(0L), output.getId());
        assertEquals("Title0", output.getTitle());
        assertEquals("Author0", output.getAuthor());
        assertEquals(150.00, output.getPrice());
    }

    @Test
    public void parseBookEntityListToDTOListTest() {
        List<BookDTO> outputList = parseListObjects(mockBook.mockEntityList(), BookDTO.class);
        BookDTO outputZero = outputList.get(0);

        assertEquals(Long.valueOf(0L), outputZero.getId());
        assertEquals("Title0", outputZero.getTitle());
        assertEquals("Author0", outputZero.getAuthor());
        assertEquals(150.00, outputZero.getPrice());

        BookDTO outputSeven = outputList.get(7);

        assertEquals(Long.valueOf(7L), outputSeven.getId());
        assertEquals("Title7", outputSeven.getTitle());
        assertEquals("Author7", outputSeven.getAuthor());
        assertEquals(150.00 + 7L, outputSeven.getPrice());

        BookDTO outpuTwelve = outputList.get(12);

        assertEquals(Long.valueOf(12L), outpuTwelve.getId());
        assertEquals("Title12", outpuTwelve.getTitle());
        assertEquals("Author12", outpuTwelve.getAuthor());
        assertEquals(150.00 + 12L, outpuTwelve.getPrice());
    }

    @Test
    public void parseBookDTOToEntityTest() {
        Book output = parseObject(mockBook.mockDTO(), Book.class);
        assertEquals(Long.valueOf(0L), output.getId());
        assertEquals("Title0", output.getTitle());
        assertEquals("Author0", output.getAuthor());
        assertEquals(150.00, output.getPrice());
    }

    @Test
    public void parserBookDTOListToEntityListTest() {
        List<Book> outputList = parseListObjects(mockBook.mockDTOList(), Book.class);
        Book outputZero = outputList.get(0);

        assertEquals(Long.valueOf(0L), outputZero.getId());
        assertEquals(Long.valueOf(0L), outputZero.getId());
        assertEquals("Title0", outputZero.getTitle());
        assertEquals("Author0", outputZero.getAuthor());
        assertEquals(150.00, outputZero.getPrice());

        Book outputSeven = outputList.get(7);

        assertEquals(Long.valueOf(7L), outputSeven.getId());
        assertEquals("Title7", outputSeven.getTitle());
        assertEquals("Author7", outputSeven.getAuthor());
        assertEquals(150.00 + 7L, outputSeven.getPrice());

        Book outpuTwelve = outputList.get(12);

        assertEquals(Long.valueOf(12L), outpuTwelve.getId());
        assertEquals("Title12", outpuTwelve.getTitle());
        assertEquals("Author12", outpuTwelve.getAuthor());
        assertEquals(150.00 + 12L, outpuTwelve.getPrice());
    }
}