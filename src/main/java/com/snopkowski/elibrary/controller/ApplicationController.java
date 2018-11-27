package com.snopkowski.elibrary.controller;

import com.snopkowski.elibrary.dao.*;
import com.snopkowski.elibrary.dto.NewBookDto;
import com.snopkowski.elibrary.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Controller
public class ApplicationController {

    @Autowired
    BookController bookController;

    @Autowired
    UserProfileService userProfileService;

    @Autowired
    AuthorService authorService;

    @Autowired
    GenreService genreService;

    @Autowired
    UserService userService;

    @Autowired
    BookService bookService;

    @Autowired
    PublisherService publisherService;

    @Autowired
    BorrowingService borrowingService;

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String homePage(ModelMap model) {
        model.addAttribute("user", getPrincipal());
        model.addAttribute("roles", getRoles());
        return "welcome";
    }

    @RequestMapping(value = "/admin/books", method = RequestMethod.GET)
    public String adminBooksPage(ModelMap model) {
        BorrowingDao borrowingDao = new BorrowingDao();
        model.addAttribute("borrow", borrowingDao);
        model.addAttribute("roles", getRoles());
        return "admin/books";
    }

    @RequestMapping(value = "/user/books", method = RequestMethod.GET)
    public String userPage(ModelMap model) {
        model.addAttribute("roles", getRoles());
        return "user/books";
    }

    @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());
        return "accessDenied";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }


    @RequestMapping(value = "/admin/newUser", method = RequestMethod.GET)
    public String newRegistration(ModelMap model) {
        UserDao userDao = new UserDao();
        model.addAttribute("user", userDao);
        model.addAttribute("userRoles", getRoles());
        return "admin/newuser";
    }

    /*
     * This method will be called on form submission, handling POST request It
     * also validates the userDao input
     */
    @RequestMapping(value = "/admin/newUser", method = RequestMethod.POST)
    public String saveRegistration(@Valid UserDao userDao,
                                   BindingResult result, ModelMap model) {

        if (result.hasErrors()) {
            System.out.println("There were errors");
            return "admin/newuser";
        }
        userService.save(userDao);

        System.out.println("First Name : " + userDao.getFirstName());
        System.out.println("Last Name : " + userDao.getLastName());
        System.out.println("SSO ID : " + userDao.getSsoId());
        System.out.println("Password : " + userDao.getPassword());
        System.out.println("Email : " + userDao.getEmail());
        System.out.println("Checking UsrProfiles....");
        if (userDao.getUserProfileDaos() != null) {
            for (UserProfileDao profile : userDao.getUserProfileDaos()) {
                System.out.println("Profile : " + profile.getType());
            }
        }

        model.addAttribute("user", userDao.getFirstName());
        return "redirect:/?addSuccess";
    }

    @RequestMapping(value = "/admin/newBook", method = RequestMethod.GET)
    public String newBook(ModelMap model) {
        NewBookDto newBookDto = new NewBookDto();
        model.addAttribute("book", newBookDto);
        model.addAttribute("roles", getRoles());
        return "admin/newbook";
    }

    @RequestMapping(value = "/admin/newBook", method = RequestMethod.POST)
    public String saveBook(@Valid NewBookDto newBookDto,
                           BindingResult result, ModelMap model) {

        if (result.hasErrors()) {
            System.out.println("There were errors");
            return "admin/newbook";
        }

        BookDao bookDao = new BookDao();
        bookDao.setName(newBookDto.getName());
        bookDao.setIsbn(newBookDto.getIsbn());
        bookDao.setGenreDaos(newBookDto.getGenreDaos());
        PublisherDao publisherDao = publisherService.findByName(newBookDto.getPublisher());
        if (publisherDao == null) {
            publisherDao = new PublisherDao();
            publisherDao.setName(newBookDto.getPublisher());
            publisherService.save(publisherDao);
        }
        bookDao.setPublisherDao(publisherDao);

        AuthorDao authorDao = authorService.findByFullName(newBookDto.getAuthorFirstName(), newBookDto.getAuthorLastName());
        if (authorDao == null) {
            authorDao = new AuthorDao();
            authorDao.setFirstName(newBookDto.getAuthorFirstName());
            authorDao.setLastName(newBookDto.getAuthorLastName());
            authorService.save(authorDao);
        }
        bookDao.setAuthorDao(authorDao);
        bookDao.setVisible(true);
        bookService.save(bookDao);
        return "redirect:/admin/books?addSuccess";
    }

    @RequestMapping(value = "/admin/borrow", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded")
    public String borrowBook(@RequestParam int userDao, @RequestParam int bookDao,
                             ModelMap model) {

        BorrowingDao borrowingDao = new BorrowingDao();
        borrowingDao.setId(borrowingService.getNextId().intValue());
        borrowingDao.setBookDao(bookService.findById(bookDao));
        borrowingDao.setUserDao(userService.findById(userDao));
        borrowingDao.setDateOfBorrowing(new Date());
        borrowingDao.setDateOfReturning(null);

        borrowingService.save(borrowingDao);
        return "redirect:/admin/books?borrowSuccess";
    }

    @RequestMapping(value = "/admin/deleteBook", method = RequestMethod.GET)
    public String deleteBook(@RequestParam int id) {
        int result = bookController.deleteBook(id);
        if (result == id)
            return "redirect:/admin/books?deleteSuccess";
        else
            return "redirect:/admin/books?deleteFault";
    }

    @RequestMapping(value = "/admin/returnBook", method = RequestMethod.GET)
    public String returnBook(@RequestParam int id) {
        BorrowingDao borrowingDao = borrowingService.findById(id);
        borrowingDao.setDateOfReturning(new Date());
        borrowingService.update(borrowingDao);
        return "redirect:/admin/books?returnSuccess";
    }

    private String getPrincipal() {
        String userName;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

    private List<String> getRoles() {
        String userName;
        List<String> roles = new ArrayList<String>();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        try {
            Set<UserProfileDao> profiles = userService.findBySso(userName).getUserProfileDaos();
            for (UserProfileDao profile : profiles)
                roles.add(profile.getType());
        } catch (NullPointerException e) {
            return roles;
        }
        return roles;
    }


    @ModelAttribute("roles")
    public List<UserProfileDao> initializeProfiles() {
        return userProfileService.findAll();
    }

    @ModelAttribute("authors")
    public List<AuthorDao> initializeAuthors() {
        return authorService.findAll();
    }

    @ModelAttribute("genres")
    public List<GenreDao> initializeGenres() {
        return genreService.findAll();
    }

    @ModelAttribute("publishers")
    public List<PublisherDao> initializePublishers() {
        return publisherService.findAll();
    }

    @ModelAttribute("users")
    public List<UserDao> initializeUsers() {
        return userService.findAllUsers();
    }
}