package com.snopkowski.elibrary.service;

import com.snopkowski.elibrary.dao.AuthorDao;
import com.snopkowski.elibrary.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("authorService")
@Transactional
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public void save(AuthorDao authorDao) {
        authorRepository.save(authorDao);
    }

    public List<AuthorDao> findAll() {
        return authorRepository.findAll();
    }

    public AuthorDao findByFullName(String firstName, String lastName) {
        return authorRepository.findByFullName(firstName, lastName);
    }


    public AuthorDao findByName(String name) {
        return authorRepository.findByName(name);
    }

    public AuthorDao findById(int id) {
        return authorRepository.findById(id);
    }
}
