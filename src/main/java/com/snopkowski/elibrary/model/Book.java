package com.snopkowski.elibrary.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "BOOK")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    @Column(name = "NAME", nullable = false)
    private String name;

    @NotEmpty
    @Column(name = "ISBN", nullable = false)
    private String isbn;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Genre.class)
    @JoinTable(name = "BOOKS_GENRES",
            joinColumns = {@JoinColumn(name = "BOOK_ID")},
            inverseJoinColumns = {@JoinColumn(name = "GENRE_ID")})
    @JsonManagedReference
    private Set<Genre> genres = new HashSet<Genre>();

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Author.class)
    @JoinTable(name = "BOOKS_AUTHORS",
            joinColumns = {@JoinColumn(name = "BOOK_ID")},
            inverseJoinColumns = {@JoinColumn(name = "AUTHOR_ID")})
    @JsonManagedReference
    private Set<Author> authors = new HashSet<Author>();

    @ManyToOne
    private Publisher publisher;

    @Transient
    private Integer currentBorrowId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Integer getCurrentBorrowId() {
        return currentBorrowId;
    }

    public void setCurrentBorrowId(Integer currentBorrowId) {
        this.currentBorrowId = currentBorrowId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Book))
            return false;
        Book other = (Book) obj;
        if (id != other.id)
            return false;
        return isbn.equals(other.isbn);
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", isbn=" + isbn + "]";
    }

}
