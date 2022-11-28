package com.prueba.cart.web.app.models.services;

import java.util.List;

import com.prueba.cart.web.app.models.entity.Cart;



public interface ICartService {
	
	public List <Cart> findAll();

	public void save(Cart cart);

	public Cart findById(Long id);

	public void delete(Cart cart);

}
