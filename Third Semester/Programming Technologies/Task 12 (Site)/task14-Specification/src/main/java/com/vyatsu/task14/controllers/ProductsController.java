package com.vyatsu.task14.controllers;

import com.sun.org.apache.xml.internal.dtm.ref.DTMAxisIterNodeList;
import com.vyatsu.task14.entities.Product;
import com.vyatsu.task14.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductsController {
    private ProductsService productsService;

    @Autowired
    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }
    Product product = new Product();
    Specification<Product> specification = Specification.where(null);
    int page = 1;

    @GetMapping
    public String showProductsList(Model model,
                                   @RequestParam(value = "title", required = false) String title,
                                   @RequestParam(value = "minPrice", required = false) Integer minPrice,
                                   @RequestParam(value = "maxPrice", required = false) Integer maxPrice) {
        model.addAttribute("products", productsService.getProductsWithPagingAndFiltering(specification, PageRequest.of(page-1,5).first()));
        model.addAttribute("product", new Product());
        model.addAttribute("title", title);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        return "products";
    }

//    @PostMapping("/add")
//    public String addProduct(@ModelAttribute(value = "product") Product product) {
//        productsService.add(product);
//        return "redirect:/products";
//    }

    @PostMapping("/delete")
    public String deleteProduct(@RequestParam(value = "id") Long id) {
        productsService.deleteById(id);
        System.out.println("УДАЛЕНО");
        return "redirect:/products";
    }

    @PostMapping("/add")
    public String addOrUpdateProduct(@ModelAttribute(value = "product") Product product) {
        productsService.saveOrUpdate(product);
        System.out.println("ДОБАВЛЕНО");
        return "redirect:/products";
    }




//    private void editProduct(Product product, String title, Integer price) {
//    product.setTitle(title);
//    product.setPrice(price);
//    productsService.add(product);
//}


}