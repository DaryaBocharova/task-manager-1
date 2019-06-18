package ru.stepanenko.tm.config;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.server.WebFilter;
import ru.stepanenko.tm.util.DataGenerator;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("ru.stepanenko.tm.api.repository")
@PropertySource("classpath:application.properties")
public class DataBaseConfig {

    @Bean
    public DataSource dataSource(
            @Value("${datasource.driver}") final String dataSourceDriver,
            @Value("${datasource.url}") final String dataSourceUrl,
            @Value("${datasource.user}") final String dataSourceUser,
            @Value("${datasource.password}") final String dataSourcePassword
    ) {
        final DriverManagerDataSource dataSource =
                new DriverManagerDataSource();
        dataSource.setDriverClassName(dataSourceDriver);
        dataSource.setUrl(dataSourceUrl);
        dataSource.setUsername(dataSourceUser);
        dataSource.setPassword(dataSourcePassword);
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            final DataSource dataSource,
            @Value("${hibernate.show_sql}") final boolean showSql,
            @Value("${hibernate.hbm2ddl.auto}") final String tableStrategy,
            @Value("${hibernate.dialect}") final String dialect
    ) {
        final LocalContainerEntityManagerFactoryBean factoryBean;
        factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.setPackagesToScan("ru.stepanenko.tm.model.entity");
        final Properties properties = new Properties();
        properties.put("hibernate.show_sql", showSql);
        properties.put("hibernate.hbm2ddl.auto", tableStrategy);
        properties.put("hibernate.dialect", dialect);
        properties.put(Environment.USE_SECOND_LEVEL_CACHE, "true");
        properties.put(Environment.USE_QUERY_CACHE, "true");
        properties.put(Environment.USE_MINIMAL_PUTS, "true");
        properties.put("hibernate.cache.hazelcast.use_lite_member", "true");
        properties.put(Environment.CACHE_REGION_PREFIX, "task-manager");
        properties.put(Environment.CACHE_PROVIDER_CONFIG, "hazelcast.xml");
        properties.put(Environment.CACHE_REGION_FACTORY, "com.hazelcast.hibernate.HazelcastLocalCacheRegionFactory");
        factoryBean.setJpaProperties(properties);
        return factoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager(
            final LocalContainerEntityManagerFactoryBean emf
    ) {
        final JpaTransactionManager transactionManager =
                new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf.getObject());
        return transactionManager;
    }
}
