package com.eurder.service;

import com.eurder.domain.item.Item;
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
			Item item = itemRepository.getItemByUuid(itemGroupDTO.getItemId())
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
		List<Order> orders = orderRepository.getOrderList();
		List<ShippingDTO> shippingList = new ArrayList<>();
		for (Order order : orders) {
			shippingList.addAll(createShippingListForOrder(order));
		}
		return shippingList;
	}


	public List<ShippingDTO> createShippingListForOrder(Order order) {
		List<ShippingDTO> tempList = new ArrayList<>();
		for (ItemGroup itemGroup : order.getItemGroupList()) {
			if (itemGroup.getShippingDate().equals(LocalDate.now())) {
				tempList.add(new ShippingDTO(itemGroup.getItemId(), order.getOrderId(),
						itemGroup.getAmountOfItems(), itemGroup.getTotalPrice(), itemGroup.getShippingDate(),
						addressMapper.mapToDTO(order.getCustomer().getAddress())));
			}
		}
		return tempList;
	}
}
