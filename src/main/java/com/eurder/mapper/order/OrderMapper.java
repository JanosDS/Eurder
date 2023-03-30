package com.eurder.mapper.order;

import com.eurder.domain.item.Currency;
import com.eurder.domain.item.Price;
import com.eurder.domain.order.ItemGroup;
import com.eurder.domain.order.Order;
import com.eurder.dto.order.OrderDTO;
import com.eurder.dto.order.OrderReportDTO;
import com.eurder.dto.order.ShippingDTO;
import com.eurder.mapper.user.AddressMapper;
import com.eurder.mapper.user.UserMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

	private final ItemGroupMapper itemGroupMapper;
	private final UserMapper userMapper;

	private final AddressMapper addressMapper;

	public OrderMapper(ItemGroupMapper itemGroupMapper, UserMapper userMapper, AddressMapper addressMapper) {
		this.itemGroupMapper = itemGroupMapper;
		this.userMapper = userMapper;
		this.addressMapper = addressMapper;
	}

	public OrderDTO mapToDTO(Order order) {
		return new OrderDTO(
				userMapper.mapToDTO(order.getCustomer()),
				itemGroupMapper.mapToDTO(order.getItemGroupList()),
				order.getOrderId(),
				order.getTotalPrice()
		);
	}

	public List<OrderDTO> mapToDTO(List<Order> orderList) {
		return orderList.stream()
				.map(this::mapToDTO)
				.collect(Collectors.toList());
	}

	public OrderReportDTO mapToReport(List<OrderDTO> orderDTOList) {
		return new OrderReportDTO(orderDTOList, orderDTOList.stream()
				.map(OrderDTO::getTotalPrice)
				.reduce(new Price(Currency.EURO, 0), Price::addPrice));
	}

	public ShippingDTO mapToShippingDTO(ItemGroup itemGroup, Order order) {
		return new ShippingDTO(itemGroup.getItemId(), order.getOrderId(),
						itemGroup.getAmountOfItems(), itemGroup.getTotalPrice(), itemGroup.getShippingDate(),
						addressMapper.mapToDTO(order.getCustomer().getAddress()));
	}

}
