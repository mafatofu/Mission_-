package com.example.springboot_mission1.service;

import com.example.springboot_mission1.entity.Article;
import com.example.springboot_mission1.entity.Comment;
import com.example.springboot_mission1.repo.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository repository;

    public List<Comment> showComments(Long articleId){
        List<Comment> optionalCommentList = repository.findAllByArticleId(articleId);
        //List<Comment> commentList = optionalCommentList.orElse(null);
        //return optionalCommentList.orElse(null);
        return optionalCommentList.stream().toList();
    }

    public Comment showComment(Long commentId){
        return repository.findById(commentId).orElse(null);
    }

    //댓글추가
    public void createComment(Article article, String contents, String createCommentPwd){
        Comment comment = new Comment();
        comment.setArticle(article);
        comment.setContents(contents);
        comment.setPassword(createCommentPwd);

        repository.save(comment);
    }
    //댓글삭제
    public void deleteComments(Long articleId){
        repository.deleteById(articleId);
    }
}
