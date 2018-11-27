package com.snopkowski.elibrary.repository;

import com.snopkowski.elibrary.dao.AuthorDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("authorDao")
public class AuthorRepository extends AbstractRepository<Integer, AuthorDao> {

    public void save(AuthorDao authorDao) {
        persist(authorDao);
    }

    public AuthorDao findById(int id) {
        return getByKey(id);
    }

    public AuthorDao findByName(String name) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("fullName", "%" + name + "%"));
        return (AuthorDao) crit.uniqueResult();
    }

    public AuthorDao findByFullName(String firstName, String lastName) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.and(Restrictions.eq("firstName", firstName), Restrictions.eq("lastName", lastName)));
        return (AuthorDao) crit.uniqueResult();
    }

    public List<AuthorDao> findAll() {
        Criteria crit = createEntityCriteria();
        crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        crit.addOrder(Order.asc("lastName"));
        return (List<AuthorDao>) crit.list();
    }

}
