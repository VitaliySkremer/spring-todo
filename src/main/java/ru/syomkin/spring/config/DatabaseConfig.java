package ru.syomkin.spring.config;

import jakarta.persistence.EntityManagerFactory;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:/application.properties")
public class DatabaseConfig {
    @Value("${db.driver}")
    private String databaseDriver;

    @Value("${db.url}")
    private String databaseUrl;

    @Value("${db.username}")
    private String databaseUsername;

    @Value("${db.password}")
    private String databasePassword;

    @Value("${hibernate.dialect}")
    private String hibernateDialect;

    @Value("${hibernate.show_sql}")
    private String hibernateShowSql;

    @Value("${hibernate.hbm2ddl.auto}")
    private String hibernateHbm2DdlAuto;

    @Value("${db.connection-pool.initial-size}")
    private int connectionPoolInitialSize;

    @Value("${db.connection-pool.min-idle}")
    private int connectionPoolMinIdle;

    @Value("${db.connection-pool.max-idle}")
    private int connectionPoolMaxIdle;

    @Value("${db.connection-pool.max-total}")
    private int connectionPoolMaxTotal;

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName(databaseDriver);
        dataSource.setUrl(databaseUrl);
        dataSource.setUsername(databaseUsername);
        dataSource.setPassword(databasePassword);

        dataSource.setInitialSize(connectionPoolInitialSize);
        dataSource.setMinIdle(connectionPoolMinIdle);
        dataSource.setMaxIdle(connectionPoolMaxIdle);
        dataSource.setMaxTotal(connectionPoolMaxTotal);

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();

        entityManager.setDataSource(dataSource);
        entityManager.setPackagesToScan("ru.syomkin.spring.entity");
        entityManager.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManager.setJpaProperties(getHibernateProperties());

        return entityManager;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory  entityManagerFactory) {
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(entityManagerFactory);
        return manager;
    }

    private Properties getHibernateProperties() {
        Properties properties = new Properties();

        properties.setProperty("hibernate.dialect", hibernateDialect);
        properties.setProperty("hibernate.show_sql", hibernateShowSql);
        properties.setProperty("hibernate.hbm2ddl.auto", hibernateHbm2DdlAuto);

        return properties;
    }
}
