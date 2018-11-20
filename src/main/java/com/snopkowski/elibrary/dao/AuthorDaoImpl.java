package com.snopkowski.elibrary.dao;

import com.snopkowski.elibrary.model.Author;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("authorDao")
public class AuthorDaoImpl extends AbstractDao<Integer, Author> implements AuthorDao {

    public void save(Author author) {
        persist(author);
    }

    public Author findById(int id) {
        return getByKey(id);
    }

    public Author findByName(String name) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.ilike("fullName", "%" + name + "%"));
        return (Author) crit.uniqueResult();
    }

    public List<Author> findAll() {
        Criteria crit = createEntityCriteria();
        crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        crit.addOrder(Order.asc("lastName"));
        return (List<Author>) crit.list();
    }

}
