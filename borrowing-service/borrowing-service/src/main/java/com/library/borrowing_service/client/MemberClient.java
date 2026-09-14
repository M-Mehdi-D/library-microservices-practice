package com.library.borrowing_service.client;

import com.library.borrowing_service.dto.MemberDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "member-service") // looks this up in Eureka
public interface MemberClient {
    @GetMapping("/members/{id}")
    MemberDto getMember(@PathVariable("id") int id);
}
