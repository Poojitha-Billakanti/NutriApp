package com.example.fetch.api.controller;

import com.example.fetch.api.service.FoodService;
import com.example.fetch.api.bo.USAFoodData;
import com.example.fetch.api.entity.FoodEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("foodcontroller")
public class FoodController {
    @Autowired
    USAFoodData foodAccess;
    @Autowired
    FoodService foodService;

    private static final Logger LOGGER = LoggerFactory.getLogger(FoodController.class);

    @GetMapping("/allfood")
    public ResponseEntity<String> getAllFoodList(){

        ResponseEntity<String> collectedList = null;
        try{
            String url = "https://api.nal.usda.gov/fdc/v1/foods/list?api_Key=z6JPjHU5NbjgoImGPbSfrchg8qiO6tMCprqie8a8";
            collectedList = foodAccess.getAllFood(url);
        }catch (Exception ex){

        }
        return collectedList;
    }
    @GetMapping("/allfood/{query}")
    public ResponseEntity<List<FoodEntity>> getFoodBySearch(@PathVariable String query) {
        return foodService.getFoodBySearch(query);

    }

}



