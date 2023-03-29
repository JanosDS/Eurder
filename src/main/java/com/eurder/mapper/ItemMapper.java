package com.eurder.mapper;

import com.eurder.domain.item.Item;
import com.eurder.dto.item.ItemDTO;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {


	public Item mapToDomain(ItemDTO itemDTO){
		return new Item(itemDTO.getName(), itemDTO.getDescription(), itemDTO.getPrice(), itemDTO.getAmountInStock());
	}

	public ItemDTO mapToDTO(Item item){
		return new ItemDTO(item.getUuid(), item.getName(), item.getDescription(), item.getPrice(), item.getAmountInStock());
	}
}
