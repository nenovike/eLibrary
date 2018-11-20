package com.snopkowski.elibrary.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "GENRE")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    @Column(name = "NAME", nullable = false)
    private String name;

    @ManyToMany(targetEntity = Book.class)
    @JoinTable(name = "BOOKS_GENRES",
            joinColumns = {@JoinColumn(name = "GENRE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "BOOK_ID")})
    @JsonBackReference
    private Set<Book> books = new HashSet<Book>();

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

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Genre))
            return false;
        Genre other = (Genre) obj;
        if (id != other.id)
            return false;
        return name.equals(other.name);
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + "]";
    }


}
