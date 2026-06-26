package th.co.muangthai.testbbl.model;

import lombok.Data;

@Data
public class Address {
	
	private String street;
	private String suite;
	private String  city;
	private String  zipcode;
	private GeoModel geo;

}
