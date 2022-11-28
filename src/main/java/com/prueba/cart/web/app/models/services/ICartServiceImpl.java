package com.prueba.cart.web.app.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.prueba.cart.web.app.models.entity.Cart;

public class ICartServiceImpl implements ICartService{
	@Autowired
	private ICartService cartDao;
	@Override
	@Transactional(readOnly=true)
	public List<Cart> findAll() {
		return (List<Cart>) cartDao.findAll();
	}

	@Override
	@Transactional
	public void save(Cart cart) {
		cartDao.save(cart);
		
	}

	@Override
	@Transactional
	public Cart findById(Long id) {
		return cartDao.findById(id);
	}

	@Override
	@Transactional
	public void delete(Cart cart) {
		cartDao.delete(cart);
		

		
	}

}
