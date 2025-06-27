package br.com.oldschool69.rest_with_spring_boot_and_java.unittests.mapper.mocks;

import br.com.oldschool69.rest_with_spring_boot_and_java.data.dto.v1.BookDTO;
import br.com.oldschool69.rest_with_spring_boot_and_java.model.Book;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MockBook {


    public Book mockEntity() {
        return mockEntity(0);
    }
    
    public BookDTO mockDTO() {
        return mockDTO(0);
    }
    
    public List<Book> mockEntityList() {
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            books.add(mockEntity(i));
        }
        return books;
    }

    public List<BookDTO> mockDTOList() {
        List<BookDTO> books = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            books.add(mockDTO(i));
        }
        return books;
    }
    
    public Book mockEntity(Integer number) {
        Book book = new Book();
        book.setId(number.longValue());
        book.setTitle("Title" + number);
        book.setAuthor("Author" + number);
        book.setPrice(150.00 + number);
        book.setLaunchDate(getDate());
        return book;
    }

    public BookDTO mockDTO(Integer number) {
        BookDTO book = new BookDTO();
        book.setId(number.longValue());
        book.setTitle("Title" + number);
        book.setAuthor("Author" + number);
        book.setPrice(150.00 + number);
        book.setLaunchDate(getDate());
        return book;
    }

    public Date getDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.JUNE, 27, 10, 30, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

}