package com.snopkowski.elibrary.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Formula;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "AUTHOR")
public class AuthorDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @NotEmpty
    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @JsonIgnore
    @Formula(value = "FIRST_NAME || ' '|| LAST_NAME")
    private String fullName;

    @ManyToMany(targetEntity = BookDao.class)
    @JoinTable(name = "BOOKS_AUTHORS",
            joinColumns = {@JoinColumn(name = "AUTHOR_ID")},
            inverseJoinColumns = {@JoinColumn(name = "BOOK_ID")})
    @JsonBackReference
    private Set<BookDao> bookDaos = new HashSet<BookDao>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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
        if (!(obj instanceof AuthorDao))
            return false;
        AuthorDao other = (AuthorDao) obj;
        return id == other.id;
    }

    @Override
    public String toString() {
        return "UserDao [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
    }

}
