package com.eurder.repository;

import com.eurder.domain.item.Item;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ItemRepository {

	private final int LOW_STOCK_AMOUNT = 5;
	private final int MEDIUM_STOCK_AMOUNT = 10;

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

	public Item updateItem(Item item){
		itemList.set(itemList.indexOf(item), item);
		return item;
	}

	public List<Item> getLowStockItems(){
		return itemList.stream()
				.filter(item -> item.getAmountInStock() < LOW_STOCK_AMOUNT)
				.sorted(Comparator.comparing(Item::getAmountInStock))
				.collect(Collectors.toList());
	}

	public List<Item> getMediumStockItems(){
		return itemList.stream()
				.filter(item -> item.getAmountInStock() > LOW_STOCK_AMOUNT && item.getAmountInStock() < MEDIUM_STOCK_AMOUNT)
				.sorted(Comparator.comparing(Item::getAmountInStock))
				.collect(Collectors.toList());
	}

	public List<Item> getHighStockItems(){
		return itemList.stream()
				.filter(item -> item.getAmountInStock() > MEDIUM_STOCK_AMOUNT)
				.sorted(Comparator.comparing(Item::getAmountInStock))
				.collect(Collectors.toList());
	}

}
