package com.prueba.cart.web.app.models.services;

import java.util.List;

import com.prueba.cart.web.app.models.entity.Cart;
import com.prueba.cart.web.app.models.entity.Product;

public interface ICartService {

	public List<Cart> findAll();

	public void save(Cart cart);

	public void update(Long cartId, Cart cart);

	public Cart findById(Long id);

	public void delete(Cart cart);

	public void addProduct(Long cartId, Long productId);

	public Double getTotalAmounts(Long cartId);

	public List<Product> listProductOfCart(Long cartId);

}
