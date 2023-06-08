package com.nnhuy.restaurantmanagement.bean;

public class FoodBean {
	private int foodID;
	private String foodName;
	private int amount;
	private int price;
	private String image;
	private int countryID;

	public int getFoodID() {
		return foodID;
	}

	public void setFoodID(int foodID) {
		this.foodID = foodID;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getCountryID() {
		return countryID;
	}

	public void setCountryID(int countryID) {
		this.countryID = countryID;
	}

	public FoodBean() {
		super();
	}

	public FoodBean(int foodID, String foodName, int amount, int price, String image, int countryID) {
		super();
		this.foodID = foodID;
		this.foodName = foodName;
		this.amount = amount;
		this.price = price;
		this.image = image;
		this.countryID = countryID;
	}
}
