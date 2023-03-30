package com.eurder.dto.item;

import java.util.List;

public class ItemOverviewDTO {

	private final String overviewName;
	private final List<ItemDTO> itemList;

	public ItemOverviewDTO(String overviewName, List<ItemDTO> itemList) {
		this.overviewName = overviewName;
		this.itemList = itemList;
	}

	public String getOverviewName() {
		return overviewName;
	}

	public List<ItemDTO> getItemList() {
		return itemList;
	}
}
