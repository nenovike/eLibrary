package com.snopkowski.elibrary.controller;

import com.snopkowski.elibrary.dao.BookDao;
import com.snopkowski.elibrary.dao.UserDao;
import com.snopkowski.elibrary.dto.AdminBookDto;
import com.snopkowski.elibrary.dto.UserBookDto;
import com.snopkowski.elibrary.service.BookService;
import com.snopkowski.elibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;
import java.util.List;

@RestController
@EnableWebMvc
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    UserService userService;

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/admin/api/books", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<AdminBookDto> getAllBooks(@RequestParam(required = false) String search) {
        List<BookDao> bookDaos;
        if (search == null)
            bookDaos = bookService.findAll();
        else
            bookDaos = bookService.findByAnything(search);
        List<AdminBookDto> bookDtos = new ArrayList<AdminBookDto>();
        for (BookDao bookDao : bookDaos)
            bookDtos.add(new AdminBookDto(bookDao, bookService.findCurrentBorrow(bookDao.getId()), null));
        return bookDtos;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/admin/api/books/delete", method = RequestMethod.DELETE)
    public @ResponseBody
    int deleteBook(@RequestParam int id) {
        BookDao bookDao = bookService.findById(id);
        bookService.delete(bookDao);
        return id;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/user/api/books", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<UserBookDto> getUserBooks(@RequestParam(required = false) String search) {
        String userName;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        try {
            int id = userService.findBySso(userName).getId();
            List<BookDao> bookDaos;
            if (search == null)
                bookDaos = bookService.findByUserId(id);
            else
                bookDaos = bookService.findByAnything(id, search);
            List<UserBookDto> bookDtos = new ArrayList<UserBookDto>();
            for (BookDao bookDao : bookDaos)
                bookDtos.add(new UserBookDto(bookDao));
            return bookDtos;

        } catch (NullPointerException e) {
            return new ArrayList<UserBookDto>();
        }
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/admin/api/users", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<UserDao> getAllUsers() {
        return userService.findAllUsers();
    }
}