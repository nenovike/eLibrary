package com.snopkowski.elibrary.service;

import com.snopkowski.elibrary.dao.BorrowingDao;
import com.snopkowski.elibrary.repository.BorrowingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("borrowService")
@Transactional
public class BorrowingService {

    @Autowired
    BorrowingRepository borrowingRepository;

    public void save(BorrowingDao borrowingDao) {
        borrowingRepository.save(borrowingDao);
    }

    public void update(BorrowingDao borrowingDao) {
        borrowingRepository.update(borrowingDao);
    }

    public BorrowingDao findById(int id) {
        return borrowingRepository.findById(id);
    }

    public Long getNextId() {
        return borrowingRepository.getNextId();
    }
}
