package br.com.rsoft.api.cdc.book;

import br.com.rsoft.api.cdc.author.Author;
import br.com.rsoft.api.cdc.category.Category;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import static jakarta.persistence.GenerationType.*;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String summary;
    private BigDecimal price;
    @Column(name = "number_of_pages")
    private Integer numberOfPages;
    private String isbn;
    @Column(name = "publication_date")
    private LocalDate publicationDate;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @Deprecated
    public Book() {
    }


}
