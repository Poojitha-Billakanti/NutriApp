
package com.example.fetch.api.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@ComponentScan
@Service

public class USAFoodData {
    private static final String BASE_URL = "https://api.nal.usda.gov/fdc/v1/foods/search";
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    public USAFoodData(RestTemplate restTemplate)
    {
        this.restTemplate=restTemplate;
    }
    @Value("${api.key}")
    private String apiKey;
    public ResponseEntity<String> getSearchedFood(String query) throws Exception{

        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam("api_key", apiKey)
                .queryParam("query", query)
                .toUriString();

        // Make the API call
        return restTemplate.getForEntity(url, String.class);
    }
        public ResponseEntity<String> getAllFood(String url) throws Exception {

        ResponseEntity<String> collectedList =
                restTemplate.getForEntity(url,String.class);

        return collectedList;

    }

}
