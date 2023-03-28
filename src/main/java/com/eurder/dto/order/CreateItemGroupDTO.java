package com.eurder.dto.order;

import java.util.UUID;

public class CreateItemGroupDTO {
	private UUID itemId;
	private int amountOfItems;

	public CreateItemGroupDTO(UUID itemId, int amountOfItems) {
		this.itemId = itemId;
		this.amountOfItems = amountOfItems;
	}

	public UUID getItemId() {
		return itemId;
	}

	public int getAmountOfItems() {
		return amountOfItems;
	}
}
