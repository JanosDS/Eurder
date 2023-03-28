package com.eurder.domain.item;

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
}
