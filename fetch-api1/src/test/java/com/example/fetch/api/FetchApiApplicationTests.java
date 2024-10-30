package com.example.fetch.api;

import com.example.fetch.api.bo.USAFoodData;
import com.example.fetch.api.controller.FoodController;
import com.example.fetch.api.entity.FoodEntity;
import com.example.fetch.api.entity.NutrientInfo;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class FetchApiApplicationTests {
	@Autowired
	USAFoodData foodBo;
	@Autowired
	FoodController foodController;
	@Test
	public void getFoodTest() {
		ResponseEntity<String> result=foodController.getAllFoodList();
		Assertions.assertEquals(result.getStatusCode().toString(),"200 OK");
	}

	@Test
	public void getAllFoodTest()
	{
		ResponseEntity<List<FoodEntity>> result=foodController.getFoodBySearch("apple");
		Assertions.assertEquals(result.getStatusCode().toString(),"200 OK");
	}
	@Test
	public void foodEntityTest()
	{
		List<NutrientInfo> nutritionEntityList= new ArrayList<>();
		nutritionEntityList.add(new NutrientInfo(1087,"Calcium, Ca"));
		nutritionEntityList.add(new NutrientInfo(1004,"Total lipid (fat)"));
		FoodEntity foodEntity=new FoodEntity();
		FoodEntity foodEntity1=new FoodEntity("454004","APPLE","Branded", LocalDate.parse("2019-04-01"),"Pre-Packaged Fruit & Vegetables",nutritionEntityList);
		foodEntity.setFdcId("454004");
		Assertions.assertEquals("454004",foodEntity.getFdcId());
		foodEntity.setDescription("APPLE");
		Assertions.assertEquals("APPLE",foodEntity.getDescription());
		foodEntity.setDataType("Branded");
		Assertions.assertEquals("Branded",foodEntity.getDataType());
		foodEntity.setPublishedDate(LocalDate.parse("2019-04-01"));
		Assertions.assertEquals("2019-04-01", foodEntity.getPublishedDate().toString());
		foodEntity.setFoodCategory("Pre-Packaged Fruit & Vegetables");
		Assertions.assertEquals("Pre-Packaged Fruit & Vegetables",foodEntity.getFoodCategory());
		foodEntity.setNutrientInfoList(nutritionEntityList);
		Assertions.assertEquals(nutritionEntityList,foodEntity.getNutrientInfoList());
		Assertions.assertEquals("454004",foodEntity1.getFdcId());
		Assertions.assertEquals("APPLE",foodEntity1.getDescription());
		Assertions.assertEquals("Branded",foodEntity1.getDataType());
		Assertions.assertEquals("2019-04-01", foodEntity1.getPublishedDate().toString());
		Assertions.assertEquals("Pre-Packaged Fruit & Vegetables",foodEntity1.getFoodCategory());
		Assertions.assertEquals(nutritionEntityList,foodEntity.getNutrientInfoList());
	}
	@Test
	public void NutritionEntity()
	{
		NutrientInfo nutritionEntity=new NutrientInfo();
		NutrientInfo nutritionEntity1=new NutrientInfo(1003,"Protein");
		nutritionEntity.setNutrientId(1004);
		nutritionEntity.setNutrientName("Total lipid (fat)");
		Assertions.assertEquals(nutritionEntity.getNutrientId(),1004);
		Assertions.assertEquals(nutritionEntity.getNutrientName(),"Total lipid (fat)");
		Assertions.assertEquals(nutritionEntity1.getNutrientId(),1003);
		Assertions.assertEquals(nutritionEntity1.getNutrientName(),"Protein");

	}
	@Test
	public void getAllFoodBoTest() throws Exception {
		String url="https://api.nal.usda.gov/fdc/v1/foods/search?api_key=z6JPjHU5NbjgoImGPbSfrchg8qiO6tMCprqie8a8";
		RestTemplate restTemplate=new RestTemplate();
		USAFoodData foodBo=new USAFoodData(restTemplate);
		ResponseEntity<String> result = foodBo.getAllFood(url);
		Assertions.assertEquals(result.getStatusCode().toString(), "200 OK");
		ResponseEntity<String> result1 = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
		Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result1.getStatusCode());
	}
	@Test
	public void getSearchedFoodBoTest() throws Exception {
		ResponseEntity<String> result=foodBo.getSearchedFood("cheese");
		Assertions.assertEquals(result.getStatusCode().toString(),"200 OK");
	}
	@Test
	public void testSetterAndGetters()
	{
		FoodEntity foodEntity=new FoodEntity();
		String fdcId="12345";
		String description="Test Description";
		String dataType="Test Type";
		LocalDate publishedDate=LocalDate.now();
		String foodCategory="Test Category";
		List <NutrientInfo> nutrientInfoList=new ArrayList<>();
		foodEntity.setFdcId(fdcId);
		foodEntity.setDescription(description);
		foodEntity.setFoodCategory(foodCategory);
		foodEntity.setPublishedDate(publishedDate);
		foodEntity.setDataType(dataType);
		foodEntity.setNutrientInfoList(nutrientInfoList);

		assertEquals(publishedDate,foodEntity.getPublishedDate());
		assertEquals(nutrientInfoList,foodEntity.getNutrientInfoList());
		assertEquals(fdcId,foodEntity.getFdcId());
		assertEquals(description,foodEntity.getDescription());
		assertEquals(dataType,foodEntity.getDataType());
		assertEquals(foodCategory,foodEntity.getFoodCategory());
	}
	@Test
	public void testNutrientEntity()
	{
		NutrientInfo nutrientInfo=new NutrientInfo(1,"carbohydrates");
		assertEquals(1,nutrientInfo.getNutrientId());
		assertEquals("carbohydrates",nutrientInfo.getNutrientName());
	}
	@Test
	public void testGettersAndSetters() {
		NutrientInfo nutrientInfo = new NutrientInfo();
		nutrientInfo.setNutrientId(2);
		nutrientInfo.setNutrientName("Another Nutrient");
		assertEquals(2, nutrientInfo.getNutrientId());
		assertEquals("Another Nutrient", nutrientInfo.getNutrientName());
	}

	@Test
	public void testEqualsAndHashCode() {
		NutrientInfo nutrientInfo1 = new NutrientInfo(1, "Test Nutrient");
		NutrientInfo nutrientInfo2 = new NutrientInfo(1, "Test Nutrient");
		NutrientInfo nutrientInfo3 = new NutrientInfo(2, "Another Nutrient");

		assertEquals(nutrientInfo1, nutrientInfo2);
		assertNotEquals(nutrientInfo1, nutrientInfo3);

		assertEquals(nutrientInfo1.hashCode(), nutrientInfo2.hashCode());
		assertNotEquals(nutrientInfo1.hashCode(), nutrientInfo3.hashCode());
	}

	@Test
	public void testToString() {
		NutrientInfo nutrientInfo = new NutrientInfo(1, "Test Nutrient");
		String expectedString = "NutrientInfo(nutrientId=1, nutrientName=Test Nutrient)";
		assertEquals(expectedString, nutrientInfo.toString());
	}



}
