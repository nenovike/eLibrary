package com.snopkowski.elibrary.dao;

import com.snopkowski.elibrary.model.Genre;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("genreDao")
public class GenreDaoImpl extends AbstractDao<Integer, Genre> implements GenreDao {

    public void save(Genre genre) {
        persist(genre);
    }

    public Genre findById(int id) {
        return getByKey(id);
    }

    public Genre findByName(String name) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("name", name));
        return (Genre) crit.uniqueResult();
    }

    public List<Genre> findAll() {
        Criteria crit = createEntityCriteria();
        crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        crit.addOrder(Order.asc("name"));
        return (List<Genre>) crit.list();
    }

}
