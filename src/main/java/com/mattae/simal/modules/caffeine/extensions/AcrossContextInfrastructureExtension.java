/*
 * Copyright 2014 the original author or authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mattae.simal.modules.caffeine.extensions;

import com.foreach.across.core.annotations.ModuleConfiguration;
import com.foreach.across.core.cache.AcrossCompositeCacheManager;
import com.foreach.across.core.context.bootstrap.AcrossBootstrapConfigurer;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;


@RequiredArgsConstructor
@ModuleConfiguration(AcrossBootstrapConfigurer.CONTEXT_INFRASTRUCTURE_MODULE)
@EnableConfigurationProperties(CacheProperties.class)
class AcrossContextInfrastructureExtension {
    private final CacheProperties properties;

    @Bean
    public CacheManager caffeineCacheManager(AcrossCompositeCacheManager acrossCompositeCacheManager) {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCacheSpecification(properties.getCaffeine().getSpec());
        acrossCompositeCacheManager.addCacheManager(cacheManager);
        return cacheManager;
    }

    @Bean
    @Primary
    public CacheManager primaryCacheManager(AcrossCompositeCacheManager acrossCompositeCacheManager) {
        return acrossCompositeCacheManager;
    }
}
