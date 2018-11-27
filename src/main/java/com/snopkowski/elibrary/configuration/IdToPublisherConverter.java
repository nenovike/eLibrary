package com.snopkowski.elibrary.configuration;

import com.snopkowski.elibrary.dao.PublisherDao;
import com.snopkowski.elibrary.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IdToPublisherConverter implements Converter<Object, PublisherDao> {

    @Autowired
    PublisherService publisherService;

    public PublisherDao convert(Object element) {
        int id = Integer.parseInt((String) element);
        return publisherService.findById(id);
    }
}