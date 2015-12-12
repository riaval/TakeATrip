package dto;

public class TransferReport {
	private String name;
	private int duration;
	private int price;
	public TransferReport() {	}
	public TransferReport(String name, int duration, int price){
		this.name=name;
		this.duration=duration;
		this.price=price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
}
