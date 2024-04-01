package cn.com.small_design.common.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author gejj
 * @createTime 2024年03月25日 14点59分
 * @version 1.0
 */
@ConfigurationProperties(prefix = "spring.datasource.druid")
public class DruidDataSourceProperties {
    private String driverClassName;
    private String url;
    private String username;
    private String password;

    /**
     * 初始化连接池大小.默认值 0
     */
    private int initialSize;
    /**
     * 线程池最小空闲数
     */
    private int minIdle;
    /**
     * 连接池最大数量 默认值 8
     */
    private int maxActive = 100;
    /**
     * 获取连接时最大等待时间，单位毫秒，设置-1时表示无限等待。
     * 缺省启用公平锁，并发效率会有所下降，如果需要可以通过配置useUnfairLock属性为true使用非公平锁。
     */
    private long maxWait;
    /**
     * 检查空闲连接的频率，单位毫秒
     */
    private long timeBetweenEvictionRunsMillis;
    /**
     * 连接保持空闲而不被驱逐的最小时间
     */
    private long minEvictableIdleTimeMillis;
    /**
     * 用来检测连接是否有效的sql。
     * 要求是一个查询语句，常用select 'x'。
     * 如果validationQuery为null，testOnBorrow、testOnReturn、testWhileIdle都不会起作用。
     */
    private String validationQuery;
    /**
     * 建议配置为true，不影响性能，并且保证安全性。
     * 申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。
     * 默认值 false
     */
    private boolean testWhileIdle;
    /**
     * 申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。
     * 默认值 true
     */
    private boolean testOnBorrow;
    /**
     * 归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。
     * 默认值 false
     */
    private boolean testOnReturn;
    /**
     * 是否缓存preparedStatement，也就是PSCache。
     * PSCache对支持游标的数据库性能提升巨大，比如说oracle。在mysql下建议关闭。
     * 默认值 false
     */
    private boolean poolPreparedStatements;
    /**
     * 要启用PSCache，必须配置大于0，
     * 当大于0时，poolPreparedStatements自动触发修改为true。
     * 在Druid中，不会存在Oracle下PSCache占用内存过多的问题，可以把这个数值配置大一些，比如说100
     * 默认值 -1
     */
    private int maxPoolPreparedStatementPerConnectionSize;
    // filter

    /**
     * 属性类型是字符串，通过别名的方式配置扩展插件，常用的插件有：
     * 监控统计用的 filter:stat
     * 日志用的 filter:log4j
     * 防御sql注入的 filter:wall
     */
    private String filters;

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getInitialSize() {
        return initialSize;
    }

    public void setInitialSize(int initialSize) {
        this.initialSize = initialSize;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public int getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    public long getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(long maxWait) {
        this.maxWait = maxWait;
    }

    public long getTimeBetweenEvictionRunsMillis() {
        return timeBetweenEvictionRunsMillis;
    }

    public void setTimeBetweenEvictionRunsMillis(long timeBetweenEvictionRunsMillis) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
    }

    public long getMinEvictableIdleTimeMillis() {
        return minEvictableIdleTimeMillis;
    }

    public void setMinEvictableIdleTimeMillis(long minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
    }

    public String getValidationQuery() {
        return validationQuery;
    }

    public void setValidationQuery(String validationQuery) {
        this.validationQuery = validationQuery;
    }

    public boolean isTestWhileIdle() {
        return testWhileIdle;
    }

    public void setTestWhileIdle(boolean testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
    }

    public boolean isTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public boolean isTestOnReturn() {
        return testOnReturn;
    }

    public void setTestOnReturn(boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
    }

    public boolean isPoolPreparedStatements() {
        return poolPreparedStatements;
    }

    public void setPoolPreparedStatements(boolean poolPreparedStatements) {
        this.poolPreparedStatements = poolPreparedStatements;
    }

    public int getMaxPoolPreparedStatementPerConnectionSize() {
        return maxPoolPreparedStatementPerConnectionSize;
    }

    public void setMaxPoolPreparedStatementPerConnectionSize(int maxPoolPreparedStatementPerConnectionSize) {
        this.maxPoolPreparedStatementPerConnectionSize = maxPoolPreparedStatementPerConnectionSize;
    }

    public String getFilters() {
        return filters;
    }

    public void setFilters(String filters) {
        this.filters = filters;
    }
}
