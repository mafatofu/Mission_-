package com.example.springboot_mission1.repo;

import com.example.springboot_mission1.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByBoard(Long board);
    List<Article> findAllByBoardOrderByIdDesc(Long board);
    //패스워드확인
    //Article findByIdAndPassword(Long id, String password);
}
