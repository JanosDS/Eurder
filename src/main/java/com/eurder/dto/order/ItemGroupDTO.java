package com.eurder.dto.order;

import com.eurder.domain.item.Price;

import java.time.LocalDate;
import java.util.UUID;

public class ItemGroupDTO {
	private final UUID itemId;
	private final int amountOfItems;
	private final Price price;
	private final LocalDate shippingDate;

	public ItemGroupDTO(UUID itemId, int amountOfItems, Price price, LocalDate shippingDate) {
		this.itemId = itemId;
		this.amountOfItems = amountOfItems;
		this.price = price;
		this.shippingDate = shippingDate;
	}

	public UUID getItemId() {
		return itemId;
	}

	public int getAmountOfItems() {
		return amountOfItems;
	}

	public Price getPrice() {
		return price;
	}

	public LocalDate getShippingDate() {
		return shippingDate;
	}
}
