package com.snopkowski.elibrary.dao;

import com.snopkowski.elibrary.model.Genre;

import java.util.List;

public interface GenreDao {

    void save(Genre genre);

    Genre findById(int id);

    Genre findByName(String name);

    List<Genre> findAll();

}

