package com.takeatrip.getinfo;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.takeatrip.domain.City;
import com.takeatrip.service.CityService;

public class InfoSearcher {
	@Autowired
	CityService cityService;

	public InfoSearcher() {
	}

	public void makeCities() throws IOException {
		String urlString="https://raw.githubusercontent.com/David-Haim/CountriesToCitiesJSON/master/countriesToCities.json";
		URL url = new URL(urlString);
		String str = "";
		int startIndex = 0;
		int endIndex = 0;
		try (InputStream in = url.openStream()) {
			int c;
			while ((c = in.read()) != -1){
				if(c!=0){
					char chr=(char)c;
					str += chr;
					if(chr=='\"' && startIndex==0){
						startIndex=str.length()-1;
					}
					if(chr==']'){
						endIndex=str.length();
						str=str.substring(startIndex,endIndex);
						startIndex=0;
						makeCountryCities(str);
						str="";
					}
			}}
		}
	}

	private void makeCountryCities(String str){
		int startIndex=str.indexOf("\"")+1;
		int endIndex=str.indexOf("\"",startIndex);
		String country=str.substring(startIndex, endIndex);
		if(!nameIsFine(country) || !country.equals("Monaco"))
			return;
		
		startIndex=str.indexOf("\"",endIndex+1)+1;
		while(startIndex!=-1){
			endIndex=str.indexOf("\"",startIndex);
			String city=str.substring(startIndex, endIndex);
			if(nameIsFine(city))
				cityService.add(new City(city,country,"",1,0,0,Date.valueOf("2015-12-12")));
			startIndex=str.indexOf("\"",endIndex+1)+1;
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

	public void updateFood(List<City> cities) throws IOException {
		int N = cities.size();
		for (int i = 0; i < N / 2; i++)
			updateFood(cities.get(2 * i), cities.get(2 * i + 1));
		if (N % 2 == 1)
			updateFood(cities.get(cities.size() - 1));
	}

	public void updateFood(City city1, City city2) throws IOException {
		String urlString = "http://www.numbeo.com/cost-of-living/compare_cities.jsp?country1=";
		urlString += city1.getCountry() + "&city1=" + city1.getName()
				+ "&country2=" + city2.getCountry();
		urlString += "&city2=" + city2.getName() + "&displayCurrency=USD";
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
		} else {
			int startIndex = str
					.indexOf("<tr><td>Meal, Inexpensive Restaurant</td>");
			int endIndex = str.indexOf("</tr>", startIndex);
			str = str.substring(startIndex, endIndex);

			startIndex = str.indexOf("class") + 10;
			endIndex = str.indexOf("&", startIndex);

			city1.setPriceFood((int) Math.round(Double.parseDouble(str
					.substring(startIndex, endIndex))));

			str = str.substring(endIndex);

			startIndex = str.indexOf("class=\"\">") + 10;
			endIndex = str.indexOf("&", startIndex);
			city2.setPriceFood((int) Math.round(Double.parseDouble(str
					.substring(startIndex, endIndex))));
		}
	}

	public void updateFood(City city) throws IOException {
		String urlString = "http://www.numbeo.com/cost-of-living/compare_cities.jsp?country1=";
		urlString += city.getCountry() + "&city1=" + city.getName()
				+ "&country2=Ukraine&city2=Kiev&displayCurrency=USD";
		URL url = new URL(urlString);
		String str = "";
		try (InputStream in = url.openStream()) {
			int c;
			while ((c = in.read()) != -1)
				str += (char) c;
		}

		if (str.indexOf("No data for one of these cities :(. ") != -1)
			city.setPriceFood(0);
		else {
			int startIndex = str
					.indexOf("<tr><td>Meal, Inexpensive Restaurant</td>");
			int endIndex = str.indexOf("</tr>", startIndex);
			str = str.substring(startIndex, endIndex);

			startIndex = str.indexOf("class") + 10;
			endIndex = str.indexOf("&", startIndex);

			city.setPriceFood((int) Math.round(Double.parseDouble(str
					.substring(startIndex, endIndex))));
		}
	}
}
