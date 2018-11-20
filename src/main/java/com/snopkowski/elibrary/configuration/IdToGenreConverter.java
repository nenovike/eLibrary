package com.snopkowski.elibrary.configuration;

import com.snopkowski.elibrary.model.Genre;
import com.snopkowski.elibrary.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IdToGenreConverter implements Converter<Object, Genre> {

    @Autowired
    GenreService genreService;

    public Genre convert(Object element) {
        int id = Integer.parseInt((String) element);
        return genreService.findById(id);
    }
}