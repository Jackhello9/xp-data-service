package com.cloud.datasev.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.datasev.mapper.CarDataMapper;
import com.cloud.datasev.pojo.CarData;
import com.cloud.datasev.service.CarDataService;

@Service
public class CarDataServiceImpl implements CarDataService{
	
	@Autowired
	private CarDataMapper carDataMapper;

	@Override
	public List<String> queryByPage(int page, int rows) {
		// 设置从哪一条数据开始查
		Integer start = (page - 1) * rows;

		// 设置参数
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("rows", rows);

		// 分页查询数据
		List<String> list = this.carDataMapper.queryByPage(map);

		// 返回查询结果
		return list;
	}

	@Override
	public void saveCarData(CarData carData) {
		// TODO Auto-generated method stub
		this.carDataMapper.save(carData);
	}


}
