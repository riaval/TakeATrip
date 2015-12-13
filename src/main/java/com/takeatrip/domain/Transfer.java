package com.takeatrip.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "transfers")
public class Transfer {
	@Id
    private String id;
	@DBRef
	private City cityA;
	@DBRef
	private City cityB;
	private int price;
	private int duration;
	private TransferType type;
	private Date date;
	public Transfer() {	}
	public Transfer(City start, City finish, int price, int duration, TransferType type, Date date){
		this.cityA=(start.getName().compareTo(finish.getName())!=1)?start:finish;
		this.cityB=(start.getName().compareTo(finish.getName())!=-1)?finish:start;
		this.price=price;
		this.duration=duration;
		this.type=type;
		this.date=date;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public City getCityA() {
		return cityA;
	}
	public void setCityA(City cityA) {
		if(cityA.getName().compareTo(cityB.getName())!=1)
			this.cityA=cityA;
		else{
			this.cityA=cityB;
			cityB=cityA;
		}
	}
	public City getCityB() {
		return cityB;
	}
	public void setCityB(City cityB) {
		if(cityB.getName().compareTo(cityA.getName())!=-1)
			this.cityB=cityB;
		else{
			this.cityB=cityA;
			cityA=cityB;
		}
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cityA == null) ? 0 : cityA.hashCode());
		result = prime * result + ((cityB == null) ? 0 : cityB.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
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
		if (cityA == null) {
			if (other.cityA != null)
				return false;
		} else if (!cityA.equals(other.cityA))
			return false;
		if (cityB == null) {
			if (other.cityB != null)
				return false;
		} else if (!cityB.equals(other.cityB))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
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
