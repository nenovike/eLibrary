package com.snopkowski.elibrary.service;

import com.snopkowski.elibrary.dao.GenreDao;
import com.snopkowski.elibrary.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("genreService")
@Transactional
public class GenreService {

    @Autowired
    GenreRepository genreRepository;

    public void save(GenreDao genreDao) {
        genreRepository.save(genreDao);
    }

    public List<GenreDao> findAll() {
        return genreRepository.findAll();
    }

    public GenreDao findByName(String name) {
        return genreRepository.findByName(name);
    }

    public GenreDao findById(int id) {
        return genreRepository.findById(id);
    }
}
