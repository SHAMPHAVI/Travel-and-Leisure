package com.niit.talbackend.dao;

import java.io.Serializable;
import java.util.List;

import com.niit.talbackend.model.Order;

public interface OrderDao extends Serializable{
	public String save(Order order);
	public String update(Order order);
	public String delete(Order order);
    public Order get(String id);
    public List<Order> listall();

}
