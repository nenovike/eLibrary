package com.snopkowski.elibrary.dao;

import com.snopkowski.elibrary.model.Publisher;

import java.util.List;

public interface PublisherDao {

    void save(Publisher publisher);

    Publisher findById(int id);

    Publisher findByName(String name);

    List<Publisher> findAll();

}

