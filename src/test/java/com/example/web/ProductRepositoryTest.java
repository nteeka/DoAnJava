package com.example.web;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.example.web.Model.Product;
import com.example.web.Model.ProductRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class ProductRepositoryTest {

	@Autowired
	private TestEntityManager testEntityManager;
	@Autowired
	private ProductRepository productRepository;
	@Test
	public void TestCreate() {
		Product product = new Product();
		product.setName("SamSung Galaxy Ultra");
		product.setBrand("SamSung");
		product.setPrice((long) 50000);
		
		Product product1 = new Product();
		product1.setName("IphoneX");
		product1.setBrand("Apple");
		product1.setPrice((long) 40000);
		
		productRepository.save(product);
		productRepository.save(product1);
		
		Product selectProduct = testEntityManager.find(Product.class, product.getId());
	    assertThat(product.getName()).isEqualTo(selectProduct.getName());

	    Product selectProduct1 = testEntityManager.find(Product.class, product1.getId());
	    assertThat(product1.getName()).isEqualTo(selectProduct1.getName());
	}
}
