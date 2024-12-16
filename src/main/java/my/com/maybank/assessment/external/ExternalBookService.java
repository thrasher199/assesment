package my.com.maybank.assessment.external;


import my.com.maybank.assessment.book.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExternalBookService {

    private static final Logger log = LoggerFactory.getLogger(ExternalBookService.class);

    private final RestTemplate restTemplate;

    @Value("${openlibrary.api.url:https://openlibrary.org}")
    private String apiUrl;

    public ExternalBookService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Book> searchBooks(String title) {
        try {
            String url = UriComponentsBuilder
                    .fromHttpUrl(apiUrl + "/search.json")
                    .queryParam("title", title.replace(" ", "+"))
                    .build()
                    .toUriString();

            OpenLibraryResponse response = restTemplate.getForObject(url, OpenLibraryResponse.class);

            if (response != null && response.getDocs() != null) {
                return response.getDocs().stream()
                        .map(this::convertToBook)
                        .collect(Collectors.toList());
            }

            return Collections.emptyList();

        } catch (Exception e) {
            throw new RuntimeException("Failed to search books from OpenLibrary API", e);
        }
    }

    private Book convertToBook(OpenLibraryDoc doc) {
        Book book = new Book();
        book.setTitle(doc.getTitle());
        book.setIsbn(doc.getFirstIsbn());
        book.setAuthor(doc.getFirstAuthor());
        book.setPublicationYear(doc.getFirstPublishYear());
        // Price would typically come from your own database
        return book;
    }
}
