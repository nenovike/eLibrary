package com.snopkowski.elibrary.service;

import com.snopkowski.elibrary.dao.PublisherDao;
import com.snopkowski.elibrary.model.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("publisherService")
@Transactional
public class PublisherServiceImpl implements PublisherService {

    @Autowired
    PublisherDao dao;

    public void save(Publisher publisher) {
        dao.save(publisher);
    }

    public List<Publisher> findAll() {
        return dao.findAll();
    }

    public Publisher findByName(String name) {
        return dao.findByName(name);
    }

    public Publisher findById(int id) {
        return dao.findById(id);
    }
}
