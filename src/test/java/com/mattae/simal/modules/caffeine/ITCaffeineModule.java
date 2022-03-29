package com.mattae.simal.modules.caffeine;

import com.foreach.across.core.AcrossContext;
import com.foreach.across.test.AcrossTestConfiguration;
import com.foreach.across.test.AcrossWebAppConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

@ExtendWith({SpringExtension.class})
@DirtiesContext
@ContextConfiguration
@AcrossWebAppConfiguration
@TestExecutionListeners({
    DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
    TransactionalTestExecutionListener.class
})
public class ITCaffeineModule {
    @Autowired
    private CacheManager cacheManager;

    @Test
    public void bootstrapModule() {
        Assertions.assertNotNull(cacheManager);

        Cache cache = cacheManager.getCache("elementCache");
        Assertions.assertNotNull(cache);

        cache.put("item1", "value1");
        Assertions.assertNotNull(cache.get("item1"), "Cache should not be empty");

        cache.put("item2", "value2");
        Assertions.assertNull(cache.get("item3"));
        Assertions.assertNotNull(cache.get("item2"));
    }

    @Configuration
    @AcrossTestConfiguration(modules = CaffeineModule.NAME)
    @PropertySource(value = "classpath:application-test.properties", ignoreResourceNotFound = false)
    protected static class Config /*implements AcrossContextConfigurer */ {

        //@Override
        public void configure(AcrossContext context) {
            context.addModule(new CaffeineModule());
        }
    }
}
