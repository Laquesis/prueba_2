package com.prueba.cart.web.app.models.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
	public Cart save(Cart cart) {

		return cartDao.save(cart);
	}

	@Override
	@Transactional
	public Cart update(Long cartId, Cart cart) {

		Cart cartActual = cartDao.findById(cartId).orElse(null);
		cartActual.setProducts(cart.getProducts());
		return cartDao.save(cartActual);

	}

	@Override
	@Transactional
	public Cart findById(Long id) {

		return cartDao.findById(id).orElse(null);

	}

	@Override
	@Transactional
	public void delete(Long id) {
		cartDao.deleteById(id);

	}

	@Override
	@Transactional
	public Cart addProduct(Long cartId, Long productId) {

		Cart savedCart = cartDao.findById(cartId).orElse(null);
		Long[] list = savedCart.getProducts();
		ArrayList<Long> listIdProductInCart = new ArrayList<Long>(Arrays.asList(list));
		listIdProductInCart.add(productId);
		list = (listIdProductInCart).toArray(list);
		savedCart.setProducts(list);
		return cartDao.save(savedCart);

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

		Cart savedCart = this.findById(cartId);
		Long[] list = savedCart.getProducts();

		ArrayList<Long> listIdProductInCart = new ArrayList<Long>(Arrays.asList(list));

		Double totalAmounts = 0.0;

		for (Long productId : listIdProductInCart) {

			Product product = productDao.findById(productId).orElse(null);

			totalAmounts += product.getAmount();

		}

		return totalAmounts;

	}

}
