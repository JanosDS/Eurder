package com.eurder.domain.item;

import com.eurder.exception.InvalidInputException;

public class Price {

	private final Currency currency;
	private final double amount;

	public Price(Currency currency, double amount) {
		this.currency = currency;
		this.amount = amount;
	}

	public Currency getCurrency() {
		return currency;
	}

	public double getAmount() {
		return amount;
	}

	public Price addPrice(Price price){
		if(this.currency == price.getCurrency()){
			return new Price(currency, this.amount+price.getAmount());
		}
		throw new InvalidInputException("Cannot add prices in 2 different currencies.");
	}
}
