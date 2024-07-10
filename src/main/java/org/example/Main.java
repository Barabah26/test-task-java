package org.example;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        DocumentManager documentManager = new DocumentManager();

        DocumentManager.Author author1 = DocumentManager.Author.builder()
                .id("author1")
                .name("John Doe")
                .build();

        DocumentManager.Author author2 = DocumentManager.Author.builder()
                .id("author2")
                .name("Jane Smith")
                .build();


        DocumentManager.Document document1 = DocumentManager.Document.builder()
                .title("Introduction to Java")
                .content("Java is a high-level programming language.")
                .author(author1)
                .build();

        DocumentManager.Document document2 = DocumentManager.Document.builder()
                .title("Spring Boot Guide")
                .content("Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications.")
                .author(author2)
                .build();

        documentManager.save(document1);
        documentManager.save(document2);

        System.out.println("All Documents: " + documentManager.search(null));

        DocumentManager.SearchRequest searchRequest = DocumentManager.SearchRequest.builder()
                .titlePrefixes(Arrays.asList("Introduction", "Spring"))
                .containsContents(Arrays.asList("Java", "Spring"))
                .authorIds(Arrays.asList("author1", "author2"))
                .createdFrom(Instant.parse("2023-01-01T00:00:00Z"))
                .createdTo(Instant.now())
                .build();

        List<DocumentManager.Document> searchResults = documentManager.search(searchRequest);
        System.out.println("Search Results: " + searchResults);

        Optional<DocumentManager.Document> foundDocument = documentManager.findById(document1.getId());
        foundDocument.ifPresent(document -> System.out.println("Found Document: " + document));
    }
}
