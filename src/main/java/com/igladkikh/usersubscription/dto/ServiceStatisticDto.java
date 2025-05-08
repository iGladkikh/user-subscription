package com.igladkikh.usersubscription.dto;

import com.igladkikh.usersubscription.model.ServiceName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ServiceStatisticDto {
    private ServiceName service;
    private Long uniqueUserCount;
    private Long paidCount;
}
