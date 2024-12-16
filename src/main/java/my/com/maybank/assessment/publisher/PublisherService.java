package my.com.maybank.assessment.publisher;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PublisherService {
    private final PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public Page<Publisher> getAllPublishers(Pageable pageable) {
        return publisherRepository.findAll(pageable);
    }

    public Publisher getPublisherById(Long id) {
        return publisherRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Publisher not found with id: " + id));
    }

    @Transactional
    public Publisher createPublisher(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    @Transactional
    public Publisher updatePublisher(Long id, Publisher publisherDetails) {
        Publisher publisher = getPublisherById(id);
        publisher.setName(publisherDetails.getName());
        publisher.setAddress(publisherDetails.getAddress());
        return publisherRepository.save(publisher);
    }

    @Transactional
    public void deletePublisher(Long id) {
        publisherRepository.deleteById(id);
    }
}
