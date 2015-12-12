package com.takeatrip.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "transfers")
public class Transfer {
	@Id
    private String id;
	@DBRef
	private City cityStart;
	@DBRef
	private City cityFinish;
	private int price;
	private int duration;
	private TransferType type;
	public Transfer() {	}
	public Transfer(City start, City finish, int price, int duration, TransferType type){
		cityStart=start;
		cityFinish=finish;
		this.price=price;
		this.duration=duration;
		this.type=type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public City getCityStart() {
		return cityStart;
	}
	public void setCityStart(City cityStart) {
		this.cityStart = cityStart;
	}
	public City getCityFinish() {
		return cityFinish;
	}
	public void setCityFinish(City cityFinish) {
		this.cityFinish = cityFinish;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public TransferType getType() {
		return type;
	}
	public void setType(TransferType type) {
		this.type = type;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cityFinish == null) ? 0 : cityFinish.hashCode());
		result = prime * result
				+ ((cityStart == null) ? 0 : cityStart.hashCode());
		result = prime * result + duration;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + price;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Transfer other = (Transfer) obj;
		if (cityFinish == null) {
			if (other.cityFinish != null)
				return false;
		} else if (!cityFinish.equals(other.cityFinish))
			return false;
		if (cityStart == null) {
			if (other.cityStart != null)
				return false;
		} else if (!cityStart.equals(other.cityStart))
			return false;
		if (duration != other.duration)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (price != other.price)
			return false;
		if (type != other.type)
			return false;
		return true;
	}
}
