package com.snopkowski.elibrary.repository;

import com.snopkowski.elibrary.dao.BookDao;
import com.snopkowski.elibrary.dao.BorrowingDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Repository("bookDao")
public class BookRepository extends AbstractRepository<Integer, BookDao> {

    public void save(BookDao bookDao) {
        persist(bookDao);
    }

    public void delete(BookDao bookDao) {
        bookDao.setVisible(false);
        update(bookDao);
    }

    public BookDao findById(int id) {
        return getByKey(id);
    }

    public List<BookDao> findByAnything(String search) {
        Criteria criteria = createEntityCriteria(BookDao.class, "bookDao");
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.createAlias("bookDao.authorDao", "authorDao");
        criteria.createAlias("bookDao.publisherDao", "publisherDao");
        criteria.createAlias("bookDao.genreDaos", "genreDaos");
        criteria.add(Restrictions.or(
                Restrictions.ilike("bookDao.name", "%" + search + "%"),
                Restrictions.ilike("bookDao.isbn", "%" + search + "%"),
                Restrictions.ilike("authorDao.fullName", "%" + search + "%"),
                Restrictions.ilike("publisherDao.name", "%" + search + "%"),
                Restrictions.ilike("genreDaos.name", "%" + search + "%")
        ));
        return (List<BookDao>) criteria.list();
    }

    public BookDao findByISBN(String isbn) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("isbn", isbn));
        return (BookDao) crit.uniqueResult();
    }

    public List<BookDao> findAll() {
        Criteria crit = createEntityCriteria();
        crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        crit.add(Restrictions.not(Restrictions.eq("visible", false)));
        crit.addOrder(Order.asc("name"));
        return ((List<BookDao>) crit.list());
    }

    public Integer findCurrentBorrow(int id) {
        Criteria crit = createEntityCriteria(BorrowingDao.class, "borrowingDao");
        crit.createAlias("borrowingDao.bookDao", "book");
        crit.add(Restrictions.eq("book.id", id));
        crit.add(Restrictions.isNull("dateOfReturning"));
        BorrowingDao borrowingDao = (BorrowingDao) crit.uniqueResult();
        if (borrowingDao != null)
            return borrowingDao.getId();
        else
            return null;
    }

    public List<BookDao> findByUserId(int userId) {
        Criteria borrowCriteria = createEntityCriteria(BorrowingDao.class, "borrowing");
        borrowCriteria.createAlias("borrowing.userDao", "userDao");
        borrowCriteria.createAlias("borrowing.bookDao", "bookDao");
        borrowCriteria.add(Restrictions.eq("userDao.id", userId));
        borrowCriteria.add(Restrictions.isNull("borrowing.dateOfReturning"));
        borrowCriteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        borrowCriteria.setProjection(Projections.property("bookDao"));
        return (List<BookDao>) borrowCriteria.list();
    }

    public List<BookDao> findByAnything(int id, String search) {
        Criteria borrowCriteria = createEntityCriteria(BorrowingDao.class, "borrowing");
        borrowCriteria.createAlias("borrowing.userDao", "userDao");
        borrowCriteria.createAlias("borrowing.bookDao", "bookDao");
        borrowCriteria.add(Restrictions.eq("userDao.id", id));
        borrowCriteria.add(Restrictions.isNull("borrowing.dateOfReturning"));
        borrowCriteria.createAlias("bookDao.authorDao", "bookauthorDao");
        borrowCriteria.createAlias("bookDao.publisherDao", "bookpublisherDao");
        borrowCriteria.createAlias("bookDao.genreDaos", "bookgenreDaos");
        borrowCriteria.add(Restrictions.or(
                Restrictions.ilike("bookDao.name", "%" + search + "%"),
                Restrictions.ilike("bookDao.isbn", "%" + search + "%"),
                Restrictions.ilike("bookauthorDao.fullName", "%" + search + "%"),
                Restrictions.ilike("bookpublisherDao.name", "%" + search + "%"),
                Restrictions.ilike("bookgenreDaos.name", "%" + search + "%"
                )));
        borrowCriteria.setProjection(Projections.property("bookDao"));
        borrowCriteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        return new ArrayList<BookDao>(new HashSet<BookDao>((List<BookDao>) borrowCriteria.list()));
    }
}
