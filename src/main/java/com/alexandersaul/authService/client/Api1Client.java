package com.alexandersaul.authService.client;

import com.alexandersaul.authService.dto.Api1RequestDTO;
import com.alexandersaul.authService.dto.Api1ResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "api1Client", url = "https://api-analitica-wgyy.onrender.com")
public interface Api1Client {

    @PostMapping("/predict")
    Api1ResponseDTO predict(@RequestBody Api1RequestDTO request);
}
