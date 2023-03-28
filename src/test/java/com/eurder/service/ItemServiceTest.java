package com.eurder.service;

import com.eurder.domain.item.Currency;
import com.eurder.domain.item.Price;
import com.eurder.dto.item.CreateItemDTO;
import com.eurder.dto.user.AddressDTO;
import com.eurder.exception.MandatoryFieldException;
import com.eurder.mapper.ItemMapper;
import com.eurder.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemServiceTest {

	private ItemService itemService;
	private final CreateItemDTO validItemDTO = new CreateItemDTO("ItemName", "Short description of the item", new Price(Currency.EURO, 15), 5);

	@BeforeEach
	void setup(){
		this.itemService = new ItemService(new ItemRepository(), new ItemMapper());
	}

	@Nested
	@DisplayName("Validate mandatory item field tests")
	class validateItem {

		@Test
		@DisplayName("Validate valid item")
		void validateItem() {
			itemService.addItem(validItemDTO);
		}

		@Test
		@DisplayName("Validate name missing")
		void validateAddress_nameMissing_throwException(){
			CreateItemDTO createItemDTO = new CreateItemDTO(null, "Short description of the item", new Price(Currency.EURO, 15), 5);
			Exception exception = assertThrows(MandatoryFieldException.class, () -> {
				itemService.validateMandatoryItemFields(createItemDTO);
			});
		}
		@Test
		@DisplayName("Validate description missing")
		void validateAddress_descriptionMissing_throwException(){
			CreateItemDTO createItemDTO = new CreateItemDTO("ItemName", null, new Price(Currency.EURO, 15), 5);
			Exception exception = assertThrows(MandatoryFieldException.class, () -> {
				itemService.validateMandatoryItemFields(createItemDTO);
			});
		}
		@Test
		@DisplayName("Validate price missing")
		void validateAddress_priceMissing_throwException(){
			CreateItemDTO createItemDTO = new CreateItemDTO("ItemName", "Short description of the item", null, 5);
			Exception exception = assertThrows(MandatoryFieldException.class, () -> {
				itemService.validateMandatoryItemFields(createItemDTO);
			});
		}



	}
}