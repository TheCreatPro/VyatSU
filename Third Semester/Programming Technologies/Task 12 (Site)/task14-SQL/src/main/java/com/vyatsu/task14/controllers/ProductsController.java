package com.vyatsu.task14.controllers;

import com.vyatsu.task14.entities.Product;
import com.vyatsu.task14.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping
    public String showProductsList(Model model,
                                   @RequestParam(value = "title", required = false) String title,
                                   @RequestParam(value = "minPrice", required = false) Integer minPrice,
                                   @RequestParam(value = "maxPrice", required = false) Integer maxPrice) {
        List<Product> filteredProducts = productsService.filterProducts(title, minPrice, maxPrice);
        model.addAttribute("products", filteredProducts);
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
        return "redirect:/products";
    }

    @PostMapping("/add")
    public String addOrUpdateProduct(@RequestParam(value = "id") Long id,
                                     @RequestParam(value = "title") String title,
                                     @RequestParam(value = "price") Integer price) {
        Product product = productsService.getById(id);
        if (product != null) {
            productsService.deleteById(product.getId());
            // Если товар с таким ID существует, обновляем его
            product.setId(id);
            product.setTitle(title);
            product.setPrice(price);
            productsService.add(product); // save() в репозитории выполняет обновление
            System.out.println("Обновили старый");
        } else {
            // Если товара нет, создаём новый
            product = new Product();
            product.setId(id);
            product.setTitle(title);
            product.setPrice(price);
            productsService.add(product);
            System.out.println("Добавили новый");
        }
        return "redirect:/products";
    }




//    private void editProduct(Product product, String title, Integer price) {
//    product.setTitle(title);
//    product.setPrice(price);
//    productsService.add(product);
//}


}