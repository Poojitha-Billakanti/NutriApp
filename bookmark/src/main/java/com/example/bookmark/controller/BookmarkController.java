package com.example.bookmark.controller;

import com.example.bookmark.BookmarkService;
import com.example.bookmark.entity.Bookmark;
import com.example.bookmark.entity.Bookmark1;
import com.example.bookmark.interfaces.BookmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class BookmarkController {

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Autowired
    private BookmarkService bookmarkService;

    // Endpoint to create a new bookmark
    @PostMapping("/add")
    public Bookmark createBookmark(@RequestBody Bookmark bookmark) {


        return bookmarkRepository.save(bookmark);
    }

    // Endpoint to delete a bookmark by userId and fdcId
    @DeleteMapping("/{userId}/{fdcId}")
    public ResponseEntity<String> deleteBookmarkByUserIdAndFdcId(@PathVariable String userId, @PathVariable String fdcId) {
        bookmarkService.deleteBookmarkByUserIdAndFdcId(userId, fdcId);
        return ResponseEntity.ok("Bookmark deleted successfully");
    }

    // Endpoint to get bookmarks by userId
    @GetMapping("/bookmarks/{userid}")
    public ResponseEntity<List<Bookmark>> getAllBookmarks(@PathVariable String userid) {
        List<Bookmark> bookmarks = bookmarkService.getAllBookmarks(userid);
        return ResponseEntity.ok(bookmarks);
    }
    // Endpoint to get a bookmark by userId and fdcId
    @GetMapping("/{userId}/{fdcId}")
    public ResponseEntity<Void> doesBookmarkExist(@PathVariable String userId, @PathVariable String fdcId) {
        Bookmark1 bookmarkId = new Bookmark1(userId, fdcId);
        if (bookmarkRepository.existsById(bookmarkId)) {
            return ResponseEntity.ok().build(); // Return 200 OK if bookmark exists
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // Return 400 Bad Request if bookmark doesn't exist
        }
    }

}
