package my.com.maybank.assessment.publisher;

import jakarta.persistence.EntityNotFoundException;
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
class PublisherServiceTest {

    @Autowired
    private PublisherService publisherService;

    @Autowired
    private PublisherRepository publisherRepository;

    @Test
    void getAllPublishers_ShouldReturnPaginatedPublishers() {
        // Arrange
        for (int i = 0; i < 15; i++) {
            Publisher publisher = new Publisher();
            publisher.setName("Publisher " + i);
            publisher.setAddress("Address " + i);
            publisherRepository.save(publisher);
        }

        // Act
        Page<Publisher> result = publisherService.getAllPublishers(PageRequest.of(0, 10));

        // Assert
        assertEquals(10, result.getContent().size());
        assertEquals(15, result.getTotalElements());
        assertEquals(2, result.getTotalPages());
    }

    @Test
    void getPublisherById_ShouldReturnPublisher_WhenPublisherExists() {
        // Arrange
        Publisher publisher = new Publisher();
        publisher.setName("Test Publisher");
        publisher.setAddress("Test Address");
        Publisher savedPublisher = publisherRepository.save(publisher);

        // Act
        Publisher result = publisherService.getPublisherById(savedPublisher.getId());

        // Assert
        assertNotNull(result);
        assertEquals("Test Publisher", result.getName());
        assertEquals("Test Address", result.getAddress());
    }

    @Test
    void getPublisherById_ShouldThrowException_WhenPublisherDoesNotExist() {
        // Act & Assert
        assertThrows(EntityNotFoundException.class, () ->
                publisherService.getPublisherById(999L)
        );
    }

    @Test
    void createPublisher_ShouldSavePublisher() {
        // Arrange
        Publisher publisher = new Publisher();
        publisher.setName("New Publisher");
        publisher.setAddress("New Address");

        // Act
        Publisher result = publisherService.createPublisher(publisher);

        // Assert
        assertNotNull(result.getId());
        assertEquals("New Publisher", result.getName());
        assertEquals("New Address", result.getAddress());
    }

    @Test
    void updatePublisher_ShouldUpdateExistingPublisher() {
        // Arrange
        Publisher publisher = new Publisher();
        publisher.setName("Original Name");
        publisher.setAddress("Original Address");
        Publisher savedPublisher = publisherRepository.save(publisher);

        Publisher updateDetails = new Publisher();
        updateDetails.setName("Updated Name");
        updateDetails.setAddress("Updated Address");

        // Act
        Publisher result = publisherService.updatePublisher(savedPublisher.getId(), updateDetails);

        // Assert
        assertEquals("Updated Name", result.getName());
        assertEquals("Updated Address", result.getAddress());
    }
}