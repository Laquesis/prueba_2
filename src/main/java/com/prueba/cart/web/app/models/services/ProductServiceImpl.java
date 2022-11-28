package com.prueba.cart.web.app.models.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prueba.cart.web.app.models.dao.IProductDao;
import com.prueba.cart.web.app.models.entity.Product;

@Service
public class ProductServiceImpl implements IProductService {
	@Autowired
	private IProductDao productDao;

	@Override
	@Transactional(readOnly = true)
	public List<Product> findAll() {
		return (List<Product>) productDao.findAll();
	}

	@Override
	@Transactional
	public void save(Product product) {
		productDao.save(product);

	}

	@Override
	@Transactional(readOnly = true)
	public Product findById(Long id) {
		return productDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Product product) {
		productDao.delete(product);

	}

}
