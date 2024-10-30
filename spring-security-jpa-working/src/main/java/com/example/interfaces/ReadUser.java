package com.example.interfaces;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "http://cpsproject/foodcontroller")
public interface ReadUser {

    @GetMapping("/allfood/{query}")
    public ResponseEntity<JsonNode> getFood(@PathVariable String query);


}