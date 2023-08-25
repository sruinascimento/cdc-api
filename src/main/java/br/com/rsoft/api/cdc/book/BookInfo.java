package br.com.rsoft.api.cdc.book;

public record BookInfo(Long id, String name) {
    public BookInfo(Book book) {
        this(book.getId(), book.getTitle());
    }
}
