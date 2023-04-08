package org.stanislav.relations;

import org.stanislav.relations.entity.Author;
import org.stanislav.relations.entity.Book;
import org.stanislav.relations.repository.AuthorRepository;
import org.stanislav.relations.repository.BookRepository;

import java.util.List;

/**
 * @author Stanislav Hlova
 */
public class Main {
    public static void main(String[] args) {
        AuthorRepository authorRepository = new AuthorRepository();
        BookRepository bookRepository = new BookRepository();

        Book book1 = new Book("Book1");
        Book book2 = new Book("Book2");
        Book book3 = new Book("Book3");
        Author author1 = new Author("Author 1", "Lastname 1", List.of(book1, book2));
        Author author2 = new Author("Author 2", "Lastname 2", List.of(book2, book3));
        book1.setAuthors(List.of(author1));
        book2.setAuthors(List.of(author1, author2));
        book3.setAuthors(List.of(author2));
        authorRepository.create(author1);

        List<Author> authors = authorRepository.readAll();
        System.out.println(authors);
        List<Book> books = bookRepository.readAll();
        System.out.println(books);
    }
}
