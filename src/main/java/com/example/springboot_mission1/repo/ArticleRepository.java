package com.example.springboot_mission1.repo;

import com.example.springboot_mission1.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByBoard(Long board);
    List<Article> findAllByBoardOrderByIdDesc(Long board);

    //검색쿼리
    //전체게시판 제목으로 조회
    List<Article> findAllByTitleContainingOrderByIdDesc(String title);
    //전체게시판 내용으로 조회
    List<Article> findAllByContentsContainingOrderByIdDesc(String contents);
    //개별게시판 제목으로 조회
    List<Article> findByBoardAndTitleContainingOrderByIdDesc(Long board, String title);
    //개별게시판 내용으로 조회
    List<Article> findByBoardAndContentsContainingOrderByIdDesc(Long board, String contents);
}
