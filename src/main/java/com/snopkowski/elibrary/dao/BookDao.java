package com.snopkowski.elibrary.dao;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "BOOK")
public class BookDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    @Column(name = "NAME", nullable = false)
    private String name;

    @NotEmpty
    @Column(name = "ISBN", nullable = false)
    private String isbn;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = GenreDao.class)
    @JoinTable(name = "BOOKS_GENRES",
            joinColumns = {@JoinColumn(name = "BOOK_ID")},
            inverseJoinColumns = {@JoinColumn(name = "GENRE_ID")})
    @JsonManagedReference
    private Set<GenreDao> genreDaos = new HashSet<GenreDao>();

    @ManyToOne
    private AuthorDao authorDao;

    @ManyToOne
    private PublisherDao publisherDao;

    @Column(name = "VISIBLE")
    private Boolean visible;

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

    public Set<GenreDao> getGenreDaos() {
        return genreDaos;
    }

    public void setGenreDaos(Set<GenreDao> genreDaos) {
        this.genreDaos = genreDaos;
    }

    public PublisherDao getPublisherDao() {
        return publisherDao;
    }

    public void setPublisherDao(PublisherDao publisherDao) {
        this.publisherDao = publisherDao;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof BookDao))
            return false;
        BookDao other = (BookDao) obj;
        if (id != other.id)
            return false;
        return isbn.equals(other.isbn);
    }

    @Override
    public String toString() {
        return "UserDao [id=" + id + ", name=" + name + ", isbn=" + isbn + "]";
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public AuthorDao getAuthorDao() {
        return authorDao;
    }

    public void setAuthorDao(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }
}
