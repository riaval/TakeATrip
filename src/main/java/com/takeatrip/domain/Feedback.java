package com.takeatrip.domain;

import java.sql.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import dto.*;

@Document(collection = "feedbacks")
public class Feedback {
	@Id
    private String id;
	private List<Trip> trips;
	private Date date;
	@DBRef
	private User user;
	public Feedback() {	}
	public Feedback(List<Trip> trips, Date date, User user){
		this.trips=trips;
		this.date=date;
		this.user=user;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<Trip> getTrips() {
		return trips;
	}
	public void setTrips(List<Trip> trips) {
		this.trips = trips;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((trips == null) ? 0 : trips.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Feedback other = (Feedback) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (trips == null) {
			if (other.trips != null)
				return false;
		} else if (!trips.equals(other.trips))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
	public FeedbackReport calculate() {
		int days=0;
		int money=0;
		FeedbackReport res=new FeedbackReport();
		
		for(Trip t:trips){
			days+=t.getDays();
			days+=t.getTransfer().getDuration();
			money+=(t.getPriceFood()+t.getPriceLiving())*t.getDays();
			money+=t.getTransfer().getPrice();
			String transName="Transfer: "+t.getTransfer().getType()+" from "
					+t.getTransfer().getCityStart().getName()+" to "
					+t.getTransfer().getCityFinish().getName();
			TransferReport tRep=new TransferReport(transName,t.getTransfer().getDuration(),
					t.getTransfer().getPrice());
			CityReport cRep=new CityReport(t.getTransfer().getCityFinish().getName(),
					t.getDays(), t.getPriceFood(), t.getPriceLiving());
			res.addTrasfer(tRep);
			res.addCity(cRep);
		}
		res.setDaysTotal(days);
		res.setPriceTotal(money);
		return res;
	}
}
