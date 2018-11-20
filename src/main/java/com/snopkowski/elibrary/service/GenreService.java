package com.snopkowski.elibrary.service;

import com.snopkowski.elibrary.model.Genre;

import java.util.List;

public interface GenreService {

    void save(Genre genre);

    List<Genre> findAll();

    Genre findByName(String name);

    Genre findById(int id);
}
