package com.example.binance.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "trades")
public class Trade {
	@Id
	private String id;
	@JsonProperty("s")
	private String symbol;
	@JsonProperty("p")
	private String price;
	@JsonProperty("q")
	private String quantity;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Trade [id=" + id + ", symbol=" + symbol + ", price=" + price + ", quantity=" + quantity + "]";
	}

}
