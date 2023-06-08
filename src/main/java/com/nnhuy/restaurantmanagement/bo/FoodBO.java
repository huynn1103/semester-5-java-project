package com.nnhuy.restaurantmanagement.bo;

import java.util.ArrayList;

import com.nnhuy.restaurantmanagement.bean.FoodBean;
import com.nnhuy.restaurantmanagement.dao.FoodDAO;

public class FoodBO {
	public ArrayList<FoodBean> getAllFoods() {
		FoodDAO fdao = new FoodDAO();
		return fdao.getAllFoods();
	}

	public ArrayList<FoodBean> getFoodsFromCountry(int countryID) {
		FoodDAO fdao = new FoodDAO();
		return fdao.getFoodsFromCountry(countryID);
	}
	
	public ArrayList<FoodBean> getFoodsFromKey(String key) {
		FoodDAO fdao = new FoodDAO();
		return fdao.getFoodsFromKey(key);
	}
	
	public ArrayList<FoodBean> getBestPriceFoods() {
		FoodDAO fdao = new FoodDAO();
		return fdao.getBestPriceFoods();
	}
	
	public ArrayList<FoodBean> getBestSellerFoods() {
		FoodDAO fdao = new FoodDAO();
		return fdao.getBestSellerFoods();
	}
}
