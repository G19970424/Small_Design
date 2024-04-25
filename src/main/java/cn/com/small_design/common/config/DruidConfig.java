package cn.com.small_design.common.config;

import cn.com.small_design.common.properties.DruidDataSourceProperties;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Servlet;
import javax.servlet.Filter;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author gejj
 * @create 2024年03月25日 14点59分
 * @version 1.0
 */
@Configuration
@EnableConfigurationProperties({DruidDataSourceProperties.class})
public class DruidConfig {

    @Autowired
    private DruidDataSourceProperties dataSourceProperties;

    @Bean
    @ConditionalOnMissingBean
    public DataSource druidDataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
        druidDataSource.setUrl(dataSourceProperties.getUrl());
        druidDataSource.setUsername(dataSourceProperties.getUsername());
        druidDataSource.setPassword(dataSourceProperties.getPassword());

        druidDataSource.setInitialSize(dataSourceProperties.getInitialSize());
        druidDataSource.setMinIdle(dataSourceProperties.getMinIdle());
        druidDataSource.setMaxActive(dataSourceProperties.getMaxActive());
        druidDataSource.setMaxWait(dataSourceProperties.getMaxWait());
        druidDataSource.setTimeBetweenEvictionRunsMillis(dataSourceProperties.getTimeBetweenEvictionRunsMillis());
        druidDataSource.setMinEvictableIdleTimeMillis(dataSourceProperties.getMinEvictableIdleTimeMillis());
        druidDataSource.setValidationQuery(dataSourceProperties.getValidationQuery());
        druidDataSource.setTestWhileIdle(dataSourceProperties.isTestWhileIdle());
        druidDataSource.setTestOnBorrow(dataSourceProperties.isTestOnBorrow());
        druidDataSource.setTestOnReturn(dataSourceProperties.isTestOnReturn());
        druidDataSource.setPoolPreparedStatements(dataSourceProperties.isPoolPreparedStatements());
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(dataSourceProperties.getMaxPoolPreparedStatementPerConnectionSize());
        try {
            druidDataSource.setFilters(dataSourceProperties.getFilters());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return druidDataSource;
    }

    /**
     * 配置 Druid 监控管理后台的Servlet；
     * 内置 Servler 容器时没有web.xml文件，所以使用 Spring Boot 的注册 Servlet 方式
     * public ServletRegistrationBean druidServlet()相当于WebServlet配置。
     */
    @Bean
    @ConditionalOnMissingBean
    public ServletRegistrationBean<Servlet> druidServlet(){
        ServletRegistrationBean<Servlet> servletServletRegistrationBean = new ServletRegistrationBean<Servlet>(new StatViewServlet(), "/druid/*");
        //白名单
        servletServletRegistrationBean.addInitParameter("allow","127.0.0.1");  //表示只有本机可以访问,为空或者为null时，表示允许所有访问
        //ip黑名单(存在共同时，deny优先于allow)
        //如果满足deny的话会提示，sorry， you are not permitted to view this page
        servletServletRegistrationBean.addInitParameter("deny","172.13.13.31");
        //登录查看信息的账号和密码，用于登录Druid监控后台
        servletServletRegistrationBean.addInitParameter("loginUsername","admin");
        servletServletRegistrationBean.addInitParameter("loginPassword","admin");
        //是否能重置数据
        servletServletRegistrationBean.addInitParameter("resetEnable","true");
        return servletServletRegistrationBean;
    }

    /**
     * 配置 Druid 监控 之  web 监控的 filter
     * WebStatFilter：用于配置Web和Druid数据源之间的管理关联监控统计
     * public FilterRegistrationBean filterRegistrationBean()相当于Web Filter配置。
     */
    @Bean
    @ConditionalOnMissingBean
    public FilterRegistrationBean<Filter> filterFilterRegistrationBean(){
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<Filter>();
        bean.setFilter(new WebStatFilter());

        //exclusions：设置哪些请求进行过滤排除掉，从而不进行统计
        Map<String, String> initParams = new HashMap<>();
        initParams.put("exclusions", "*.js,*.css,/druid/*");
        bean.setInitParameters(initParams);

        //"/*" 表示过滤所有请求
        bean.setUrlPatterns(Collections.singletonList("/*"));
        return bean;
    }

}
