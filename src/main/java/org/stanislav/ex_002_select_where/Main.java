package org.stanislav.ex_002_select_where;





import org.apache.log4j.Logger;
import org.stanislav.ex_002_select_where.entity.Author;
import org.stanislav.ex_002_select_where.repository.AuthorRepository;

import java.util.List;

/**
 * Created by Asus on 01.11.2017.
 */
public class Main {

    private static final Logger LOG = Logger.getLogger( AuthorRepository.class.getName() );

    public static void main(String[] args) {
        AuthorRepository authorRepository = new AuthorRepository();
        List<Author> authorList = authorRepository.getAuthorList();
        for (Author author : authorList) {
            LOG.info(author.getId() + " " + author.getName() + " " + author.getLastName());
        }

        authorRepository.updateAuthorsNameWhereSizeOfLastnameBiggerThan("1",7);

        authorList = authorRepository.getAuthorList();
        for (Author author : authorList) {
            LOG.info(author.getId() + " " + author.getName() + " " + author.getLastName());
        }

        LOG.info("Get author by name like a ");
        authorList = authorRepository.getAuthorsByName("a");
        for (Author author : authorList) {
            LOG.info(author.getId() + " " + author.getName() + " " + author.getLastName());
        }

    }

}
