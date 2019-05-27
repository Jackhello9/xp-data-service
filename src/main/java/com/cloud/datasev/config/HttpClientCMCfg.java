package com.cloud.datasev.config;

import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpClientCMCfg {

	@Bean
	public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager() {
		// 创建连接管理器
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();

		// 设置最大连接数
		cm.setMaxTotal(200);

		// 设置每个并发连接数
		cm.setDefaultMaxPerRoute(20);

		return cm;
	}
}

