package dto;

public class CityReport {
	private String name;
	private int days;
	private int total;
	private int priceLivingDay;
	private int priceFoodDay;
	private int priceLiving;
	private int priceFood;
	public CityReport() {	}
	public CityReport(String name, int days, int priceLivingDay, int priceFoodDay){
		this.name=name;
		this.days=days;
		this.priceLivingDay=priceLivingDay;
		this.priceFoodDay=priceFoodDay;
		priceLiving=priceLivingDay*days;
		priceFood=priceFoodDay*days;
		total=priceLiving+priceFood;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getPriceLivingDay() {
		return priceLivingDay;
	}
	public void setPriceLivingDay(int priceLivingDay) {
		this.priceLivingDay = priceLivingDay;
	}
	public int getPriceFoodDay() {
		return priceFoodDay;
	}
	public void setPriceFoodDay(int priceFoodDay) {
		this.priceFoodDay = priceFoodDay;
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
}
