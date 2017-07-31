package com.puigros.configuration;
import java.util.Properties;

import com.gemstone.gemfire.cache.GemFireCache;
import com.gemstone.gemfire.cache.server.CacheServer;
import com.gemstone.gemfire.internal.DistributionLocator;
import com.puigros.gemfire.model.Customer;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.gemfire.CacheFactoryBean;
import org.springframework.data.gemfire.LocalRegionFactoryBean;
import org.springframework.data.gemfire.ReplicatedRegionFactoryBean;
import org.springframework.shell.converters.BigDecimalConverter;
import org.springframework.util.StringUtils;


/**
 * Created by gpuigros on 28/07/17.
 */
@Configuration
@Log4j2
public class GemfireConfig {
//    @Value("${gemfire.nodename}")
//    private String nodeName;
//    @Value("${gemfire.setupLocator}")
    private boolean setupLocator;
//    @Value("${spring.gemfire.manager.port}")
//    private Integer manPort;

    protected static final int DEFAULT_CACHE_SERVER_PORT = CacheServer.DEFAULT_PORT;
    protected static final int DEFAULT_LOCATOR_PORT = DistributionLocator.DEFAULT_LOCATOR_PORT;
    protected static final int DEFAULT_MANAGER_PORT = 1099;
    protected static final String DEFAULT_LOG_LEVEL = "config";

    @Bean
    Properties gemfireProperties() {
        Properties gemfireProperties = new Properties();
        gemfireProperties.setProperty("name", "nodeName");
        gemfireProperties.setProperty("mcast-port", "8081");
        if (setupLocator) {
            gemfireProperties.setProperty("jmx-manager", "true");
            gemfireProperties.setProperty("jmx-manager-port", String.valueOf(1099));
            gemfireProperties.setProperty("jmx-manager-start", String.valueOf(true));

            gemfireProperties.setProperty("locators", "localhost[10334]");
            gemfireProperties.setProperty("start-locator", "localhost[10334]");
        }else{
            gemfireProperties.setProperty("jmx-manager", "true");
            gemfireProperties.setProperty("jmx-manager-port", String.valueOf(1199));
            gemfireProperties.setProperty("jmx-manager-start", String.valueOf(true));
            gemfireProperties.setProperty("locators", "localhost[10334]");
            //gemfireProperties.setProperty("start-locator", "localhost[10335]");

        }

        return gemfireProperties;
    }

/*
    @Bean
    Properties gemfireProperties(
            @Value("${spring.gemfire.log-level:"+DEFAULT_LOG_LEVEL+"}") String logLevel,
            @Value("${spring.gemfire.locators:localhost["+DEFAULT_LOCATOR_PORT+"]}") String locators,
            @Value("${spring.gemfire.manager.port:"+DEFAULT_MANAGER_PORT+"}") int managerPort,
            @Value("${spring.gemfire.manager.start:false}") boolean jmxManagerStart,
            @Value("${spring.gemfire.start-locator}") String startLocator) {

        log.warn("spring.gemfire.log-level is [{}]", logLevel);
        Properties gemfireProperties = new Properties();
        gemfireProperties.setProperty("name", nodeName);
        gemfireProperties.setProperty("mcast-port", "8081");
        gemfireProperties.setProperty("log-level", logLevel);

        gemfireProperties.setProperty("jmx-manager", "true");
        gemfireProperties.setProperty("jmx-manager-port", String.valueOf(manPort));
        gemfireProperties.setProperty("jmx-manager-start", String.valueOf(jmxManagerStart));


        if (setupLocator) {
            gemfireProperties.setProperty("locators", locators);

            if (StringUtils.hasText(startLocator)) {
                gemfireProperties.setProperty("start-locator", startLocator);
            }
        }
        return gemfireProperties;
    }
    */
    @Bean
    CacheFactoryBean gemfireCache() {
        CacheFactoryBean gemfireCache = new CacheFactoryBean();
        //gemfireCache.setProperties(gemfireProperties(DEFAULT_LOG_LEVEL,"localhost[10334]",DEFAULT_MANAGER_PORT,true,"localhost[10334]"));
        //gemfireCache.setProperties(gemfireProperties());
        return gemfireCache;
    }

    @Bean
    ReplicatedRegionFactoryBean<String, Customer> customerRegion(final GemFireCache cache) {
        ReplicatedRegionFactoryBean<String, Customer> customerRegion = new ReplicatedRegionFactoryBean<>();
        customerRegion.setCache(cache);
        customerRegion.setName("customer");
        customerRegion.setPersistent(false);
        return customerRegion;
    }
}
