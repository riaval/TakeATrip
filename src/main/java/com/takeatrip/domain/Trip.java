package com.takeatrip.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "trips")
public class Trip {

	@Id
    private String id;
	@DBRef
	private Transfer transfer;
	private int priceLiving;
	private int priceFood;
	private int days;
	public Trip() {	}
	public Trip(Transfer transfer, int priceLiving, int priceFood, int days){
		this.transfer=transfer;
		this.priceLiving=priceLiving;
		this.priceFood=priceFood;
		this.days=days;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Transfer getTransfer() {
		return transfer;
	}
	public void setTransfer(Transfer transfer) {
		this.transfer = transfer;
	}
	public int getPriceLiving() {
		return priceLiving;
	}
	public void setPriceLiving(int priceLiving) {
		this.priceLiving = priceLiving;
	}
	public int getPriceFood() {
		return priceFood;
	}
	public void setPriceFood(int priceFood) {
		this.priceFood = priceFood;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + days;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + priceFood;
		result = prime * result + priceLiving;
		result = prime * result
				+ ((transfer == null) ? 0 : transfer.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trip other = (Trip) obj;
		if (days != other.days)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (priceFood != other.priceFood)
			return false;
		if (priceLiving != other.priceLiving)
			return false;
		if (transfer == null) {
			if (other.transfer != null)
				return false;
		} else if (!transfer.equals(other.transfer))
			return false;
		return true;
	}
}
