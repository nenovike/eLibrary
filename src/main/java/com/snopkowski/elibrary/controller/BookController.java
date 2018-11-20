package com.snopkowski.elibrary.controller;

import com.snopkowski.elibrary.model.Book;
import com.snopkowski.elibrary.model.User;
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
    List<Book> getAllBooks() {
        return bookService.findAll();
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/admin/api/books/delete", method = RequestMethod.DELETE)
    public @ResponseBody
    int deleteBook(@RequestParam int id) {
        Book book = bookService.findById(id);
        bookService.delete(book);
        return id;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/user/api/books", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Book> getUserBooks() {
        String userName;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        try {
            int id = userService.findBySso(userName).getId();
            return bookService.findByUserId(id);

        } catch (NullPointerException e) {
            return new ArrayList<Book>();
        }
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/admin/api/users", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<User> getAllUsers() {
        return userService.findAllUsers();
    }
}