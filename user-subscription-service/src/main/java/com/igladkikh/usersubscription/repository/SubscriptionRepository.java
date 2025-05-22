package com.igladkikh.usersubscription.repository;

import com.igladkikh.usersubscription.dto.ServiceStatisticDto;
import com.igladkikh.usersubscription.model.ServiceName;
import com.igladkikh.usersubscription.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    List<Subscription> findByUserId(Long userId);

    @Query(value = "SELECT new com.igladkikh.usersubscription.dto.ServiceStatisticDto(sub.service, COUNT(DISTINCT sub.user), COUNT(sub.user)) " +
            "FROM Subscription AS sub " +
            "GROUP BY sub.service " +
            "ORDER BY COUNT(DISTINCT sub.user) DESC, COUNT(sub.user) DESC " +
            "LIMIT :limit")
    List<ServiceStatisticDto> findTopServices(int limit);

    Optional<Subscription> findByUserIdAndServiceAndExpiryDateIsAfter(Long userId, ServiceName service, LocalDate date);
}