package com.eurder.dto.order;

import com.eurder.domain.item.Price;
import com.eurder.dto.user.UserDTO;

import java.util.List;
import java.util.UUID;

public class OrderDTO {
	private UserDTO customerDTO;
	private List<ItemGroupDTO> itemGroupDTOList;
	private UUID orderId;
	private Price totalPrice;

	public OrderDTO(UserDTO customerDTO, List<ItemGroupDTO> itemGroupDTOList) {
		this.customerDTO = customerDTO;
		this.itemGroupDTOList = itemGroupDTOList;
	}

	public OrderDTO(UserDTO customerDTO, List<ItemGroupDTO> itemGroupDTOList, UUID orderId) {
		this.customerDTO = customerDTO;
		this.itemGroupDTOList = itemGroupDTOList;
		this.orderId = orderId;
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

	public Price getTotalPrice(){
		return totalPrice;
	}
}
