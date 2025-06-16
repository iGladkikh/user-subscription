package com.igladkikh.subscriptionservice.client;

import com.igladkikh.subscriptionservice.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@FeignClient("user-service")
public interface UserFeignClient {
    @RequestMapping(method = RequestMethod.GET, value = "/users/{userId}", consumes = "application/json")
    UserDto findById(@PathVariable Long userId);
}
