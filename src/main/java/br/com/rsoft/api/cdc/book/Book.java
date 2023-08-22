package br.com.rsoft.api.cdc.book;

import br.com.rsoft.api.cdc.author.Author;
import br.com.rsoft.api.cdc.category.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    @Size(max = 500)
    @Column(columnDefinition = "TEXT")
    private String summary;
    @Column(columnDefinition = "TEXT", name = "table_of_contents")
    private String tableOfContent;
    @NotNull
    @DecimalMin("20.00")
    private BigDecimal price;
    @Column(name = "number_of_pages")
    private Integer numberOfPages;
    @NotBlank
    private String isbn;
    @Future
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

    public Book(@NotBlank String title,
                @NotBlank String summary,
                String tableOfContent,
                @NotNull @DecimalMin("20.00") BigDecimal price,
                @NotNull @Min(20) Integer numberOfPages,
                @NotBlank String isbn,
                @Future LocalDate publicationDate) {
        this.title = title;
        this.summary = summary;
        this.tableOfContent = tableOfContent;
        this.price = price;
        this.numberOfPages = numberOfPages;
        this.isbn = isbn;
        this.publicationDate = publicationDate;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public String getTableOfContent() {
        return tableOfContent;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public String getIsbn() {
        return isbn;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public Category getCategory() {
        return category;
    }

    public Author getAuthor() {
        return author;
    }
}
