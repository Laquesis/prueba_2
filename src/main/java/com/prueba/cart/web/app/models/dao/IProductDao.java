package com.prueba.cart.web.app.models.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.prueba.cart.web.app.models.entity.Product;

@Repository
public interface IProductDao extends CrudRepository<Product, Long> {

}
