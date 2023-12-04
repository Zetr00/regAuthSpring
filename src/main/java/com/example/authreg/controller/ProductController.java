package com.example.authreg.controller;

import com.example.authreg.model.ProductModel;
import com.example.authreg.repo.ProductRepo;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
@PreAuthorize("hasAnyAuthority('USER')")
public class ProductController {
    private final ProductRepo productRepo;

    @Autowired
    public ProductController(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @GetMapping()
    public String listProduct(Model model) {
        Iterable<ProductModel> products = productRepo.findAll();
        model.addAttribute("products", products);
        return "products/allProduct";
    }

    @GetMapping("/addProduct")
    public String showAddProductForm(Model model) {
        ProductModel product = new ProductModel();
        model.addAttribute("product", product);
        return "products/addProduct";
    }

    @PostMapping("/addProduct")
    public String addProduct(@Valid @ModelAttribute("product") ProductModel product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "products/addProduct";
        }
        System.out.println("Name: " + product.getName());
        productRepo.save(product);
        return "redirect:/products";
    }

    @GetMapping("/editProduct/{id}")
    public String showEditProductForm(@PathVariable("id") long id, Model model) {
        ProductModel product = productRepo.findById(id).orElse(null);
        if (product == null) {
            return "redirect:/products";
        }
        model.addAttribute("product", product);
        return "products/updateProduct";
    }

    @PostMapping("/editProduct/{id}")
    public String editProduct(@PathVariable("id") int id, @Valid @ModelAttribute("product") ProductModel product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "products/updateProduct";
        }
        product.setId(id);
        productRepo.save(product);
        return "redirect:/products";
    }



    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") long id) {
        productRepo.deleteById(id);
        return "redirect:/products";
    }
}
