package com.eurder.dto.order;

import com.eurder.domain.item.Price;
import com.eurder.dto.user.AddressDTO;

import java.time.LocalDate;
import java.util.UUID;

public class ShippingDTO {

	private UUID itemId;
	private UUID orderId;
	private int amountOfItems;
	private Price price;
	private LocalDate shippingDate;
	private AddressDTO addressDTO;

	public ShippingDTO(UUID itemId, UUID orderId, int amountOfItems, Price price, LocalDate shippingDate, AddressDTO addressDTO) {
		this.itemId = itemId;
		this.orderId = orderId;
		this.amountOfItems = amountOfItems;
		this.price = price;
		this.shippingDate = shippingDate;
		this.addressDTO = addressDTO;
	}

	public UUID getItemId() {
		return itemId;
	}

	public UUID getOrderId() {
		return orderId;
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

	public AddressDTO getAddressDTO() {
		return addressDTO;
	}
}
