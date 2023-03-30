package com.eurder.mapper;

import com.eurder.domain.item.Item;
import com.eurder.dto.item.ItemDTO;
import com.eurder.dto.item.ItemOverviewDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemMapper {


	public Item mapToDomain(ItemDTO itemDTO) {
		return new Item(itemDTO.getName(), itemDTO.getDescription(), itemDTO.getPrice(), itemDTO.getAmountInStock());
	}

	public ItemDTO mapToDTO(Item item) {
		return new ItemDTO(item.getUuid(), item.getName(), item.getDescription(), item.getPrice(), item.getAmountInStock());
	}

	public List<ItemDTO> mapToDTO(List<Item> itemList) {
		return itemList.stream().map(this::mapToDTO).collect(Collectors.toList());
	}

	public ItemOverviewDTO mapToItemOverviewDTO(String name, List<Item> itemList){
		return new ItemOverviewDTO(name, mapToDTO(itemList));
	}

}
