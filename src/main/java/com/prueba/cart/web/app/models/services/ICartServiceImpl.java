package com.prueba.cart.web.app.models.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prueba.cart.web.app.models.entity.Cart;
import com.prueba.cart.web.app.models.entity.Product;

@Service
public class ICartServiceImpl implements ICartService {
	@Autowired
	private ICartService cartService;
	@Autowired
	private IProductService productService;

	@Override
	@Transactional(readOnly = true)
	public List<Cart> findAll() {
		return (List<Cart>) cartService.findAll();
	}

	@Override
	@Transactional
	public void save(Cart cart) {

		cartService.save(cart);
	}

	@Override
	@Transactional
	public void update(Long cartId, Cart cart) {

		this.findById(cartId);
		cart.setId(cartId);

		cartService.save(cart);

	}

	@Override
	@Transactional
	public Cart findById(Long id) {
		return cartService.findById(id);
	}

	@Override
	@Transactional
	public void delete(Cart cart) {
		cartService.delete(cart);

	}

	@Override
	@Transactional
	public void addProduct(Long cartId, Product product) {

		Cart savedCart = this.findById(cartId);
		Product savedProduct = productService.findById(product.getId());

		try {
			List<Product> products = new ArrayList<Product>();
			products.add(savedProduct);
			savedCart.setProducts(products);
			this.update(cartId, savedCart);
		} catch (Exception e) {

		}

	}

	@Override
	public Double getTotalAmounts(Long cartId) {

		Cart savedCart = this.findById(cartId);
		List<Product> products = savedCart.getProducts();
		Double totalAmounts = 0D;

		for (Product product : products) {
			totalAmounts += product.getAmount();
		}

		return totalAmounts;

	}

}
