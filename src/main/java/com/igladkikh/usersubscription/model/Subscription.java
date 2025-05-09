package com.igladkikh.usersubscription.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PreRemove;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.format.annotation.DateTimeFormat;
import com.igladkikh.usersubscription.util.DateUtil;

import java.time.LocalDate;
import java.util.Objects;

@Slf4j
@Getter
@Setter
@ToString
@Entity
@Table(name = "subscriptions")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "service_name", nullable = false)
    private ServiceName service;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "expiry_date", nullable = false)
    @DateTimeFormat(pattern = DateUtil.DEFAULT_DATE_FORMAT)
    private LocalDate expiryDate;

    @PrePersist
    public void logNewSubscriptionAttempt() {
        log.info("Attempting to add new subscription: {}", this);
    }

    @PostPersist
    public void logNewSubscriptionAdded() {
        log.info("Added subscription: {}", this);
    }

    @PreRemove
    public void logSubscriptionRemovalAttempt() {
        log.info("Attempting to delete subscription: {}", this);
    }

    @PostRemove
    public void logSubscriptionRemoval() {
        log.info("Deleted subscription: {}", this);
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Subscription that = (Subscription) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
