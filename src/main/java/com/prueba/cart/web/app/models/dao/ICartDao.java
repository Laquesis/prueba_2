package com.prueba.cart.web.app.models.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.prueba.cart.web.app.models.entity.Cart;

@Repository
public interface ICartDao extends CrudRepository<Cart, Long> {

}
