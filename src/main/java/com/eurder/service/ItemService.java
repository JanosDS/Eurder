package com.eurder.service;

import com.eurder.dto.item.CreateItemDTO;
import com.eurder.dto.item.ItemDTO;
import com.eurder.exception.MandatoryFieldException;
import com.eurder.mapper.ItemMapper;
import com.eurder.repository.ItemRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

	private final ItemRepository itemRepository;
	private final ItemMapper itemMapper;

	public ItemService(ItemRepository itemRepository, ItemMapper itemMapper) {
		this.itemRepository = itemRepository;
		this.itemMapper = itemMapper;
	}

	public ItemDTO addItem(CreateItemDTO createItemDTO){
		//TODO vallidation

		return itemMapper.mapToDTO(itemRepository.addItem(itemMapper.mapCreateItemDTOToDomain(createItemDTO)));
	}

	public void validateMandatoryItemFields(CreateItemDTO createItemDTO){
		if(createItemDTO.getName() == null){
			throw new MandatoryFieldException("The name field cannot be empty");
		}
		if(createItemDTO.getDescription() == null){
			throw new MandatoryFieldException("The description field cannot be empty");
		}
		if(createItemDTO.getPrice() == null){
			throw new MandatoryFieldException("The price field cannot be empty");
		}
	}
}
