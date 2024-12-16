package my.com.maybank.assessment.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findByTitleLikeIgnoreCase(String title, Pageable pageable);
}