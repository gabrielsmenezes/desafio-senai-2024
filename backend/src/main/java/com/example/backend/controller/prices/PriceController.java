package com.example.backend.controller.prices;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/prices")
public class PriceController {

    @GetMapping()
    public ResponseEntity<String> coin(@RequestParam String coin){
        RestTemplate rt = new RestTemplate();
        return rt.getForEntity("https://economia.awesomeapi.com.br/last/"+coin, String.class);
    }
}
