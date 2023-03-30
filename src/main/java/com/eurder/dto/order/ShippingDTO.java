package com.eurder.dto.order;

import com.eurder.domain.item.Price;
import com.eurder.dto.user.AddressDTO;

import java.time.LocalDate;
import java.util.UUID;

public class ShippingDTO {

	private final UUID itemId;
	private final UUID orderId;
	private final int amountOfItems;
	private final Price price;
	private final LocalDate shippingDate;
	private final AddressDTO addressDTO;

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
