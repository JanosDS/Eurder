package com.eurder.mapper.order;

import com.eurder.domain.order.ItemGroup;
import com.eurder.dto.order.ItemGroupDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemGroupMapper {

	public ItemGroup mapToDomain(ItemGroupDTO itemGroupDTO){
		return new ItemGroup(itemGroupDTO.getItemId(), itemGroupDTO.getAmountOfItems(), itemGroupDTO.getPrice());
	}

	public List<ItemGroup> mapToDomain(List<ItemGroupDTO> itemGroupDTOList){
		return itemGroupDTOList.stream()
				.map(this::mapToDomain)
				.collect(Collectors.toList());
	}

	public ItemGroupDTO mapToDTO(ItemGroup itemGroup){
		return new ItemGroupDTO(itemGroup.getItemId(), itemGroup.getAmountOfItems(), itemGroup.getPricePerItem(), itemGroup.getShippingDate());
	}

	public List<ItemGroupDTO> mapToDTO(List<ItemGroup> itemGroupList){
		return itemGroupList.stream()
				.map(this::mapToDTO)
				.collect(Collectors.toList());
	}
}
