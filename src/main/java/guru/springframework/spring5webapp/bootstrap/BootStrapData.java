package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher p = new Publisher("RVSP Publications","New Delhi, India");
        publisherRepository.save(p);

        Author JKR = new Author("J.K.","Rowling");
        Book HP = new Book("Harry Potter", "12345");

        JKR.getBooks().add(HP);
        HP.getAuthors().add(JKR);
        p.getBooks().add(HP);
        HP.setPublisher(p);

        authorRepository.save(JKR);
        bookRepository.save(HP);
        publisherRepository.save(p);

        Author GRRM = new Author("G.R.R.", "Martin");
        Book GOT = new Book("Game of Thrones","98765");

        GRRM.getBooks().add(GOT);
        GOT.getAuthors().add(GRRM);
        GOT.setPublisher(p);
        p.getBooks().add(GOT);

        authorRepository.save(GRRM);
        bookRepository.save(GOT);
        publisherRepository.save(p);



        System.out.println("In Bootstrap....");
        System.out.println("Total Books: "+bookRepository.count());
        System.out.println("Publisher: "+p.getBooks().size());
    }
}
