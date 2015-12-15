package com.takeatrip.getinfo;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.takeatrip.domain.City;
import com.takeatrip.domain.TransferType;
import com.takeatrip.service.CityService;
import com.takeatrip.service.TransferService;

import org.springframework.stereotype.Component;

@Component
public class InfoSearcher {
	@Autowired
	CityService cityService;
	@Autowired
	TransferService transferService;

	public InfoSearcher() {
	}

	public void makeCities() throws IOException {
		String urlString = "https://raw.githubusercontent.com/David-Haim/CountriesToCitiesJSON/master/countriesToCities.json";
		URL url = new URL(urlString);
		String str = "";
		int startIndex = 0;
		int endIndex = 0;
		try (InputStream in = url.openStream()) {
			int c;
			int brNum = 0;
			while ((c = in.read()) != -1) {
				if (c != 0) {
					char chr = (char) c;
					str += chr;
					if (chr == '\"' && startIndex == 0) {
						startIndex = str.length() - 1;
					}
					if (chr == '[')
						brNum++;
					if (chr == ']') {
						brNum--;
						if (brNum == 0 && str.charAt(str.length() - 2) == '\"') {
							endIndex = str.length();
							str = str.substring(startIndex, endIndex - 1) + "]";
							startIndex = 0;
							makeCountryCities(str);
							str = "";
						}
					}
				}
			}
		}
	}

	private void makeCountryCities(String str) {
		int startIndex = str.indexOf("\"") + 1;
		int endIndex = str.indexOf("\"", startIndex);
		String country = str.substring(startIndex, endIndex);
		if (!nameIsFine(country) || !country.equals("Ukraine"))
			return;

		if (!country.equals("Jersey")) {
			startIndex = str.indexOf("\"", endIndex + 1) + 1;
			while (startIndex != -1) {
				endIndex = str.indexOf(",", startIndex + 1);
				if (endIndex == -1)
					endIndex = str.indexOf("\"", startIndex);
				else
					endIndex--;
				String city = str.substring(startIndex, endIndex);
				if (nameIsFine(city))
					cityService.add(new City(city, country, "", 1, 0, 0,
							new Date()));
				startIndex = str.indexOf("\"", endIndex + 1);
				if (startIndex != -1)
					startIndex++;
				if (startIndex == str.length() - 1)
					startIndex = -1;
			}
		}
	}

	private Boolean nameIsFine(String name) {
		for (int i = 0; i < name.length(); i++) {
			char chr = name.charAt(i);
			if (chr != ' ' && chr != '\'' && chr != '`' && chr != '-'
					&& !(chr >= 'a' && chr <= 'z')
					&& !(chr >= 'A' && chr <= 'Z'))
				return false;
		}
		return true;
	}

	public void updateFood() throws IOException{
		List<City> list=cityService.getAll();
		updateFood(list);
		Set<String> countries=new HashSet<>();
		for(City c:list)
			countries.add(c.getCountry());
		for(String cnt:countries){
			correctFood0(cnt);
		}
	}
	
	public void updateFood(List<City> cities) throws IOException {
		int N = cities.size();
		for (int i = 0; i < N / 2; i++)
			updateFood(cities.get(2 * i), cities.get(2 * i + 1));
		if (N % 2 == 1)
			updateFood(cities.get(cities.size() - 1));
	}

	public void updateFood(City city1, City city2) throws IOException {
		String urlString = "http://www.numbeo.com/cost-of-living/compare_cities.jsp?country1=";
		urlString += city1.getCountry() + "&city1=" + city1.getName();
		if (!city1.getRegion().equals(""))
			urlString += "%2C+";
		urlString += city1.getRegion() + "&country2=" + city2.getCountry();
		urlString += "&city2=" + city2.getName();
		if (!city2.getRegion().equals(""))
			urlString += "%2C+";
		urlString += city2.getRegion() + "&displayCurrency=USD";
		urlString = urlString.replace(' ', '+');
		URL url = new URL(urlString);
		String str = "";
		try (InputStream in = url.openStream()) {
			int c;
			while ((c = in.read()) != -1)
				str += (char) c;
		}

		if (str.indexOf("No data for one of these cities :(. ") != -1) {
			updateFood(city1);
			updateFood(city2);
		} else if (str
				.indexOf("Not enough data to calculate difference in Restaurant Prices") != -1) {
			updateFood(city1);
			updateFood(city2);
		} else if (str
				.indexOf("Numbeo doesn't have one of those cities in the database") != -1) {
			updateFood(city1);
			updateFood(city2);
		} else {
			int startIndex = str.indexOf("Meal, Inexpensive Restaurant");
			int endIndex = str.indexOf("</tr>", startIndex);
			str = str.substring(startIndex, endIndex);

			startIndex = str.indexOf("class") + 10;
			endIndex = str.indexOf("&", startIndex);

			String str1 = str.substring(startIndex, endIndex);
			city1.setPriceFood((int) Math.round(Double.parseDouble(str1)));
			cityService.add(city1);

			str = str.substring(endIndex);

			startIndex = str.indexOf("class=\"\">") + 10;
			endIndex = str.indexOf("&", startIndex);
			str1 = str.substring(startIndex, endIndex);
			city2.setPriceFood((int) Math.round(Double.parseDouble(str1)));
			cityService.add(city2);
		}
	}

	public void updateFood(City city) throws IOException {
		String urlString = "http://www.numbeo.com/cost-of-living/compare_cities.jsp?country1=";
		urlString += city.getCountry() + "&city1=" + city.getName();
		if (!city.getRegion().equals(""))
			urlString += "%2C+";
		urlString += city.getRegion()
				+ "&country2=Ukraine&city2=Kiev&displayCurrency=USD";
		urlString = urlString.replace(' ', '+');
		URL url = new URL(urlString);
		String str = "";
		try (InputStream in = url.openStream()) {
			int c;
			while ((c = in.read()) != -1)
				str += (char) c;
		}

		if (str.indexOf("No data for one of these cities :(. ") != -1) {
			city.setPriceFood(0);
		} else if (str
				.indexOf("Not enough data to calculate difference in Restaurant Prices") != -1) {
			city.setPriceFood(0);
		} else if (str
				.indexOf("Numbeo doesn't have one of those cities in the database") != -1) {
			city.setPriceFood(0);
		} else {
			int startIndex = str
					.indexOf("<tr><td>Meal, Inexpensive Restaurant</td>");
			int endIndex = str.indexOf("</tr>", startIndex);
			str = str.substring(startIndex, endIndex);

			startIndex = str.indexOf("class") + 10;
			endIndex = str.indexOf("&", startIndex);

			city.setPriceFood((int) Math.round(Double.parseDouble(str
					.substring(startIndex, endIndex))));
			cityService.add(city);
		}
	}
	public void correctFood0(String country){
		List<City> list=cityService.getCountry(country);
		int not0=0;
		double avg=0;
		for(City c:list){
			if(c.getPriceFood()!=0){
				not0++;
				avg+=c.getPriceFood();
			}				
		}
		if(not0!=0){
			int iAvg=(int) Math.round(avg/not0);
			for(City c:list){
				if(c.getPriceFood()==0){
					c.setPriceFood(iAvg);
					cityService.add(c);
				}
			}
		}
	}

	public void updateLive() throws IOException{
		List<City> cities=cityService.getAll();
		Set<String> countries=new HashSet<>();
		for(City c:cities)
			countries.add(c.getCountry());
		
		for(String count:countries){
			List<City> countryCities=new ArrayList<>();
			for(City c:cities){
				if(c.getCountry().equals(count))
					countryCities.add(c);
			}
			
			updateCountryLive(countryCities);
		}
	}
	
	public void updateCountryLive(List<City> cities) throws IOException {
		String urlString = "http://www.numbeo.com/travel-prices/country_result.jsp?country=";
		urlString += cities.get(0).getCountry() + "&displayCurrency=USD";
		urlString=urlString.replace(' ', '+');
		URL url = new URL(urlString);
			String str = "";
		try (InputStream in = url.openStream()) {
			int c;
			while ((c = in.read()) != -1)
				str += (char) c;
		}

		if (str.indexOf("Numbeo doesn't have that country in the database") != -1) {
			for (City c : cities) {
				c.setPriceLive(0);
			}
			cityService.add(cities);
		} else {
			int startIndex = str
					.indexOf("<table class=\"nicerTable hotel_price_country\">");
			int endIndex = str.indexOf("</table>", startIndex);
			str = str.substring(startIndex, endIndex);

			int not0=0;
			double avg=0;
			
			for (City c : cities) {
				String cityStr = c.getName()
						+ (!((c.getRegion().equals(""))) ? ", " : "")
								+ c.getRegion();
				startIndex = str.indexOf(cityStr + "</a></td>");
				if (startIndex != -1) {
					endIndex = str.indexOf("<a href=", startIndex);
					String str1;
					if(endIndex!=-1)
						str1 = str.substring(startIndex, endIndex);
					else str1 = str.substring(startIndex);

					startIndex = str1.indexOf("<td style",
							str1.indexOf("<td style") + 1);
					endIndex = str1.indexOf("</td>", startIndex);
					str1 = str1.substring(startIndex, endIndex);

					startIndex = str1.indexOf(">") + 1;
					endIndex = str1.lastIndexOf('&');
					str1 = str1.substring(startIndex, endIndex);
					int price = (int) Math.round(Double.parseDouble(str1));
					not0++;
					avg+=price;
					c.setPriceLive(price);
				}
				else c.setPriceLive(0);
			}
			if(not0!=0){
				int iAvg=(int)Math.round(avg/not0);
				for(City c:cities){
					if(c.getPriceLive()==0)
						c.setPriceLive(iAvg);
				}
			}
			cityService.add(cities);
		}
	}
	
	public void updateTransport(){
		//setRandomTransport();
	}
	private void setRandomTransport(){
		Random rnd=new Random();
		List<City> cities=cityService.getAll();
		for(City c1:cities){
			for(City c2:cities){
				if(c1.getId().compareTo(c2.getId())==1){
					transferService.add(c1.getId(),c2.getId(),rnd.nextInt(2)+3,0,TransferType.TRAIN);
					transferService.add(c1.getId(),c2.getId(),rnd.nextInt(2)+5,0,TransferType.BUS);
					transferService.add(c1.getId(),c2.getId(),rnd.nextInt(6)+15,0,TransferType.PLANE);
				}
			}
		}
	}
}
