package com.eurder.mapper.order;

import com.eurder.domain.order.Order;
import com.eurder.dto.order.OrderDTO;
import com.eurder.mapper.user.UserMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

	private final ItemGroupMapper itemGroupMapper;
	private final UserMapper userMapper;

	public OrderMapper(ItemGroupMapper itemGroupMapper, UserMapper userMapper) {
		this.itemGroupMapper = itemGroupMapper;
		this.userMapper = userMapper;
	}

	public OrderDTO mapToDTO(Order order) {
		return new OrderDTO(
				userMapper.mapToDTO(order.getCustomer()),
				itemGroupMapper.mapToDTO(order.getItemGroupList()),
				order.getOrderId()
		);
	}
}
