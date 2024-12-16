package my.com.maybank.assessment.external;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class OpenLibraryDoc {
    private String key;
    private String title;
    private List<String> author_name;
    @JsonProperty("first_publish_year")
    private Integer firstPublishYear;
    private List<String> isbn;
    private List<String> publisher;
    @JsonProperty("number_of_pages_median")
    private Integer numberOfPagesMedian;

    public OpenLibraryDoc() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(List<String> author_name) {
        this.author_name = author_name;
    }

    public Integer getFirstPublishYear() {
        return firstPublishYear;
    }

    public void setFirstPublishYear(Integer firstPublishYear) {
        this.firstPublishYear = firstPublishYear;
    }

    public List<String> getIsbn() {
        return isbn;
    }

    public void setIsbn(List<String> isbn) {
        this.isbn = isbn;
    }

    public List<String> getPublisher() {
        return publisher;
    }

    public void setPublisher(List<String> publisher) {
        this.publisher = publisher;
    }

    public Integer getNumberOfPagesMedian() {
        return numberOfPagesMedian;
    }

    public void setNumberOfPagesMedian(Integer numberOfPagesMedian) {
        this.numberOfPagesMedian = numberOfPagesMedian;
    }

    public String getFirstAuthor() {
        if (author_name != null && !author_name.isEmpty()) {
            return author_name.get(0);
        }
        return null;
    }

    public String getFirstIsbn() {
        if (isbn != null && !isbn.isEmpty()) {
            return isbn.get(0);
        }
        return null;
    }

    public String getFirstPublisher() {
        if (publisher != null && !publisher.isEmpty()) {
            return publisher.get(0);
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OpenLibraryDoc that = (OpenLibraryDoc) o;
        return Objects.equals(key, that.key) &&
                Objects.equals(title, that.title) &&
                Objects.equals(author_name, that.author_name) &&
                Objects.equals(firstPublishYear, that.firstPublishYear) &&
                Objects.equals(isbn, that.isbn) &&
                Objects.equals(publisher, that.publisher) &&
                Objects.equals(numberOfPagesMedian, that.numberOfPagesMedian);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, title, author_name, firstPublishYear, isbn, publisher, numberOfPagesMedian);
    }

    @Override
    public String toString() {
        return "OpenLibraryDoc{" +
                "key='" + key + '\'' +
                ", title='" + title + '\'' +
                ", author_name=" + author_name +
                ", firstPublishYear=" + firstPublishYear +
                ", isbn=" + isbn +
                ", publisher=" + publisher +
                ", numberOfPagesMedian=" + numberOfPagesMedian +
                '}';
    }
}
