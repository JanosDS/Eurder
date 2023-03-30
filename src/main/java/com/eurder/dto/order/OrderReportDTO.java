package com.eurder.dto.order;

import com.eurder.domain.item.Currency;
import com.eurder.domain.item.Price;

import java.util.List;

public class OrderReportDTO {

	private Price totalPriceAllReports;
	private List<OrderDTO> orderDTOList;

	public OrderReportDTO(List<OrderDTO> orderDTOList) {
		this.orderDTOList = orderDTOList;
		calculateTotalPrice();
	}

	private void calculateTotalPrice() {
		this.totalPriceAllReports = new Price(Currency.EURO, orderDTOList.stream()
				.map(OrderDTO::getTotalPrice)
				.mapToDouble(Price::getAmount)
				.sum());
	}

}
