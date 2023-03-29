package com.eurder.service;

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

	public ItemDTO addItem(ItemDTO itemDTO){
		validateMandatoryItemFields(itemDTO);
		return itemMapper.mapToDTO(itemRepository.addItem(itemMapper.mapToDomain(itemDTO)));
	}

	public ItemDTO updateItem(ItemDTO itemDTO){
		validateMandatoryItemFields(itemDTO);
		return itemMapper.mapToDTO(itemRepository.updateItem(itemMapper.mapToDomain(itemDTO)));
	}

	public void validateMandatoryItemFields(ItemDTO itemDTO){
		if(itemDTO.getName() == null){
			throw new MandatoryFieldException("The name field cannot be empty");
		}
		if(itemDTO.getDescription() == null){
			throw new MandatoryFieldException("The description field cannot be empty");
		}
		if(itemDTO.getPrice() == null){
			throw new MandatoryFieldException("The price field cannot be empty");
		}
	}
}
