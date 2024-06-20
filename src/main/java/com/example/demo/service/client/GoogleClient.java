package com.example.demo.service.client;

import com.example.demo.model.clientresponse.auth.ClientGoogleTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "GOOGLE", url = "${feign.base-path.google}")
public interface GoogleClient {

    @GetMapping
    ClientGoogleTokenResponse getInfo(@RequestParam("id_token") String token);

}
