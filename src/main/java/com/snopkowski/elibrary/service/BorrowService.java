package com.snopkowski.elibrary.service;

import com.snopkowski.elibrary.model.Borrow;

public interface BorrowService {

    void save(Borrow borrow);

    void update(Borrow borrow);

    Borrow findById(int id);

    Long getNextId();
}
