package dto;

import java.util.ArrayList;

import com.takeatrip.domain.TransferType;

public class CityReport {
	private ArrayList<TransferType> type;
	private ArrayList<Integer> price;
	private int priceLive;
	private int priceFood;
	public CityReport() {	}
	public CityReport(ArrayList<TransferType> type,ArrayList<Integer> price,int priceLive,int priceFood){
		this.type=type;
		this.price=price;
		this.priceLive=priceLive;
		this.priceFood=priceFood;
	}
	public ArrayList<TransferType> getType() {
		return type;
	}
	public void setType(ArrayList<TransferType> type) {
		this.type = type;
	}
	public ArrayList<Integer> getPrice() {
		return price;
	}
	public void setPrice(ArrayList<Integer> price) {
		this.price = price;
	}
	public int getPriceLive() {
		return priceLive;
	}
	public void setPriceLive(int priceLive) {
		this.priceLive = priceLive;
	}
	public int getPriceFood() {
		return priceFood;
	}
	public void setPriceFood(int priceFood) {
		this.priceFood = priceFood;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + priceFood;
		result = prime * result + priceLive;
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
		CityReport other = (CityReport) obj;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (priceFood != other.priceFood)
			return false;
		if (priceLive != other.priceLive)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
}
