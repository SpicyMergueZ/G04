package com.gestion.g04;

import com.gestion.g04.entities.Category;
import com.gestion.g04.entities.Product;
import com.gestion.g04.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
class G04ApplicationTests {

	@Autowired
	ProductRepository productRepository;

	@Test
	public void TestCreateProduct(){

		Product product = new Product("Apple TV", 3000.0, new Date());
		productRepository.save(product);
	}

	@Test
	public  void TestUpdateProduct(){
		Product product = productRepository.findById(1L).get();
		product.setPriceProduct(2500.00);
		productRepository.save(product);
	}

	@Test
	public void TestFindProductById(){
		Product product = productRepository.findById(1L).get();
		System.out.println("RESULTAT");
		System.out.println(product);

	}
	@Test
	public void TestFindAllProducts(){

		List<Product>products = productRepository.findAll();

		products.forEach(System.out::println);
	}

	@Test
	public void TestDeleteProductById(){
		productRepository.deleteById(1L);

	}

	@Test
	public  void TestDeleteAllproducts(){

		productRepository.deleteAll();
	}

	@Test
	public void TestFindAllProductsByPrice(){

		List<Product> products=productRepository.findAllProductsByPrice(18.0);
		products.forEach(System.out::println);

	}

	@Test
	public void TestFindAllProductsByNamePrice(){

		List<Product> products=productRepository.findAllProductsByNamePrice("Juice",18.0);
		products.forEach(System.out::println);

	}

	@Test
	public void TestFindAllProductsByCategory(){
		Category category=new Category();
		category.setIdCategory(3L);
		List<Product> products=productRepository.findAllProductsByCategory(category);
		products.forEach(System.out::println);
	}
	@Test
	public void TestFindAllProductsByNameSort(){

		List<Product> products=productRepository.findAllProductsByNameSort();
		products.forEach(System.out::println);

	}




}
