package com.nnhuy.restaurantmanagement.bo;

import com.nnhuy.restaurantmanagement.bean.CountryBean;
import com.nnhuy.restaurantmanagement.dao.CountryDAO;

public class CountryBO {
	public CountryBean getCountryFromName(String countryName) {
		CountryDAO ctydao = new CountryDAO();
		return ctydao.getCountryFromName(countryName);
	}
}
