package com.prueba.cart.web.app.models.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prueba.cart.web.app.models.dao.ICartDao;
import com.prueba.cart.web.app.models.dao.IProductDao;
import com.prueba.cart.web.app.models.entity.Cart;
import com.prueba.cart.web.app.models.entity.Product;

@Service
public class ICartServiceImpl implements ICartService {
	@Autowired
	private ICartDao cartDao;
	@Autowired
	private IProductDao productDao;

	@Override
	@Transactional(readOnly = true)
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
	public void update(Long cartId, Cart cart) {

		this.findById(cartId);
		cart.setId(cartId);

		cartDao.save(cart);

	}

	@Override
	@Transactional
	public Cart findById(Long id) {

		return cartDao.findById(id).orElse(null);

	}

	@Override
	@Transactional
	public void delete(Cart cart) {
		cartDao.delete(cart);

	}

	@Override
	@Transactional
	public void addProduct(Long cartId, Long productId) {

		Cart savedCart = cartDao.findById(cartId).orElse(null);
		Long[] list = savedCart.getProducts();
		ArrayList<Long> listIdProductInCart = new ArrayList<Long>(Arrays.asList(list));
		listIdProductInCart.add(productId);
		list = (listIdProductInCart).toArray(list);
		savedCart.setProducts(list);
		this.update(cartId, savedCart);

	}
	
	@Override
	@Transactional
	public List<Product> listProductOfCart(Long cartId) {
	
		Cart savedCart = cartDao.findById(cartId).orElse(null);
		Long[] list = savedCart.getProducts();
		List<Product> listTotal = new ArrayList<Product>();

		ArrayList<Long> listIdProductInCart = new ArrayList<Long>(Arrays.asList(list));

		for (Long productId : listIdProductInCart) {

			Product product = productDao.findById(productId).orElse(null);

			listTotal.add(product);
		}

		return listTotal;
	}

	@Override
	public Double getTotalAmounts(Long cartId) {

		Product product;
		Cart savedCart = this.findById(cartId);
		Long[] list = savedCart.getProducts();

		ArrayList<Long> listIdProductInCart = new ArrayList<Long>(Arrays.asList(list));

		Double totalAmounts = 0D;

		for (Long productId : listIdProductInCart) {

			product = productDao.findById(productId).orElse(null);

			totalAmounts += product.getAmount();
		}

		return totalAmounts;

	}
	

}
