package com.eurder.service;

import com.eurder.dto.item.ItemDTO;
import com.eurder.dto.item.ItemOverviewDTO;
import com.eurder.exception.MandatoryFieldException;
import com.eurder.mapper.ItemMapper;
import com.eurder.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

	public List<ItemDTO> getAllItems(){
		return itemMapper.mapToDTO(itemRepository.getAllItems());
	}

	public List<ItemOverviewDTO> getAllItemsOverview(){
		return new ArrayList<ItemOverviewDTO>(){{
			add(getLowStockItemsOverview());
			add(getMediumStockItemsOverview());
			add(getHighStockItemsOverview());
		}};
	}

	public ItemOverviewDTO getLowStockItemsOverview() {
		return new ItemOverviewDTO("Low Stock", itemMapper.mapToDTO(itemRepository.getLowStockItems()));
	}
	public ItemOverviewDTO getMediumStockItemsOverview() {
		return new ItemOverviewDTO("Medium Stock", itemMapper.mapToDTO(itemRepository.getMediumStockItems()));
	}
	public ItemOverviewDTO getHighStockItemsOverview() {
		return new ItemOverviewDTO("High Stock", itemMapper.mapToDTO(itemRepository.getHighStockItems()));
	}
}
