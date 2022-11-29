package com.prueba.cart.web.app.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
import org.springframework.http.ResponseEntity;

import com.prueba.cart.web.app.models.entity.Cart;
import com.prueba.cart.web.app.models.entity.Product;
import com.prueba.cart.web.app.models.services.ICartService;
import com.prueba.cart.web.app.models.services.IProductService;

@CrossOrigin(origins = { "http://localhost:8080" })
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
	public ResponseEntity<?> showCartById(@PathVariable Long id) {

		Cart carro = null;
		Map<String, Object> res = new HashMap<>();
		try {
			carro = cartService.findById(id);
		} catch (DataAccessException e) {

			res.put("message", "Error en la consulta en la bbdd");
			res.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(res, HttpStatus.NOT_FOUND);

		}

		if (carro == null) {

			res.put("message", "El carrito Id:".concat(id.toString().concat("no existe")));
			return new ResponseEntity<Map<String, Object>>(res, HttpStatus.NOT_FOUND);

		}

		return new ResponseEntity<Cart>(carro, HttpStatus.OK);

	}

	// Ver una lista de productos de un carrito
	@GetMapping("/carts/{id}/products")
	public ResponseEntity<?> showProductsListOfCart(@PathVariable Long id) {
		// return cartService.listProductOfCart(id);
		List<Product> products = null;
		Map<String, Object> res = new HashMap<>();
		try {
			products = cartService.listProductOfCart(id);
		} catch (DataAccessException e) {

			res.put("message", "Error en la consulta en la bbdd");
			res.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(res, HttpStatus.NOT_FOUND);

		}

		if (products == null) {

			res.put("message", "El carrito Id:".concat(id.toString().concat("no existe")));
			return new ResponseEntity<Map<String, Object>>(res, HttpStatus.NOT_FOUND);

		}

		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);

	}

	// Crear un carrito
	@PostMapping("/carts")
	// @ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> createCart(@RequestBody Cart cart) {

		Cart carroNew = null;
		Map<String, Object> res = new HashMap<>();
		try {
			carroNew = cartService.save(cart);
		} catch (DataAccessException e) {
			res.put("message", "Error en la consulta en la bbdd");
			res.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(res, HttpStatus.NOT_FOUND);

		}
		res.put("message", "Carrito creado éxitosamente");
		res.put("carro", carroNew);

		return new ResponseEntity<Map<String, Object>>(res, HttpStatus.CREATED);

	}

	// Update un carrito
	@PutMapping("/carts/{id}")
	// @ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> updateCart(@PathVariable Long id, @RequestBody Cart cart) {
		Cart carroUpdate = null;
		Map<String, Object> res = new HashMap<>();
		try {
			carroUpdate = cartService.update(id, cart);

		} catch (DataAccessException e) {
			res.put("message", "No se pudo editar en la bbdd");
			res.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		res.put("message", "Carrito editado éxitosamente");
		res.put("carro", carroUpdate);

		return new ResponseEntity<Map<String, Object>>(res, HttpStatus.CREATED);

	}

	// Borrar carrito
	@DeleteMapping("/carts/{id}")
	// @ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> delete(@PathVariable Long id) {

		Map<String, Object> res = new HashMap<>();
		try {

			this.cartService.delete(id);

		} catch (DataAccessException e) {
			res.put("message", "No se pudo borrar en la bbdd");
			res.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(res, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		res.put("message", "Carrito borrado éxitosamente");
		return new ResponseEntity<Map<String, Object>>(res, HttpStatus.OK);
	}

	// Añadir producto
	@PostMapping("/carts/{id}/products")
	public ResponseEntity<?> addProduct(@PathVariable Long id, @RequestBody Long product) {
		cartService.addProduct(id, product);
		return new ResponseEntity("producto añadido", HttpStatus.OK);

	}

	// Total amount de la lista de productos
	@GetMapping("/carts/{id}/products/totalAmount")
	public Double getAllAmounts(@PathVariable Long id) {
		return cartService.getTotalAmounts(id);

	}

}
