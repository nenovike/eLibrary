package com.snopkowski.elibrary.repository;

import com.snopkowski.elibrary.dao.UserDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userDao")
public class UserRepository extends AbstractRepository<Integer, UserDao> {

    public void save(UserDao userDao) {
        persist(userDao);
    }

    public UserDao findById(int id) {
        return getByKey(id);
    }

    public UserDao findBySSO(String sso) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("ssoId", sso));
        return (UserDao) crit.uniqueResult();
    }

    public List<UserDao> findAllUsers() {
        Criteria criteria = createEntityCriteria();
        criteria.createAlias("userProfileDaos", "userProfiles");
        criteria.add(Restrictions.eq("userProfiles.type", "USER"));
        //criteria.setProjection(Projections.property("user"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return (List<UserDao>) criteria.list();
    }

}
