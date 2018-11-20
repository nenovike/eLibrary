package com.snopkowski.elibrary.dao;

import com.snopkowski.elibrary.model.Borrow;

public interface BorrowDao {

    void save(Borrow borrow);

    void update(Borrow borrow);

    Borrow findById(int id);

    Long getNextId();
}

