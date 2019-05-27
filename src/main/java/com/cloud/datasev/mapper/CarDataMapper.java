package com.cloud.datasev.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.cloud.datasev.pojo.CarData;

@Mapper
public interface CarDataMapper {
	/**
	 * 分页查询数据
	 * 
	 * @param start
	 * @param rows
	 * @return
	 */
	@Select("SELECT title FROM car_test LIMIT #{start},#{rows}")
	List<String> queryByPage(Map<String, Integer> map);
	/**
	 * 保存
	 * 
	 * @param carTest
	 */
	@Insert(
	"INSERT INTO `car_test` (" +
	"	`title`," +
	"	`test_speed`," +
	"	`test_brake`," +
	"	`test_oil`," +
	"	`editor_name1`," +
	"	`editor_remark1`," +
	"	`editor_name2`," +
	"	`editor_remark2`," +
	"	`editor_name3`," +
	"	`editor_remark3`," +
	"	`image`," +
	"	`created`," +
	"	`updated`" +
	")" +
	"VALUES" +
	"	(" +
	"		#{title}," +
	"		#{test_speed}," +
	"		#{test_brake}," +
	"		#{test_oil}," +
	"		#{editor_name1}," +
	"		#{editor_remark1}," +
	"		#{editor_name2}," +
	"		#{editor_remark2}," +
	"		#{editor_name3}," +
	"		#{editor_remark3}," +
	"		#{image}," +
	"		#{created}," +
	"		#{updated}" +
	"	)")

	void save(CarData carData);

}
