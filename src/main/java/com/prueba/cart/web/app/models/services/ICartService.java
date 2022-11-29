package com.prueba.cart.web.app.models.services;

import java.util.List;

import com.prueba.cart.web.app.models.entity.Cart;
import com.prueba.cart.web.app.models.entity.Product;

public interface ICartService {

	public List<Cart> findAll();

	public Cart save(Cart cart);

	public Cart update(Long cartId, Cart cart);

	public Cart findById(Long id);

	public Cart addProduct(Long cartId, Long productId);

	public Double getTotalAmounts(Long cartId);

	public List<Product> listProductOfCart(Long cartId);

	public void delete(Long id);

}
