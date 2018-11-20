package com.snopkowski.elibrary.service;

import com.snopkowski.elibrary.dao.AuthorDao;
import com.snopkowski.elibrary.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("authorService")
@Transactional
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorDao dao;

    public void save(Author author) {
        dao.save(author);
    }

    public List<Author> findAll() {
        return dao.findAll();
    }

    public Author findByName(String name) {
        return dao.findByName(name);
    }

    public Author findById(int id) {
        return dao.findById(id);
    }
}
