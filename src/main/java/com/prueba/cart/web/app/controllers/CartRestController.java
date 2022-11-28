package com.prueba.cart.web.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import com.prueba.cart.web.app.models.entity.Cart;
import com.prueba.cart.web.app.models.entity.Product;
import com.prueba.cart.web.app.models.services.ICartService;
import com.prueba.cart.web.app.models.services.IProductService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class CartRestController {

	@Autowired
	private ICartService cartService;
	private IProductService productService;

	// Listado de todos los carritos
	@GetMapping("/carts")
	public List<Cart> indexCarts() {
		return cartService.findAll();
	}

	// Ver un carrito
	@GetMapping("/carts/{id}")
	public Cart showCartById(@PathVariable Long id) {
		return this.cartService.findById(id);
	}

	// Crear un carrito
	@PostMapping("/carts")
	@ResponseStatus(HttpStatus.CREATED)
	public Cart createCart(@RequestBody Cart cart) {
		this.cartService.save(cart);
		return cart;
	}
	//Update un carrito
	@PutMapping("/carts/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Cart updateCart(@PathVariable Long id, @RequestBody Cart cart) {
		this.cartService.update(id, cart);
		return cart;
	}
	
	//Borrar carrito
	@DeleteMapping("/carts/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		Cart currentCart = this.cartService.findById(id);
		this.cartService.delete(currentCart);
	}
	
	//
	@PostMapping("/carts/{id}/products")
	public void addProduct(@PathVariable Long id, @RequestBody Product product) {
		this.cartService.addProduct(id, product);

	}

	@GetMapping("/carts/{id}/products/totalAmount")
	public Double getAllTransactionFees(@PathVariable Long id) {
		return this.cartService.getTotalAmounts(id);

	}

}
