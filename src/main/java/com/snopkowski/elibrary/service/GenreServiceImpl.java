package com.snopkowski.elibrary.service;

import com.snopkowski.elibrary.dao.GenreDao;
import com.snopkowski.elibrary.model.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("genreService")
@Transactional
public class GenreServiceImpl implements GenreService {

    @Autowired
    GenreDao dao;

    public void save(Genre genre) {
        dao.save(genre);
    }

    public List<Genre> findAll() {
        return dao.findAll();
    }

    public Genre findByName(String name) {
        return dao.findByName(name);
    }

    public Genre findById(int id) {
        return dao.findById(id);
    }
}
