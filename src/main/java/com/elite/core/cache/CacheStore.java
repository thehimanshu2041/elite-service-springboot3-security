package com.elite.core.cache;

import com.elite.core.factory.MessageResource;
import com.elite.core.log.ESLog;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class CacheStore<T> {

    private final Cache<String, T> cache;

    public CacheStore() {
        cache = CacheBuilder.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .concurrencyLevel(Runtime.getRuntime().availableProcessors())
                .build();
    }

    public boolean check(String key) {
        return null != cache.getIfPresent(key);
    }

    public T get(String key) {
        return cache.getIfPresent(key);
    }

    public void add(String key, T value) {
        if (key != null && value != null) {
            cache.put(key, value);
            log.info(MessageResource.getMessage(ESLog.ES_002), value.getClass().getSimpleName(), key);
        }
    }
}
