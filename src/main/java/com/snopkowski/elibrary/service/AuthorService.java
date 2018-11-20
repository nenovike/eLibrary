package com.snopkowski.elibrary.service;

import com.snopkowski.elibrary.model.Author;

import java.util.List;

public interface AuthorService {

    void save(Author author);

    List<Author> findAll();

    Author findByName(String name);

    Author findById(int id);
}
