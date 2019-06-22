package com.hangsun.spring5webapp.bootstrap;

import com.hangsun.spring5webapp.model.Author;
import com.hangsun.spring5webapp.model.Book;
import com.hangsun.spring5webapp.model.Publisher;
import com.hangsun.spring5webapp.repositories.AuthorRepository;
import com.hangsun.spring5webapp.repositories.BookRepository;
import com.hangsun.spring5webapp.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {


    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData(){

        Publisher publisher = new Publisher();
        publisher.setName("foo");
        publisherRepository.save(publisher);

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design","1234", publisher);
        eric.getBooks().add(ddd);

        authorRepository.save(eric);
        bookRepository.save(ddd);

        Author rod = new Author("Rod", "Johnson");
        Book noDJB = new Book("J2EE Dev", "2345", publisher);
        rod.getBooks().add(noDJB);

        authorRepository.save(rod);
        bookRepository.save(noDJB);
    }
}


