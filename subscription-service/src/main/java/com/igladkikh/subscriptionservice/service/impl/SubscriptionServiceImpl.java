package com.igladkikh.subscriptionservice.service.impl;

import com.igladkikh.subscriptionservice.client.UserFeignClient;
import com.igladkikh.subscriptionservice.dto.ServiceStatisticDto;
import com.igladkikh.subscriptionservice.dto.SubscriptionRequestDto;
import com.igladkikh.subscriptionservice.dto.SubscriptionResponseDto;
import com.igladkikh.subscriptionservice.dto.UserDto;
import com.igladkikh.subscriptionservice.dto.mapper.SubscriptionMapper;
import com.igladkikh.subscriptionservice.exception.SubscriptionServiceException;
import com.igladkikh.subscriptionservice.model.Subscription;
import com.igladkikh.subscriptionservice.repository.SubscriptionRepository;
import com.igladkikh.subscriptionservice.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;
    private final UserFeignClient userClient;
//    private final CacheUtil cacheUtil;

    @Override
//    @Cacheable(value = CacheUtil.TOP_SUBSCRIPTIONS_CACHE_ID, key = "#limit")
    public List<ServiceStatisticDto> findTopServices(int limit) {
        return subscriptionRepository.findTopServices(limit);
    }

    @Override
//    @Cacheable(value = CacheUtil.USER_SUBSCRIPTION_CACHE_ID, key = "#userId")
    public List<SubscriptionResponseDto> findByUserId(Long userId) {
        // Проверка наличия пользователя
        UserDto user = userClient.findById(userId);
        return subscriptionMapper.toResponseDto(subscriptionRepository.findByUserId(userId));
    }

    @Override
//    @CacheEvict(value = CacheUtil.USER_SUBSCRIPTION_CACHE_ID, key = "#userId")
    public SubscriptionResponseDto create(Long userId, SubscriptionRequestDto dto) {
        // Проверка наличия у пользователя действующей подписки на сервис
        Optional<Subscription> optionalExistedSubscription = subscriptionRepository.findByUserIdAndServiceAndExpiryDateIsAfter(userId,
                dto.getService(), LocalDate.now().minusDays(1));
        if (optionalExistedSubscription.isPresent()) {
            Subscription existedSubscription = optionalExistedSubscription.get();
            throw new SubscriptionServiceException(HttpStatus.CONFLICT,
                    "Service %s is already rental by user #%d unto %s ".formatted(
                            existedSubscription.getService(),
                            userId, existedSubscription.getExpiryDate())
            ) {
            };
        }
        // Одновременная проверка наличия пользователя
        UserDto user = userClient.findById(userId);
        Subscription subscription = subscriptionMapper.toEntity(dto);
        Subscription resultSubscription = subscriptionRepository.save(subscription);
        // Очищаем связанный кэш
//        cacheUtil.evictUserCache(userId);
//        cacheUtil.clearTopSubscriptionCache();

        return subscriptionMapper.toResponseDto(resultSubscription);
    }

    @Override
//    @CacheEvict(value = CacheUtil.USER_SUBSCRIPTION_CACHE_ID, key = "#userId")
    public void delete(Long userId, Long subscriptionId) {
        // Одновременная проверка наличия пользователя
        UserDto user = userClient.findById(userId);
        Subscription subscription = findEntity(subscriptionId);
        // Проверка приналежности подписки пользователю
        if (!Objects.equals(subscription.getUserId(), userId)) {
            throw new SubscriptionServiceException(HttpStatus.CONFLICT, "User does not belong to this Subscription");
        }
        subscriptionRepository.delete(findEntity(subscriptionId));
        // Очищаем связанный кэш
//        cacheUtil.evictUserCache(userId);
//        cacheUtil.clearTopSubscriptionCache();
    }

    private Subscription findEntity(Long id) {
        return subscriptionRepository.findById(id).orElseThrow(() ->
                new SubscriptionServiceException(HttpStatus.NOT_FOUND, "Subscription with id `%s` not found".formatted(id)));
    }
}
