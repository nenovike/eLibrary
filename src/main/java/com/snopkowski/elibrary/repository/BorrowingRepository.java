package com.snopkowski.elibrary.repository;

import com.snopkowski.elibrary.dao.BorrowingDao;
import org.hibernate.Query;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository("borrowDao")
public class BorrowingRepository extends AbstractRepository<Integer, BorrowingDao> {

    public void save(BorrowingDao borrowingDao) {
        persist(borrowingDao);
    }

    public void update(BorrowingDao borrowingDao) {
        super.update(borrowingDao);
    }

    public BorrowingDao findById(int id) {
        return getByKey(id);
    }

    public Long getNextId() {
        Query query = getSession().createSQLQuery("select nextval('lib.borrowing_id_seq') as num")
                .addScalar("num", StandardBasicTypes.BIG_INTEGER);

        return ((BigInteger) query.uniqueResult()).longValue();
    }
}
