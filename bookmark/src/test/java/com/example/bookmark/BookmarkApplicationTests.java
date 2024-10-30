package com.example.bookmark;

import com.example.bookmark.controller.BookmarkController;
import com.example.bookmark.entity.Bookmark;
import com.example.bookmark.entity.Bookmark1;
import com.example.bookmark.interfaces.BookmarkRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookmarkApplicationTests {
	@Autowired
	BookmarkController bookmarksController;
	@Autowired
	private BookmarkRepository bookmarkRepository;

	@Autowired
	private BookmarkService bookmarkService;
	@Test
	public void allBookmarksTest(){
		try {
			ResponseEntity<List<Bookmark>> listResponseEntity= (ResponseEntity<List<Bookmark>>) bookmarksController.getAllBookmarks("1");
			assertEquals(HttpStatus.OK, listResponseEntity.getStatusCode());
		}catch (Exception ex){
			fail();
		}
	}
	@Test
	public void addBookmarkTest(){
		try {
			Bookmark bookmark = new Bookmark();
			bookmark.setUserId("6");
			bookmark.setFdcId("123590");
			bookmarksController.createBookmark(bookmark);
			String result = bookmarkService.findByUserIdAndFdcId("6", "123590");
			assertEquals(result,"Bookmark Found");
		}catch(Exception ex){
			fail();
		}
	}

	@Test
	public void TestCase2(){
		try {
			Bookmark bookmark = new Bookmark();
			bookmark.setUserId("9");
			bookmark.setFdcId("129");
			bookmarkService.deleteBookmarkByUserIdAndFdcId("9", "129");
			String result = bookmarkService.findByUserIdAndFdcId("9", "129");
			assertEquals(result,"Bookmark Not Found");
		}catch(Exception ex){
			fail();
		}
	}


	@Test
	void contextLoads() {
	}
	@Test
	public void DeleteByUserIdAndFdcIDTest(){
		try {
			ResponseEntity<String> listResponseEntity=bookmarksController.deleteBookmarkByUserIdAndFdcId("manisree","123");
			assertEquals(HttpStatus.OK, listResponseEntity.getStatusCode());
		}catch (Exception ex){
			fail();
		}
	}
	@Test
	public void  ExistsByIDTest(){
		Bookmark1 bookmarkId = new Bookmark1("1","123");
		boolean result=bookmarkRepository.existsById(bookmarkId);
		assertNotEquals(HttpStatus.OK, result);
		Bookmark1 bookmarkId1 = new Bookmark1(null,"123");
		boolean result1=bookmarkRepository.existsById(bookmarkId);
		assertNotEquals(HttpStatus.BAD_REQUEST, result1);


	}
	@Test
	public void testConstructorAndGetters() {
		// Create a Bookmark1 object using the constructor
		Bookmark1 bookmark = new Bookmark1("testUserId", "testFdcId");

		// Verify that the fields are initialized correctly
		assertEquals("testUserId", bookmark.getUserId());
		assertEquals("testFdcId", bookmark.getFdcId());
	}
	@Test
	public void testSetters() {
		// Create a Bookmark1 object
		Bookmark1 bookmark = new Bookmark1("initialUserId", "initialFdcId");

		// Change the values using setters
		bookmark.setUserId("newUserId");
		bookmark.setFdcId("newFdcId");

		// Verify that the setters work correctly
		assertEquals("newUserId", bookmark.getUserId());
		assertEquals("newFdcId", bookmark.getFdcId());
	}


	@Test
	public void testSetters1() {
		// Create a Bookmark object
		Bookmark bookmark = new Bookmark("initialUserId", "initialFdcId");

		// Set values using setters
		bookmark.setDescription("Test description");
		bookmark.setPublishedDate(LocalDate.of(2022, 3, 16));

		// Verify that setters work correctly
		assertEquals("Test description", bookmark.getDescription());
		assertEquals(LocalDate.of(2022, 3, 16), bookmark.getPublishedDate());
	}


}




