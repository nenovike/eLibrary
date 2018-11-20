package com.snopkowski.elibrary.dao;

import com.snopkowski.elibrary.model.Publisher;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("publisherDao")
public class PublisherDaoImpl extends AbstractDao<Integer, Publisher> implements PublisherDao {

    public void save(Publisher publisher) {
        persist(publisher);
    }

    public Publisher findById(int id) {
        return getByKey(id);
    }

    public Publisher findByName(String name) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("name", name));
        return (Publisher) crit.uniqueResult();
    }

    public List<Publisher> findAll() {
        Criteria crit = createEntityCriteria();
        crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        crit.addOrder(Order.asc("name"));
        return (List<Publisher>) crit.list();
    }

}
