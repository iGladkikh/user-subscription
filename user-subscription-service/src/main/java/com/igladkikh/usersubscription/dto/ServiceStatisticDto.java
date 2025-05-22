package com.igladkikh.usersubscription.dto;

import com.igladkikh.usersubscription.model.ServiceName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ServiceStatisticDto implements Serializable {
    private ServiceName service;
    private Long uniqueUserCount;
    private Long paidCount;
}
