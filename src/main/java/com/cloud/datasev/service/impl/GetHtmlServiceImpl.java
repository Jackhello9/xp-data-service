package com.cloud.datasev.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.datasev.service.GetHtmlService;

@Service
public class GetHtmlServiceImpl implements GetHtmlService {

	@Autowired
	private PoolingHttpClientConnectionManager cm;

	@Override
	public String getHtml(String url) {
		// 使用连接池管理器获取连接
		CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();

		// 创建httpGet请求
		HttpGet httpGet = new HttpGet(url);
		// 设置用户代理信息
		httpGet.setHeader("User-Agent", "");
		httpGet.setConfig(getRequestConfig());

		CloseableHttpResponse response = null;
		try {
			// 发起请求
			response = httpClient.execute(httpGet);

			// 判断请求是否成功
			if (response.getStatusLine().getStatusCode() == 200) {

				String html = null;

				// 判断是否有响应体
				if (response.getEntity() != null) {
					// 如果有响应体，则进行解析
					html = EntityUtils.toString(response.getEntity(), "UTF-8");

				}
				// 返回
				return html;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放连接
			if (response != null) {
				try {
					response.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return null;
	}

	@Override
	public String getImage(String url) {
		// 使用连接池管理器获取连接
		CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();

		// 创建httpGet请求
		HttpGet httpGet = new HttpGet(url);
		// 设置用户代理信息
		httpGet.setHeader("User-Agent", "");
		httpGet.setConfig(getRequestConfig());

		CloseableHttpResponse response = null;
		try {
			// 发起请求
			response = httpClient.execute(httpGet);

			// 判断请求是否成功
			if (response.getStatusLine().getStatusCode() == 200) {
				// 判断是否有响应体
				if (response.getEntity() != null) {
					// 如果有响应体，则下载
					// 获取文件后缀
					String extName = StringUtils.substringAfterLast(url, ".");
					// 用UUID重新生成文件名
					String image = UUID.randomUUID().toString() + "." + extName;

					// 下载图片
					OutputStream outstream = new FileOutputStream(new File("G:/images/" + image));
					response.getEntity().writeTo(outstream);

					// 返回图片名
					return image;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放连接
			if (response != null) {
				try {
					response.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return null;
	}

	/**
	 * 获取请求参数
	 * 
	 */
	private RequestConfig getRequestConfig() {

		RequestConfig build = RequestConfig.custom().setConnectTimeout(2000).setConnectionRequestTimeout(1000)
				.setSocketTimeout(1000).build();

		return build;

	}
	
	
	
	
	
	

}
