package com.snopkowski.elibrary.dao;

import com.snopkowski.elibrary.model.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

    public void save(User user) {
        persist(user);
    }

    public User findById(int id) {
        return getByKey(id);
    }

    public User findBySSO(String sso) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("ssoId", sso));
        return (User) crit.uniqueResult();
    }

    public List<User> findAllUsers() {
        Criteria criteria = createEntityCriteria();
        criteria.createAlias("userProfiles", "userProfiles");
        criteria.add(Restrictions.eq("userProfiles.type", "USER"));
        //criteria.setProjection(Projections.property("user"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return (List<User>) criteria.list();
    }

}
