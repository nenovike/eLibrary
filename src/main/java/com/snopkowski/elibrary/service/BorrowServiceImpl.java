package com.snopkowski.elibrary.service;

import com.snopkowski.elibrary.dao.BorrowDao;
import com.snopkowski.elibrary.model.Borrow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("borrowService")
@Transactional
public class BorrowServiceImpl implements BorrowService {

    @Autowired
    BorrowDao dao;

    public void save(Borrow borrow) {
        dao.save(borrow);
    }

    public void update(Borrow borrow) {
        dao.update(borrow);
    }

    public Borrow findById(int id) {
        return dao.findById(id);
    }

    public Long getNextId() {
        return dao.getNextId();
    }
}
