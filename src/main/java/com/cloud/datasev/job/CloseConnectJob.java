package com.cloud.datasev.job;

import java.util.Date;

import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

@DisallowConcurrentExecution
public class CloseConnectJob extends QuartzJobBean {

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		// System.out.println("执行: "+new Date().getTime());
		// 获取spring容器
		ApplicationContext applicationContext = (ApplicationContext) context.getJobDetail().getJobDataMap()
				.get("context");

		// 从容器中获取HttpClient连接管理器
		PoolingHttpClientConnectionManager cm = applicationContext.getBean(PoolingHttpClientConnectionManager.class);

		// 关闭失效连接
		cm.closeExpiredConnections();
		System.out.println("关闭失效连接"+new Date().getTime());
	}

}

