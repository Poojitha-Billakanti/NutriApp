package com.example.controller;

import com.example.entity.AuthRequest;
import com.example.entity.AuthResponse;
import com.example.entity.Bookmark;
import com.example.entity.UserInfo;

import com.example.interfaces.ReadBookmark;
import com.example.interfaces.ReadUser;
import com.example.service.JwtService;
import com.example.service.UserInfoService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class UserController {

    @Autowired
    ReadBookmark readBookmark;

    @Autowired
    ReadUser readUser;

    @Autowired
    private UserInfoService service;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping("/addNewUser")
    public String addNewUser(@RequestBody UserInfo userInfo) {
        return service.addUser(userInfo);
    }

    @GetMapping("/user/userProfile")
    @PreAuthorize("hasAuthority('user')")
    public String userProfile() {
        return "Welcome to User Profile";
    }

    @GetMapping("/admin/adminProfile")
    @PreAuthorize("hasAuthority('admin')")
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }


    @PostMapping("/generateToken")
    public AuthResponse authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(authRequest.getUsername());
            return new AuthResponse(token, authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }

    @GetMapping("/user/getAllFood/{query}")
    @PreAuthorize("hasAuthority('user')")
    public ResponseEntity<JsonNode> callAnotherApi(@PathVariable String query) {

        return readUser.getFood(query);
    }

    @PostMapping("/user/add")
    @PreAuthorize("hasAuthority('user')")
    public Bookmark createBookmark(@RequestBody Bookmark bookmark) {

        return readBookmark.createBookmark(bookmark);
    }

    @GetMapping("/user/{userId}/{fdcId}")
    @PreAuthorize("hasAuthority('user')")
    public ResponseEntity<Void> doesBookmarkExist(@PathVariable String userId, @PathVariable String fdcId) {
        return readBookmark.doesBookmarkExist(userId, fdcId);


    }
    @DeleteMapping("/user/{userId}/{fdcId}")
    @PreAuthorize("hasAuthority('user')")
    public ResponseEntity<String> deleteBookmarkByUserIdAndFdcId(@PathVariable String userId, @PathVariable String fdcId) {
        return readBookmark.deleteBookmarkByUserIdAndFdcId(userId, fdcId);


    }

    @GetMapping("/user/bookmarks/{userId}")
    public ResponseEntity<List<Bookmark>> getUserBookmarks1(@PathVariable String userId) {
        return readBookmark.getUserBookmarks1(userId);

    }
}