package com.eurder.repository;

import com.eurder.domain.item.Currency;
import com.eurder.domain.item.Item;
import com.eurder.domain.item.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ItemRepository {
	private final JdbcTemplate template;
	private final int LOW_STOCK_AMOUNT = 5;
	private final int MEDIUM_STOCK_AMOUNT = 10;
	private List<Item> itemList;

	@Autowired
	public ItemRepository(JdbcTemplate template) {
		this.template = template;
		this.itemList = new ArrayList<>();
	}

	public List<Item> getAllItems() {
		return template.query("SELECT * FROM items",
				(resultSet, rowNumber) -> new Item(
						resultSet.getObject("item_id", UUID.class),
						resultSet.getString("item_name"),
						resultSet.getString("item_description"),
						new Price(Currency.EURO,
								resultSet.getDouble("item_price_amount")),
						resultSet.getInt("item_amount_in_stock")
				));
	}

	public Item addItem(Item item) {
		template.update("INSERT INTO items(item_id, item_name, item_description, item_amount_in_stock, item_price_amount, item_price_currency)" +
						" VALUES(?, ?,?,?,?,?)",
				item.getUuid(),
				item.getName(),
				item.getDescription(),
				item.getAmountInStock(),
				item.getPrice().getAmount(),
				item.getPrice().getCurrency().toString()
		);
		return item;
	}

	public Optional<Item> getItemByUuid(UUID uuid) {
		return template.query("SELECT * FROM items WHERE item_id = ?",
				(resultSet, rowNumber) -> new Item(
						resultSet.getObject("item_id", UUID.class),
						resultSet.getString("item_name"),
						resultSet.getString("item_description"),
						new Price(Currency.EURO,
								resultSet.getDouble("item_price_amount")),
						resultSet.getInt("item_amount_in_stock")
				),
				uuid).stream().findFirst();
	}

	public Item updateItem(Item item) {
		template.update("UPDATE items SET item_id = ?, item_name =?, item_description =?, item_amount_in_stock =?, item_price_amount =?, item_price_currency =?" +
						" WHERE item_id = ?",
				item.getUuid(),
				item.getName(),
				item.getDescription(),
				item.getAmountInStock(),
				item.getPrice().getAmount(),
				item.getPrice().getCurrency().toString(),
				item.getUuid()
		);
		return item;
	}

	public List<Item> getLowStockItems() {

		return template.query("SELECT * FROM items WHERE item_amount_in_stock < ?",
				(resultSet, rowNumber) -> new Item(
						resultSet.getObject("item_id", UUID.class),
						resultSet.getString("item_name"),
						resultSet.getString("item_description"),
						new Price(Currency.EURO,
								resultSet.getDouble("item_price_amount")),
						resultSet.getInt("item_amount_in_stock")
				),
				LOW_STOCK_AMOUNT
				);
	}

	public List<Item> getMediumStockItems() {

		return template.query("SELECT * FROM items WHERE item_amount_in_stock > ? AND item_amount_in_stock < ?",
				(resultSet, rowNumber) -> new Item(
						resultSet.getObject("item_id", UUID.class),
						resultSet.getString("item_name"),
						resultSet.getString("item_description"),
						new Price(Currency.EURO,
								resultSet.getDouble("item_price_amount")),
						resultSet.getInt("item_amount_in_stock")
				),
				LOW_STOCK_AMOUNT,
				MEDIUM_STOCK_AMOUNT
		);
	}

	public List<Item> getHighStockItems() {
		return template.query("SELECT * FROM items WHERE item_amount_in_stock > ?",
				(resultSet, rowNumber) -> new Item(
						resultSet.getObject("item_id", UUID.class),
						resultSet.getString("item_name"),
						resultSet.getString("item_description"),
						new Price(Currency.EURO,
								resultSet.getDouble("item_price_amount")),
						resultSet.getInt("item_amount_in_stock")
				),
				MEDIUM_STOCK_AMOUNT
		);
	}

}
