package com.eurder.dto.order;

import com.eurder.domain.item.Price;

import java.util.List;

public class OrderReportDTO {

	private final Price totalPriceAllReports;
	private final List<OrderDTO> orderDTOList;

	public OrderReportDTO(List<OrderDTO> orderDTOList, Price totalPrice) {
		this.orderDTOList = orderDTOList;
		this.totalPriceAllReports = totalPrice;
	}

	public Price getTotalPriceAllReports() {
		return totalPriceAllReports;
	}

	public List<OrderDTO> getOrderDTOList() {
		return orderDTOList;
	}
}
