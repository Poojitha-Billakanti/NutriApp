package com.example.fetch.api.service;




import com.example.fetch.api.bo.USAFoodData;
import com.example.fetch.api.entity.FoodEntity;
import com.example.fetch.api.entity.NutrientInfo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class FoodService {
    @Autowired
    USAFoodData foodAccess;
    public ResponseEntity<List<FoodEntity>> getFoodBySearch(@PathVariable String query){
        ResponseEntity<String> collectedList = null;

        try {
            collectedList = foodAccess.getSearchedFood(query) ;
            String responseBody = collectedList.getBody();
            if (responseBody == null || responseBody.isEmpty()) {
                //throw new Exception("Empty response received");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
            }


            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(responseBody);

            JsonNode foodsNode = jsonNode.path("foods");
            if (foodsNode == null || foodsNode.isNull() || !foodsNode.isArray() ) {
                // Throw an exception indicating that food data is not found
                //throw new Exception("Food data not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
            }
            List<FoodEntity> foodEntities = new ArrayList<>();
            for (JsonNode foodNode : foodsNode) {
                String fdcId = foodNode.get("fdcId").asText();
                String description = foodNode.get("description").asText();
                String dataType = foodNode.get("dataType").asText();
                LocalDate publishedDate = LocalDate.parse(foodNode.get("publishedDate").asText());
                String foodCategory = foodNode.get("foodCategory").asText();
                List<NutrientInfo> nutrientInfoList = new ArrayList<>();
                JsonNode nutrientJson = foodNode.path("foodNutrients");
                int count=0;
                for (JsonNode nutrientNode : nutrientJson) {
                    count++;
                    int nutrientId = nutrientNode.get("nutrientId").asInt();
                    String nutrientName = nutrientNode.get("nutrientName").asText();

                    NutrientInfo nutrientInfo = new NutrientInfo(nutrientId, nutrientName);
                    nutrientInfoList.add(nutrientInfo);
                    if(count==2)
                        break;
                }

                FoodEntity foodEntity = new FoodEntity(fdcId, description, dataType, publishedDate, foodCategory,nutrientInfoList);
                foodEntities.add(foodEntity);

            }


            return ResponseEntity.ok(foodEntities);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }



}
