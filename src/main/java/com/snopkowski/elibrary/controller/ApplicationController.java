package com.snopkowski.elibrary.controller;

import com.snopkowski.elibrary.model.*;
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
    BorrowService borrowService;

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String homePage(ModelMap model) {
        model.addAttribute("user", getPrincipal());
        model.addAttribute("roles", getRoles());
        return "welcome";
    }

    @RequestMapping(value = "/admin/books", method = RequestMethod.GET)
    public String adminBooksPage(ModelMap model) {
        Borrow borrow = new Borrow();
        model.addAttribute("borrow", borrow);
        model.addAttribute("roles", getRoles());
        return "admin/books";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String userPage(ModelMap model) {
        model.addAttribute("roles", getRoles());
        return "user";
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
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("userRoles", getRoles());
        return "admin/newuser";
    }

    /*
     * This method will be called on form submission, handling POST request It
     * also validates the user input
     */
    @RequestMapping(value = "/admin/newUser", method = RequestMethod.POST)
    public String saveRegistration(@Valid User user,
                                   BindingResult result, ModelMap model) {

        if (result.hasErrors()) {
            System.out.println("There were errors");
            return "admin/newuser";
        }
        userService.save(user);

        System.out.println("First Name : " + user.getFirstName());
        System.out.println("Last Name : " + user.getLastName());
        System.out.println("SSO ID : " + user.getSsoId());
        System.out.println("Password : " + user.getPassword());
        System.out.println("Email : " + user.getEmail());
        System.out.println("Checking UsrProfiles....");
        if (user.getUserProfiles() != null) {
            for (UserProfile profile : user.getUserProfiles()) {
                System.out.println("Profile : " + profile.getType());
            }
        }

        model.addAttribute("user", user.getFirstName());
        return "redirect:/?addSuccess";
    }

    @RequestMapping(value = "/admin/newBook", method = RequestMethod.GET)
    public String newBook(ModelMap model) {
        Book book = new Book();
        model.addAttribute("book", book);
        model.addAttribute("roles", getRoles());
        return "admin/newbook";
    }

    @RequestMapping(value = "/admin/newBook", method = RequestMethod.POST)
    public String saveBook(@Valid Book book,
                           BindingResult result, ModelMap model) {

        if (result.hasErrors()) {
            System.out.println("There were errors");
            return "admin/newbook";
        }
        bookService.save(book);
        return "redirect:/admin/books?addSuccess";
    }

    @RequestMapping(value = "/admin/borrow", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded")
    public String borrowBook(@RequestParam int user, @RequestParam int book,
                             ModelMap model) {

        Borrow borrow = new Borrow();
        borrow.setId(borrowService.getNextId().intValue());
        borrow.setBook(bookService.findById(book));
        borrow.setUser(userService.findById(user));
        borrow.setDateOfBorrowing(new Date());
        borrow.setDateOfReturning(null);

        borrowService.save(borrow);
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
        Borrow borrow = borrowService.findById(id);
        borrow.setDateOfReturning(new Date());
        borrowService.update(borrow);
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
            Set<UserProfile> profiles = userService.findBySso(userName).getUserProfiles();
            for (UserProfile profile : profiles)
                roles.add(profile.getType());
        } catch (NullPointerException e) {
            return roles;
        }
        return roles;
    }


    @ModelAttribute("roles")
    public List<UserProfile> initializeProfiles() {
        return userProfileService.findAll();
    }

    @ModelAttribute("authors")
    public List<Author> initializeAuthors() {
        return authorService.findAll();
    }

    @ModelAttribute("genres")
    public List<Genre> initializeGenres() {
        return genreService.findAll();
    }

    @ModelAttribute("publishers")
    public List<Publisher> initializePublishers() {
        return publisherService.findAll();
    }

    @ModelAttribute("users")
    public List<User> initializeUsers() {
        return userService.findAllUsers();
    }
}