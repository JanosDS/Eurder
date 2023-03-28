package com.eurder.repository;

import com.eurder.domain.order.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepository {
	private List<Order> orderList;

	public OrderRepository() {
		this.orderList = new ArrayList<>();
	}

	public Order addOrder(Order order){
		orderList.add(order);
		return order;
	}
}
