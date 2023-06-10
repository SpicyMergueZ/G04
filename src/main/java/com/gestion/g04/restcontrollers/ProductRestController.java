package com.gestion.g04.restcontrollers;


import com.gestion.g04.entities.Product;
import com.gestion.g04.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
//@AllArgsConstructor
public class ProductRestController {

   @Autowired
    ProductService productService;

   @GetMapping
   List<Product> getALlProducts(){
       return  productService.getAllProducts();
   }

}
