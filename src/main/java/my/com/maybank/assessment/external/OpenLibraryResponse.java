package my.com.maybank.assessment.external;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class OpenLibraryResponse {
    private int numFound;
    private int start;
    private boolean numFoundExact;
    private List<OpenLibraryDoc> docs;

    public OpenLibraryResponse() {
    }

    public int getNumFound() {
        return numFound;
    }

    public void setNumFound(int numFound) {
        this.numFound = numFound;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public boolean isNumFoundExact() {
        return numFoundExact;
    }

    public void setNumFoundExact(boolean numFoundExact) {
        this.numFoundExact = numFoundExact;
    }

    public List<OpenLibraryDoc> getDocs() {
        return docs;
    }

    public void setDocs(List<OpenLibraryDoc> docs) {
        this.docs = docs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OpenLibraryResponse that = (OpenLibraryResponse) o;
        return numFound == that.numFound &&
                start == that.start &&
                numFoundExact == that.numFoundExact &&
                Objects.equals(docs, that.docs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numFound, start, numFoundExact, docs);
    }

    @Override
    public String toString() {
        return "OpenLibraryResponse{" +
                "numFound=" + numFound +
                ", start=" + start +
                ", numFoundExact=" + numFoundExact +
                ", docs=" + docs +
                '}';
    }
}
