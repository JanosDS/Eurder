package com.eurder.domain.item;

import java.util.UUID;

public class Item {

	private UUID uuid;
	private String name;
	private String description;
	private Price price;
	private int amountInStock;

	public Item(String name, String description, Price price, int amountInStock) {
		this.uuid = UUID.randomUUID();
		this.name = name;
		this.description = description;
		this.price = price;
		this.amountInStock = amountInStock;
	}

	public UUID getUuid() {
		return uuid;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Price getPrice() {
		return price;
	}

	public int getAmountInStock() {
		return amountInStock;
	}
}
