package com.example.interfaces;

import com.example.entity.Bookmark;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name="http://bookmark/api")
public interface ReadBookmark {
    @PostMapping("/add")
    Bookmark createBookmark(Bookmark bookmark);
    @GetMapping("/bookmarks/{userId}")
    public ResponseEntity<List<Bookmark>> getUserBookmarks1(@PathVariable String userId);
    @GetMapping("/{userId}/{fdcId}")
    public ResponseEntity<Void> doesBookmarkExist(@PathVariable String userId, @PathVariable String fdcId) ;

    @DeleteMapping("/{userId}/{fdcId}")
    public ResponseEntity<String> deleteBookmarkByUserIdAndFdcId(@PathVariable String userId, @PathVariable String fdcId);
}