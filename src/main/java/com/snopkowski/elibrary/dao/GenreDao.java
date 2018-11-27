package com.snopkowski.elibrary.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "GENRE")
public class GenreDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    @Column(name = "NAME", nullable = false)
    private String name;

    @ManyToMany(targetEntity = BookDao.class)
    @JoinTable(name = "BOOKS_GENRES",
            joinColumns = {@JoinColumn(name = "GENRE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "BOOK_ID")})
    @JsonBackReference
    private Set<BookDao> bookDaos = new HashSet<BookDao>();

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

    public Set<BookDao> getBookDaos() {
        return bookDaos;
    }

    public void setBookDaos(Set<BookDao> bookDaos) {
        this.bookDaos = bookDaos;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof GenreDao))
            return false;
        GenreDao other = (GenreDao) obj;
        if (id != other.id)
            return false;
        return name.equals(other.name);
    }

    @Override
    public String toString() {
        return "UserDao [id=" + id + ", name=" + name + "]";
    }


}
