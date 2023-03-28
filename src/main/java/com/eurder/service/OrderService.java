package com.eurder.service;

import com.eurder.domain.item.Item;
import com.eurder.domain.order.Order;
import com.eurder.domain.user.User;
import com.eurder.dto.order.*;
import com.eurder.dto.user.UserDTO;
import com.eurder.exception.InvalidInputException;
import com.eurder.mapper.order.ItemGroupMapper;
import com.eurder.mapper.order.OrderMapper;
import com.eurder.mapper.user.UserMapper;
import com.eurder.repository.ItemRepository;
import com.eurder.repository.OrderRepository;
import com.eurder.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

	private final OrderRepository orderRepository;
	private final OrderMapper orderMapper;
	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final ItemRepository itemRepository;
	private final ItemGroupMapper itemGroupMapper;

	public OrderService(OrderRepository orderRepository, OrderMapper orderMapper, UserRepository userRepository, UserMapper userMapper, ItemRepository itemRepository, ItemGroupMapper itemGroupMapper) {
		this.userMapper = userMapper;
		this.userRepository = userRepository;
		this.orderRepository = orderRepository;
		this.orderMapper = orderMapper;
		this.itemRepository = itemRepository;
		this.itemGroupMapper = itemGroupMapper;
	}

	public OrderDTO createOrder(CreateOrderDTO createOrderDTO) {
		Order order = new Order(
				itemGroupMapper.mapToDomain(validateItemGroupList(createOrderDTO.getItemGroupDTOList())),
				validateCustomer(createOrderDTO.getCustomerId())
		);
		return orderMapper.mapToDTO(orderRepository.addOrder(order));
	}

	public User validateCustomer(UUID userId){
		return userRepository.getUserByUuid(userId)
				.orElseThrow(()-> new InvalidInputException("There is no user with ID: " + userId + ", could not create the order."));
	}

	public List<ItemGroupDTO> validateItemGroupList(List<CreateItemGroupDTO> createItemGroupDTOList){
		List<ItemGroupDTO> itemGroupDTOList = new ArrayList<>();
		for(CreateItemGroupDTO createItemGroupDTO : createItemGroupDTOList){
			Item item = itemRepository.getItemByUuid(createItemGroupDTO.getItemId())
					.orElseThrow(() -> new InvalidInputException("There is no item with ID: " + createItemGroupDTO.getItemId() + ", could not place order."));
			itemGroupDTOList.add(new ItemGroupDTO(item.getUuid(), createItemGroupDTO.getAmountOfItems(), item.getPrice()));
		}
		return itemGroupDTOList;
	}
}
