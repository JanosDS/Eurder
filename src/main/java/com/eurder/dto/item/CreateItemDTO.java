package com.eurder.dto.item;

import com.eurder.domain.item.Price;

public class CreateItemDTO {
	private String name;
	private String description;
	private Price price;
	private int amountInStock;

	public CreateItemDTO(String name, String description, Price price, int amountInStock) {
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
}
