package com.snopkowski.elibrary.repository;

import com.snopkowski.elibrary.dao.UserProfileDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userProfileDao")
public class UserProfileRepository extends AbstractRepository<Integer, UserProfileDao> {

    @SuppressWarnings("unchecked")
    public List<UserProfileDao> findAll() {
        Criteria crit = createEntityCriteria();
        crit.addOrder(Order.asc("type"));
        return (List<UserProfileDao>) crit.list();
    }

    public UserProfileDao findById(int id) {
        return getByKey(id);
    }

    public UserProfileDao findByType(String type) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("type", type));
        return (UserProfileDao) crit.uniqueResult();
    }
}
