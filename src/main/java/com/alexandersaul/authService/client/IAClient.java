package com.alexandersaul.authService.client;

import com.alexandersaul.authService.dto.ApiAleResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "apiAleClient", url = "https://api-ale.onrender.com")
interface IAClient {

    @PostMapping("/predict")
    ApiAleResponseDTO predict(@RequestBody ApiAleRequestDTO request);

}
