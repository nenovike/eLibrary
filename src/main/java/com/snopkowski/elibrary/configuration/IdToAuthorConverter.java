package com.snopkowski.elibrary.configuration;

import com.snopkowski.elibrary.model.Author;
import com.snopkowski.elibrary.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IdToAuthorConverter implements Converter<Object, Author> {

    @Autowired
    AuthorService authorService;

    public Author convert(Object element) {
        int id = Integer.parseInt((String) element);
        return authorService.findById(id);
    }
}