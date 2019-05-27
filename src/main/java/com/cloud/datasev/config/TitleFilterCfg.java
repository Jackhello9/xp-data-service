package com.cloud.datasev.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloud.datasev.service.CarDataService;
import com.cloud.datasev.utils.TitleFilter;

@Configuration
public class TitleFilterCfg {

	@Autowired
	private CarDataService carDataService;

	@Bean
	public TitleFilter titleFilter() {
		// 创建车辆标题过滤器
		TitleFilter titleFilter = new TitleFilter();

		// 从数据库查询车辆标题，分页查询
		List<String> list = this.carDataService.queryByPage(1, 5000);

		// 遍历查询结果
		for (String str : list) {
			// 把查询到的数据放到过滤器中
			titleFilter.add(str);
		}

		// 返回创建好的过滤器
		return titleFilter;
	}

}
