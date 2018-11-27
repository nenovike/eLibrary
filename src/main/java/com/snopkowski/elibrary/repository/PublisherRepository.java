package com.snopkowski.elibrary.repository;

import com.snopkowski.elibrary.dao.PublisherDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("publisherDao")
public class PublisherRepository extends AbstractRepository<Integer, PublisherDao> {

    public void save(PublisherDao publisherDao) {
        persist(publisherDao);
    }

    public PublisherDao findById(int id) {
        return getByKey(id);
    }

    public PublisherDao findByName(String name) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("name", name));
        return (PublisherDao) crit.uniqueResult();
    }

    public List<PublisherDao> findAll() {
        Criteria crit = createEntityCriteria();
        crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        crit.addOrder(Order.asc("name"));
        return (List<PublisherDao>) crit.list();
    }

}
