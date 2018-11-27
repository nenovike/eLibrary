package com.snopkowski.elibrary.repository;

import com.snopkowski.elibrary.dao.GenreDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("genreDao")
public class GenreRepository extends AbstractRepository<Integer, GenreDao> {

    public void save(GenreDao genreDao) {
        persist(genreDao);
    }

    public GenreDao findById(int id) {
        return getByKey(id);
    }

    public GenreDao findByName(String name) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("name", name));
        return (GenreDao) crit.uniqueResult();
    }

    public List<GenreDao> findAll() {
        Criteria crit = createEntityCriteria();
        crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        crit.addOrder(Order.asc("name"));
        return (List<GenreDao>) crit.list();
    }

}
