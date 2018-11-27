package com.snopkowski.elibrary.configuration;

import com.snopkowski.elibrary.dao.GenreDao;
import com.snopkowski.elibrary.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IdToGenreConverter implements Converter<Object, GenreDao> {

    @Autowired
    GenreService genreService;

    public GenreDao convert(Object element) {
        int id = Integer.parseInt((String) element);
        return genreService.findById(id);
    }
}