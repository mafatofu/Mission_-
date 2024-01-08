package com.example.springboot_mission1.service;

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
    //댓글추가
    //댓글삭제
}
