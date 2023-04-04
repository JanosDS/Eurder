package com.eurder.repository;

import com.eurder.domain.item.Currency;
import com.eurder.domain.item.Item;
import com.eurder.domain.item.Price;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;

import java.util.UUID;


@DataJdbcTest
@ContextConfiguration(classes = {ItemRepository.class})
@EnableAutoConfiguration
class ItemRepositoryTest {

	@Autowired
	private ItemRepository itemRepository;

	@Test
	@Sql("createTestItems.sql")
	void getAllItems() {
		Assertions
				.assertThat(this.itemRepository.getAllItems())
				.hasSize(3);
	}

	@Test
	void test_adding_newItem() {
		Item item = new Item(UUID.randomUUID(), "Test", "test", new Price(Currency.EURO, 20), 5);
		this.itemRepository.addItem(item);
		Assertions
				.assertThat(this.itemRepository.getAllItems())
				.hasSize(1);
	}

	@Test
	@Sql("createTestItems.sql")
	void test_update_item() {
		Item item = this.itemRepository.getAllItems().get(0);
		item.setName("NewName");
		this.itemRepository.updateItem(item);
		Item itemResult = this.itemRepository.getItemByUuid(item.getUuid()).orElse(null);
		Assertions.assertThat(itemResult).isEqualTo(item);
	}

	@Test
	@Sql("createTestItems.sql")
	void test_getLowStock_item() {
		Assertions
				.assertThat(this.itemRepository.getLowStockItems())
				.hasSize(1);
	}

	@Test
	@Sql("createTestItems.sql")
	void test_getMediumStock_item() {
		Assertions
				.assertThat(this.itemRepository.getMediumStockItems())
				.hasSize(1);
	}
	@Test
	@Sql("createTestItems.sql")
	void test_getHighStock_item() {
		Assertions
				.assertThat(this.itemRepository.getHighStockItems())
				.hasSize(1);
	}
}