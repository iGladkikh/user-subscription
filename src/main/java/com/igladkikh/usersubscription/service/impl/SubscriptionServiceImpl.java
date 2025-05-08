package com.igladkikh.usersubscription.service.impl;

import com.igladkikh.usersubscription.dto.ServiceStatisticDto;
import com.igladkikh.usersubscription.dto.SubscriptionRequestDto;
import com.igladkikh.usersubscription.dto.SubscriptionResponseDto;
import com.igladkikh.usersubscription.dto.mapper.SubscriptionMapper;
import com.igladkikh.usersubscription.exception.AppException;
import com.igladkikh.usersubscription.model.Subscription;
import com.igladkikh.usersubscription.model.User;
import com.igladkikh.usersubscription.repository.SubscriptionRepository;
import com.igladkikh.usersubscription.service.UserService;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements com.igladkikh.usersubscription.service.SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;
    private final UserService userService;

    @Override
    public List<ServiceStatisticDto> findTopServices(int limit) {
        return subscriptionRepository.findTopServices(limit);
    }

    @Override
    public List<SubscriptionResponseDto> findByUserId(Long userId) {
        // Проверка наличия пользователя
        userService.findEntity(userId);
        return subscriptionMapper.toResponseDto(subscriptionRepository.findByUserId(userId));
    }

    @Override
    public SubscriptionResponseDto create(Long userId, SubscriptionRequestDto dto) {
        // Проверка наличия у пользователя действующей подписки на сервис
        Optional<Subscription> optionalExistedSubscription = subscriptionRepository.findByUserIdAndServiceAndExpiryDateIsAfter(userId,
                dto.getService(), LocalDate.now().minusDays(1));
        if (optionalExistedSubscription.isPresent()) {
            Subscription existedSubscription = optionalExistedSubscription.get();
            throw new AppException(HttpStatus.CONFLICT,
                    "Service %s is already rental by user #%d unto %s ".formatted(
                            existedSubscription.getService(),
                            userId, existedSubscription.getExpiryDate())
            );
        }
        // Одновременная проверка наличия пользователя
        User user = userService.findEntity(userId);
        Subscription subscription = subscriptionMapper.toEntity(dto);
        subscription.setUser(user);
        Subscription resultSubscription = subscriptionRepository.save(subscription);
        return subscriptionMapper.toResponseDto(resultSubscription);
    }

    @Override
    public void delete(Long userId, Long subscriptionId) {
        // Одновременная проверка наличия пользователя
        User user = userService.findEntity(userId);
        Subscription subscription = findEntity(subscriptionId);
        // Проверка приналежности подписки пользователю
        if (!Objects.equals(subscription.getUser().getId(), user.getId())) {
            throw new AppException(HttpStatus.CONFLICT, "User does not belong to this Subscription");
        }
        subscriptionRepository.delete(findEntity(subscriptionId));
    }

    private Subscription findEntity(Long id) {
        return subscriptionRepository.findById(id).orElseThrow(() ->
                new AppException(HttpStatus.NOT_FOUND, "Subscription with id `%s` not found".formatted(id)));
    }
}
