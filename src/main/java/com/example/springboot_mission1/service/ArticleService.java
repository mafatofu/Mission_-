package com.example.springboot_mission1.service;

import com.example.springboot_mission1.entity.Article;
import com.example.springboot_mission1.repo.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository repository;

    //게시글 전부가져오기
    public List<Article> showArticlesAll(){
        return repository.findAll();
    }

    //내림차순 게시글 전부가져오기
    public List<Article> showArticlesAllDesc(){
        return repository.findAll(Sort.by(Sort.Direction.DESC,"id"));

    }

    //게시판에 따라 게시글 가져오기
    public List<Article> showArticles(Long board){
        return repository.findByBoard(board);
    }

    //내림차순 게시판에 따라 게시글 가져오기
    public List<Article> showArticlesDesc(Long board){
        return repository.findAllByBoardOrderByIdDesc(board);
    }


    //게시글 하나 가져오기
    public Article showArticle(Long articleId){
        return repository.findById(articleId).orElse(null);
    }
    //패스워드확인
    //public Article showArticleForDelete()

    //게시글작성
    public void createArticle(Long board, String title, String contents, String password){
        Article article = new Article();
        article.setBoard(board);
        article.setTitle(title);
        article.setContents(contents);
        article.setPassword(password);

        repository.save(article);
    }
    //게시글삭제
    public void deleteArticle(Long articleId){
        repository.deleteById(articleId);
    }
    //게시글수정

    public void updateArticle(Long articleId, Long board, String title, String contents, String newPwd){
        Article article = showArticle(articleId);
        article.setBoard(board);
        article.setTitle(title);
        article.setContents(contents);
        article.setPassword(newPwd);
        repository.save(article);
    }

}
