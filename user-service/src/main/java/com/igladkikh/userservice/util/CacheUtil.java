package com.igladkikh.userservice.util;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CacheUtil {
    public static final String USER_CACHE_ID = "userCache";
    public static final String USER_SUBSCRIPTION_CACHE_ID = "userSubscriptionsCache";
    public static final String TOP_SUBSCRIPTIONS_CACHE_ID = "topSubscriptionsCache";

    private final CacheManager cacheManager;

    public void clearTopSubscriptionCache() {
        cacheManager.getCache(TOP_SUBSCRIPTIONS_CACHE_ID).clear();
    }

    public void evictUserCache(Long userId) {
        cacheManager.getCache(USER_CACHE_ID).evictIfPresent(userId);
    }

    public void evictUserSubscriptionsCache(Long userId) {
        cacheManager.getCache(USER_SUBSCRIPTION_CACHE_ID).evictIfPresent(userId);
    }
}
