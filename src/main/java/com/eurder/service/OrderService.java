package com.eurder.service;

import com.eurder.domain.order.ItemGroup;
import com.eurder.domain.order.Order;
import com.eurder.domain.user.User;
import com.eurder.dto.order.ItemGroupDTO;
import com.eurder.dto.order.OrderDTO;
import com.eurder.dto.order.OrderReportDTO;
import com.eurder.dto.order.ShippingDTO;
import com.eurder.exception.InvalidInputException;
import com.eurder.mapper.order.ItemGroupMapper;
import com.eurder.mapper.order.OrderMapper;
import com.eurder.mapper.user.AddressMapper;
import com.eurder.repository.ItemRepository;
import com.eurder.repository.OrderRepository;
import com.eurder.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {
	private final OrderRepository orderRepository;
	private final OrderMapper orderMapper;
	private final UserRepository userRepository;
	private final AddressMapper addressMapper;
	private final ItemRepository itemRepository;
	private final ItemGroupMapper itemGroupMapper;

	public OrderService(OrderRepository orderRepository, OrderMapper orderMapper, UserRepository userRepository, AddressMapper addressMapper, ItemRepository itemRepository, ItemGroupMapper itemGroupMapper) {
		this.addressMapper = addressMapper;
		this.userRepository = userRepository;
		this.orderRepository = orderRepository;
		this.orderMapper = orderMapper;
		this.itemRepository = itemRepository;
		this.itemGroupMapper = itemGroupMapper;
	}

	public OrderDTO createOrder(OrderDTO orderDTO) {
		Order order = new Order(
				itemGroupMapper.mapToDomain(validateItemGroupList(orderDTO.getItemGroupDTOList())),
				validateCustomer(orderDTO.getCustomerDTO().getUuid())
		);
		return orderMapper.mapToDTO(orderRepository.addOrder(order));
	}

	public User validateCustomer(UUID userId) {
		return userRepository.getUserByUuid(userId)
				.orElseThrow(() -> new InvalidInputException("There is no user with ID: " + userId + ", could not create the order."));
	}

	public List<ItemGroupDTO> validateItemGroupList(List<ItemGroupDTO> itemGroupDTOList) {
		for (ItemGroupDTO itemGroupDTO : itemGroupDTOList) {
			itemRepository.getItemByUuid(itemGroupDTO.getItemId())
					.orElseThrow(() -> new InvalidInputException("There is no item with ID: " + itemGroupDTO.getItemId() + ", could not place order."));
		}
		return itemGroupDTOList;
	}

	public OrderReportDTO getAllOrdersForUserId(UUID uuid) {
		return orderMapper.mapToReport(orderMapper.mapToDTO(orderRepository.getAllOrdersForUserId(uuid)));
	}

	public OrderDTO reorder(UUID orderId) {
		return createOrder(orderMapper.mapToDTO(orderRepository.getOrderForId(orderId)
				.orElseThrow(() -> new InvalidInputException("There is no order found for the provided ID, cannot reorder."))));
	}

	public List<ShippingDTO> getShipmentsForToday() {
		return orderRepository.getOrderList()
				.stream()
				.flatMap(order -> order.getItemGroupList()
						.stream()
						.filter(itemGroup -> itemGroup.getShippingDate().isEqual(LocalDate.now()))
						.map(itemGroup -> orderMapper.mapToShippingDTO(itemGroup, order)))
				.collect(Collectors.toList());
	}



}
