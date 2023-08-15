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
    @GeneratedValue(strategy = IDENTITY  )
    private Long id;
    private String title;
    private String sumary;
    private BigDecimal price;
    private Integer numberOfPages;
    private String isbn;
    private LocalDate publicationDate;
    @ManyToOne
    private Category category;
    @ManyToOne
    private Author author;

    @Deprecated
    public Book() {
    }


}
