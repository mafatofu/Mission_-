package com.example.springboot_mission1.repo;

import com.example.springboot_mission1.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    //List<Comment> findAllByArticle(Long articleId);
    //List<Comment> findByArticle_id(Long articleId);
    List<Comment> findAllByArticleId(Long articleId);

}
