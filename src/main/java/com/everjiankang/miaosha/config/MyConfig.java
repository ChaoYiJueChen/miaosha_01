package com.everjiankang.miaosha.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.ResourceServlet;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.everjiankang.miaosha.redis.RedisConfig;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class MyConfig {
	
	@Autowired
	RedisConfig redisConfig;
	
	@Bean
	public JedisPool jedisPoolFactory() {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxIdle(redisConfig.getPoolMaxIdle());
		poolConfig.setMaxTotal(redisConfig.getPoolMaxTotal());
		poolConfig.setMaxWaitMillis(redisConfig.getPoolMaxWait() * 1000);
		return new JedisPool(poolConfig, redisConfig.getHost(), redisConfig.getPort(), redisConfig.getTimeout(), redisConfig.getPasspord());
	}

    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource dataSource() {
        //对应DataSourceConfiguration类下的几个类型
        //javax.sql.DataSource (Generic)
        //BasicDataSource (Dbcp2)
        //HikariDataSource (Hikari)
        //org.apache.tomcat.jdbc.pool.DataSource (tomcat)
        return DataSourceBuilder.create().type(DruidDataSource.class).build();
    }

    //配置Druid数据源的Servelt
    @Bean
    public ServletRegistrationBean<StatViewServlet> statViewServlet() {
        ServletRegistrationBean<StatViewServlet> servlet = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");

        Map<String,String> initParam = new HashMap<>();
        initParam.put(ResourceServlet.PARAM_NAME_USERNAME,"admin");
        initParam.put(ResourceServlet.PARAM_NAME_PASSWORD,"123456");
        initParam.put(ResourceServlet.PARAM_NAME_ALLOW,"");//默认允许所有地址来源的访问
        initParam.put(ResourceServlet.PARAM_NAME_DENY,"192.168.1.234");

        servlet.setInitParameters(initParam);
        return servlet;
    }
    //配置Druid数据源的过滤器
    @Bean
    public FilterRegistrationBean<WebStatFilter> StatViewFilter() {
        WebStatFilter druidFilter = new WebStatFilter();
        FilterRegistrationBean<WebStatFilter> filterBean = new FilterRegistrationBean<>(druidFilter);

        Map<String,String> initParam = new HashMap<>();
        initParam.put(WebStatFilter.PARAM_NAME_EXCLUSIONS,"*.js,*.css,/druid/*");
        filterBean.setUrlPatterns(Arrays.asList("/*")); //过滤所有请求
        filterBean.setInitParameters(initParam);
        return filterBean;
    }


}