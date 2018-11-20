package com.snopkowski.elibrary.dao;

import com.snopkowski.elibrary.model.Book;
import com.snopkowski.elibrary.model.Borrow;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("bookDao")
public class BookDaoImpl extends AbstractDao<Integer, Book> implements BookDao {

    public void save(Book book) {
        persist(book);
    }

    public void delete(Book book) {
        super.delete(book);
    }

    public Book findById(int id) {
        return getByKey(id);
    }

    public Book findByISBN(String isbn) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("isbn", isbn));
        return (Book) crit.uniqueResult();
    }

    public List<Book> findAll() {
        Criteria crit = createEntityCriteria();
        crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        crit.addOrder(Order.asc("name"));
        List<Book> books = (List<Book>) crit.list();
        for (Book book : books)
            book.setCurrentBorrowId(findCurrentBorrow(book.getId()));
        return books;
    }

    private Integer findCurrentBorrow(int id) {
        Criteria crit = createEntityCriteria(Borrow.class, "borrow");
        crit.createAlias("borrow.book", "book");
        crit.add(Restrictions.eq("book.id", id));
        crit.add(Restrictions.isNull("dateOfReturning"));
        Borrow borrow = (Borrow) crit.uniqueResult();
        if (borrow != null)
            return borrow.getId();
        else
            return null;
    }

    public List<Book> findByUserId(int userId) {
        Criteria borrowCriteria = createEntityCriteria(Borrow.class, "borrow");
        borrowCriteria.createAlias("borrow.user", "user");
        borrowCriteria.createAlias("borrow.book", "book");
        borrowCriteria.add(Restrictions.eq("user.id", userId));
        borrowCriteria.add(Restrictions.isNull("borrow.dateOfReturning"));
        borrowCriteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        borrowCriteria.setProjection(Projections.property("book"));
        return (List<Book>) borrowCriteria.list();
    }
}
