package com.snopkowski.elibrary.dao;

import com.snopkowski.elibrary.model.Borrow;
import org.hibernate.Query;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository("borrowDao")
public class BorrowDaoImpl extends AbstractDao<Integer, Borrow> implements BorrowDao {

    public void save(Borrow borrow) {
        persist(borrow);
    }

    public void update(Borrow borrow) {
        super.update(borrow);
    }

    public Borrow findById(int id) {
        return getByKey(id);
    }

    public Long getNextId() {
        Query query = getSession().createSQLQuery("select nextval('lib.borrow_id_seq') as num")
                .addScalar("num", StandardBasicTypes.BIG_INTEGER);

        return ((BigInteger) query.uniqueResult()).longValue();
    }
}
