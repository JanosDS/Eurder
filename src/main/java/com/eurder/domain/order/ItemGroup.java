package com.eurder.domain.order;

import com.eurder.domain.item.Item;
import com.eurder.domain.item.Price;

import java.time.LocalDate;
import java.util.UUID;

public class ItemGroup {
	private UUID itemId;
	private int amountOfItems;
	private Price price;
	private LocalDate shippingDate;

	public ItemGroup(UUID itemId, int amount, Price price) {
		this.itemId = itemId;
		this.amountOfItems = amount;
		this.price = price;
		calculateShippingDate();
	}

	public UUID getItemId() {
		return itemId;
	}

	public int getAmountOfItems() {
		return amountOfItems;
	}

	public LocalDate getShippingDate() {
		return shippingDate;
	}

	public Price getPricePerItem(){
		return price;
	}

	public Price getTotalPrice(){
		return new Price(price.getCurrency(), (price.getAmount() * amountOfItems));
	}

	private void calculateShippingDate(){
		this.shippingDate = LocalDate.now().plusDays(7);
	}
}
