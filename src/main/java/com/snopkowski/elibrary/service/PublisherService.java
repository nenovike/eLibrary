package com.snopkowski.elibrary.service;

import com.snopkowski.elibrary.dao.PublisherDao;
import com.snopkowski.elibrary.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("publisherService")
@Transactional
public class PublisherService {

    @Autowired
    PublisherRepository publisherRepository;

    public void save(PublisherDao publisherDao) {
        publisherRepository.save(publisherDao);
    }

    public List<PublisherDao> findAll() {
        return publisherRepository.findAll();
    }

    public PublisherDao findByName(String name) {
        return publisherRepository.findByName(name);
    }

    public PublisherDao findById(int id) {
        return publisherRepository.findById(id);
    }
}
