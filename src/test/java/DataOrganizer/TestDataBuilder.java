package DataOrganizer;

import java.util.ArrayList;
import java.util.List;

import POJO.Location;
import POJO.maps;

public class TestDataBuilder {
	
	public maps addplacepayload(String name, String language, String address) {
		maps map=new maps();
		map.setAccuracy(50);
		map.setAddress(address);
		map.setLanguage(language);
		map.setPhone_number("45678888");
		map.setWebsite("www.google.com");
		map.setName(name);
		List<String> li=new ArrayList<String>();
		li.add("selenium");
		li.add("resrassured");
		map.setTypes(li);
		Location loc=new Location();
		loc.setLat("-38.383494");
		loc.setLng("38.383494");
		map.setLocation(loc);
		return map;
	}

}
