package my.com.maybank.assessment.external;

import my.com.maybank.assessment.book.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/external/books")
public class ExternalBookController {

    private final ExternalBookService externalBookService;

    public ExternalBookController(ExternalBookService externalBookService) {
        this.externalBookService = externalBookService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBookByTitle(@RequestParam String title) {
        List<Book> books = externalBookService.searchBooks(title);
        return books != null ? ResponseEntity.ok(books) : ResponseEntity.notFound().build();
    }
}
