package com.snopkowski.elibrary.dao;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "BORROWING")
public class BorrowingDao {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(name = "DATE_OF_BORROWING", nullable = false)
    private Date dateOfBorrowing;

    @Column(name = "DATE_OF_RETURNING")
    private Date dateOfReturning;

    @ManyToOne
    private BookDao bookDao;

    @ManyToOne
    private UserDao userDao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateOfBorrowing() {
        return dateOfBorrowing;
    }

    public void setDateOfBorrowing(Date dateOfBorrowing) {
        this.dateOfBorrowing = dateOfBorrowing;
    }

    public Date getDateOfReturning() {
        return dateOfReturning;
    }

    public void setDateOfReturning(Date dateOfReturning) {
        this.dateOfReturning = dateOfReturning;
    }

    public BookDao getBookDao() {
        return bookDao;
    }

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof BorrowingDao))
            return false;
        BorrowingDao other = (BorrowingDao) obj;
        return id == other.id;
    }
}
