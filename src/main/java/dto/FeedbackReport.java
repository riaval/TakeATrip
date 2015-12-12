package dto;

import java.util.ArrayList;

public class FeedbackReport {
	private ArrayList<TransferReport> transfer;
	private ArrayList<CityReport> cities;
	private int daysTotal;
	private int priceTotal;
	public FeedbackReport() {
		transfer=new ArrayList<>();
		cities=new ArrayList<>();
	}
	public FeedbackReport(ArrayList<TransferReport> transfer, ArrayList<CityReport> cities,int daysTotal,int priceTotal){
		this.transfer=transfer;
		this.cities=cities;
		this.daysTotal=daysTotal;
		this.priceTotal=priceTotal;
	}
	public ArrayList<TransferReport> getTransport() {
		return transfer;
	}
	public void setTrans(ArrayList<TransferReport> transfer) {
		this.transfer = transfer;
	}
	public ArrayList<CityReport> getCities() {
		return cities;
	}
	public void setCities(ArrayList<CityReport> cities) {
		this.cities = cities;
	}
	public int getDaysTotal() {
		return daysTotal;
	}
	public void setDaysTotal(int daysTotal) {
		this.daysTotal = daysTotal;
	}
	public int getPriceTotal() {
		return priceTotal;
	}
	public void setPriceTotal(int priceTotal) {
		this.priceTotal = priceTotal;
	}
	public void addTrasfer(TransferReport transferRep){
		transfer.add(transferRep);
	}
	public void addCity(CityReport cityRep){
		cities.add(cityRep);
	}
}
