package com.prueba.cart.web.app.models.services;

import java.util.List;

import com.prueba.cart.web.app.models.entity.Product;

public interface IProductService {

	public List<Product> findAll();

	public void save(Product product);

	public Product findById(Long id);

	public void delete(Product product);

}
