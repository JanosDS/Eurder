package com.eurder.dto.item;

import com.eurder.domain.item.Price;

import java.util.UUID;

public class ItemDTO {
	private final UUID uuid;
	private final String name;
	private final String description;
	private final Price price;
	private final int amountInStock;

	public ItemDTO(UUID uuid, String name, String description, Price price, int amountInStock) {
		this.uuid = uuid;
		this.name = name;
		this.description = description;
		this.price = price;
		this.amountInStock = amountInStock;
	}

	public ItemDTO(String name, String description, Price price, int amountInStock) {
		this.uuid = null;
		this.name = name;
		this.description = description;
		this.price = price;
		this.amountInStock = amountInStock;
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

	public UUID getUuid() {
		return uuid;
	}
}
