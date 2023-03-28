package com.eurder.mapper;

import com.eurder.domain.item.Item;
import com.eurder.dto.item.CreateItemDTO;
import com.eurder.dto.item.ItemDTO;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {


	public Item mapCreateItemDTOToDomain(CreateItemDTO createItemDTO){
		return new Item(createItemDTO.getName(), createItemDTO.getDescription(), createItemDTO.getPrice(), createItemDTO.getAmountInStock());
	}

	public ItemDTO mapToDTO(Item item){
		return new ItemDTO(item.getUuid(), item.getName(), item.getDescription(), item.getPrice(), item.getAmountInStock());
	}
}
