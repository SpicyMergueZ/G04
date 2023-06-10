package com.gestion.g04.controllers;

import com.gestion.g04.entities.Product;
import com.gestion.g04.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @RequestMapping("/createProduct")
    public String createProduct() {

        return "CreateProduct";
    }


    @RequestMapping(value = "/helloMethod", method = RequestMethod.POST)
    public String helloMethod(
            @ModelAttribute("product") Product product,
            ModelMap modelMap,
            @RequestParam("nameProduct") String nameProduct, @RequestParam("priceProduct") String priceProduct,
            @RequestParam("dateJsp") String dateController
    ) throws Exception {
        //System.out.println(nameProduct);System.out.println(priceProduct);
        //AU CHOIX
        // PAR MODELATTRIBUTE POUR LA CREATION DIRECTE DE L'ENTITE DEPUIS HTML
        //CORRESPONDANCE ENTRE LES PARAMETRE DANS HTML ET LES NOM DES ATTRIBUTS
        product.setDateCreate(new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(dateController)));
        productService.saveProduct(product);

        //OU
        //PAR REQUESTPARAM (PARAMETRES POUR CREER LA NOUVELLE ENTITE)
        Product p = new Product();
        p.setNameProduct(nameProduct + "-bis");
        p.setPriceProduct(Double.parseDouble(priceProduct));
        p.setDateCreate(new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(dateController)));
        productService.saveProduct(p);

        /*Product product= new Product();SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
        Date dateCreation = dateFormat.parse(String.valueOf(dateController));product.setNameProduct(nameProduct);
        product.setPriceProduct(priceProduct);product.setDateCreate(dateCreation);productService.saveProduct(product);*/
        List<Product> myproducts = productService.getAllProducts();
        Double mavariable = 50.0;
        modelMap.addAttribute("mon_message", p);
        modelMap.addAttribute("message", "MESSAGE RECU");

        return "ProductsList";
    }

    @RequestMapping("/saveProduct")
    public String saveProduct(
            @Valid Product product,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "CreateProduct";
        }
        Product memo = productService.saveProduct(product);
        return "CreateProduct";
    }

    @RequestMapping("/productsList")
    public String productsList(ModelMap modelMap,
                                @RequestParam (name ="page", defaultValue = "0") int page,
                               @RequestParam (name ="size", defaultValue = "4") int size
    ) {

        Page<Product> productsController = productService.getAllProductsByPage(page, size);
        modelMap.addAttribute("productsJsp", productsController);
        modelMap.addAttribute("pages", new int[productsController.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);

        return "ProductsList";
    }

    @RequestMapping("/deleteProduct")
    public String deleteProduct(@RequestParam("id") Long id, ModelMap modelMap,
                                @RequestParam (name ="page", defaultValue = "0") int page,
                                @RequestParam (name ="size", defaultValue = "4") int size) {

        productService.deleteProductById(id);

        Page<Product> productsController = productService.getAllProductsByPage(page, size);
        modelMap.addAttribute("productsJsp", productsController);
        modelMap.addAttribute("pages", new int[productsController.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);

        return "ProductsList";

    }

    @RequestMapping("/showProduct")
    public String showProduct(@RequestParam("id") Long id, ModelMap modelMap) {

        Product productController = productService.getProduct(id);
        modelMap.addAttribute("productJsp", productController);

        return "EditProduct";

    }

    @RequestMapping("/updateProduct")
    public String updateProduct(
            @ModelAttribute("product") Product product
    ) {

        Product memo = productService.saveProduct(product);


        return "ProductsList";
    }

    @RequestMapping("/hello")
    public String hello() {
        System.out.println("HELLO");

        return "wMaPage";
    }

    @GetMapping("/")
    public String home(){

        return "redirect:/productsList";
    }
    @GetMapping("/accessDenied")
    public String accessDenied(){

        return "accessDenied";
    }
   @GetMapping("/login")
    public String login(){

        return "login";
    }

}
