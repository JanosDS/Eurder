package com.eurder.domain.order;

import com.eurder.domain.user.User;

import java.util.List;
import java.util.UUID;

public class Order {

	private UUID orderId;
	private List<ItemGroup> itemGroupList;
	private User customer;

	public Order(List<ItemGroup> itemGroupList, User customer) {
		this.orderId = UUID.randomUUID();
		this.itemGroupList = itemGroupList;
		this.customer = customer;
	}

	public UUID getOrderId() {
		return orderId;
	}

	public List<ItemGroup> getItemGroupList() {
		return itemGroupList;
	}

	public User getCustomer() {
		return customer;
	}
}
