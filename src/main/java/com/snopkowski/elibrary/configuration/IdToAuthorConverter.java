package com.snopkowski.elibrary.configuration;

import com.snopkowski.elibrary.dao.AuthorDao;
import com.snopkowski.elibrary.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IdToAuthorConverter implements Converter<Object, AuthorDao> {

    @Autowired
    AuthorService authorService;

    public AuthorDao convert(Object element) {
        int id = Integer.parseInt((String) element);
        return authorService.findById(id);
    }
}