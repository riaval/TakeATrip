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
	static CityService cityService;

	public InfoSearcher() {
	}

	static public void makeCities() throws IOException {
		String urlString = "https://raw.githubusercontent.com/David-Haim/CountriesToCitiesJSON/master/countriesToCities.json";
		URL url = new URL(urlString);
		String str = "";
		try (InputStream in = url.openStream()) {
			int c;
			while ((c = in.read()) != -1)
				str += (char) c;
		}

		int startIndex = str.indexOf("\"", 0);
		int endIndex = 0;
		while (startIndex != -1) {
			endIndex = str.indexOf("]", startIndex);
			String str1 = str.substring(startIndex, endIndex + 1);
			if(str1=="Monaco")
				makeCountryCities(str1);
			startIndex = str.indexOf("\"", endIndex);
		}
	}

	static private void makeCountryCities(String str){
		int startIndex=str.indexOf("\"")+1;
		int endIndex=str.indexOf("\"",startIndex);
		String country=str.substring(startIndex, endIndex);
		if(!nameIsFine(country))
			return;
		
		startIndex=str.indexOf("\"",endIndex+1)+1;
		while(startIndex!=-1){
			endIndex=str.indexOf("\"",startIndex);
			String city=str.substring(startIndex, endIndex);
			if(nameIsFine(city))
				cityService.add(new City(city,country,"",1,0,0,Date.valueOf(LocalDate.now())));
			startIndex=str.indexOf("\"",endIndex+1)+1;
		}
	}

	static private Boolean nameIsFine(String name) {
		for (int i = 0; i < name.length(); i++) {
			char chr = name.charAt(i);
			if (chr != ' ' && chr != '\'' && chr != '`'
					&& !(chr >= 'a' && chr <= 'z')
					&& !(chr >= 'A' && chr <= 'Z'))
				return false;
		}
		return true;
	}

	static public void updateFood(List<City> cities) throws IOException {
		int N = cities.size();
		for (int i = 0; i < N / 2; i++)
			updateFood(cities.get(2 * i), cities.get(2 * i + 1));
		if (N % 2 == 1)
			updateFood(cities.get(cities.size() - 1));
	}

	static public void updateFood(City city1, City city2) throws IOException {
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

	static public void updateFood(City city) throws IOException {
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
