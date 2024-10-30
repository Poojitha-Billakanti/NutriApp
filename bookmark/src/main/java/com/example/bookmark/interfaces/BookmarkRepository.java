package com.example.bookmark.interfaces;

import com.example.bookmark.entity.Bookmark;
import com.example.bookmark.entity.Bookmark1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Bookmark1> {
    Bookmark findByUserIdAndFdcId(String userId, String fdcId);

    List<Bookmark> findByUserId(String userId);

    void deleteByUserIdAndFdcId(String userId, String fdcId);
}

