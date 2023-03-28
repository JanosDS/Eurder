package com.eurder.dto.order;

import java.util.List;
import java.util.UUID;

public class CreateOrderDTO {

	private List<CreateItemGroupDTO> itemGroupDTOList;
	private UUID customerId;

	public CreateOrderDTO(List<CreateItemGroupDTO> itemGroupDTOList, UUID customerId) {
		this.itemGroupDTOList = itemGroupDTOList;
		this.customerId = customerId;
	}

	public List<CreateItemGroupDTO> getItemGroupDTOList() {
		return itemGroupDTOList;
	}

	public UUID getCustomerId() {
		return customerId;
	}
}
