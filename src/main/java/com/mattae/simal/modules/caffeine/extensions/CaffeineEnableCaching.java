package com.mattae.simal.modules.caffeine.extensions;

import com.foreach.across.core.annotations.ModuleConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.core.Ordered;


@ModuleConfiguration(optional = true)
@EnableCaching(order = CaffeineEnableCaching.INTERCEPT_ORDER)
public class CaffeineEnableCaching {
	public static final int INTERCEPT_ORDER = Ordered.HIGHEST_PRECEDENCE + 10;
}
