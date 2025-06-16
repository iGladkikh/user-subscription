package com.igladkikh.subscriptionservice.repository;

import com.igladkikh.subscriptionservice.dto.ServiceStatisticDto;
import com.igladkikh.subscriptionservice.model.ServiceName;
import com.igladkikh.subscriptionservice.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    List<Subscription> findByUserId(Long userId);

    @Query(value = "SELECT new com.igladkikh.subscriptionservice.dto.ServiceStatisticDto(sub.service, COUNT(DISTINCT sub.userId), COUNT(sub.userId)) " +
            "FROM Subscription AS sub " +
            "GROUP BY sub.service " +
            "ORDER BY COUNT(DISTINCT sub.userId) DESC, COUNT(sub.userId) DESC " +
            "LIMIT :limit")
    List<ServiceStatisticDto> findTopServices(int limit);

    Optional<Subscription> findByUserIdAndServiceAndExpiryDateIsAfter(Long userId, ServiceName service, LocalDate date);
}