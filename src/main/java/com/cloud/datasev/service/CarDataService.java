package com.cloud.datasev.service;

import java.util.List;

import com.cloud.datasev.pojo.CarData;

public interface CarDataService {

	List<String> queryByPage(int i, int j);

	void saveCarData(CarData carData);

}
