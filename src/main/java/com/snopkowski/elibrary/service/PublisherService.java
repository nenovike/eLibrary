package com.snopkowski.elibrary.service;

import com.snopkowski.elibrary.model.Publisher;

import java.util.List;

public interface PublisherService {

    void save(Publisher publisher);

    List<Publisher> findAll();

    Publisher findByName(String name);

    Publisher findById(int id);
}
