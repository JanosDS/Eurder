package com.eurder.repository;

import com.eurder.domain.item.Item;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ItemRepository {

	private List<Item> itemList;

	public ItemRepository() {
		this.itemList = new ArrayList<>();
	}

	public List<Item> getAllItems(){
		return itemList;
	}

	public Item addItem(Item item){
		itemList.add(item);
		return item;
	}

	public Optional<Item> getItemByUuid(UUID uuid){
		return itemList.stream()
				.filter(item -> item.getUuid().equals(uuid))
				.findFirst();
	}
}
