package my.com.maybank.assessment.book;

import jakarta.persistence.EntityNotFoundException;
import my.com.maybank.assessment.publisher.Publisher;
import my.com.maybank.assessment.publisher.PublisherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.utility.TestcontainersConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Import(TestcontainersConfiguration.class)
@ActiveProfiles("test")
@Transactional
class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    private Publisher publisher;

    @BeforeEach
    void setUp() {
        publisher = new Publisher();
        publisher.setName("Test Publisher");
        publisher.setAddress("123 Test St");
        publisherRepository.save(publisher);
    }

    @Test
    void getAllBooks_ShouldReturnPaginatedBooks() {
        // Arrange
        for (int i = 0; i < 15; i++) {
            Book book = new Book();
            book.setTitle("Book " + i);
            book.setIsbn("ISBN-" + i);
            book.setPrice(29.99);
            book.setPublisher(publisher);
            bookRepository.save(book);
        }

        // Act
        Page<Book> result = bookService.getBooks(PageRequest.of(0, 10));

        // Assert
        assertEquals(10, result.getContent().size());
        assertEquals(15, result.getTotalElements());
        assertEquals(2, result.getTotalPages());
    }

    @Test
    void getBookById_ShouldReturnBook_WhenBookExists() {
        // Arrange
        Book book = new Book();
        book.setTitle("Test Book");
        book.setIsbn("ISBN-123");
        book.setPrice(29.99);
        book.setPublisher(publisher);
        Book savedBook = bookRepository.save(book);

        // Act
        Book result = bookService.getBookById(savedBook.getId());

        // Assert
        assertNotNull(result);
        assertEquals("Test Book", result.getTitle());
        assertEquals("ISBN-123", result.getIsbn());
    }

    @Test
    void getBookById_ShouldThrowException_WhenBookDoesNotExist() {
        // Act & Assert
        assertThrows(EntityNotFoundException.class, () ->
                bookService.getBookById(999L)
        );
    }

    @Test
    void createBook_ShouldSaveBook_WhenPublisherExists() {
        // Arrange
        Book book = new Book();
        book.setTitle("New Book");
        book.setIsbn("ISBN-NEW");
        book.setPrice(39.99);
        book.setPublisher(publisher);

        // Act
        Book result = bookService.createBook(book);

        // Assert
        assertNotNull(result.getId());
        assertEquals("New Book", result.getTitle());
        assertEquals(publisher.getId(), result.getPublisher().getId());
    }

    @Test
    void createBook_ShouldThrowException_WhenPublisherDoesNotExist() {
        // Arrange
        Book book = new Book();
        book.setTitle("New Book");
        Publisher nonExistentPublisher = new Publisher();
        nonExistentPublisher.setId(999L);
        book.setPublisher(nonExistentPublisher);

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () ->
                bookService.createBook(book)
        );
    }

    @Test
    void updateBook_ShouldUpdateExistingBook() {
        // Arrange
        Book book = new Book();
        book.setTitle("Original Title");
        book.setIsbn("ISBN-123");
        book.setPrice(29.99);
        book.setPublisher(publisher);
        Book savedBook = bookRepository.save(book);

        Book updateDetails = new Book();
        updateDetails.setTitle("Updated Title");
        updateDetails.setIsbn("ISBN-456");
        updateDetails.setPrice(39.99);

        // Act
        Book result = bookService.updateBook(savedBook.getId(), updateDetails);

        // Assert
        assertEquals("Updated Title", result.getTitle());
        assertEquals("ISBN-456", result.getIsbn());
        assertEquals(39.99, result.getPrice());
    }
}