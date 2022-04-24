package com.autohome.frostmourne.monitor.config;

import java.io.IOException;
import java.util.Properties;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;

/**
 * @author kcq
 */
@Configuration
@EnableTransactionManagement
@MapperScan(value = "com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper", sqlSessionFactoryRef = "frostmourneSqlSessionFactory")
public class FrostmourneDataSourceConfig {
    @Value("${druid.datasource.frostmourne.mapperLocations}")
    private String mapperLocations;

    @Bean(name = "frostmourneDataSource", destroyMethod = "close")
    @ConfigurationProperties(prefix = "druid.datasource.frostmourne")
    public DruidDataSource frostmourneDataSource() {
        return new DruidDataSource();
    }

    @Bean(name = "frostmourneSqlSessionFactory")
    public SqlSessionFactory frostmourneSqlSessionFactory() throws Exception {
        PageInterceptor pageInterceptor = new PageInterceptor();
        pageInterceptor.setProperties(new Properties());
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(frostmourneDataSource());
        sqlSessionFactoryBean.setPlugins(pageInterceptor);

        try {
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            sqlSessionFactoryBean.setMapperLocations(resolver.getResources(this.mapperLocations));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        try {
            return sqlSessionFactoryBean.getObject();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Bean
    public DataSourceTransactionManager frostmourneTransactionManager() throws Exception {
        return new DataSourceTransactionManager(frostmourneDataSource());
    }
}