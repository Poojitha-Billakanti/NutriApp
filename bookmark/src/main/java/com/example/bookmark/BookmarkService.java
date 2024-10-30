package com.example.bookmark;

import com.example.bookmark.entity.Bookmark;
import com.example.bookmark.interfaces.BookmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookmarkService {

    @Autowired
    private BookmarkRepository bookmarkRepository;
    public void deleteBookmarkByUserIdAndFdcId(String userId, String fdcId) {

        Bookmark bookmark = bookmarkRepository.findByUserIdAndFdcId(userId, fdcId);
        if (bookmark != null) {
            bookmarkRepository.delete(bookmark);
        }
    }
    public String findByUserIdAndFdcId(String userId,String fdcId){
        Bookmark bookmark = bookmarkRepository.findByUserIdAndFdcId(userId, fdcId);
        if (bookmark != null) {
            return "Bookmark Found";
        }
        return "Bookmark Not Found";

    }

    public List<Bookmark> getAllBookmarks(String query) {

        List<Bookmark> result= bookmarkRepository.findByUserId(query);
        return result;
    }
}
