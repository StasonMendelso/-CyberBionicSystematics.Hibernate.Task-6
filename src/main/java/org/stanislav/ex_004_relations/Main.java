package org.stanislav.ex_004_relations;


import org.apache.log4j.Logger;
import org.stanislav.ex_004_relations.entity.Book;
import org.stanislav.ex_004_relations.repository.AuthorRepository;
import org.stanislav.ex_004_relations.repository.BookRepository;

import java.util.List;

/**
 * Created by Asus on 01.11.2017.
 */
public class Main {

    private static final Logger LOG = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        AuthorRepository authorRepository = new AuthorRepository();
        BookRepository bookRepository = new BookRepository();

        for (Book book : bookRepository.getBookList()) {
            LOG.info(book.getName() + " " + book.getAuthor().getName());
        }

        System.out.println(bookRepository.deleteById(13));
        for (Book book : bookRepository.getBookList()) {
            LOG.info(book.getName() + " " + book.getAuthor().getName());
        }

        System.out.println(bookRepository.deleteBooksByAuthorName(authorRepository.getAuthorById(1)));
        for (Book book : bookRepository.getBookList()) {
            LOG.info(book.getName() + " " + book.getAuthor().getName());
        }

    }

}
