package com.example.authreg.controller;

import com.example.authreg.model.BookModel;
import com.example.authreg.model.ClassModel;
import com.example.authreg.model.ProductModel;
import com.example.authreg.repo.BookRepo;
import com.example.authreg.repo.ClassRepo;
import com.example.authreg.repo.ProductRepo;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    public BookRepo bookRepo;
    @Autowired
    public ClassRepo classRepo;
    @Autowired
    private ProductRepo productRepo;
    @PersistenceContext // Внедрение EntityManager
    private EntityManager entityManager;


    @GetMapping()
    public String listBook(Model model) {
        Iterable<BookModel> books = bookRepo.findAll();
        model.addAttribute("books", books);
        return "books/allBook";
    }

    @GetMapping("/addBook")
    public String showAddBookForm(Model model) {
        BookModel book = new BookModel();
        model.addAttribute("book", book);

        Iterable<ClassModel> classes = classRepo.findAll();
        model.addAttribute("classes", classes);

        Iterable<ProductModel> product = productRepo.findAll();
        model.addAttribute("product", product);

        return "books/addBook";
    }



    @PostMapping("/addBook")
    public String addBook(
            @Valid @ModelAttribute("book") BookModel book,
            BindingResult bindingResult,
            @RequestParam(name = "product.id", required = false) Long productId
    ) {

        if (productId != null) {
            ProductModel product = productRepo.findById(productId).orElse(null);
            if (product != null) {

                product = entityManager.merge(product);

                book.setProduct(product);
            }
        }

        bookRepo.save(book);

        return "redirect:/books";
    }




    @GetMapping("/editBook/{id}")
    public String showEditBookForm(@PathVariable("id") long id, Model model) {
        BookModel book = bookRepo.findById(id).orElse(null);
        if (book == null) {
            return "redirect:/books";
        }
        Iterable<ClassModel> classes = classRepo.findAll();
        model.addAttribute("classes", classes);

        Iterable<ProductModel> product = productRepo.findAll();
        model.addAttribute("product", product);

        model.addAttribute("book", book);
        return "books/updateBook";
    }

    @PostMapping("/editBook/{id}")
    public String editBook(
            @PathVariable("id") long id,
            @Valid @ModelAttribute("book") BookModel book,
            BindingResult bindingResult,
            Model model,
            @RequestParam(name = "product.id", required = false) Long productId
    ) {
        if (bindingResult.hasErrors()) {
            Iterable<ClassModel> classes = classRepo.findAll();
            model.addAttribute("classes", classes);

            Iterable<ProductModel> product = productRepo.findAll();
            model.addAttribute("product", product);

            return "books/updateBook";
        }
        book.setId(id);

        if (productId != null) {
            ProductModel product = productRepo.findById(productId).orElse(null);
            if (product != null) {
                book.setProduct(product);
            }
        }

        bookRepo.save(book);
        return "redirect:/books";
    }


    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") long id) {
        bookRepo.deleteById(id);
        return "redirect:/books";
    }

    @GetMapping("/search")
    public String searchProducts(@RequestParam(name = "name") String name, Model model) {
        Iterable<BookModel> books = bookRepo.findByNameContainingIgnoreCase(name);
        model.addAttribute("books", books);
        return "books/allBook";
    }

}