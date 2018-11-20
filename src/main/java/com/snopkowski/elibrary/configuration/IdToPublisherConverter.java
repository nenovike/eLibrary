package com.snopkowski.elibrary.configuration;

import com.snopkowski.elibrary.model.Publisher;
import com.snopkowski.elibrary.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IdToPublisherConverter implements Converter<Object, Publisher> {

    @Autowired
    PublisherService publisherService;

    public Publisher convert(Object element) {
        int id = Integer.parseInt((String) element);
        return publisherService.findById(id);
    }
}