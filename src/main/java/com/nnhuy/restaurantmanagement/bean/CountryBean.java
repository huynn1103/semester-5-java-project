package com.nnhuy.restaurantmanagement.bean;

public class CountryBean {
	private int countryID;
	private String countryName;

	public int getCountryID() {
		return countryID;
	}

	public void setCountryID(int countryID) {
		this.countryID = countryID;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public CountryBean() {
		super();
	}

	public CountryBean(int countryID, String countryName) {
		super();
		this.countryID = countryID;
		this.countryName = countryName;
	}
}
