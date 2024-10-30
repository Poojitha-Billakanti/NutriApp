package com.example.fetch.api.entity;

import lombok.*;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FoodEntity {

    private String fdcId;

    private String description;

    private String dataType;

    private LocalDate publishedDate;

    private String foodCategory;

    private List<NutrientInfo> nutrientInfoList;

}


