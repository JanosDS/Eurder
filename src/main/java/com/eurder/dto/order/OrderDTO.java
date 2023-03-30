package com.eurder.dto.order;

import com.eurder.domain.item.Price;
import com.eurder.dto.user.UserDTO;

import java.util.List;
import java.util.UUID;

public class OrderDTO {
	private final UserDTO customerDTO;
	private final List<ItemGroupDTO> itemGroupDTOList;
	private final UUID orderId;
	private final Price totalPrice;

	public OrderDTO(UserDTO customerDTO, List<ItemGroupDTO> itemGroupDTOList, UUID orderId, Price totalPrice) {
		this.customerDTO = customerDTO;
		this.itemGroupDTOList = itemGroupDTOList;
		this.orderId = orderId;
		this.totalPrice = totalPrice;
	}

	public UserDTO getCustomerDTO() {
		return customerDTO;
	}

	public List<ItemGroupDTO> getItemGroupDTOList() {
		return itemGroupDTOList;
	}

	public UUID getOrderId() {
		return orderId;
	}
	public Price getTotalPrice() {
		return totalPrice;
	}
}
