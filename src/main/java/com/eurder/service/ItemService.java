package com.eurder.service;

import com.eurder.dto.ItemDTO;
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
		return null;
	}
}
