package com.snopkowski.elibrary.dao;

import com.snopkowski.elibrary.model.Author;

import java.util.List;

public interface AuthorDao {

    void save(Author author);

    Author findById(int id);

    Author findByName(String name);

    List<Author> findAll();

}

